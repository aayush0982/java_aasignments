package Feb_13_Prac;

public class BinarySearch {

	public static boolean binarySearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] == key) {
				return true;
			} else if (arr[mid] < key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50 };
		int key = 30;

		System.out.println(binarySearch(arr, key));
	}
}
