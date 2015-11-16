import java.util.*;

public class SumOfThrees {

	// The user will have access to only this public function
	public static void GameOfThrees(int number){
		
		// Basic validation
		if (number >= 1) {
			
			// This list will contain final results
			List<String> list = new ArrayList<String>();

			// Private function being called
			GameOfThrees(number, 0, new ArrayList<String>(), list);

			// To check how many "impossible" are there
			int counter = 0;

			for (int run = 0; run < list.size(); run++) {
				
				if (list.get(run).equals("Impossible\n"))
					++counter;
				//Print out the results
				else
					System.out.print(list.get(run));

			}
			
			//If the list only contains string "Impossible"
			if (counter == list.size())
				System.out.println("Impossible");
		}
		else
			System.out.println("Invalid input");
		
	}
	
	// This function generates the list.no=Value user passed,sum The sum of all the numbers that were added,
	//print= List that contains the sequence that when reaches 1 will be added in results List as a single string
	private static void GameOfThrees(int no, int sum, List<String> print,List <String> results) {

		// Prevents the no to become equal to 0
		if (no == 2) {
			
			// Temporary string
			String current = no + " ";
			
			// Current sequence 
			print.add(current + "1\n");
			
			// Recursive call since only 2+1 = 3 
			GameOfThrees((no + 1) / 3, sum + 1, print,results);

		}

		else {
			//Base case
			if (no == 1 && sum == 0) {

				//Temporary string
				String finalResult="+++++++++++++++++\n";
				
				//All the values in print List as single string stored in finalResult
				for (int run = 0; run < print.size(); run++) {

					finalResult=finalResult+print.get(run);

				}
				
				// Since sequence always leads to 1 so the last number should be 1 
				finalResult=finalResult+no+"\n";
	
				// The string gets added in results List
				results.add(finalResult);
				

			} else {

				// Another base case
				if (no == 1 && sum != 0) {
					
					// In case the sequence reaches 1 and sum is not 0
					results.add("Impossible\n");
					
				}

				else {

					// Current sequence string
					String current = no + " ";

					// In case no is divisible by 3
					if (no % 3 == 0) {

						print.add(current + 0 + "\n");
						GameOfThrees(no / 3, sum + 0, print,results);
						
					} else {

						// Deep copy of all the arguments that passed
						int secNo = new Integer(no);
						int secSum = new Integer(sum);
						List<String> secPrint = new ArrayList<String>(print);

						// In case no+1 is divisible by 3
						if ((no + 1) % 3 == 0) {

							//First option in the current sequence
							print.add(current + 1 + "\n");
							
							//recursive call for the first option
							GameOfThrees((no + 1) / 3, sum + 1, print,results);

							//If no+1 is divisible by 3 than no-2 is also divisible by 3 

							//Second option in the current sequence
							secPrint.add(current + "-2\n");
							
							//recursive call for the second option
							GameOfThrees((secNo - 2) / 3, secSum - 2, secPrint,results);

						}
						//Since all other possibilities have already been tested. Same logic as the above if statement
						else {

							print.add(current + "-1\n");
							GameOfThrees((no - 1) / 3, sum - 1, print,results);
							
							//If no-1 is divisible by 3 than no+2 is also divisible by 3 

							secPrint.add(current + "2\n");
							GameOfThrees((secNo + 2) / 3, secSum + 2, secPrint,results);

						}
					}

				}

			}

		}
	}




	public static void main(String[] args) {

		SumOfThrees.GameOfThrees(929);
		
	}

}
