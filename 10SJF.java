// Shortest Job First Preemptive

import java.util.*;

public class SJF {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of processes: ");
		int n = in.nextInt();
		int[] pid = new int[n];
		int[] at = new int[n];
		int[] bt = new int[n];
		int[] ct = new int[n];
		int[] tat = new int[n];
		int[] wt = new int[n];
		int[] k = new int[n]; // copy of bt
		boolean[] f = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter arrival time for process " + (i+1) + ": ");
			at[i] = in.nextInt();
			System.out.print("Enter burst time for process " + (i+1) + ": ");
			bt[i] = in.nextInt();
			pid[i] = i+1;
			k[i] = bt[i];
		}
		
		in.close();
		
		int completed = 0;
		int st = 0; // system time
		while (true) {
			if (completed == n)
				break;
			
			int min = 999999;
			int c = n;
			for (int i = 0; i < n; i++) {
				if ((at[i] <= st) && (!f[i]) && (bt[i] < min)) {
					min = bt[i];
					c = i;
				}
			}
			
			if (c == n) 
				st++;
			else {
				bt[c]--;
				st++;
				if (bt[c] == 0) {
					ct[c] = st;
					f[c] = true;
					completed++;
				}
			}
		}
		
		float total_wt = 0;
		float total_tat = 0;
		for (int i = 0; i < n; i++) {
			tat[i] = ct[i] - at[i];
			wt[i] = tat[i] - k[i];
			total_wt += wt[i];
			total_tat += tat[i];
		}
		
		System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d", pid[i], at[i], k[i], ct[i], tat[i], wt[i]));
		}
		
		System.out.println("\nAverage waiting time: " + (total_wt / n));
		System.out.println("Average turn around time: " + (total_tat / n));
	}	
	
}
