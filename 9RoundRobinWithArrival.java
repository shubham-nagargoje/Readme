import java.util.Scanner;

public class RoundRobinWithArrival {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of processes: ");
		int n = in.nextInt();
		
		int[] pid = new int[n];
		int[] at = new int[n];
		int[] bt = new int[n];
		int[] ct = new int[n];
		int[] wt = new int[n];
		int[] tat = new int[n];
		int[] rem = new int[n];
		boolean[] f = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter arrival time for process " + (i+1) + ": ");
			at[i] = in.nextInt();
			System.out.print("Enter burst time for process " + (i+1) + ": ");
			rem[i] = bt[i] = in.nextInt();
			pid[i] = i + 1;
		}
		
		System.out.print("Enter quantum value: ");
		int q = in.nextInt();
		
		// Sort w.r.t. arrival time
		for (int i = 0;  i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (at[j] > at[j+1]) {
					int temp = at[j];
					at[j] = at[j+1];
					at[j+1] = temp;
					
					temp = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = temp;
					rem[j] = bt[j];
					rem[j+1] = bt[j+1];
					
					temp = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = temp;
				}
			}
		}
		
		System.out.println();
		int completed = 0;
		int time = 0;
		float tot_wt = 0, tot_tat = 0;
		while (true) {
			if (completed == n)
				break;
			
			boolean idle = true;	
			for (int i = 0; i < n; i++) {
				if (!f[i] && at[i] <= time) {
					idle = false;
					if (rem[i] > q) {
						time += q;
						rem[i] -= q;
						System.out.println(String.format("%d - %d -> Process %d", time-q, time, pid[i]));
					} else {
						time += rem[i];
						System.out.println(String.format("%d - %d -> Process %d", time-rem[i], time, pid[i]));
						rem[i] = 0;
						completed++;
						ct[i] = time;
						f[i] = true;
						tat[i] = ct[i] - at[i];
						wt[i] = tat[i] - bt[i];
						tot_tat += tat[i];
						tot_wt += wt[i];
					}
				}
			}
			
			if (idle) {
				time++;
				System.out.println(String.format("%d - %d -> IDLE", time-1, time));
			}
		}
		
		System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]));
		}
		
		System.out.println("\nAverage Turn Around Time: " + (tot_tat / n));
		System.out.println("Average Waiting Time: " + (tot_wt / n));
	}	
	
}
