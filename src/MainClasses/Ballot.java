package MainClasses;


import java.lang.instrument.IllegalClassFormatException;
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
	private boolean isValidBallot = true;

	public Ballot(String ballotString, Scanner candidates) {
	/* An instance of the class stores all information regarding the ballot
	 * Ballot number, candidate iD, List containing the votes that were cast
	 */
		String ballotValues[] = ballotString.split(",");
		this.ballotNumber = Integer.parseInt(ballotValues[0]);	//Retrieves ballot number from string line and stores it as an integer value
		this.votes = new LinkedList<Integer>();
		int rankNum = 1;
		for(int i = 1; i < ballotValues.length; i++) {
			if(Integer.parseInt(ballotValues[i].substring(ballotValues[i].length()-1)) != rankNum) {
				isValidBallot = false;
			}
			int candidateID = Integer.parseInt(ballotValues[i].substring(0,1));
			/* The list's index is a reference to the rank given by the voter.
			 * 
			 */
			this.votes.add(candidateID);
			rankNum++;
		}
	}
	
	public boolean isValidBallot() {
		return isValidBallot;
	}
	
	public LinkedList<Integer> getVotes() {
		return this.votes;
	}
	
	public int getBallotNumber() {
		return ballotNumber;
	}
	
	/* Return candidate that has the given rank
	 * 
	 */
	public int getCandidateByRank(int rank) {
		return this.votes.get(rank - 1);
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
	public boolean eliminate(int candidateID) {
		Integer toBeRemoved = this.getIndex(candidateID);
		return this.getVotes().remove(toBeRemoved);
	}
	public int getIndex(int candidateID) {
		int target = -1;
		if(this.getVotes().isEmpty()) {
			return target;
		}
		for(int i = 0; i < this.getVotes().size(); i++) {
			if(this.getVotes().get(i) == candidateID) {
				target = i;
				break;
			}
		}
		return target;
	}
	
	public int getRankedOne() {
		return this.getVotes().get(0);
	}
	
	public boolean isBlank() {
		return this.getVotes().size() == 0;
	}
	
}
