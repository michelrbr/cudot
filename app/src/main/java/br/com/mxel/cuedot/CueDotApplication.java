package br.com.mxel.cuedot;

import android.app.Application;
import android.os.StrictMode;


/**
 * Created by michelribeiro on 18/07/17.
 */

public class CueDotApplication extends Application {

    private static AppComponent _appComponent;

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

        _appComponent = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    // GETTER / SETTER ============================================================================

    public static AppComponent getAppComponent() {
        return _appComponent;
    }
}
