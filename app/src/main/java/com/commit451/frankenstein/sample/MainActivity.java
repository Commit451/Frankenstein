package com.commit451.frankenstein.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textUsername = (TextView) findViewById(R.id.text_username);
        textUsername.setText(App.get().getUserName());
        findViewById(R.id.button_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new IllegalArgumentException("blah");
            }
        });
        findViewById(R.id.button_crash_delayed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        throw new IllegalStateException("Blah");
                    }
                }, TimeUnit.SECONDS.toMillis(3));
            }
        });
    }
}
