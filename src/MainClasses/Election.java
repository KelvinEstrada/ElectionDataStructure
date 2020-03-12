package MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import DataStructures.ArrayList;
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
		System.out.println("Number of ballots: " + ballotSet.size());
		System.out.println("Number of blank ballots: " + 0);
		List<Set<Ballot>> setList = new LinkedList<Set<Ballot>>();
		for(int i = 1; i <= ids.size(); i++) {
			for(Ballot b: ballotSet) {
				Set<Ballot> sb = store(i, b);
				setList.add(sb);
			}
		}
		for(int i = 0; i < setList.size(); i++) {
			for(Ballot b: setList.get(i)) {
				
			}
		}
		System.out.println("Round 1:" + " was eliminated with... ");
	

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
	
	private static Set<Ballot> store(int id, Ballot ballot) {
		Set<Ballot> result = new DynamicSet<Ballot>(3);
		if(ballot.getRankByCandidate(id) == 1) {
			result.add(ballot);
		}
		return result;
	}

}



