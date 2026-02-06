package java_assignment_arrays;

import java.util.*;

public class Code2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[][] square = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                square[i][j] = input.nextInt();
            }
        }

        int[][] rotated = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = square[i][j];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(rotated[i][j] + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
