package java_assignment_arrays;

import java.util.*;

public class Code3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int r = input.nextInt();
        int c = input.nextInt();
        int[][] matrix = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                matrix[i][j] = input.nextInt();
            }
        }

        String direction = input.next();

        if(direction.equals("H")) { 
            for(int i = 0; i < r / 2; i++) {
                int[] tmp = matrix[i];
                matrix[i] = matrix[r - 1 - i];
                matrix[r - 1 - i] = tmp;
            }
        } else if(direction.equals("V")) { 
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c / 2; j++) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][c - 1 - j];
                    matrix[i][c - 1 - j] = tmp;
                }
            }
        }

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
