import java.util.Arrays; 

public class QuickSort  {

	/**
	* From quick sort method
	*
	*
	*/


	public static void sort(int[] a, int left, int right) {
		if (left < right) {
			int p = partition(a, left, right); 
			sort(a, left, p - 1); 
			sort(a, p + 1, right); 
		}
	}

	public static int partition(int[] a, int left, int right) {
		int pivot = left; 
		int i = left + 1; 
		int k = right; 
		while (i < k) {
			while (i < right && a[i] <= a[pivot]) {
				i++; 
			}
			while (k > left && a[pivot] < a[k]) {
				k--; 
			}	
			if (i < k) {
				swap(a, i, k); 
			}	
		}
		if (a[pivot] > a[k]) {
			swap(a, pivot, k);
		}
		return k; 
	}

	public static void swap(int[] a, int x, int y) {
		int temp = a[x]; 
		a[x] = a[y]; 
		a[y] = temp; 
	}
}