package hr.rx.demo.cold;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class BehaviorSubjectEx {
    public static void main(String[] args) {
        BehaviorSubjectEx behaviorSubjectEx = new BehaviorSubjectEx();

        behaviorSubjectEx.createBehaviorSubject();

    }

    public void createBehaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("PINK");

        subject.subscribe((data) -> System.out.println("1st Subscribe =" + data));

        subject.onNext("RED");
        subject.onNext("GREEN");

        subject.subscribe((data) -> System.out.println("2nd Subscribe =" + data));

        subject.onNext("BLUE");

        subject.subscribe((data) -> System.out.println("3rd Subscribe =" + data));

        subject.onComplete();
    }
}
