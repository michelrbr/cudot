package br.com.mxel.cuedot.data.remote;

import br.com.mxel.cuedot.data.model.ListResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by michelribeiro on 26/07/17.
 */

public interface IRemoteDataSource {

    @GET("movie/{order_by}")
    Observable<ListResult> getMoviesOrderBy(@Path("order_by") String orderBy);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovie(@Path("movie_id") long movieId);
}
