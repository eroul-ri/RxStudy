package hr.rx.demo.observable;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.Callable;

public class CallableEx {
    public static void main(String[] args) {
        Callable<String> callable = () -> "Smile";
        Observable<String> source = Observable.fromCallable(callable);

        source.subscribe(System.out::println);
    }
}
