package com.example.observermodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    Button btn1;
    Button btn2;
    TextView txt1;
    TextView txt2;
    Button notifyBtn;
    Button cancelBtn;

    ConcreteObserver observer1;
    ConcreteObserver observer2;
    Subject subject;

    int messageNumber = 1;
    StringBuilder message = new StringBuilder("发送消息");
    MessageBean messageBean = new MessageBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.observer1);
        btn2 = findViewById(R.id.observer2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);

        notifyBtn = findViewById(R.id.subject);
        notifyBtn.setOnClickListener(this);

        cancelBtn = findViewById(R.id.remove_subject);
        cancelBtn.setOnClickListener(this);

        observer1 = new ConcreteObserver();
        observer2 = new ConcreteObserver();
        subject = new ConcreteSubject();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.observer1) {
//            subject.addObserver(observer1);
            messageBean.setFromOne(true);
            ConcreteSubject.getInstance().addObserver(this);
        } else if (id == R.id.observer2) {
//            subject.addObserver(observer2);
            messageBean.setFromTwo(true);
            ConcreteSubject.getInstance().addObserver(this);
        } else if (id == R.id.subject) {
//            subject.notifyObserver(messageBean);
            messageBean.setMessage(message.append(messageNumber++) + "");
            ConcreteSubject.getInstance().notifyObserver(messageBean);
        } else if (id == R.id.remove_subject) {
            messageBean.setFromOne(false);
            messageBean.setFromTwo(false);
            ConcreteSubject.getInstance().clearObserver();
        }
    }

    @Override
    public void update(MessageBean message) {
        if (message.getFromOne() == true) {
            txt1.setText(message.getMessage());
        }
        if (message.getFromTwo() == true) {
            txt2.setText(message.getMessage());
        }
        if (messageBean.getFromOne() == true && messageBean.getFromTwo() == true) {
            txt1.setText(message.getMessage());
            txt2.setText(message.getMessage());
        }

    }
}
