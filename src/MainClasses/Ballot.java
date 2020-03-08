package MainClasses;


import java.util.NoSuchElementException;
import java.util.Scanner;

import DataStructures.DynamicSet;
import DataStructures.LinkedList;
import DataStructures.StaticSet;
import Interfaces.List;
import Interfaces.Set;

public class Ballot{

	//private fields
	private int ballotNumber;
	private LinkedList<Integer> votes;

	public Ballot(String ballotString, Scanner candidates) {
	/* An instance of the class stores all information regarding the ballot
	 * Ballot number, candidate iD, List containing the votes that were cast
	 */
		String ballotValues[] = ballotString.split(",");
		this.ballotNumber = Integer.parseInt(ballotValues[0]);	//Retrieves ballot number from string line and stores it as an integer value
		this.votes = new LinkedList<Integer>();
		for(int i = 1; i < ballotValues.length; i++) {
			int candidateID = Integer.parseInt(ballotValues[i].substring(0,1));
			/* The list's index is a reference to the rank given by the voter.
			 * 
			 */
			this.votes.add(candidateID);
		}
	}
	
	public LinkedList<Integer> getVotes() {
		return this.votes;
	}
	
	public int getBallotNumber() {
		return ballotNumber;
	}
	
	public int getCandidateByRank(int rank) {
		return this.votes.get(rank);
	}
	
	/* Keep in mind index of the list refers to the rank given to the candidate
	 * in that index/position within the List
	 */
	public int getRankByCandidate(int candidateID) {
		for(int i = 0; i < this.votes.size(); i++) {
			if(this.votes.get(i) == candidateID) {
				int target = i;
				return target + 1;
			}
		}
		throw new NoSuchElementException();
	}
	public boolean eliminate(int candidateID) 
	{
		//TODO-METHOD
		return false;
	}
	
}
