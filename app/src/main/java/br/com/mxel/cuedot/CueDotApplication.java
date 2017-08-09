package br.com.mxel.cuedot;

import android.app.Application;
import android.os.StrictMode;

import br.com.mxel.cuedot.data.DaggerDataComponent;
import br.com.mxel.cuedot.data.DataComponent;

/**
 * Created by michelribeiro on 18/07/17.
 */

public class CueDotApplication extends Application {

    private static DataComponent _dataComponent;

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

        _dataComponent = DaggerDataComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    // GETTER / SETTER ============================================================================

    public static DataComponent getDataComponent() {
        return _dataComponent;
    }
}
