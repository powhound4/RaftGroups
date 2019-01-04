// Assignment: WanderlustBoat
// Author: George Hatch
// Date:   Aug 25, 2018
// Email:  georgehatch91@gmail.com

public class Party {
 private String name;
 private int size;
 private String desc;
 private boolean inBoat;
	
 /* This version only accounts for the name of the party and size. Eventually I'd like to work in description 
 whether the party is strong or weak 
*/
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
//assigns party to boat so they are no longer considered for any other boats
 public void assignBoat(){
	 this.inBoat = true;
 }
 public boolean inaBoat(){
	 return this.inBoat;
 }
 
}
