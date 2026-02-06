package java_assignment_arrays;

import java.util.*;

public class Code4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int r1 = input.nextInt();
        int k1 = input.nextInt();
        int[][] mat1 = new int[r1][k1];
        for(int i = 0; i < r1; i++) {
            for(int j = 0; j < k1; j++) {
                mat1[i][j] = input.nextInt();
            }
        }

        int k2 = input.nextInt();
        int c2 = input.nextInt();
        int[][] mat2 = new int[k2][c2];
        for(int i = 0; i < k2; i++) {
            for(int j = 0; j < c2; j++) {
                mat2[i][j] = input.nextInt();
            }
        }

        int[][] prod = new int[r1][c2];
        for(int i = 0; i < r1; i++) {
            for(int j = 0; j < c2; j++) {
                for(int t = 0; t < k1; t++) {
                    prod[i][j] += mat1[i][t] * mat2[t][j];
                }
            }
        }

        for(int i = 0; i < r1; i++) {
            for(int j = 0; j < c2; j++) {
                System.out.print(prod[i][j] + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
