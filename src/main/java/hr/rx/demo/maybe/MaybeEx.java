package hr.rx.demo.maybe;

import io.reactivex.rxjava3.core.Maybe;

/**
 * Maybe
 * - Single Class 처럼 최대 1개의 데이터만 가질 수 있지만 발행없이 바로 데이터를 발생시킬 수 있음.
 * - 0 or 1
 */
public class MaybeEx {

    public static void main(String[] args) {
        MaybeEx maybeEx = new MaybeEx();

        maybeEx.createMaybe().subscribe(System.out::println);
    }

    /**
     * Maybe create function
     * - onSuccess 를 만나면 바로 종료됨.
     * - 여러개의 데이터를 발행하려고해도 단 하나만 발행함
     * @return
     */
    public Maybe<String> createMaybe() {
        return Maybe.create((maybeEmitter) -> {
            maybeEmitter.onSuccess("First onSuccess");
            maybeEmitter.onSuccess("Second onSuccess");
            maybeEmitter.onComplete();
        });
    }
}
