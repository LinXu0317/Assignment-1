import java.util.*; 

public class MySort {

	public static final int Array_Size = 50000; 
	public static final int RUN_SIZE = 10;

	/**
	* In main function, create the array and its copy
	* Set up the time measuring methods for the new sort and merge sort for a later comparison
	* @param null
	*
	*
	*/
	public static void main(String[] args) {
		int arr[] = new int[Array_Size];
		int arrCopy[] = Arrays.copyOf(arr, Array_Size);
		Random generator = new Random(); 
		for (int i = 0; i < Array_Size; i++) {
			arr[i] = generator.nextInt() * 1000; 
		}

		long start = System.currentTimeMillis();
		MySort(arr, RUN_SIZE); 
		long finish = System.currentTimeMillis() - start; 
		System.out.println("Run time for \"MySort\": " + finish); 

		start = System.currentTimeMillis(); 
		MergeSort.sort(arrCopy, 0, Array_Size - 1); 
		finish = System.currentTimeMillis() - start; 
		System.out.println("Run time for mergesort: " + finish); 
	}

	/**
	 * The new sorting method using a hybrid of quicksort and mergesort
	 * @param arr Array to sort
	 * @param runsize Sizes of run
	 * 
	 * @see #merge(int[], int[], int, int, int)
	 */
	public static void MySort(int[] arr, int run_size) {
		ArrayList<Run> run_collection = new ArrayList<Run>(); 

		//so basically we got to loop through and look for the runs
		int lastIndex = 0; 
		int currentIndex = 0; 
		for (int i = 1; i < arr.length; i++) {

			// First, make sure we check everything in a 'run'
			if (arr[i] < arr[i - 1]) { 

				//if the run we had is bigger than the given runsize
				if ((i - currentIndex) >= run_size) { 
					//we break it into runsized runs or smaller runs 
					while ((lastIndex + run_size) < currentIndex) { 
						QuickSort.sort(arr, lastIndex, lastIndex + run_size - 1); 
						run_collection.add(new Run(lastIndex, lastIndex + run_size - 1)); 
						lastIndex += run_size;
					}
					QuickSort.sort(arr, lastIndex, currentIndex - 1); 
					run_collection.add(new Run(lastIndex, currentIndex - 1)); 

					//finish what's left over outside the range
					run_collection.add(new Run(currentIndex, i - 1)); 

					
					lastIndex = i; 
				}
				currentIndex = i; 
			}
		}
		//finish soring the rest of the array
		while ((lastIndex + run_size) < arr.length) {
			QuickSort.sort(arr, lastIndex, lastIndex + run_size - 1); 
			run_collection.add(new Run(lastIndex, lastIndex + run_size - 1)); 
			lastIndex += run_size;
		}


		QuickSort.sort(arr, lastIndex, arr.length - 1); 
		run_collection.add(new Run(lastIndex, arr.length - 1)); 

		while(run_collection.size() > 1) {
			int[] arrCopy = Arrays.copyOf(arr, arr.length); 
			for (int i = 0; i < run_collection.size() - 1; i++) {

				// merge the runs
				merge(arrCopy, arr, run_collection.get(i), run_collection.get(i + 1)); 
				
				run_collection.set(i, new Run(run_collection.get(i).getFirst(), run_collection.get(i + 1).getLast()));
				//after merge two runs into one, get rid off one
				run_collection.remove(i + 1); 
			}
		}
	}

	/**
	 *checks if the final array is sorted
	 */
	protected boolean isSorted(int [] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] > arr[i+1])
				return false;
		}
		return true;
	}

	/**
	 * Merges two runs based on the first and last index value stored in them
	 * Rearrange the actual values stored in those indices after merge(sorted) 
	 * @param a array
	 * @param start starting index of array
	 * @param end last index of array
	 * @param targetArray sorted array
	 */
	public static void merge(int[] a, int[] targetArray, Run run_1, Run run_2) {
		int target = run_1.getFirst(); 
		int left = run_1.getFirst();
		int right = run_2.getFirst(); 

		while (left <= run_1.getLast() && right <= run_2.getLast()) {
			if (a[left] <= a[right]) {
				targetArray[target++] = a[left++]; 
			}
			else {
				targetArray[target++] = a[right++]; 
			}
		}

		while (left <= run_1.getLast()) {
			targetArray[target++] = a[left++];
		}

		while (right <= run_2.getLast()) {
			targetArray[target++] = a[right++];
		}

	}	
}
