import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Assignment: WanderlustBoat
// Author: George Hatch
// Date:   Aug 25, 2018
// Email:  Powhound@rams.colostate.edu

public class Trip implements Iterator<Party> {
	Iterator<String> iterS;
	private String line;
	private int totPeople;
	//private String nextToken;
	//private int stLeng;
	//private int iter;
	private int numBoats;
	private double waterLevel;
	//private int groupsLeft;
	private ArrayList<Party> customers = new ArrayList<Party>();  
	private static ArrayList<Boat> Boats = new ArrayList<Boat>();
	//private ArrayList<ArrayList<Party>> parties = new ArrayList<ArrayList<Party>>();
	
	

	private Trip(String line){
		BuildTrip(line);
	}
	
	private void BuildTrip(String line){
		ReadFile(line);
		System.out.println(customers.toString());
		//groupsLeft = customers.size();
		BuildBoats(customers);
		System.out.println(Boats.toString());
		System.out.println("Total # of People = " + Boats.get(0).getTotPeople());
	}
	
	private void ReadFile(String line){
		iterS = new Scanner(line);
		//iter = 0;
		if(line.contains("TripSheet.txt")){
		Scanner reader;
		try{
			
			reader = new Scanner(new File(line));
			while(reader.hasNextLine()){
				Party i = new Party(reader.next(), reader.nextInt(), null);
				if(i.getSize() > 8){
					splitGroup(i,i.getSize(), 2,0);
				}
				else{
					customers.add(i);
				}
			}
			
			reader.close();
		}
			catch (IOException e) {
		        System.out.println("Cannot read file: " + line);
		        System.exit(0);
				}
		}
		else{
			this.line = line.trim();
			//stLeng = this.line.length();
		}
		
	}
	private void BuildBoats(ArrayList<Party> cus){
		for(int i = 0; i < cus.size();i++){
			 if(cus.get(i).getSize()<5 && cus.get(i).inaBoat() == false){
				//System.out.println("Checking groups for "+ cus.get(i));
				checkGroups(cus.get(i));
			 
			 
				for(int j =1; j<=cus.size()-1;j++){
					if(cus.get(i).getSize()+cus.get(j).getSize() <=7 && (cus.get(j).inaBoat()==false && cus.get(i).inaBoat()==false) && 
							!(cus.get(i).getName().equals(cus.get(j).getName()))){
						ArrayList<Party> group = new ArrayList<Party>();
						group.add(cus.get(i));
						group.add(cus.get(j));
						//System.out.println("Group Name = " + cus.get(i));
						//System.out.println("Group Name = " + cus.get(j));
						cus.get(i).assignBoat();
						cus.get(j).assignBoat();
						
						int grpSize = cus.get(i).getSize()+cus.get(j).getSize();
						while (grpSize <= 4){
							//System.out.println("ENTERING");

							int index = refineGroup(cus, j, grpSize);
							if(index == -1){
								break;
							}
							else
							group.add(cus.get(index));
							cus.get(index).assignBoat();

							grpSize += cus.get(index).getSize();
						}
						Boat tempBoat = new Boat(group);
						Boats.add(tempBoat);
						
					}
				}
			}
		}
	}
	
	private void checkGroups(Party prty){
		for(int i = 0; i <Boats.size();i++){
			if(prty.getSize() + Boats.get(i).getNumPeople() < 8 && prty.inaBoat() == false){
				addToBoat(prty);
			}
		}
	}
	
	private void addToBoat(Party grp){
		int min = 999;
		int index = 999;
		for(int i = 0; i < Boats.size();i++){
			if(Boats.get(i).getNumPeople() < min){
				min = Boats.get(i).getNumPeople();
				index = i;
			}
		}
		//System.out.println(grp+ " will be joining " + Boats.get(index));
		Boats.get(index);
		editBoat(Boats.get(index), grp, index);
		
		
	}
	private void editBoat(Boat old, Party add, int iob){
		ArrayList<ArrayList<Party>> parties = old.getParties();
		ArrayList<Party> party = parties.get(0);
		ArrayList<Party> newGroup = new ArrayList<Party>();
		int numPeeps = old.getNumPeople();
		
		for (int i = 0; i < party.size(); i++){
			//System.out.println("The "+ party.get(i) + " is in a boat?" + party.get(i).inaBoat());
			newGroup.add(party.get(i));
			party.get(i).assignBoat();
		}
		newGroup.add(add);
		//System.out.println("The add on group "+ add + " is in a boat?" + add.inaBoat());

		add.assignBoat();
		//System.out.println("Now "+ add + " is in a boat?" + add.inaBoat());


		//System.out.println("New Group = " + newGroup);
		Boat tempBoat = new Boat(newGroup);
		
		//System.out.println("NUM Peeps after = "+ tempBoat.getNumPeople());

		Boats.add(tempBoat);
		tempBoat.setNumPeople(numPeeps + add.getSize());
		//int oldI = Boats.indexOf(old);
		//tempBoat.setBoatNum(Boats.get(oldI -1).getBoatNum() + 1);
		Boats.remove(iob);
	}

	private void splitGroup(Party temp, int size, int num, int pBoat) {
		int groupSize = size/num;
		int denom = num;
		int previousBoat= pBoat;
		
		if(groupSize <=8 && size >= 4){
			while(num > 0){
				if(num == 1 && size%denom > 0){
					groupSize+=size%denom;
					int test = Boats.get(previousBoat).getNumPeople() + 1;
					//System.out.println("Test = " + test);

					if((previousBoat > 0) && (groupSize > test)){
						Boats.get(previousBoat).incNumPeople(1);
						groupSize--;
					}
					if(groupSize>8){
						splitGroup(temp, groupSize, 2, previousBoat);
						break;
					}
				}
				
				ArrayList<Party> group = new ArrayList<Party>();
				group.add(temp);
				Boat tempBoat = new Boat(group);
				tempBoat.setNumPeople(groupSize);
				Boats.add(tempBoat);
				previousBoat = Boats.size()-1;
				//System.out.println("Boat number: " + previousBoat);
				num--;
			}		
		}
		else{
			splitGroup(temp,size, num+1, previousBoat);
		}
		
		
	}
	
	private int refineGroup(ArrayList<Party> cus, int j, int boatSize){
		int index = -1;
		//System.out.println("boatsize"+ boatSize);
		for(int i = j; i <= cus.size()-1; i++){
			if(boatSize + cus.get(i).getSize() < 8 && cus.get(i).inaBoat() == false){
				index = i;
				//System.out.println("index"+ index);
				break;
			}
		}
	return index;
	}
	
	public void setTotPeople(int num){
		this.totPeople+=num;
	}
	
	public String toString(){
		String s = "";
		for(int i =0;i<Boats.size(); i++){
			s+= "Boat# "+ i +  Boats.get(i).toString();
		}
		return s;
	}
	

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Party next() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		Trip Test = new Trip("TripSheet.txt");
		Test.toString();
		//System.out.println("Boats = " + Boats.toString());
		
	}
	

}
