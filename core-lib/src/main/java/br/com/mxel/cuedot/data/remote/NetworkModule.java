package br.com.mxel.cuedot.data.remote;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by michelribeiro on 26/07/17.
 */

@Module
public class NetworkModule {

    private static final String API_QUERY = "api_key";

    @Provides
    @Singleton
    public HttpUrl provideBaseUrl(@Named("baseUrl") String baseUrl) {

        return HttpUrl.parse(baseUrl);
    }

    @Provides
    @Singleton
    public Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory proviveCallAdapterFactory(ISchedulerProvider scheduler) {
        return RxJava2CallAdapterFactory.createWithScheduler(scheduler.backgroundThread());
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    @Named("insertKeyInterceptor")
    public Interceptor provideOkHttpInterceptor(@Named("apiKey") String apiKey){

        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter(API_QUERY, apiKey)
                    .build();

            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl)
                    .build();

            return chain.proceed(newRequest);
        };
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor loggingInterceptor,
            @Named("insertKeyInterceptor") Interceptor insertKeyInterceptor,
            @Named("isDebug") boolean isDebug) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(insertKeyInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        //show logs if app is in Debug mode
        if (isDebug)
            okHttpClient.addInterceptor(loggingInterceptor);

        return okHttpClient.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(HttpUrl baseUrl,
                                    Converter.Factory converterFactory,
                                    CallAdapter.Factory callAdapterFactory,
                                    OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public IRemoteDataSource provideApiService(Retrofit retrofit) {
        return retrofit.create(IRemoteDataSource.class);
    }
}
