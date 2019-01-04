import java.util.ArrayList;

// Assignment: WanderlustBoat
// Author: George Hatch
// Date:   Aug 25, 2018
// Email:  Powhound@rams.colostate.edu

public class Boat {
	private int numPeople =0;
	private static int totPeople=0;
	private static int boatNum;
	//private static int prevBoatNum;
	private String GroupNames = "";
	private boolean isFull;
	//private int spotsLeft;
	private ArrayList<ArrayList<Party>> parties = new ArrayList<ArrayList<Party>>();
	
	public Boat(ArrayList<Party> party){		
		parties.add(party);
		buildGroupNames(party);
	}
	
	private void buildGroupNames(ArrayList<Party> party){
			//System.out.println("party size = " + party.size());
			if (party.size() == 1){
				GroupNames+=party.get(0).getName();
				numPeople+=party.get(0).getSize();

			}
			if(party.size() >= 2){
				for(int t = party.size()-1; t>=0; --t){
					//System.out.println("party name = " + party.get(t).getName());
					//System.out.println("t = " + t);
					//System.out.println("party size = " + party.size());

					if (t > 0){
						GroupNames+=party.get(t).getName() + " & ";
						numPeople+=party.get(t).getSize();
					}
					
					if(t == 0){
						//System.out.println("party name = " + party.get(t).getName());
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
