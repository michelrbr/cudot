package br.com.mxel.cuedot;

import android.app.Application;
import android.os.StrictMode;

import br.com.mxel.cuedot.data.DaggerDataComponent;
import br.com.mxel.cuedot.data.DataComponent;
import br.com.mxel.cuedot.data.remote.NetworkModule;

/**
 * Created by michelribeiro on 18/07/17.
 */

public class CueDotApplication extends Application {

    private DataComponent _dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }

        initDagger();
    }

    private void initDagger(){

        String baseUrl = String.format(
                "%s/%s/",
                BuildConfig.THE_MOVIE_DB_API_URL,
                BuildConfig.THE_MOVIE_DB_API_VERSION
        );

        _dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(baseUrl, BuildConfig.THE_MOVIE_DB_API_KEY))
                .build();
    }

    // GETTER / SETTER ============================================================================

    public DataComponent getDataComponent() {
        return _dataComponent;
    }
}
