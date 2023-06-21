package hr.rx.demo.cold;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.AsyncSubject;

public class AsyncSubjectEx {
    public static void main(String[] args) {
        AsyncSubjectEx asyncSubjectEx = new AsyncSubjectEx();

        asyncSubjectEx.createAsyncSubject();
        asyncSubjectEx.subscribeAsyncSubjectFromObservable();
    }

    /**
     * async subject
     * - observable에서 발행한 마지막 데이터만 얻어올 수 있는 클래스
     * - 완료되기전 마지막 데이터에만 관심있음.
     * - 완료되기전까지는 구독자에게 데이터를 전달하지 않다가 완료된 시점의 마지막 데이터만 발행하고 종료됨.
     */
    public void createAsyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("BLUE");
        subject.onNext("RED");

        subject.subscribe((data) -> System.out.println("1st Subscribe =" + data));

        subject.onNext("BLACK");

        subject.onComplete();

        subject.onNext("PINK");

        subject.subscribe((data) -> System.out.println("2nd Subscribe =" + data));
        subject.subscribe((data) -> System.out.println("3rd Subscribe =" + data));

    }

    /**
     * Subject 클래스가 Observable을 상속하고 Observable Interface를 구현하고 있기 때문에
     * Observable의 구독자로도 동작 가능함.
     * - public abstract class Subject<T> extends Observable<T> implements Observer<T>
     */
    public void subscribeAsyncSubjectFromObservable() {
        Float [] temp = new Float[] {30.1f, 35.1f, 32.1f};

        Observable<Float> source = Observable.fromArray(temp);

        AsyncSubject<Float> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(System.out::println);

        source.subscribe(asyncSubject);
    }
}
