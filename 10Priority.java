import java.util.Scanner;

public class Priority {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of processes: ");
		int n = in.nextInt();
		
		int[] pid = new int[n];
		int[] at = new int[n];
		int[] bt = new int[n];
		int[] p = new int[n];
		int[] ct = new int[n];
		int[] tat = new int[n];
		int[] wt = new int[n];
		boolean[] f = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter arrival time for process " + (i+1) + ": ");
			at[i] = in.nextInt();
			System.out.print("Enter burst time for process " + (i+1) + ": ");
			bt[i] = in.nextInt();
			System.out.print("Enter priority value for process " + (i+1) + ": ");
			p[i] = in.nextInt();
			pid[i] = i + 1;
		}
		
		int completed = 0;
		int curr_time = 0;
		float total_tat = 0;
		float total_wt = 0;
		while (true) {
			if (completed == n)
				break;
				
			int max = -1;
			int c = n;
			for (int i = 0; i < n; i++) {
				if (at[i] <= curr_time && !f[i] && p[i] > max) {
					max = p[i];
					c = i;
				}
			}
			
			if (c == n) {
				curr_time++;
			} else {
				curr_time += bt[c];
				f[c] = true;
				ct[c] = curr_time;
				tat[c] = ct[c] - at[c];
				wt[c] = tat[c] - bt[c];
				total_tat += tat[c];
				total_wt += wt[c];
				completed++;
			}
		}
		
		System.out.println("\nPID\tAT\tBT\tPRIO\tCT\tTAT\tWT");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d\t%d", pid[i], at[i], bt[i], p[i], ct[i], tat[i], wt[i]));
		}
		
		System.out.println("\nAverage Turn Around Time: " + (total_tat / n));
		System.out.println("Average Waiting Time: " + (total_wt / n));
	}

}
