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
		Set<Ballot> ballotSet = new DynamicSet<Ballot>(5);
		LinkedList<Integer> rankedOneList = new LinkedList<Integer>();
		
		Scanner scBallot = new Scanner(ballotFile);
		Scanner scCandidates = new Scanner(candidates);
	
		while(scBallot.hasNextLine()) {
			String inputBallotNumber = scBallot.nextLine();
			Ballot ballot = new Ballot(inputBallotNumber, scCandidates);
			ballotSet.add(ballot);
		}
		System.out.println("Number of ballots: " + countBallots(ballotSet));
		System.out.println("Number of blank ballot: " + countBlankBallots(ballotSet));
		System.out.println("Number of invalid ballots: " + invalidBallots(ballotSet));
		for(Ballot b: ballotSet) {
			rankedOneList.add(b.getRankedOne());
		}
		for(int i = 0; i < rankedOneList.size(); i++) {
			System.out.print(rankedOneList.get(i) + " ");
		}
		System.out.println("");
		System.out.println(getMin(rankedOneList));
		ballotSet.iterator().next().eliminate(getMin(rankedOneList));
		printList(ballotSet.iterator().next().getVotes());
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