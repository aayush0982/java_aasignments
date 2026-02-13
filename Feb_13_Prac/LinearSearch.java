package Feb_13_Prac;

public class LinearSearch {

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 25, 30, 45, 50};
        int target = 30;

        int result = linearSearch(arr, target);

        if (result != -1) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }
}

