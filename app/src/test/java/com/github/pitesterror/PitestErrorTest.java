package com.github.pitesterror;

import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class PitestErrorTest {

  @Rule public TestSchedulerRule schedulerRule = new TestSchedulerRule();

  PitestError pitestError;

  @Before
  public void setUp() throws Exception {
    pitestError = new PitestError();
  }

  @Test
  public void returnsOne() {
    TestObserver<Integer> testObserver = pitestError.someMethod().test();
    testObserver.assertValue(1);
  }
}