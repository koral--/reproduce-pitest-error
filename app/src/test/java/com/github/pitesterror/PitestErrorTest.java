package com.github.pitesterror;

import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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