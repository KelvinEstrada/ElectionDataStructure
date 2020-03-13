package MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
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

		String outputFile = "output.txt";
		PrintWriter output = new PrintWriter(outputFile);
		File ballotFile = new File(BALLOTS);
		File candidates = new File(CANDIDATES);

		Set<Ballot> ballotSet = new DynamicSet<Ballot>(1);
		//Set<Integer> eliminatedCandidates = new DynamicSet<Integer>(1);

		Scanner sc = new Scanner(ballotFile);
		LinkedList<String> ids = candidatesList(candidates);

		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			Ballot ballot = new Ballot(input1);
			ballotSet.add(ballot);
		}
		output.println("Number of ballots: " + ballotSet.size());
		output.println("Number of blank ballots: " + blankBallots(ballotSet));
		output.println("Number of invalid ballots: " + invalidBallots(ballotSet));
		List<Set<Ballot>> ballotSetList = ones(ids, ballotSet);
		//		for(int i = 0; i < ballotSetList.size(); i++) {
		//			for(Ballot b: ballotSetList.get(i)) {
		//				printList(b.getBallotVotes());
		//				System.out.println("");
		//			}
		//		}
		int candidateToEliminate = getCandidateToEliminate(ballotSetList);
		output.println("Round 1: " + name(candidateToEliminate, ids) + " was eliminated with " + onesCount(candidateToEliminate, ballotSetList) + " #1's");
		editSet(getSetToChange(ballotSetList), candidateToEliminate);
		int max = max(ballotSetList);

		int candidateToEliminate2 = getCandidateToEliminate(ballotSetList);
		output.println("Round 2: " + name(candidateToEliminate2, ids) + " was eliminated with " + onesCount(candidateToEliminate2, ballotSetList) + " #1's");
		editSet(getSetToChange(ballotSetList), candidateToEliminate2);
		max = max(ballotSetList);


		//int candidateToEliminate3 = getCandidateToEliminate(ballotSetList);
		//System.out.println(candidateToEliminate3);
		//int position3 = setPos(ballotSetList);
		//editSet(getSetToChange(ballotSetList), candidateToEliminate3);
		//max = max(ballotSetList);
		//System.out.println(max);

		//		for(int i = 0; i < ballotSetList.size(); i++) {
		//			for(Ballot b: ballotSetList.get(i)) {
		//				printList(b.getBallotVotes());
		//				System.out.println("");
		//			}
		//		}
		output.close();
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
	/*	Print list for debugging
	 * 
	 */
	private static void printList(LinkedList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	/*	Returns a List of sets of ballots that contain each candidate's ballot where they are ranked #1
	 * 
	 */
	private static List<Set<Ballot>> ones(LinkedList<String> ids, Set<Ballot> ballotSet) {
		List<Set<Ballot>> setList = new LinkedList<Set<Ballot>>();
		for(int i = 0; i < ids.size(); i++) {
			Set<Ballot> ones = new DynamicSet<Ballot>(1);
			for(Ballot b: ballotSet) {
				if(Integer.parseInt(ids.get(i).substring(ids.get(i).length()-1)) == b.getFirst()) {
					ones.add(b);
				}
			}
			setList.add(ones);
		}
		return setList;
	}
	public static int onesCount(int candidateID, List<Set<Ballot>> ones) {
		int count = 0;
		for(int i = 0; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(b.getFirst() == candidateID) {
					count++;
				}
			}
		}
		return count;

	}
	/*	Returns candidate ID number to be eliminated
	 * 
	 */
	private static int getCandidateToEliminate(List<Set<Ballot>> ones) {
		int min = ones.get(0).size();
		for(int i = 1; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(ones.get(i).size() < min) {
					if(b.getFirst() != -1) {
						min = b.getFirst();
					}
				}
			}
		}
		return min;
	}
	private static Set<Ballot> getSetToChange(List<Set<Ballot>> ones){
		int min = ones.get(0).size();
		Set<Ballot> minBallotSet = ones.get(0);
		for(int i = 1; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(ones.get(i).size() < min) {
					if(b.getFirst() != -1) {
						minBallotSet = ones.get(i);
						min = b.getFirst();
					}
				}
			}
		}
		return minBallotSet;
	}
	private static void editSet(Set<Ballot> ballotSet, int candidateID) {
		ballotSet.iterator().next().eliminate(candidateID);
	}
	/*	Return Set position that is to be altered when removing a candidate from a ballot
	 * 
	 */
	private static int setPos(List<Set<Ballot>> ones) {
		int min = ones.get(0).size();
		int target = -1;
		for(int i = 1; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(b.getFirst() != -1) {
					if(ones.get(i).size() < min) {
						target = i;
						min = b.getFirst();
					}
				}
			}
		}
		return target;
	}
	/*	Returns maximum ranked candidate
	 * 
	 */
	private static int max(List<Set<Ballot>> ones) {
		int max = ones.get(0).size();
		for(int i = 1; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(ones.get(i).size() > max) {
					if(b.getFirst() != -1) {
						max = b.getFirst();
					}
				}
			}
		}
		return max;
	}
	/*	Counts the number of blank ballots in the ballot set
	 * 
	 */
	public static int blankBallots(Set<Ballot> ballotSet) {
		int count = 0;
		for(Ballot b: ballotSet) {
			if(b.isBlankBallot())
				count++;
		}
		return count;
	}
	/*	Counts the number of blank ballots in the ballot set
	 * 
	 */
	public static int invalidBallots(Set<Ballot> ballotSet) {
		int count = 0;
		for(Ballot b: ballotSet) {
			if(!b.isValid())
				count++;
		}
		return count;
	}
	/*	Gets candidate name given their candidate ID
	 * 
	 */
	public static String name(int candidateID, LinkedList<String> names) {
		String name = "";
		for(int i = 0; i < names.size(); i++) {
			if(Integer.parseInt(names.get(i).substring(names.get(i).length()-1)) == candidateID) {
				name = names.get(i).substring(0, names.get(i).length()-2);
			}
		}
		return name;
	}
}


