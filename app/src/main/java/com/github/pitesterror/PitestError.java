package com.github.pitesterror;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PitestError {

  Observable<Integer> someMethod() {
    return Observable.just(1)
        .observeOn(AndroidSchedulers.mainThread());
  }
}
