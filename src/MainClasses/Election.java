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
		Scanner sc2 = new Scanner(candidates);
		
		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			Ballot ballot = new Ballot(input1, sc2);
			ballotSet.add(ballot);
		}
		Set<DynamicSet<Ballot>> sets = new DynamicSet<DynamicSet<Ballot>>(1);
		Set<Ballot> b1 = new DynamicSet<Ballot>(1);
		Set<Ballot> b2 = new DynamicSet<Ballot>(1);
		Set<Ballot> b3 = new DynamicSet<Ballot>(1);
		Set<Ballot> b4 = new DynamicSet<Ballot>(1);
		Set<Ballot> b5 = new DynamicSet<Ballot>(1);
		for(Ballot b: ballotSet) {
			for(int i = 1; i <= b.getCandidatesNum(); i++) {
				if(rankedOne(i,b)) {
					b1.add(b);
				}
			}
		}
	}
	
	/*	Return trues if the candidate is ranked one in the given ballot
	 * 
	 */
	private static boolean rankedOne(int candidateID, Ballot ballot) {
		return ballot.getRankByCandidate(candidateID) == 1;
	}
}