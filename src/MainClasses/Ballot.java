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

	public Ballot(String ballotString) {
	/* An instance of the class stores all information regarding the ballot
	 * Ballot number, candidate iD and a List containing the votes that were cast by the voters
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
			 * If candidate is in position 1, he/she has rank #1 and so on...
			 */
			this.votes.add(candidateID);
			rankNum++;
		}
	}

	/*	Returns ballot number
	 * 	DONE
	 */
	public int getBallotNum() {
		return this.ballotNumber;
	}
	
	/*	Returns candidate rank given its candidateID
	 * 	Returns -1 if candidate is not in the list
	 * 	DONE
	 */	
	public int getRankByCandidate(int candidateID) {
		for(int i = 0; i < this.votes.size(); i++) {
			if(this.votes.get(i).equals(candidateID)) {
				int target = i;
				return target+1;
			}
		}
		return -1;
	}
	
	/*	Return candidate's ID give its rank
	 *	DONE
	 */
	public int getCandidateByRank(int rank) {
		for(int i = 0; i < this.votes.size(); i++) {
			if(i == rank-1) {
				return this.votes.get(i);
			}
		}
		return -1;
	}
	
	/*	Eliminates given candidate from the ballot
	 * 	DONE
	 */
	public boolean eliminate(int candidateID) {
		return this.votes.removeElement(candidateID);
	}
	
	/*	Prints ballot's state to the console
	 * 
	 */
	public void printBallot() {
		for(Integer b: this.votes) {
			System.out.print(b + " ");
		}
	}
	
	/*	Return how many candidates are in the ballots
	 * 
	 */
	public int getCandidatesNum() {
		return this.votes.size();
	}
	
	/*	Return votes list
	 * 
	 */
	public LinkedList<Integer> getBallotVotes() {
		return this.votes;
	}
	
	/*	Return #1 ranked candidate
	 * 
	 */
	public int getFirst() {
		return this.votes.get(0);
	}
}
