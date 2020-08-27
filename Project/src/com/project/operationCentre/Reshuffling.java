package com.project.operationCentre;

import java.util.ArrayList;
import java.util.List;

import com.project.util.Util;

/**
 * The Operation centre of reshuffling
 * Created on 15 Jul 2020
 * Revised on 12 Aug 2020
 * @author Yixin Lu
 *
 */

public class Reshuffling {

	Util util = new Util();

	int[][] iniStacksData;
	int[][] iniStacksDataForH2;
	List<Integer> problematicStacksBtoT;
	List<Integer> problematicStacksTtoB;
	List<Integer> goodStacks;
	List<Integer> goodStacksForH2;

	/*
	 * Iterate through the stacks from bottom to top
	 */
	public int heuristicOne() {

		List<Integer> putUrgentStacks = new ArrayList<Integer>();
		int cost = 0;

		for (int p = 0; p < problematicStacksBtoT.size(); p++) {

			for (int k = 0; k < 3; k++) {
				
				// The operation of reshuffling.
				try {
					
					if (putUrgentStacks.size() > 0 && iniStacksData[k][problematicStacksBtoT.get(p)] == 1) {

						if (iniStacksData[1][putUrgentStacks.get(0)] == 0) {
							iniStacksData[1][putUrgentStacks.get(0)] = iniStacksData[k][problematicStacksBtoT.get(p)];
							iniStacksData[k][problematicStacksBtoT.get(p)] = 0;
							cost++;
						} else if (iniStacksData[0][putUrgentStacks.get(0)] == 0) {
							iniStacksData[0][putUrgentStacks.get(0)] = iniStacksData[k][problematicStacksBtoT.get(p)];
							iniStacksData[k][problematicStacksBtoT.get(p)] = 0;
							cost++;
						} else {
							putUrgentStacks.remove(0);
						}

					} else {

						if (iniStacksData[2][goodStacks.get(0)] == 0) {
							iniStacksData[2][goodStacks.get(0)] = iniStacksData[k][problematicStacksBtoT.get(p)];
							iniStacksData[k][problematicStacksBtoT.get(p)] = 0;
							cost++;
						} else if (iniStacksData[1][goodStacks.get(0)] == 0) {
							iniStacksData[1][goodStacks.get(0)] = iniStacksData[k][problematicStacksBtoT.get(p)];
							iniStacksData[k][problematicStacksBtoT.get(p)] = 0;
							cost++;
						} else if (iniStacksData[0][goodStacks.get(0)] == 0) {
							iniStacksData[0][goodStacks.get(0)] = iniStacksData[k][problematicStacksBtoT.get(p)];
							iniStacksData[k][problematicStacksBtoT.get(p)] = 0;
							cost++;
						}

					}
					
				} catch (Exception e) {
					System.out.println("Please try again.");
				}
				
				// The operation of checking after reshuffling to reorganize good stacks,
				// problematic stacks, and stacks that can only put urgent containers on them.
				try {

					if ((iniStacksData[0][problematicStacksBtoT.get(p)] == 0
							&& iniStacksData[1][problematicStacksBtoT.get(p)] == 0
							&& iniStacksData[2][problematicStacksBtoT.get(p)] == 0)
							|| (iniStacksData[0][problematicStacksBtoT.get(p)] == 0
									&& iniStacksData[1][problematicStacksBtoT.get(p)] == 0
									&& iniStacksData[2][problematicStacksBtoT.get(p)] == 2)
							|| (iniStacksData[0][problematicStacksBtoT.get(p)] == 0
									&& iniStacksData[1][problematicStacksBtoT.get(p)] == 2
									&& iniStacksData[2][problematicStacksBtoT.get(p)] == 2)) {
						goodStacks.add(p);
						problematicStacksBtoT.remove(p);
						p = 0;
						k = -1;
					}

					if ((iniStacksData[0][problematicStacksBtoT.get(p)] == 0
							&& iniStacksData[1][problematicStacksBtoT.get(p)] == 0
							&& iniStacksData[2][problematicStacksBtoT.get(p)] == 1)
							|| (iniStacksData[0][problematicStacksBtoT.get(p)] == 0
									&& iniStacksData[1][problematicStacksBtoT.get(p)] == 1
									&& iniStacksData[2][problematicStacksBtoT.get(p)] == 1)
							|| (iniStacksData[0][problematicStacksBtoT.get(p)] == 0
									&& iniStacksData[1][problematicStacksBtoT.get(p)] == 1
									&& iniStacksData[2][problematicStacksBtoT.get(p)] == 2)) {

						putUrgentStacks.add(problematicStacksBtoT.get(p));
						problematicStacksBtoT.remove(p);
						p = 0;
						k = -1;
					}

					if (iniStacksData[0][goodStacks.get(0)] != 0 && iniStacksData[1][goodStacks.get(0)] != 0
							&& iniStacksData[2][goodStacks.get(0)] != 0) {
						goodStacks.remove(0);
					}

					if ((iniStacksData[2][goodStacks.get(0)] == 1 && iniStacksData[1][goodStacks.get(0)] == 0
							&& iniStacksData[0][goodStacks.get(0)] == 0)
							|| (iniStacksData[2][goodStacks.get(0)] == 2 && iniStacksData[1][goodStacks.get(0)] == 1
									&& iniStacksData[0][goodStacks.get(0)] == 0)) {

						putUrgentStacks.add(goodStacks.get(0));
						goodStacks.remove(0);
					}

				} catch (Exception e) {
					continue;
				}

				System.out.println("The column of putUrgentStacks after reshuffling before reorganization is: ");
				for (int i = 0; i < putUrgentStacks.size(); i++) {
					System.out.println(putUrgentStacks.get(i));
				}
				System.out.println();

				// Reorganizing the stacks that can only put urgent containers on them.
				if (putUrgentStacks.size() >= 2) {

					for (int u = 1; u < putUrgentStacks.size(); u++) {

						if (!(iniStacksData[2][putUrgentStacks.get(u)] == 2
								&& iniStacksData[1][putUrgentStacks.get(u)] == 1
								&& iniStacksData[2][putUrgentStacks.get(u)] == 0)) {

							for (int n = 1; n < 3; n++) {

								if (iniStacksData[n][putUrgentStacks.get(u)] == 1) {

									if (iniStacksData[1][putUrgentStacks.get(0)] == 0) {
										iniStacksData[1][putUrgentStacks.get(0)] = iniStacksData[n][putUrgentStacks
												.get(u)];
										iniStacksData[n][putUrgentStacks.get(u)] = 0;
										cost++;
									} else if (iniStacksData[0][putUrgentStacks.get(0)] == 0) {
										iniStacksData[0][putUrgentStacks.get(0)] = iniStacksData[n][putUrgentStacks
												.get(u)];
										iniStacksData[n][putUrgentStacks.get(u)] = 0;
										cost++;
									}

									if (iniStacksData[0][putUrgentStacks.get(0)] != 0) {
										putUrgentStacks.remove(u);
										if (putUrgentStacks.size() == 0) {
											break;
										}
										u = 0;
									}

									if (iniStacksData[2][putUrgentStacks.get(u)] == 0) {
										goodStacks.add(u);
										putUrgentStacks.remove(u);
										if (putUrgentStacks.size() == 0) {
											break;
										}
									}

								}

							}

						}

					}

				}

				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < iniStacksData[0].length; j++) {

						System.out.print(iniStacksData[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("cost = " + cost);
				System.out.println();

				System.out.println("The column of putUrgentStacks after reorganization is: ");
				for (int i = 0; i < putUrgentStacks.size(); i++) {
					System.out.println(putUrgentStacks.get(i));
				}
				System.out.println();

				if (problematicStacksBtoT.size() == 0) {
					break;
				}

			}

		}

		System.out.println("===========================================================================");
		System.out.println("The final stacks after reshuffling are as follows:");
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < iniStacksData[0].length; j++) {

				System.out.print(iniStacksData[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("The total cost of heuristic 1 is: " + cost);
		System.out.println();

		return cost;

	}

	/*
	 * Iterate through the stacks from top to bottom
	 */
	public int heuristicTwo() {

		List<Integer> putUrgentStacks = new ArrayList<Integer>();
		int cost = 0;

		for (int p = 0; p < problematicStacksTtoB.size(); p++) {

			for (int k = 0; k < 3; k++) {

				// The operation of reshuffling.
				try {
					
					if (putUrgentStacks.size() > 0 && iniStacksDataForH2[k][problematicStacksTtoB.get(p)] == 1) {

						if (iniStacksDataForH2[1][putUrgentStacks.get(0)] == 0) {
							iniStacksDataForH2[1][putUrgentStacks.get(0)] = iniStacksDataForH2[k][problematicStacksTtoB.get(p)];
							iniStacksDataForH2[k][problematicStacksTtoB.get(p)] = 0;
							cost++;
						} else if (iniStacksDataForH2[0][putUrgentStacks.get(0)] == 0) {
							iniStacksDataForH2[0][putUrgentStacks.get(0)] = iniStacksDataForH2[k][problematicStacksTtoB.get(p)];
							iniStacksDataForH2[k][problematicStacksTtoB.get(p)] = 0;
							cost++;
						} else {
							putUrgentStacks.remove(0);
						}

					} else {

						if (iniStacksDataForH2[2][goodStacksForH2.get(0)] == 0) {
							iniStacksDataForH2[2][goodStacksForH2.get(0)] = iniStacksDataForH2[k][problematicStacksTtoB
									.get(p)];
							iniStacksDataForH2[k][problematicStacksTtoB.get(p)] = 0;
							cost++;
						} else if (iniStacksDataForH2[1][goodStacksForH2.get(0)] == 0) {
							iniStacksDataForH2[1][goodStacksForH2.get(0)] = iniStacksDataForH2[k][problematicStacksTtoB
									.get(p)];
							iniStacksDataForH2[k][problematicStacksTtoB.get(p)] = 0;
							cost++;
						} else if (iniStacksDataForH2[0][goodStacksForH2.get(0)] == 0) {
							iniStacksDataForH2[0][goodStacksForH2.get(0)] = iniStacksDataForH2[k][problematicStacksTtoB
									.get(p)];
							iniStacksDataForH2[k][problematicStacksTtoB.get(p)] = 0;
							cost++;
						}

					}
					
				} catch (Exception e) {
					System.out.println("Please try again.");
				}

				// The operation of checking after reshuffling to reorganize good stacks,
				// problematic stacks, and stacks that can only put urgent containers on them.
				try {

					if ((iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
							&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 0
							&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 0)
							|| (iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
									&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 0
									&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 2)
							|| (iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
									&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 2
									&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 2)) {
						goodStacksForH2.add(p);
						problematicStacksTtoB.remove(p);
						p = 0;
						k = -1;
					}

					if ((iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
							&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 0
							&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 1)
							|| (iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
									&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 1
									&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 1)
							|| (iniStacksDataForH2[0][problematicStacksTtoB.get(p)] == 0
									&& iniStacksDataForH2[1][problematicStacksTtoB.get(p)] == 1
									&& iniStacksDataForH2[2][problematicStacksTtoB.get(p)] == 2)) {

						putUrgentStacks.add(problematicStacksTtoB.get(p));
						problematicStacksTtoB.remove(p);
						p = 0;
						k = -1;
					}

					if (iniStacksDataForH2[0][goodStacksForH2.get(0)] != 0
							&& iniStacksDataForH2[1][goodStacksForH2.get(0)] != 0
							&& iniStacksDataForH2[2][goodStacksForH2.get(0)] != 0) {
						goodStacksForH2.remove(0);
					}

					if ((iniStacksDataForH2[2][goodStacksForH2.get(0)] == 1
							&& iniStacksDataForH2[1][goodStacksForH2.get(0)] == 0
							&& iniStacksDataForH2[0][goodStacksForH2.get(0)] == 0)
							|| (iniStacksDataForH2[2][goodStacksForH2.get(0)] == 2
									&& iniStacksDataForH2[1][goodStacksForH2.get(0)] == 1
									&& iniStacksDataForH2[0][goodStacksForH2.get(0)] == 0)) {

						putUrgentStacks.add(goodStacksForH2.get(0));
						goodStacksForH2.remove(0);
						p = 0;
						k = -1;
					}

				} catch (Exception e) {
					continue;
				}
				
				System.out.println("The column of putUrgentStacks after reshuffling before reorganization is: ");
				for (int i = 0; i < putUrgentStacks.size(); i++) {
					System.out.println(putUrgentStacks.get(i));
				}
				System.out.println();

				// Reorganizing the stacks that can only put urgent containers on them.
				if (putUrgentStacks.size() >= 2) {

					for (int u = 1; u < putUrgentStacks.size(); u++) {

						if (!(iniStacksDataForH2[2][putUrgentStacks.get(u)] == 2
								&& iniStacksDataForH2[1][putUrgentStacks.get(u)] == 1
								&& iniStacksDataForH2[2][putUrgentStacks.get(u)] == 0)) {

							for (int n = 1; n < 3; n++) {

								if (iniStacksDataForH2[n][putUrgentStacks.get(u)] == 1) {

									if (iniStacksDataForH2[1][putUrgentStacks.get(0)] == 0) {
										iniStacksDataForH2[1][putUrgentStacks
												.get(0)] = iniStacksDataForH2[n][putUrgentStacks.get(u)];
										iniStacksDataForH2[n][putUrgentStacks.get(u)] = 0;
										cost++;
									} else if (iniStacksDataForH2[0][putUrgentStacks.get(0)] == 0) {
										iniStacksDataForH2[0][putUrgentStacks
												.get(0)] = iniStacksDataForH2[n][putUrgentStacks.get(u)];
										iniStacksDataForH2[n][putUrgentStacks.get(u)] = 0;
										cost++;
									}

									if (iniStacksDataForH2[0][putUrgentStacks.get(0)] != 0) {
										putUrgentStacks.remove(u);
										if (putUrgentStacks.size() == 0) {
											break;
										}
										u = 0;
									}

									if (iniStacksDataForH2[2][putUrgentStacks.get(u)] == 0) {
										goodStacksForH2.add(u);
										putUrgentStacks.remove(u);
										if (putUrgentStacks.size() == 0) {
											break;
										}
									}

								}

							}

						}

					}

				}

				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < iniStacksDataForH2[0].length; j++) {

						System.out.print(iniStacksDataForH2[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("cost = " + cost);
				System.out.println();

				System.out.println("The column of putUrgentStacks after reorganization is: ");
				for (int i = 0; i < putUrgentStacks.size(); i++) {
					System.out.println(putUrgentStacks.get(i));
				}
				System.out.println();

				if (problematicStacksTtoB.size() == 0) {
					break;
				}

			}

		}

		System.out.println("===========================================================================");
		System.out.println("The final stacks after reshuffling are as follows:");
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < iniStacksDataForH2[0].length; j++) {

				System.out.print(iniStacksDataForH2[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("The total cost of heuristic 2 is: " + cost);
		System.out.println();

		return cost;

	}

	/*
	 * Get result of the reshuffling by two heuristics
	 */
	public String result(int numOfStacks) {

		iniStacksData = util.getTotalStacks(numOfStacks);
		iniStacksDataForH2 = util.getTotalStacksForH2(numOfStacks);
		problematicStacksBtoT = util.getProblematicStacksBtoT();
		problematicStacksTtoB = util.getProblematicStacksTtoB();
		goodStacks = util.getGoodStacks();
		goodStacksForH2 = util.getGoodStacksForH2();

		int cost2 = heuristicTwo();
		int cost1 = heuristicOne();
		String text = "The total cost of heuristic 1 is: " + cost1 + "\n" + "The total cost of heuristic 2 is: " + cost2;
		System.out.println(text);

		return text;

	}

	public static void main(String[] args) {

		Reshuffling r = new Reshuffling();
		r.result(8);

	}

}
