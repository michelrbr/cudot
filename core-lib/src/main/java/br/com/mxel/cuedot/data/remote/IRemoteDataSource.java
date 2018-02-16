package br.com.mxel.cuedot.data.remote;

import br.com.mxel.cuedot.data.remote.model.ListMovieResult;
import br.com.mxel.cuedot.data.remote.model.ListVideoResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by michelribeiro on 26/07/17.
 */

public interface IRemoteDataSource {

    @GET("movie/{order_by}")
    Single<ListMovieResult> getMoviesOrderBy(@Path("order_by") String orderBy,
                                             @Query("page") int page);

    // https://developers.themoviedb.org/3/getting-started/append-to-response
    // Append videos and images to response (But it comes on ListVideoResult)
    // &append_to_response=videos,images
    @GET("movie/{movie_id}")
    Single<Movie> getMovie(@Path("movie_id") long movieId);

    @GET("movie/{movie_id}/similar")
    Single<ListMovieResult> getSimilarMovies(@Path("movie_id") long movieId);

    @GET("movie/{movie_id}/videos")
    Single<ListVideoResult> getMovieVideos(@Path("movie_id") long movieId);

    // https://developers.themoviedb.org/3/search/search-movies
    // https://developers.themoviedb.org/3/search/search-tv-shows
    /*
    @GET("search/{type}")
    Single<ListMovieResult> search(@Path("type") String type,
                                   @Query(value = "query", encoded = true) String query);*/
}
