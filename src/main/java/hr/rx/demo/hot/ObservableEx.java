package hr.rx.demo.hot;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Observable
 * + 데이터소스
 * + 데이터의 흐름에 맞게 알림을 보내, subscribe 가 데이터를 처리할 수 있도록 함.
 */
public class ObservableEx {
    public static void main(String[] args) {
        ObservableEx observableEx = new ObservableEx();

        Observable<Integer> source = observableEx.fromPublisherObservable();

        source.subscribe(System.out::println);
        /*
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Smile!";
        };
        Observable<String> sourceStr = observableEx.fromCallableObservable(callable);

        sourceStr.subscribe(System.out::println);

        Observable<String> futureSource = observableEx.fromFutureObservable(callable);

        futureSource.subscribe(System.out::println);
         */
    }

    /**
     * Observable subscribe function
     */
    public void subscribeObservable() {
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
    public Observable<Integer> createObservable() {
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
    public Observable<Integer> fromArrayObservable() {
        return Observable.fromArray(new Integer[]{100, 200});
    }

    /**
     * Observable callable
     * - 비동기 실행 후 반환
     * - 동시성 API
     * @param callable
     * @return
     */
    public Observable<String> fromCallableObservable(Callable<String> callable) {
        return Observable.fromCallable(callable);
    }

    /**
     * Observable future
     * - future의 작업이 끝날 때까지 스레드 블로킹
     * @param callable
     * @return
     */
    public Observable<String> fromFutureObservable(Callable<String> callable) {
        Future<String> future = Executors.newSingleThreadExecutor()
                                         .submit(callable);
        // Observable.fromFuture(future, 1000, TimeUnit.MILLISECONDS);
        return Observable.fromFuture(future);
    }

    public Observable fromPublisherObservable() {
        Publisher<Integer> publisher = (Subscriber<? super Integer> sub) -> {
            sub.onNext(100);
            sub.onNext(300);

            sub.onComplete();
        };
        return Observable.fromPublisher(publisher);
    }
}
