import java.util.ArrayList;
import java.util.Scanner;

public class Ring {
	
//	public static int election(Process[] proc) {
//		int highest = -1;
//		for (int i = 0;i < proc.length;i++) {
//			if (proc[i].active) 
//				if (highest < proc[i].index)
//					highest = i;
//		}
//		return highest;
//	}
	
	public static int election(Process[] proc, int startID) {
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		int highest = -Integer.MAX_VALUE;
		int highestIndex = 0;
		int i = startID;
		
		while(!path.contains(i)) {
				if (proc[i].active) {
					if (proc[i].index > highest) {
						highestIndex = i;
						highest = proc[i].index;
					}	
					path.add(i);
				}
				i = (i + 1) % proc.length;
		}
		
		return highestIndex
				;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes");
		int noofproc = sc.nextInt();
		Process proc[] = new Process[noofproc];
		
		for (int i = 0;i < proc.length;i++)
			proc[i] = new Process();
		
		for (int i = 0;i < noofproc;i++) {
			
			proc[i].id = i;
			System.out.println("Enter the id of the process");
			proc[i].index = sc.nextInt();
			proc[i].active = true;
			proc[i].f = 0;
		}
		
		// Sorting by index
		int i, j;
		int temp;
		for (i = 0; i < noofproc - 1; i++) {
			for (j = 0; j < noofproc - 1; j++) {
				if (proc[j].index > proc[j + 1].index) {
					temp = proc[j].index;
					proc[j].index = proc[j + 1].index;
					proc[j + 1].index = temp;
				}
			}
		}
		
		for (i = 0; i < noofproc; i++) {
			System.out.println("  [" + i + "]" + " " + proc[i].index);
		}
		
		int ch;
		while(true) {
			System.out.println("1. Crash a process\n2. Recover a process\n3. Hold Election\n4. Bye Bye?");
			ch = sc.nextInt();
			
			switch(ch) {
				case 1: {
					System.out.println("Enter Process number");
					proc[sc.nextInt()].active = false;
					for ( i = 0;i < proc.length;i++) {
						if (proc[i].active) {
							System.out.println("Process " + i + " is active");
						} else {
							System.out.println("Process " + i + " is dead");
						}
					}
					break;
				}
				
				case 2: {
					System.out.println("Enter Process number");
					proc[sc.nextInt()].active = true;
					break;
				}
				
				case 3: {
					System.out.println("Holding the Election");
					System.out.println("Enter the Start ID");
					System.out.println("Process " + Ring.election(proc, sc.nextInt()) + " is the coordinator");
					break;
				}
				
				case 4: {
					System.exit(0);
				}
			}
		}
		
		
	}
	
}
class Process {
	int  index, id, f;
	boolean active;
}
