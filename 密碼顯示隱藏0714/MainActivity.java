package com.example.imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText edit;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        edit = (EditText)findViewById(R.id.edit);

        //設定監聽的副程式"touchlistener"
        button.setOnTouchListener(touchlistener);


    }

    Button.OnTouchListener touchlistener = new Button.OnTouchListener(){

        @Override
        public boolean onTouch(View v,MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //按下顯示密碼
                edit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else if (event.getAction() == MotionEvent.ACTION_UP){
                //放開變成*******
                edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            return true;
        }
    };






}