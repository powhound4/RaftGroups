import java.util.ArrayList;

// Assignment: WanderlustBoat
// Author: George Hatch
// Date:   Aug 25, 2018
// Email:  georgehatch91@gmail.com

public class Boat {
	private int numPeople =0;
	private static int totPeople=0;
	private static int boatNum;
	private String GroupNames = "";
	private boolean isFull;
	private ArrayList<ArrayList<Party>> parties = new ArrayList<ArrayList<Party>>();
	
	//constructor for Boat takes an arraylist of all the parties and then builds the group
	public Boat(ArrayList<Party> party){		
		parties.add(party);
		buildGroupNames(party);
	}
	//builds a string so toString() prints properly
	private void buildGroupNames(ArrayList<Party> party){
			if (party.size() == 1){
				GroupNames+=party.get(0).getName();
				numPeople+=party.get(0).getSize();

			}
			if(party.size() >= 2){
				for(int t = party.size()-1; t>=0; --t){
					if (t > 0){
						GroupNames+=party.get(t).getName() + " & ";
						numPeople+=party.get(t).getSize();
					}
					if(t == 0){
						GroupNames+=party.get(t).getName();
						numPeople+=party.get(t).getSize();
					}
				}	
			}
	}
	
	public String toString(){
		totPeople+=numPeople;
		boatNum++;
		return "Boat# " + boatNum + " " + GroupNames + " Group of " + numPeople + "\n";
	}
	
	public boolean isFull(){
		return this.isFull;
	}
	//increments number of people in boat by the number passed in
	public void incNumPeople(int inc){
		this.numPeople+= inc;
	}
	public int getNumPeople(){
		return this.numPeople;
	}
	public void setNumPeople(int num){
		this.numPeople = num;
	}
	public int getBoatNum(){
		return this.boatNum;
	}
	public void setBoatNum(int num){
		this.boatNum = num;
	}
	public int getTotPeople(){
		return totPeople;
	}
	public ArrayList<ArrayList<Party>> getParties(){
		return parties;
	}
}
