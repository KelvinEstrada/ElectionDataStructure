package MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import DataStructures.DynamicSet;
import Interfaces.Set;

public class Election {

	private static final String BALLOTS = "/home/kelvin/eclipse-workspace/Project_1_DATA/src/Resources/ballots.csv";
	private static final String CANDIDATES = "/home/kelvin/eclipse-workspace/Project_1_DATA/src/Resources/candidates.csv";
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File ballotFile = new File(BALLOTS);
		File candidates = new File(CANDIDATES);
		Set<Ballots> ballotSet = new DynamicSet<Ballots>(1);

		try (Scanner sc = new Scanner(ballotFile)) {
			while (sc.hasNextLine()){
				String inputBallotNumber = sc.nextLine();
				Ballots ballot = new Ballots(inputBallotNumber);
				ballotSet.add(ballot);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		printSet(ballotSet);
	}
	
	private static void printSet(Set<Ballots> set) {
		for(Ballots s: set) {
			System.out.println(s.getBallotNumber());
		}
	}
	
}