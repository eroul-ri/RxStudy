package hr.rx.demo.hot;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

/**
 * Single
 * - 단건의 데이터만 발행하는 Observable 형태
 * - 발행과 동시에 종료
 */
public class SingleEx {

    public static void main(String[] args) {
        SingleEx singleEx = new SingleEx();

        Single<String> single = singleEx.singleJust();

        single.subscribe(System.out::println);

        singleEx.fromObservable(
                Observable.just("from Observable")
        ).subscribe(System.out::println);

        String [] colors = {"RED", "BLACK", "WHITE"};

        singleEx.fromObservableArray(
                Observable.fromArray(colors)
        ).subscribe(System.out::println);

        singleEx.fromObservableArray(
                Observable.empty()
        ).subscribe(System.out::println);

        singleEx.fromEmptyObservable(
                Observable.empty()
        ).subscribe(System.out::println);

        List<Order> orders = new ArrayList<>();

        orders.add(new Order("1"));
        orders.add(new Order("2"));

        singleEx.takeSingle(
                Observable.fromArray(orders.toArray(new Order[orders.size()]))
        ).subscribe(System.out::println);
    }

    public Single<String> singleJust() {
        return Single.just("Single Single");
    }

    public Single<String> fromObservable(Observable<String> observable) {
        return Single.fromObservable(observable);
    }

    public Single<String> fromObservableArray(Observable<String> observable) {
        return observable.first("default");
    }

    public Single<String> fromEmptyObservable(Observable<String> observable) {
        return observable.single("empty default");
    }

    public Single<Order> takeSingle(Observable<Order> order) {
        return order.take(1)
                    .single(new Order("-9999"));
    }
}

class Order {
    private String orderNo;

    public Order(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}
