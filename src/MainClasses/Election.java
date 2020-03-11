package MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import DataStructures.DynamicSet;
import DataStructures.LinkedList;
import Interfaces.List;
import Interfaces.Set;

public class Election {

	private static final String BALLOTS = "Resources/ballots.csv";
	private static final String CANDIDATES = "Resources/candidates.csv";


	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		File ballotFile = new File(BALLOTS);
		File candidates = new File(CANDIDATES);
		DynamicSet<Ballot> ballotSet = new DynamicSet<Ballot>(1);

		Scanner sc = new Scanner(ballotFile);
		LinkedList<String> ids = candidatesList(candidates);

		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			Ballot ballot = new Ballot(input1);
			ballotSet.add(ballot);
		}
		/*	Store set of ballots inside another set. The set of ballots contains the ballot's numbers
		 * 	where the candidate is ranked #1. 
		 */
		Set<Ballot> ballotsForCandidate = new DynamicSet<Ballot>(1);
		Set<Set<Ballot>> sets = new DynamicSet<Set<Ballot>>(1);
		for(int i = 0; i < ids.size(); i++) {
			for(Ballot b: ballotSet) {
				Set<Ballot> setBallot = storeBallot(i+1, b, ballotsForCandidate);
				sets.add((Set<Ballot>) setBallot);
			}
		}
		Set<Integer> eliminatedCandidates = new DynamicSet<Integer>(1);
		LinkedList<Integer> rankCount = new LinkedList<Integer>();
		
	}

	/*	Return #1 candidate in the ballot
	 * 
	 */
	private static int rankedOne(Ballot ballot) {
		return ballot.getBallotVotes().first();
	}

	/*	This method stores the ballot (inside a set) where the given candidate is ranked one
	 * 
	 */
	private static Set<Ballot> storeBallot(int candidateID, Ballot ballot, Set<Ballot> ballotsForCandidate) {
		//Set<Ballot> ballotsForCandidate = new DynamicSet<Ballot>(1);
		if(ballot.getRankByCandidate(candidateID) == 1) {
			ballotsForCandidate.add(ballot);
		}
		return ballotsForCandidate;
	}

	/*	Store candidates in a List
	 * 
	 */
	private static LinkedList<String> candidatesList(File candidates) throws FileNotFoundException{
		LinkedList<String> candidatesList = new LinkedList<String>();
		Scanner sc2 = new Scanner(candidates);
		while(sc2.hasNext()) {
			String input1 = sc2.nextLine();
			candidatesList.add(input1);
		}
		return candidatesList;
	}

	/*	Prints a List Strings
	 * 
	 */
	private static void printList(LinkedList<String> ids) {
		for(int i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i));
		}
	}

}



