package com.senproj.counterattack;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Model {
  float xpos;
  float ypos;
  float startx,starty;
  double force;
  double angle;
  int time;
  int dwidth,dheight;
  double g=-9.8;
  Boolean landed;
  Boolean flying;
  int status; //0=in progress, 1=win, 2=lose
  ImageView object;
  ImageView obs;
  View popup;
  TextView text;
public Model(ImageView cat, ImageView counter){
  DisplayMetrics metrics=new DisplayMetrics();
  dwidth=metrics.widthPixels;
  dheight=metrics.heightPixels;
  flying=false;
  landed=false;
  status=0;
  xpos=cat.getX();
  ypos=cat.getY();
  startx=xpos;
  starty=ypos;
  force=0;
  angle=0;
  object=cat;
  obs=counter;
  time=0;
}
public void calc(float xcur, float ycur)
{
flying=true;
  //Force=sqrt(xpos^2+ypos^2)
  float xdis=xcur-xpos;
  float ydis=ycur-ypos;
  force=Math.sqrt(Math.exp(xdis)+Math.exp(ydis));
  angle=Math.atan(xdis/ydis);
  //Use tangent to find angle.
  //Calculate the trajectory, move object every tick until collision or reaches ground/edge of screen.
while (!collision())
{
  //Use formula to change x and y positions. Increase time by one. Program will check for collision every loop.
  xpos= (float) (force*time);
  ypos= (float) (xpos*Math.tan(angle)-(g*Math.exp(xpos))/(2*Math.exp(force)*Math.exp(Math.cos(angle))));
  object.setX(xpos);
  object.setY(ypos);
  time++;
}
landed();
//Check if landed
if (landed==true)
{
  status=1;
}
else {
  status=2;
}

//gameOver();
  //gravity=-9.8
  //y=xtan(angle)-(gx^2)/(2v^2*cos^2(angle)
  //x=v*t

}


public boolean collision()
{
  //If position of object collides with counter/screen, return true. Otherwise return false.
  //TODO: Find out how to get dimensions of object, along with dimensions of screen.
  //TODO: Write code to detect if the cat is close and slow enough to top of counter, which indicates that the player won.
int[] catpos = new int[2];
object.getLocationOnScreen(catpos);
int catx=catpos[0];
int caty=catpos[1];
int[] obspos=new int[2];
obs.getLocationOnScreen(obspos);
int obsx=obspos[0];
int obsy=obspos[1];

if (catx>dwidth || caty>dheight || caty<0 || catx<0)
{
  return true; //Returns true if position outside of range
}
//Create a "hitbox" for both the cat and counter
Rect catbox=new Rect();
Rect obsbox=new Rect();
catbox.left=object.getLeft();
catbox.right=object.getRight();
catbox.bottom=object.getBottom();
catbox.top=object.getTop();
obsbox.left=obs.getLeft();
obsbox.right=obs.getRight();
obsbox.bottom=obs.getBottom();
obsbox.top=obs.getTop();
if (catbox.intersect(obsbox))
{
  //Check position and speed to see if landed safely
  landed();
  return true;

}

  return false;
}
public void landed()
{
  //If Y of cat is less than 10 pixels in difference, it counts as a land.
  if (ypos-object.getTop()<=2 && ypos-object.getTop()>=-2)
  {
    landed=true;
  }
}
  public void genCounter()
  {
    Random rnd=new Random();
    int color= Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
    obs.setColorFilter(color); //Sets random color
    obs.getLayoutParams().height=rnd.nextInt(dheight);
    obs.getLayoutParams().width=rnd.nextInt(dwidth); //Sets random height, might have to change


  }
  @SuppressLint("ResourceType")
  public void gameOver()
  {
if (status==1)
{
text.setText("You win!");
}
else
{
text.setText("You lose!");
}

  }

  public void restart()
  {
    //Set landed to false
landed=false;
status=0;
    //Set cat position to start
object.setX(xpos);
object.setY(ypos);
    //Run gencounter
    genCounter();
    flying=false;
  }
}
