package com.example.cm.rxjava_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.create_btn)
    Button create_btn;
    @BindView(R.id.create_txt)
    TextView create_txt;

    @OnClick(R.id.create_btn)
    void onClickCreate() {
        createTest();
    }

    @BindView(R.id.map_btn)
    Button map_btn;
    @BindView(R.id.map_txt)
    TextView map_txt;

    @BindView(R.id.intervalRange_txt)
    TextView intervalRangeText;

    @OnClick(R.id.map_btn)
    void onClickMap() {
        mapTest();
    }

    @OnClick(R.id.intervalRange_btn)
    void onClickInter() {
        intervalRangeTest();
    }

    @OnClick(R.id.faltMap_btn)
    void onClickFlatMap() {
        flatMap();
    }

    @BindView(R.id.Interval_txt)
    TextView intervalText;

    @OnClick(R.id.Interval_btn)
    void onClickInterval() {
        interval();
    }

    @BindView(R.id.Just_txt)
    TextView justText;
    @OnClick(R.id.Just_btn)
    void onCLickJust() {
        just();
    }

    @BindView(R.id.Single_txt)
    TextView getMap_txt;
    @OnClick(R.id.Single_btn)
    void onClickSingle() {
        singleClick();
    }

    @BindView(R.id.throttle_btn)
    Button mThrottleBtn;
    @OnClick(R.id.throttle_btn)
    void onClickThrottleClick() {
        //throttleClick();

        String country = getResources().getConfiguration().locale.getCountry();

        String language = getResources().getConfiguration().locale.getLanguage();

        Toast.makeText(getApplicationContext(), country + " : " + language, Toast.LENGTH_SHORT).show();
    }

    private void throttleClick() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                int a = 1;
                a++;
                Toast.makeText(getApplicationContext(), "点击" + a, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        RxView.clicks(mThrottleBtn)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(observer);
    }

    private void singleClick() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        getMap_txt.setText(integer+" 1");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void just() {
        Observable<String> just = Observable.just("1", "2", "3");
        just.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                justText.setText(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

        Observable.just("1")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    Disposable mDisposable;

    private void interval() {
        mDisposable = Observable.interval(1, 2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        intervalText.setText(String.valueOf(aLong));
                        if (aLong == 10) {

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    //faltMap操作符，将function接收到的字符变成Observable，转化方式有：just、Observable.fromIterable(list)
    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer + "");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                String s1 = s;
            }
        });
    }

    //另外一种方式，将integer转化成数组，传送出去

    /**
     * 注意这里的多次变换，Observable.just -- > Observable
     *                   just.faltMap    -- > Observable
     *                   Observable.fromIterable() -- > Observabel
     *
     *                   返回Observable才能继续向下使用
     */
    private void flatMap_1() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4);
        Observable<Integer> integerObservable = just.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                ArrayList<Integer> integers = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    integers.add(i);
                }
                Observable<Integer> integerObservable1 = Observable.fromIterable(integers);
                return integerObservable1;
            }
        });
        integerObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
    }

    private void intervalRangeTest() {
        Observable.intervalRange(10, 10, 0, 2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        intervalRangeText.setText(String.valueOf(aLong));
                    }
                });
    }

    private void mapTest() {
        final StringBuilder sb = new StringBuilder();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return sb.append(String.valueOf(integer) + "...").toString();
            }
        }).timer(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                map_txt.setText(aLong + "");
            }
        });
    }

    private void createTest() {
        final StringBuilder sb = new StringBuilder();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                /**
                 *  接收上游发送来的Integer，e.onNext() --> onNext()
                 */
                sb.append(integer + "..");
                create_txt.setText(sb);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
