package br.com.mxel.cuedot.util;

import io.reactivex.Scheduler;

/**
 * Created by michelribeiro on 07/08/17.
 */

public interface ISchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
