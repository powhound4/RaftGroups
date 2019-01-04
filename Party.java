// Assignment: WanderlustBoat
// Author: George Hatch
// Date:   Aug 25, 2018
// Email:  Powhound@rams.colostate.edu

public class Party {
 String name;
 private int size;
 private String desc;
 private boolean inBoat;
 
 public Party(String n, int s, String d){
	 name = n;
	 size = s;
	 desc = d;
	 inBoat = false;
 }
 public int getSize(){
	 return this.size;
 }
 public void setSize(int newSize){
	 this.size = newSize;
 }
 public String getDesc(){
	 return this.desc;
 }
 
 public void setDesc(String desc){
	 this.desc = desc;
 }
 public String getName(){
	 return this.name;
 }
 public String toString(){
	 return this.name + " " + this.getSize();
 }
 public void assignBoat(){
	 this.inBoat = true;
 }
 public boolean inaBoat(){
	 return this.inBoat;
 }
 
}
