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

public class mean__mode__middle__ect extends PApplet {

int[] orderedList;

String currentString = "";

String[] enterable = {"1","2","3","4","5","6","7","8","9","0"};

int[] mode;
float mean;
float median;
int range;

public void setup(){
  
  orderedList = new int[0];
  mode = new int[0];
}


public void draw(){
  background(0);
  displayList();
  if(orderedList.length > 2){
    displayData();
  }
  text(currentString,500,500);
}

public void keyPressed(){
  if(isenterable(str(key))){
    currentString+=str(key);
  }
  if(key == ENTER){
    orderedList = appenedInt(orderedList,PApplet.parseInt(currentString));
    currentString = "";
    sortList();
    calcAll();
  }
  if(str(key).equals("U")){
    orderedList = new int[0];
    currentString = "";
  }
}

public void displayData(){
  text("Range: " + str(range),800,80);
  text("Mean: " + str(mean),800,110);
  text("Median: " + str(median),800,140);
  text("Mode(s): ",800,170);
  for(int i = 0; i < mode.length; i++){
    text(str(orderedList[mode[i]]),840,200+i*30);
  }
}

public void displayList(){
  fill(255);
  textSize(30);
  for(int i = 0; i < orderedList.length; i++){
    text(str(orderedList[i]),50,80+i*30);
  }
}

public void sortList(){
  boolean sorted = false;
  while(sorted == false){
    sorted = true;
    for(int i = 0; i < orderedList.length-1; i++){
      if(orderedList[i] > orderedList[i+1]){
        int temp = orderedList[i];
        orderedList[i] = orderedList[i+1];
        orderedList[i+1] = temp;
        sorted = false;
      }
    }
  }
}

public boolean isenterable(String toenter){
  boolean found = false;
  for(int i = 0; i < enterable.length; i++){
    if(toenter.equals(enterable[i])){
      found = true;
      break;
    }
  }
  return found;
}

public void calcRange(){
  range = orderedList[orderedList.length-1]-orderedList[0];
}

public void calcMean(){
  float avg = 0;
  for(int i = 0; i < orderedList.length; i++){
    avg+=orderedList[i];
  }
  avg/=orderedList.length;
  mean = avg;
}

public void calcMode(){
  int record = 0;
  int[] index = {};
  for(int i = 0; i < orderedList.length; i++){
    int count = getCountOf(orderedList[i]);
    if(count > record){
      record = count;
      index = new int[]{i};
    }else if(count == record){
      if(inMode(i,index) == false){
        index = appenedInt(index,i);
      }
    }
  }
  mode = index;
}

public void calcMedian(){
  int v = orderedList.length/2;
  int val1 = orderedList[v];
  int val2 = orderedList[orderedList.length-v-1];
  median = (val1+val2)/2.0f;
}

public void calcAll(){
  if(orderedList.length > 2){
    calcRange();
    calcMean();
    calcMode();
    calcMedian();
    println(range,orderedList[mode[0]],mean,median);
  }
}

public boolean inMode(int num, int[] array){
  boolean found = false;
  for(int i = 0; i < array.length; i++){
    if(array[i] == num){
      found = true;
      break;
    }
  }
  return found;
}

public int getCountOf(int num){
  int count = 0;
  for(int i = 0; i < orderedList.length; i++){
    if(orderedList[i] == num){
      count++;
    }
  }
  return count;
}

public int[] appenedInt(int[] array, int toappend){
  int[] newarray = new int[array.length+1];
  for(int i = 0; i < array.length; i++){
    newarray[i] = array[i];
  }
  newarray[array.length] = toappend;
  return newarray;
}
  public void settings() {  size(1200,1000,P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "mean__mode__middle__ect" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
