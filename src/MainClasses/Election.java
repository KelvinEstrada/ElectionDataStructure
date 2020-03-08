package MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import DataStructures.DynamicSet;
import DataStructures.LinkedList;
import Interfaces.Set;

public class Election {

	private static final String BALLOTS = "/home/kelvin/eclipse-workspace/Project_1_DATA/src/Resources/ballots.csv";
	private static final String CANDIDATES = "/home/kelvin/eclipse-workspace/Project_1_DATA/src/Resources/candidates.csv";
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		
		File ballotFile = new File(BALLOTS);
		File candidates = new File(CANDIDATES);
		Set<Ballot> ballotSet = new DynamicSet<Ballot>(5);
		
		Scanner scBallot = new Scanner(ballotFile);
		Scanner scCandidates = new Scanner(candidates);
	
		while(scBallot.hasNextLine()) {
			String inputBallotNumber = scBallot.nextLine();
			Ballot ballot = new Ballot(inputBallotNumber, scCandidates);
			ballotSet.add(ballot);
		}
		//printSet(ballotSet);
		for(Ballot s: ballotSet) {
			System.out.print(s.getBallotNumber() + ": ");
			printList(s.getVotes());
			System.out.println("");
			System.out.println("Candidate with rank 1 is: " + s.getCandidateByRank(0));
			System.out.println("Rank for candidate 1 is: " + s.getRankByCandidate(1));
		}
	}
	@SuppressWarnings("unchecked")
	private static <E> void printList(LinkedList<Integer> list) {
		E[] array = (E[]) list.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
}