

 **(a) Introduction** <br />
Sudoku is the widely known Japanese game which is basically presented by a matrix of 9 by 9 squares
where each row, column and block of 3 by 3 shouldn't have repeated numbers. Our project focuses on
solving this game by CSP using backtracking technique and genetic algorithm. The backtracking
algorithm works by assign number for empty place than check if the assigned number if valid or not. The
genetic algorithm works by a randomly generated population than calculate the fitness of each population
the lowest value will go to selection, crossover, and mutation.
  
 # **(b) Uninformed search algorithm (CSP-backtracking)**<br />
i. Problem formulation:<br />
    State: a Sudoku block and the zeros of each block.
    Initial: given board.
    Action : assagin number from 1 to 9.
    Goul : board with no repeated in row, box and column and no zeros.
ii. The evaluation function:<br />

```ruby
    public static boolean backtraking(int[][] board, int row, int col, int num) {
        Boolean flag = !(isSafeBox(row, col, num, board) || isSafeColRow(col, num, row, board));
        return flag;
    }
```
Check if the assigned number in the row, column and box.
iii. constreant :<br />
Variabl e : ["1", "2", "3", "4", "5", "6", "7", "8", "9"]<br />
Domains: [row, column, box]<br />
Box = Variable<br />
Rows = Variable<br />
Cols = Variable<br />
<br />The constraint :<br />
● The starting sudoku numbers must be in those same places in the final solution.<br />
● It must not be repeated with the same value in row, box and column<br />
{ variable in row != same column} , {variable in box != same box}. <br />


# (c) Local search: genetic algorithm (Genetic algorithms) <br />
i. Problem formulation: <br />
In this problem we designed a genetic algorithm to solve a smaller scale of the popular
game Sudoku. The actual game consists of nine rows and nine columns while in our version and
for the sake of simplicity we developed an algorithm to solve a 4X4 sudoku. Cells in the
two-dimensional array can have a random number from one up to four. The goal state is when no
repeated elements in the same column, the same row and box of 2x2.
State representation: a Sudoku block and the fitness of each block.
Initial state: a population of randomly generated 4 by 4 sudoku blocks.
Action: cross over method is applied to the best fitness of the population.
Goal state: the final state when no repeated elements in the same column, the same row and each
block.
 <br /> Fitness Function: <br />
The fitness function was calculated as the average of the number of repeated items in columns
added to repeated items in rows divided by the total number of rows. both rows and columns
have a specified method to calculate the repeated items, That is then saved in an array. To
calculate the average the values are summed together and divided by four as its the number of
elements.
 <br /> ii. Design: <br />
The program was designed into two classes the main class (GASudoku) which initiates an object
of the type class (sudokublock) and starts applying the algorithm. The flow of actions in the
algorithm is as follows. First a population is generated randomly, and fitness is calculated for
each board of the object (sudokublock). The next population is taken as the best fitness of the
starting population. A crossing method of each pair of the second generation forms the third
generation. Third generation by far has the best fitness means its closer to the solution. A
mutation method is applied into the remaining population, the resulting board is checked for the
2x2 square validation.
