// First Come First Serve Non-preemptive

import java.util.Scanner;

public class FCFS {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of processes: ");
		int n = in.nextInt();
		int[] pid = new int[n]; // process id
		int[] at = new int[n];  // arrival time
		int[] bt = new int[n];  // burst time
		int[] ct = new int[n];  // completion time
		int[] tat = new int[n]; // turn around time
		int[] wt = new int[n];  // waiting time
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter arrival time for process " + (i+1) + ": ");
			at[i] = in.nextInt();
			System.out.print("Enter burst time for process " + (i+1) + ": ");
			bt[i] = in.nextInt();
			pid[i] = i+1;
		}
		
		in.close();
		
		// Sorting in ascending order w.r.t arrival time
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				int temp;
				if (at[j] > at[j+1]) {
					temp = at[j];
					at[j] = at[j+1];
					at[j+1] = temp;
					
					temp = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = temp;
					
					temp = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = temp;
				}
			}
		}
		
		// Finding completion times
		float total_wt = 0;
		float total_tat = 0;
		
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				ct[i] = at[i] + bt[i];
			} else {
				ct[i] = Math.max(at[i], ct[i-1]) + bt[i];
			}
			tat[i] = ct[i] - at[i];
			wt[i] = tat[i] - bt[i];
			total_wt += wt[i];
			total_tat += tat[i];
		}
		
		System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]));
		}
		
		System.out.println("\nAverage waiting time: " + (total_wt / n));
		System.out.println("Average turn around time: " + (total_tat / n));
	}
	
}
