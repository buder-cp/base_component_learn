package com.example.cm.rxjava_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.create_btn)
    Button create_btn;
    @BindView(R.id.create_txt)
    TextView create_txt;
    @OnClick(R.id.create_btn)
    void onClickCreate () {
        createTest();
    }

    @BindView(R.id.map_btn)
    Button map_btn;
    @BindView(R.id.map_txt)
    TextView map_txt;
    @OnClick(R.id.map_btn)
    void onClickMap () {
        mapTest();
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
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                map_txt.setText(s);
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
                sb.append(integer+"..");
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
