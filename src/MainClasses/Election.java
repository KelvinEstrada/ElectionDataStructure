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
		Set<Ballot> ballotSet = new DynamicSet<Ballot>(1);

		Scanner sc = new Scanner(ballotFile);
		LinkedList<String> ids = candidatesList(candidates);

		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			Ballot ballot = new Ballot(input1);
			ballotSet.add(ballot);
		}
		/*	Store set of ballots inside a list. The set of ballots contains the ballot's numbers
		 * 	where the candidate is ranked #1. 
		 */
		List<Set<Ballot>> listOfSets = new LinkedList<Set<Ballot>>();	
		for(int i = 1; i <= ids.size(); i++) {
			for(Ballot b: ballotSet) {
				//Set<Ballot> setBallot = storeBallot(i+1, b);
				listOfSets.add(storeBallot(i, b));
			}
		}
		printList(listOfSets);
		
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
	private static Set<Ballot> storeBallot(int candidateID, Ballot ballot) {
		Set<Ballot> ballotsForCandidate = new DynamicSet<Ballot>(5);
		Ballot b = ballot;
		if(b.getRankByCandidate(candidateID) == 1) {
			ballotsForCandidate.add(b);
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

	/*	Prints a List
	 * 
	 */
	private static void printList(List<Set<Ballot>> ids) {
		for(int i = 0; i < ids.size(); i++) {
			for(Ballot b: ids.get(i)) {
				System.out.println(b.getBallotNum());
			}
		}
	}
}



