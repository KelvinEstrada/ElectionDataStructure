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
		
		/*	Structures to be used
		 * 
		 */
		
		// List that contains sets of ballot sets
		LinkedList<Set<Set<Ballot>>> setList = new LinkedList<Set<Set<Ballot>>>();
		// Set that contains ballot sets
		
		Set<Set<Ballot>> setOfBallotSets = new DynamicSet<Set<Ballot>>(5);
		
		
		
		
	}
	
	/*	Read input files, creates ballot objects, and stores them in a SET
	 * 
	 */
	@SuppressWarnings("unused")
	private static void readAndWrite(File ballotFile, File candidates) throws FileNotFoundException {
		// Set that contains ballots
		DynamicSet<Ballot> ballotSet = new DynamicSet<Ballot>(5);
		
		//Read input files
		Scanner scBallot = new Scanner(ballotFile);
		Scanner scCandidates = new Scanner(candidates);
	
		while(scBallot.hasNextLine()) {
			String inputBallotNumber = scBallot.nextLine();
			Ballot ballot = new Ballot(inputBallotNumber, scCandidates);
			ballotSet.add(ballot);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static <E> void printList(LinkedList<Integer> list) {
		E[] array = (E[]) list.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}	
	private static int countBallots(Set<Ballot> ballotSet) {
		int validCount = 0;
		for(Ballot b: ballotSet) {
			if(b.isValidBallot()) {
				validCount++;
			}
		}
		return validCount;
	}
	private static int countBlankBallots(Set<Ballot> ballotSet) {
		int blankCount = 0;
		for(Ballot b: ballotSet) {
			if(b.isBlank()) {
				blankCount++;
			}
		}
		return blankCount;
	}
	private static int invalidBallots(Set<Ballot> ballotSet) {
		int invalidCount = 0;
		for(Ballot b: ballotSet) {
			if(!b.isValidBallot()) {
				invalidCount++;
			}
		}
		return invalidCount;
	}
	/*	Returns candidate with less one's in the Ranked One List
	 * 
	 */
	private static int getMin(LinkedList<Integer> list) {
		int minPos = list.count(list.get(0));
		int minValue = -1;
		for(int i = 0; i < list.size(); i++) {
			if(minPos > list.count(list.get(i))) {
				minPos = list.count(list.get(i));
				minValue = list.get(i);
			}
		}
		return minValue;
	}
}