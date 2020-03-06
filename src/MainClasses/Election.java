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
		Set<Ballots> ballotsSet = new DynamicSet<Ballots>(1);
		
//		Scanner c = new Scanner(candidates);
//		Scanner s = new Scanner(ballotFile);
//		String d = c.nextLine();
//		String b = s.nextLine();
//		Ballots b1 = new Ballots(b, d);
//		
//		System.out.println(b1.getBallotNumber() + " " + b1.getCandidateID());
		
		
		try (Scanner sc = new Scanner(ballotFile)) {
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				Ballots b1 = new Ballots(data);
				ballotsSet.add(b1);
				System.out.println(b1.getCandidateID());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		printSet(ballotsSet);
	}
	private static void printSet(Set<Ballots> set) {
		for(Ballots s: set) {
			System.out.println(s.getBallotNumber());
		}
	}
}