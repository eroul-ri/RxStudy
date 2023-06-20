package hr.rx.demo.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ObservableEx {
    public static void main(String[] args) {
        Observable<Integer> source = fromArrayObservable();

        source.subscribe(System.out::println);
    }

    /**
     * Observable subscribe function
     */
    public static void subscribeObservable() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);

        Disposable disposable = source.subscribe(
                v -> System.out.println("on Next"),
                err -> System.out.println("on Error"),
                () -> System.out.println("on Complete")
        );
        System.out.println(disposable.isDisposed());
    }

    /**
     * Observable create function
     * 사용 후 구독을 반드시 해지(Dispose), 해지하지 않는 경우 memory leak 발생
     */
    public static Observable createObservable() {
        //
        return Observable.create(
                (emitter)-> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onNext(4);
                    emitter.onNext(5);

                    emitter.onComplete();
                }
        );

    }

    /**
     * Observable fromArray function
     */
    public static Observable fromArrayObservable() {
        return Observable.fromArray(new Integer[]{100, 200});
    }
}
