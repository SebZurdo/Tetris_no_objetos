import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Tetris_no_objetos extends PApplet {

int M;
int limY,limY2;
float limX,limX2;
int crota,cfx;
int tColor;
int tRotation;
int cont,nivel;
int [] T = {58, 154, 184, 178};
int [] S = {180,408,180,408};
int [] OS = {306,240,306,240};
int [] L = {480,402,120,294};
int [] OL = {312,420,456,150};
int [] I = {34952,61440,34952,61440};
int [] C = {15,15,15,15};
int r,g,b, orden;
int [] Forma;
float cX = 181.7f;
int cY = 1;
boolean max = true;
boolean tope = false;
public void setup(){
  
}

public void draw(){
  background(0);
  textSize(32);
  text(str(cX),40,40);
  text(str(cY),40,80);
  text(str(cfx),40,120);
  M = width/20;
  nivel = 1;
  stroke(192,192,192);
  pabajo();
  azarF();
  for (int i = 5; i < 16; ++i) {
    line(180,(i-5)*M,(width-(width/4)),(i-5)*M);
    line(180,(i+6)*M,(width-(width/4)),(i+6)*M);
    line(i*M,0,i*M,height);
  }
  drawT();
  esh();
}
public void azarF(){
  if(max == true){
    orden = (int)random(7);
    orden = 1;text(str(cX),40,40);
    switch(orden){
      case 0:
        Forma = C;
        r = 0;
        g = 0;
        b = 139;
        limY = 648;
        limY2 = 648;
        limX = 468.7f;
        limX2 = 468.7f;
        break;
    case 1:
        Forma = I;
        r = 220;
        g = 20;
        b = 60;
        limY = 684;
        limY2 = 576;
        limX = 504.7f;
        limX2 = 396.7f;
        break;
    case 2:
        Forma = T;
        r = 139;
        g = 69;
        b = 19;
        limY = 648;
        limY2 = 612;
        limX = 432.7f;
        limX2 = 432.7f;
        break;
    case 3:
        Forma = L;
        r = 186;
        g = 85;
        b = 211;
        limY = 612;
        limY2 = 648;
        limX = 432.7f;
        limX2 = 468.7f;
        break;
    case 4:
        Forma = OL;
        r = 255;
        g = 255;
        b = 255;
        limY = 612;
        limY2 = 648;
        limX = 432.7f;
        limX2 = 468.7f;
        break;
    case 5:
        Forma = S;
        r = 30;
        g = 144;
        b = 255;
        limY = 648;
        limY2 = 612;
        limX = 468.7f;
        limX2 = 432.7f;
        break;
    case 6:
        Forma = OS;
        r = 34;
        g = 139;
        b = 34;
        limY = 648;
        limY2 = 612;
        limX = 468.7f;
        limX2 = 432.7f;
        break;
    }
  }
  max = false;
}
public void drawT() {
  push();
  fill(r,g,b);
  if(Forma == I){
    for (int i = 0; i <= 15; i++) {
      if ((Forma[tRotation] & (1 << 15 - i)) != 0) {
        rect(((i % 4) * 143 / 4) + cX, (((i / 4) | 0) * 143 / 4) + cY , (143 / 4), (143 / 4));
      }
    }
  }else if(Forma == C){
    for (int i = 0; i <= 3; i++) {
      if ((Forma[tRotation] & (1 << 3 - i)) != 0) {
        rect(((i % 2) * 70 / 2) + cX, (((i / 2) | 0) * 70 / 2) + cY , (70 / 2), (70 / 2));
      }
    }
  }
  else{
    for (int i = 0; i <= 8; i++) {
      if ((Forma[tRotation] & (1 << 8 - i)) != 0) {
        rect(((i % 3) * 105 / 3) + cX, (((i / 3) | 0) * 105 / 3) + cY , (110 / 3), (110 / 3));
      }
    }
  }
 pop();
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP) {
      if(Forma != C){
        if(Forma == I){
          if(cfx == 1){
            if(cX == 505.7f){
              mover("IZQ");
              mover("IZQ");
              mover("IZQ");
            }
          }
          if(cfx == 0){
            if(cY > limY2){
              cY = limY2;
              tope = true;
            }
            if(cX > limX){
              mover("IZQ");
            }
          }
        }else{
          if(cfx == 0){
            if(cX > limX){
              mover("IZQ");
            }
          }
          if(cfx == 1){
            if(cX > limX2){
              mover("IZQ");
            }
          }
        }
        tRotation ++;
        crota++;
      }
    }
    tRotation = tRotation < 0 ? 3 : tRotation % 4;
  }
  if(keyCode == RIGHT){
    mover("DER");
  }
  if(keyCode == LEFT){
    mover("IZQ");
  }
  if(keyCode == DOWN){
    mover("DOWN");
  }
}

public void mover(String dir){
  if(limite(dir)){
    if(dir == "DER"){
      cX = cX+36;
    }
    if(dir == "IZQ"){
      cX = cX-36;
    }
    if(dir == "DOWN"){
      cY = cY + 36;
    }
  }
}


public boolean limite(String dir){
  switch(dir){
    case "DER":
      if(cfx ==0){
        if(cX >limX2){
          return false;
        }
      }else{
        if(cX>limX){
          return false;
        }
      }
      break;
    case "IZQ":
      if(cX<216.7f){
        return false;
      }
      break;
    case "DOWN":
      if(cfx == 0){
        if(cY>limY){
          return false;
        }
      }else{
        if(cY>limY2){
          return false;
        }
      }
      break;
  }
  return true;
}

public void pabajo(){
  if(cont%(55-nivel) == 0){
    mover("DOWN");
  }
  cont++;
}

public boolean esh(){
  if(crota % 2 == 0){
    cfx = 1;
    return true;
  }
  cfx = 0;
  return false;
}
  public void settings() {  size(720,720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Tetris_no_objetos" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
