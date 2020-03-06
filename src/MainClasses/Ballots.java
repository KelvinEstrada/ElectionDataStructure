package MainClasses;
import java.util.LinkedList;

import DataStructures.DoublyLinkedList;
import DataStructures.DynamicSet;
import Interfaces.List;
import Interfaces.Set;

public class Ballots {

	//private fields
	private int ballotNumber;
	private String candidateID;
	private DoublyLinkedList<Integer> rankList;
	private int numberOfCandidates = 0;
	
	public Ballots(String ballotString) {
	/* An instance of the class stores all information regarding the ballot
	 * Ballot number, candidate iD, List containing the votes that were cast
	 */
		String values[] = ballotString.split(",");
		this.ballotNumber = Integer.parseInt(values[0]);
		this.candidateID = values[1].substring(0);
		
	}
	
	public String getCandidateID() {
		return this.candidateID;
	}
	
	public int getBallotNumber() {
		return ballotNumber;
	}
	
	public int getCandidateByRank(int rank) {
		//TODO-METHOD 
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
	public DoublyLinkedList<Integer> getRankList()
	{
		return (DoublyLinkedList<Integer>) this.rankList;
	}
	
}
