package br.com.mxel.cuedot.data.remote;

import br.com.mxel.cuedot.data.model.ListMovieResult;
import br.com.mxel.cuedot.data.model.ListVideoResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by michelribeiro on 26/07/17.
 */

public interface IRemoteDataSource {

    @GET("movie/{order_by}")
    Observable<ListMovieResult> getMoviesOrderBy(@Path("order_by") String orderBy,
                                                 @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovie(@Path("movie_id") long movieId);

    @GET("movie/{movie_id}/similar")
    Observable<ListMovieResult> getSimilarMovies(@Path("movie_id") long movieId);

    @GET("movie/{movie_id}/videos")
    Observable<ListVideoResult> getMovieVideos(@Path("movie_id") long movieId);
}
