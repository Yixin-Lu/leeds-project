package com.project.containerData;

import java.util.Random;

/**
 * In order to generate stacks data
 * Created on 15 Jul 2020
 * Revised on 02 Aug 2020
 * @author Yixin Lu
 *
 */

public class Stacks {
	
	public int[][] iniStacks(int numOfStacks) {
		
		int [][] stacks = new int[3][numOfStacks];
		
		Random rand = new Random();
		
		for(int i = 0; i < 3; i++) {
			
			for(int j = 0; j < stacks[0].length; j++) {
				
				stacks[i][j] = rand.nextInt(2) + 1;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			
			for(int j = 0; j < stacks[0].length; j = j + 3) {
				
				stacks[i][j] = 0;
			}
		}
		
		for(int j = 0; j < stacks[0].length; j++) {
			
			if(stacks[2][j] == 0) {
				stacks[1][j] = 0;
				stacks[0][j] = 0;
			}
			if(stacks[1][j] == 0) {
				stacks[0][j] = 0;
			}
			if((stacks[0][j] == 1 && stacks[1][j] == 1 && stacks[2][j] == 1) || 
			   (stacks[0][j] == 2 && stacks[1][j] == 2 && stacks[2][j] == 2) ||
			   (stacks[0][j] == 1 && stacks[1][j] == 1 && stacks[2][j] == 2) ||
			   (stacks[0][j] == 1 && stacks[1][j] == 2 && stacks[2][j] == 2)) {
				stacks[0][j] = 2;
				stacks[1][j] = 1;
				stacks[2][j] = 2;
			}
		}
		
		System.out.println("The initial stacks are as follows:");
		for(int i = 0; i < 3; i++) {
			
			for(int j = 0; j < stacks[0].length; j++) {
				
				System.out.print(stacks[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		return stacks;
		
	}
	
	public static void main(String [] args) {
		
		Stacks s = new Stacks();
		s.iniStacks(25);
		
	}
	
}
