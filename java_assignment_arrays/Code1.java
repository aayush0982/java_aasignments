package java_assignment_arrays;

import java.util.*;

public class Code1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rows = input.nextInt();
        int cols = input.nextInt();
        int[][] matrix = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = input.nextInt();
            }
        }

        int[][] transposed = new int[cols][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        for(int i = 0; i < cols; i++) {
            for(int j = 0; j < rows; j++) {
                System.out.print(transposed[i][j] + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
