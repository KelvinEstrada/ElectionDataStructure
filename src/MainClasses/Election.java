/*	Author: Kelvin O. Estrada Soto
 * 	ICOM4035 - 096
 * 
 */
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

		/*	After running the program, refresh the project to see the output.txt file in the Resources folder
		 * 
		 */
		File outputFile = new File("Resources/output.txt");
		PrintWriter output = new PrintWriter(outputFile);
		File ballotFile = new File(BALLOTS);
		File candidates = new File(CANDIDATES);

		Set<Ballot> ballotSet = new DynamicSet<Ballot>(1);
		Set<Integer> eliminated = new DynamicSet<Integer>(1);
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
		List<Set<Ballot>> ballotSetListOnes = ones(ids, ballotSet);
		int candidateToEliminate = 0;
		for(int i = 1; i <= ids.size(); i++) {
			candidateToEliminate = getCandidateToEliminate(ballotSetListOnes, eliminated);
			eliminated.add(candidateToEliminate);
			output.println("Round " + (i+1) +": " + name(candidateToEliminate, ids) + " was eliminated with " + onesCount(candidateToEliminate, ballotSetListOnes) + " #1's");
			editSet(ballotSetListOnes, candidateToEliminate);
			if(onesCount(max(ballotSetListOnes), ballotSetListOnes) > ballotSetListOnes.size()-1) {
				break;
			}
		}
		output.println("Winner: " + name(max(ballotSetListOnes), ids) + " wins with " + onesCount(max(ballotSetListOnes), ballotSetListOnes) + " #1's");
		output.close();
	}
	/*	Store candidates in a List
	 * 
	 */
	public static LinkedList<String> candidatesList(File candidates) throws FileNotFoundException{
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
//	public static void printList(LinkedList<Integer> list) {
//		for(int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i) + " ");
//		}
//	}
	/*	Returns a List of sets of ballots that contain each candidate's ballot where they are ranked #1
	 * 
	 */
	public static List<Set<Ballot>> ones(LinkedList<String> ids, Set<Ballot> ballotSet) {
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
	/*	Counts the number of ones in a set for a specified candidate
	 * 
	 */
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
	public static int getCandidateToEliminate(List<Set<Ballot>> ones, Set<Integer> eliminatedCandidates) {
		int min = onesCount(1, ones);
		for(int i = 0; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(!eliminatedCandidates.isMember(b.getFirst()) && onesCount(b.getFirst(), ones) < min) {
					min = b.getFirst();
				}
			}
		}
		return min;
	}
	/*	Return a Set of Ballots that need to be altered after removing a candidate
	 * 
	 */
	public static Set<Ballot> getSetToChange(List<Set<Ballot>> ones){
		int min = ones.get(0).size();
		Set<Ballot> minBallotSet = ones.get(0);
		for(int i = 1; i < ones.size(); i++) {
			for(Ballot b: ones.get(i)) {
				if(ones.get(i).size() < min) {
					minBallotSet = ones.get(i);
					min = b.getFirst();
				}
			}
		}
		return minBallotSet;
	}
	public static void editSet(List<Set<Ballot>> ballotSetList, int candidateID) {
		for(int i = 0; i < ballotSetList.size(); i++) {
			for(Ballot b: ballotSetList.get(i)) {
					b.eliminate(candidateID);	
			}
		}
	}
	/*	Returns maximum ranked candidate
	 * 
	 */
	public static int max(List<Set<Ballot>> ones) {
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
	/*	Counts the number of invalid ballots in the ballot set
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
	/*	Gets candidate name given their candidate ID and the List containing the candidates names
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


