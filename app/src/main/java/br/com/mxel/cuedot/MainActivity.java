package br.com.mxel.cuedot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.remote.IRemoteDataSource;
import br.com.mxel.cuedot.util.CueDotConstants;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String API_QUERY = "api_key";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter(API_QUERY, BuildConfig.THE_MOVIE_DB_API_KEY)
                    .build();

            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl)
                    .build();

            return chain.proceed(newRequest);
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        String baseUrl = String.format(
                "%s/%s/",
                BuildConfig.THE_MOVIE_DB_API_URL,
                BuildConfig.THE_MOVIE_DB_API_VERSION
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        IRemoteDataSource api = retrofit.create(IRemoteDataSource.class);

        api.getMoviesOrderBy(
                CueDotConstants.ORDER_BY_POPULAR).subscribe(
                result -> {

                    api.getMovie(result.results.get(0).id).subscribe(movie -> {
                        System.out.println("Frist movie name: " + movie.title);
                    });

                    for (Movie m : result.results) {
                        System.out.println(m.title);
                    }
                },
                throwable -> {
                    System.out.println(throwable.toString());
                }
        );
    }
}
