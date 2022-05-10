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
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView cat=findViewById(R.id.cat);
    ImageView counter=findViewById(R.id.counter);
    Model model=new Model(cat,counter);
   // model.genCounter();
//TODO: Eventually create graphics for cat, counter and background.
    cat.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        //When held down, track cursor position. When released, run calc function with last recorded position as input.
        float xpos = 0,ypos=0;
        while (motionEvent.getAction()!=MotionEvent.ACTION_UP)
        {
         xpos=motionEvent.getX();
         ypos=motionEvent.getY();
        }
        if (model.flying==false) {
          model.calc(xpos, ypos);
        }
        return true;
      }
    });
    }

  }