package br.com.mxel.cuedot.data.remote;

import br.com.mxel.cuedot.data.model.ApiResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by michelribeiro on 26/07/17.
 */

public interface CueDotApi {

    @GET("{version}/movie/{order_by}")
    Observable<ApiResult> getMoviesOrderBy(
            @Path("version") String version,
            @Path("order_by") String orderBy,
            @Query("api_key") String publicKey);
}
