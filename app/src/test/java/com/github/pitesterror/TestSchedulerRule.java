package com.github.pitesterror;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestSchedulerRule implements TestRule {

  private final TestScheduler testScheduler = new TestScheduler();

  public TestScheduler getTestScheduler() {
    return testScheduler;
  }

  @Override
  public Statement apply(final Statement base, Description d) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        RxJavaPlugins.setIoSchedulerHandler(
            new Function<Scheduler, Scheduler>() {
              @Override
              public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
              }
            });
        RxJavaPlugins.setComputationSchedulerHandler(
            new Function<Scheduler, Scheduler>() {
              @Override
              public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
              }
            });
        RxJavaPlugins.setNewThreadSchedulerHandler(
            new Function<Scheduler, Scheduler>() {
              @Override
              public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
              }
            });
        RxAndroidPlugins.setMainThreadSchedulerHandler(
            new Function<Scheduler, Scheduler>() {
              @Override
              public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
              }
            });
        try {
          base.evaluate();
        } finally {
          RxJavaPlugins.reset();
          RxAndroidPlugins.reset();
        }
      }
    };
  }
}



