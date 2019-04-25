import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;

public class Optimal {

	public static HashMap<Integer, Integer> map = new HashMap<>();
	
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
					faults++;
					frames.add(pages[i]);
					map.put(pages[i], nextIndex(pages, i));
					display(pages[i], "FAULT");
				} else {
					hits++;
					map.put(pages[i], nextIndex(pages, i));
					display(pages[i], "HIT  ");
				}
			} else {
				if (!frames.contains(pages[i])) {
					faults++;
					int farthest = -1;
					int val = -1;
					
					for (int f : frames) {
						if (map.get(f) > farthest) {
							farthest = map.get(f);
							val = f;
						}
					}
					
					frames.remove(val);
					map.remove(val);
					frames.add(pages[i]);
					map.put(pages[i], nextIndex(pages, i));
					display(pages[i], "FAULT");
					
				} else {
					hits++;
					map.put(pages[i], nextIndex(pages, i));
					display(pages[i], "HIT  ");
				}
			}
		}
		
		System.out.println("\nNumber of page faults: " + faults);
		System.out.println("Number of page hits: " + hits);
		System.out.println("Hit ratio: " + ((float) hits / n));
	}
	
	public static int nextIndex(int[] pages, int i) {
		int page = pages[i];

		for (int j = i+1; j < pages.length; j++) {
			if (pages[j] == page) {
				return j;
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	public static void display(int page, String status) {
		System.out.println("Page: " + page + " | " + status + " | Frames: " + map.keySet());
	}

}
