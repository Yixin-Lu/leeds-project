package com.project.util;

import java.util.ArrayList;
import java.util.List;

import com.project.containerData.Stacks;

/**
 * Util class
 * Created on 15 Jul 2020
 * Revised on 08 Aug 2020
 * @author Yixin Lu
 *
 */

public class Util {

	int[][] iniStacksData;
	
	List<Integer> problematicStacksBtoT = new ArrayList<Integer>();
	List<Integer> problematicStacksTtoB = new ArrayList<Integer>();
	List<Integer> goodStacks = new ArrayList<Integer>();
	List<Integer> goodStacksForH2 = new ArrayList<Integer>();
	
	/*
	 * Get total stacks
	 */
	public int[][] getTotalStacks(int numOfStacks) {
		
		Stacks stacks = new Stacks();
		iniStacksData = stacks.iniStacks(numOfStacks);
		
		return iniStacksData;
		
	}
	
	/*
	 * Get total stacks for heuristic 2
	 */
	public int[][] getTotalStacksForH2(int numOfStacks) {
		
		int[][] iniStacksDataForH2 = new int[3][numOfStacks];		

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < iniStacksData[0].length; j++) {

				iniStacksDataForH2[i][j] = iniStacksData[i][j];
			}
		}
		
		return iniStacksDataForH2;

	}
	
	/*
	 * Record the column number in problematicStacks from bottom to top
	 */
	public List<Integer> getProblematicStacksBtoT(){
		
		// Check layer 2 and layer 3.
		for (int i = 0; i < iniStacksData[0].length; i++) {

			if (iniStacksData[1][i] > iniStacksData[2][i]) {
				problematicStacksBtoT.add(i);
			}
		}

		// Check layer 1 and layer 2.
		for (int i = 0; i < iniStacksData[0].length; i++) {

			if (iniStacksData[0][i] > iniStacksData[1][i]) {
				problematicStacksBtoT.add(i);
			}
		}
		
		System.out.println("Record the column number in problematicStacks from bottom to top");
		for(int i = 0; i < problematicStacksBtoT.size(); i++) {
			
			System.out.println("The column of problematicStacks is: " + problematicStacksBtoT.get(i));
			for(int k = 0; k < 3; k++) {
				System.out.println(iniStacksData[k][problematicStacksBtoT.get(i)]);
			}
			
		}
		System.out.println();
		
		return problematicStacksBtoT;
		
	}
	
	/*
	 * Record the column number in problematicStacks from top to bottom
	 */
	public List<Integer> getProblematicStacksTtoB(){
		
		// Check layer 1 and layer 2.
		for (int i = 0; i < iniStacksData[0].length; i++) {

			if (iniStacksData[0][i] > iniStacksData[1][i]) {
				problematicStacksTtoB.add(i);
			}
		}

		// Check layer 2 and layer 3.
		for (int i = 0; i < iniStacksData[0].length; i++) {

			if (iniStacksData[1][i] > iniStacksData[2][i]) {
				problematicStacksTtoB.add(i);
			}
		}
		
		System.out.println("Record the column number in problematicStacks from top to bottom");
		for(int i = 0; i < problematicStacksTtoB.size(); i++) {
			
			System.out.println("The column of problematicStacks is: " + problematicStacksTtoB.get(i));
			for(int k = 0; k < 3; k++) {
				System.out.println(iniStacksData[k][problematicStacksTtoB.get(i)]);
			}
			
		}
		System.out.println();
		
		return problematicStacksTtoB;
		
	}
	
	/*
	 * Record the column number in goodStacks
	 */
	public List<Integer> getGoodStacks(){
		
		for(int i = 0; i < iniStacksData[0].length; i++) {
			
			if(iniStacksData[2][i] == 0 && iniStacksData[1][i] == 0 && iniStacksData[0][i] == 0) {
				goodStacks.add(i);
			}				
		}
		
		System.out.println("The column of goodStacks is: ");
		for(int i = 0; i < goodStacks.size(); i++) {
			System.out.println(goodStacks.get(i));
		}
		System.out.println();
		
		return goodStacks;
		
	}
	
	/*
	 * Get good stacks for heuristic 2
	 */
	public List<Integer> getGoodStacksForH2() {

		for (int i = 0; i < goodStacks.size(); i++) {

			goodStacksForH2.add(goodStacks.get(i));
		}
		
		System.out.println("The column of getGoodStacksForH2 is: ");
		for(int i = 0; i < goodStacksForH2.size(); i++) {
			System.out.println(goodStacksForH2.get(i));
		}
		System.out.println();

		return goodStacksForH2;

	}
	
}
