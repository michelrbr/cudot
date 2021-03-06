package br.com.mxel.cuedot.util;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by michelribeiro on 07/08/17.
 */

public class AppSchedulerProvider implements ISchedulerProvider {

    @Inject
    public AppSchedulerProvider() {
        Timber.d("Schedulers provider initialized");
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }

}
