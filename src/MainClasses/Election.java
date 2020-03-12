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
		
		//This is the official set of ballots
		Set<Ballot> ballotSet = new DynamicSet<Ballot>(1);
		Set<Integer> eliminatedCandidates = new DynamicSet<Integer>(1);
		
		Scanner sc = new Scanner(ballotFile);
		LinkedList<String> ids = candidatesList(candidates);

		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			Ballot ballot = new Ballot(input1);
			ballotSet.add(ballot);
		}
		System.out.println("Number of ballots: " + ballotSet.size());
		System.out.println("Number of blank ballots: " + 0);
		List<Set<Ballot>> setListByOnes = new LinkedList<Set<Ballot>>();
		List<Set<Ballot>> setListByTwos = new LinkedList<Set<Ballot>>();
		System.out.println("Round 1:" + " was eliminated with... ");
			for(int i = 1; i <= ids.size(); i++) {
				//Creates a Set of ballots for each candidate in the election
				Set<Ballot> sb = new DynamicSet<Ballot>(1);
				for(Ballot b: ballotSet) {
					if(b.getRankByCandidate(i) == 1) {
						sb.add(b);
						setListByOnes.add(sb);
					}
				}
			}
			for(int i = 1; i <= ids.size(); i++) {
				//Creates a Set of ballots for each candidate in the election
				Set<Ballot> sb = new DynamicSet<Ballot>(1);
				for(Ballot b: ballotSet) {
					if(b.getRankByCandidate(i) == 2) {
						sb.add(b);
						setListByTwos.add(sb);
					}
				}
			}
			Set<Ballot> absoluteMin;
			for(Ballot b: ballotSet) {
				Set<Ballot> min1 = findMinByOnes(setListByOnes);
				Set<Ballot> min2 = findMinByTwos(setListByTwos);
				if(min1.size() < min2.size()) {
					absoluteMin = min1;
				}else {absoluteMin = min2;}
				int candidateToEliminate = absoluteMin.iterator().next().getFirst();
				System.out.println(candidateToEliminate);
				//for(Ballot b1: ballotSet) {
					for(Ballot b2: absoluteMin) {
						if(b.getBallotNum() == b2.getBallotNum()) {
							b.eliminate(candidateToEliminate);
						}
					}
				//}
				eliminatedCandidates.add(candidateToEliminate);
				for(Ballot b1: ballotSet) {
					printList(b1.getBallotVotes());
					System.out.println("");
				}
				
			}
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
	/*	Find least preferred candidate in the List (with less 1's and returns the Ballot set of the least 
	 *  preferred candidate
	 * 
	 */
	private static Set<Ballot> findMinByOnes(List<Set<Ballot>> setListByOnes) {
		//Set the first set in the list to be the one with less 1's
		//The size of the Set is the number of one's that the candidate in the list has.
		//The index of the list is a reference to the candidate number
		int min = setListByOnes.get(0).size();
		Set<Ballot> minBallotSet = setListByOnes.get(0);
		for(int i = 1; i < setListByOnes.size(); i++) {
			if(setListByOnes.get(i).size() < min) {
					min = setListByOnes.get(i).size();
					minBallotSet = setListByOnes.get(i);
			}
		}
		return minBallotSet;
	}
	/*	Find least preferred candidate in the List (with less 1's and returns the Ballot set of the least 
	 *  preferred candidate
	 * 
	 */
	private static Set<Ballot> findMinByTwos(List<Set<Ballot>> setListByTwos) {
		//Set the first set in the list to be the one with less 1's
		//The size of the Set is the number of one's that the candidate in the list has.
		//The index of the list is a reference to the candidate number
		int min = setListByTwos.get(0).size();
		Set<Ballot> minBallotSet = setListByTwos.get(0);
		for(int i = 1; i < setListByTwos.size(); i++) {
			if(setListByTwos.get(i).size() < min) {
					min = setListByTwos.get(i).size();
					minBallotSet = setListByTwos.get(i);
			}
		}
		return minBallotSet;
	}
	/*	Print list for debugging
	 * 
	 */
	private static void printList(LinkedList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}
	}
}



