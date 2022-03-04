package com.senproj.counterattack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Model {
  float xpos;
  float ypos;
  int force;
  int angle;
  ImageView object;
  ImageView obs;
public Model(ImageView cat, ImageView counter){
  xpos=cat.getX();
  ypos=cat.getY();
  force=0;
  angle=0;
  object=cat;
  obs=counter;
}
public void calc(int xpos, int ypos)
{

  //Calculate the trajectory, move object every tick until collision or reaches ground/edge of screen.

  //
}


public static boolean collision()
{
  //If position of object collides with counter/screen, return true. Otherwise return false.

  return true;
}
}
