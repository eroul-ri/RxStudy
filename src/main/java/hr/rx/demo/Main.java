package hr.rx.demo;

import io.reactivex.rxjava3.core.Observable;

public class Main {

	public static void main(String[] args) {
		/**
		 * Observable
		 * just : 인자로 넣은 데이터를 차례대로 발행 Max 10
		 */
		Observable.just("First Rx App").subscribe(System.out::println);
	}

}
