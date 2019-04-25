import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class LRU {

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
		int cap = in.nextInt();
		
		in.close();
		System.out.println();
		
		HashSet<Integer> frames = new HashSet<>(cap);		
		
		int faults = 0, hits = 0;
		for (int i = 0; i < n; i++) {
			if (frames.size() < cap) {
				if (!frames.contains(pages[i])) {
					frames.add(pages[i]);
					map.put(pages[i], i);
					faults++;
					display(pages[i], "FAULT");
				} else {
					hits++;
					map.put(pages[i], i);
					display(pages[i], "HIT  ");
				}
			} else {
				if (!frames.contains(pages[i])) {
					int lru = Integer.MAX_VALUE;
					int val = Integer.MIN_VALUE;
					
					for (int f : frames) {
						if (map.get(f) < lru) {
							lru = map.get(f);
							val = f;
						}
					}
					
					frames.remove(val);
					frames.add(pages[i]);
					map.remove(val);
					faults++;
					map.put(pages[i], i);
					display(pages[i], "FAULT");
					
				} else {
					hits++;
					map.put(pages[i], i);
					display(pages[i], "HIT  ");
				}
			}
		}
		
		System.out.println("\nNumber of page faults: " + faults);
		System.out.println("Number of page hits: " + hits);
		System.out.println("Hit ratio: " + ((float) hits / n));
	}
	
	public static void display(int page, String status) {
		System.out.println("Page: " + page + ", " + status + ", Frames: " + map.keySet());
	}

}
