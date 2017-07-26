package br.com.mxel.cuedot.data.remote;

import br.com.mxel.cuedot.data.model.ApiResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by michelribeiro on 26/07/17.
 */

public interface CueDotApi {

    public final static String ORDER_BY_POPULAR = "popular";
    public final static String ORDER_BY_TOP_RATED = "top_rated";

    @GET("movie/{order_by}")
    Observable<ApiResult> getMoviesOrderBy(@Path("order_by") String orderBy);
}
