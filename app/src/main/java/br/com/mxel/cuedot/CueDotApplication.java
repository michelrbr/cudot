package br.com.mxel.cuedot;

import android.app.Application;
import android.os.StrictMode;

import br.com.mxel.cuedot.BuildConfig;

/**
 * Created by michelribeiro on 18/07/17.
 */

public class CueDotApplication extends Application {

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
    }
}
