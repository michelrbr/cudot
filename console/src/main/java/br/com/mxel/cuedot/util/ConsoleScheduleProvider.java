package br.com.mxel.cuedot.util;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by michelribeiro on 10/08/17.
 */

public class ConsoleScheduleProvider implements ISchedulerProvider {
    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.trampoline();
    }
}
