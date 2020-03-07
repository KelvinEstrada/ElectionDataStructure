package MainClasses;
import java.util.LinkedList;

import DataStructures.DoublyLinkedList;
import DataStructures.DynamicSet;
import Interfaces.List;
import Interfaces.Set;

public class Ballots {

	//private fields
	private int ballotNumber;
	private LinkedList<String> candidatesID;
	
	public Ballots(String ballotString) {
	/* An instance of the class stores all information regarding the ballot
	 * Ballot number, candidate iD, List containing the votes that were cast
	 */
		this.candidatesID = new LinkedList<String>();
		String ballotValues[] = ballotString.split(",");
		this.ballotNumber = Integer.parseInt(ballotValues[0]);	//Retrieves ballot number from string line and stores it as an integer value
		
	}
	
	public LinkedList<String> getCandidateID(String input) {
		LinkedList<String> namesAndIds = new LinkedList<String>();
		namesAndIds.add(input);
		return namesAndIds;
	}
	
	public int getBallotNumber() {
		return ballotNumber;
	}
	
	public int getCandidateByRank(int rank) {
		//for(int i = 0; i ) 
		return 0;
	}
	public int getRankByCandidate(int candidateID) {
		//TODO-METHOD
		return 0;
	}
	public boolean eliminate(int candidateID) 
	{
		//TODO-METHOD
		return false;
	}
	
}
