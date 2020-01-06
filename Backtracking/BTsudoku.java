import java.util.*;

import javax.swing.plaf.synth.SynthToggleButtonUI;


class BTsudoku {

    public static boolean solveSudoku(int[][] board, int n) {
        for (int row = 0; row < n; row++) {
            for (int colum = 0; colum < n; colum++) {
                if (board[row][colum] == 0) {
                    for (int number = 1; number <= n; number++) {
                        if (backtraking(board, row, colum, number)) {
                            board[row][colum] = number;
                            if (solveSudoku(board, n)) {
                                return true;
                            } else {
                                board[row][colum] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean backtraking(int[][] board, int row, int col, int num) {
        Boolean flag = !(isSafeBox(row, col, num, board) || isSafeColRow(col, num, row, board));
        return flag;
    }

    private static boolean isSafeColRow(int col, int num, int row, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num || board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafeBox(int row, int col, int num, int[][] board) {
        int r = row - row % (int) Math.sqrt(board.length);
        int c = col - col % (int) Math.sqrt(board.length);

        for (int j = r; j < r + (int) Math.sqrt(board.length); j++) {
            for (int i = c; i < c + (int) Math.sqrt(board.length); i++) {
                if (board[j][i] == num) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void print(int[][] board, int n) {
        System.out.print("  TIME FOR MAGIC  \n\n");
        for (int i = 0; i < n; i++) {
            if (i % (int) Math.sqrt(n) == 0 && i != 0) {
                System.out.println();
            }
            for (int j = 0; j < n; j++) {
                if (j % (int) Math.sqrt(n) == 0 && j != 0) {
                    System.out.print("   ");
                }
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
       

//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter size of bourd 4X4(4) or 9X9(9) ");
//        int N = sc.nextInt();
//         int[][] board = new int[N][N] ; 
//         System.out.print("Enter the bourd and zero for empty \n");

//      for (int x = 0; x < N; x++){
//       for (int j = 0; j < N; j++){
//          board[x][j] = sc.nextInt();
//      }
//     }
//     int startTime =(int) System.currentTimeMillis();
//         if (solveSudoku(board, N))
//             print(board, N);
//         else
//             System.out.println("\nNo solution");
//     int endTime = (int)System.currentTimeMillis();
//     int total = (int) endTime - startTime;
// System.out.println("\ntotal time (Millis):\t"+total);

Scanner sc = new Scanner(System.in);
System.out.print("enter number ");
int a = sc.nextInt();
int min = a;

for (int j = 1 ; j<3 ; j++){
System.out.print("enter number ");
int b = sc.nextInt();
if (b < min)
min = b;
}
System.out.print(" \n mimimum numbrt "+min);
    }
}
