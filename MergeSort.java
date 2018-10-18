
import java.util.Arrays; 

public class MergeSort  {

	/**
	* from merge sort method
	*
	*
	*/
	

	public static void sort(int[] a, int left, int right) {
		if (left >= right) {
			return; 
		}
		int mid = (left + right) / 2; 
		sort(a, left, mid); 
		sort(a, mid + 1, right); 
		int[] leftarr = Arrays.copyOfRange(a, left, mid + 1);
		int[] rightArr = Arrays.copyOfRange(a, mid + 1, right + 1); 
		merge(a, left, leftarr, rightArr); 
	}
	public static void merge(int[] targetArray, int target, int[] leftarr, int[] rightArr) {
		int left = 0, right = 0; 

		while (left < leftarr.length && right < rightArr.length) {
			if (leftarr[left] < rightArr[right]) {
				targetArray[target++] = leftarr[left++]; 
			}
			else {
				targetArray[target++] = rightArr[right++]; 
			}
		}

		while (left < leftarr.length) {
			targetArray[target++] = leftarr[left++];
		}

		while (right < rightArr.length) {
			targetArray[target++] = rightArr[right++];
		}
	}
}