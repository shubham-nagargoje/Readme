import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class FIFO {

	public static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter number of pages: ");
		int n = in.nextInt();
		int[] pages = new int[n];
		
		System.out.print("Enter reference string: ");
		for (int i = 0; i < n; i++) {
			pages[i] = in.nextInt();
		}
		
		System.out.print("Enter number of frames: ");
		int capacity = in.nextInt();
		
		in.close();
		System.out.println();
		
		HashSet<Integer> frames = new HashSet<>(capacity);
		
		int faults = 0, hits = 0;
		for (int i = 0; i < n; i++) {
			if (frames.size() < capacity) {
				if (!frames.contains(pages[i])) {
					frames.add(pages[i]);
					queue.add(pages[i]);
					faults++;
					display(pages[i], "FAULT");
				} else {
					hits++;
					display(pages[i], "HIT  ");
				}
			} else {
				if (!frames.contains(pages[i])) {
					int val = queue.poll();
					frames.remove(val);
					
					frames.add(pages[i]);
					queue.add(pages[i]);
					faults++;
					display(pages[i], "FAULT");
				} else {
					display(pages[i], "HIT  ");
					hits++;
				}
			}
		}
		
		System.out.println("\nNumber of page faults: " + faults);
		System.out.println("Number of page hits: " + hits);
		System.out.println("Hit ratio: " + ((float) hits / n));
	}
	
	public static void display(int page, String status) {
		System.out.print("Page: " + page + ", " + status + ", Frames: ");
		for (int i : queue) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
