package com.senproj.counterattack;

import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView cat=findViewById(R.id.cat);
    ImageView counter=findViewById(R.id.counter);
    Model model=new Model(cat,counter);
    cat.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        //When held down, track cursor position. When released, run calc function with last recorded position as input.
        return false;
      }
    });
    }

  }