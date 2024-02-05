# Laboration 3

## Contributors
___
__Name:__ Alexander Berglund

__ID:__ albe2003
___

__Name:__ Anton Byström 

__ID:__ anby2001
___
## Purpose
The purpose of this program is to make a sudoku game with randomly generated 
boards. The sudoku board consists of a 9x9 grid and will have 4 different
difficulties, where the user can choose what difficulty they what to play.

#### Gameplay
When launching the game by running the Project a gui is displayed to the user and also the solution for
the first display board is given in the terminal. The user can the select a cell
and enter numbers between 1 and 9. When the user thinks they have solved the
game they can press the check game button and if the game is solved a new
window is displayed with the text congratulation!. They can then start a new game
by pressing the new game button. They can also select a new difficulty from the
difficulty menu and a new board is displayed.

## Procedures

### SudokuHelper
This is a Singleton class with containing some utility methods that are used 
by mainly the SudokuSolver and SudokuSolution classes. As programmers, we 
obviously strive to develop loosely coupled programs. Therefor, have a global 
instance containing methods might not seem like the most optimal solution. 
However, in our opinion, its justified to have the SudokuHelper as a Singleton 
since it contains only basic methods such as checking if an element is contained 
inside a specific array.

### SudokuSolution
This class creates a valid SudokuSolution from scratch. It does so by initializing 
an empty 9x9 2D array. It then loops through the array, filling it with random 
numbers until the complete grid is filled. The complicated part when developing 
the algorithm was figuring out how to maintain the validness of the grid while 
filling it with numbers. The solution that we came up with was a backtracking 
type of recursive solution, where the algorithm checks if the randomized number 
can be put into the grid without creating conflicts. If it does not, great, if it 
does, the algorithm simply sets the number back to 0 and tries a different solution 
until the whole grid is complete.

### SudokuSolver
This class is quite similar to the SudokuSolution class. It loops through the grid 
in a similar fashion, but instead of filling the grid with numbers, it removes the 
numbers one by one while still keeping tack of the amount of available solutions to 
the grid. It does so by removing a random number from the grid, then the algorithm 
verifies that the grid only has one available solution to it. If it has, it continues 
the procedure, if it does not, the place the removed number back to the grid (we 
take a backup of the removed number in this case). Through this class, we are also 
to set an upper bound to how many numbers are to be removed from the grid. This is 
done by simply stating the upper bound, and then, if a number have been successfully 
removed, we decrease that number. The removal of numbers continues either until the
bound is set to 0, or if the algorithm can't remove any more numbers due to there 
being more than one solution available to thr grid. The upper bound is determined by 
the caller, which sends a difficulty to the algorithm represented as an enum. In 
our program, stating an easy difficulty removes a maximum of 40 numbers from the 
grid, a medium difficulty removes a maximum of 45 numbers from the grid, a hard 
difficulty removes a maximum of 50 numbers from the grid, and finally an expert 
difficultly removes 55 numbers at max. The development of this algorithm was 
quite straight forward, since we'd already developed the majority of the algorithm 
in the SudokuSolver class.

### Board
The board extends JPanel and contains the 9x9 grid of Cells. When the Board 
is created it starts by creating a 2D array and then stores a new Cell in 
each of the positions of the array. ShowBoard is responsible for storing 
the correct number of the grid in each cell and then generating an 
incompleteGrid with the help of sudokuSolver.generateIncompleteGrid and use 
that in setCellStatus to store the current number and boolean if the cell is 
a given number of each cell. The solved method is used when the user presses 
the check game button and by checking if any of the cells is still in GUESS 
or WRONG_GUESS state the board is not solved. If the board doesn't contain 
any GUESS or WRONG_GUESS states a messageDialog window is displayed 
congratulating the user for completing the game.

There was a bit of a problem with showBoard and setCellStatus where 
setCellStatus first took two input values the incompleteGrid and the 
completeGrid from showBoard. But when using the completeGrid in setCellStatus 
the completeGrid was the same as the incompleteGrid. To solve this the 
completeGrid stores all the correct numbers in each cell before generating 
the incompleteGrid.

### Button
The button class extends JButton when created it takes a name and the Board 
as input. It sets the name, size and adds an action Listener to the button 
that is then used to identify which button was pressed and either generate 
a new board or check if the board is solved.

### Cell
The Cell class extends JTextField and is used to type / display the numbers. 
On creation of a cell it adds a keyListener that limits the input options to 
numbers 1 - 9 and adds an input limiter so only 1 number can be entered. If 
the input is between 1 and 9 it uses clearText to clear the textField and 
then uses setCellStatus to store the entered number and sets the status of 
the cell. SetNumber is used when an incomplete board is generated to set the 
given cells. The Paint method is used to check different cellStatuses and 
format the background and set the textFields to not be able to be edited if 
the cell is given at the start. SetCorrectNumber is used to store the cell's 
correct number.

There was a problem with the key listener, in the else statement where you 
were able to override the given values is the grid:
```
else {
  clearText();
  setCellStatus(c);
}
```
This was fixed by adding a method that returns true if the current cell isEditable:
```
else {
  if (textFieldIsEditable()) {
    clearText();
    setCellStatus(c);
  }
}
```

### CellStatus
Enum class that represents the different states a cell can have.

### SudokuFrame
SudokuFrame creates the GUI and adds the Board and menu options such as 
buttons and difficulty menu to it.

### Difficulty
Difficulty is an enum class that represents the different difficulty levels for a game.

### DifficultyMenu
The difficultyMenu extends JComboBox and is used by the user to select what difficulty
they want to play.

### TDD
The program have been developed with test-driven development where test cases have
been implemented with the help of the software requirements. After a test case is
created the software is created.

### Kanban
The development process have been using Trello which is a webservice that helps
create a kanban board. The kanban board consists of 5 lists Manifest, Backlog,
In Progress, Review and Completed. Each trello card is linked to a separate bitbucket
branch that makes it easy to see the implementations performed.

The manifest contains the metadata about the project and details about documentation
and how work should be conducted.

Backlog contains all software/test features or other implementations that is to be
chosen by a developer to complete.

In Progress is where a developer have chosen a card and are in the progress of developing
it.

Review holds the cards that are going to be review by the other developer to make sure
the implementation is completed and all features and tests are successful. When the
reviewer have completed the assessment, and it conforms to all conventions and guidelines
the card is moved to the completed list.

Completed holds all cards that have been reviewed and are completed. This when the
development branch of the card is merged into the complete program.

## Manual testing
### GUI creation of 9x9 grid
Validation of the gui for displaying the sudoku board. 
- [X] When launching the program a java swing gui is launched.
- [X] The gui consists of a 9x9 grid consisting of text fields.
- [X] Each textField/cell only accept 1 number for input.
- [X] Each textField/cell only accept numbers between 1 and 9.

### GUI display board
  Validation of displaying the sudoku board with numbers. Uses
  sudokuSolver.generateIncompleteGrid() to remove numbers and then
  displays numbers that are not 0.
- [X] Display given numbers.
- [X] Given cells can't be edited.
- [X] Given numbers can't be edited.
- [X] Empty cells can be edited.
- [X] Empty cells accept numbers between 1-9.

### GUI new game button
Validate a new sudoku board is created at the press of new game button.
- [X] New game button generates a new grid.
- [X] Displayed grid is different from previous.
- [X] Previously entered numbers are removed.
- [x] Background color is returned to default.

### GUI check game
Validate that sudoku board is solved and if not display color depending
on if entered number is correct or not.
- [X] Check game button checks if game is solved.
- [X] If entered number is correct cell color changes to green.
- [X] If entered number is wrong cell color changes to red.
- [X] If bored is solved popup window is displayed with solved text.

### GUI change difficulty
Validate that a new board is generated upon changing difficulty level.
- [X] Changing the difficulty level in the dropdown menu should result in a new board.
- [X] The amount of numbers left in the newly generated board should reflect the difficulty level.

## Discussion
When launching the game a sudoku board is displayed at easy difficulty. The user
can then change the difficulty by using the Difficulty menu. If the user presses
the "Check Game" button the background of cells where the user have entered numbers
will turn green if the number is correct and red if the number is wrong. If all
empty cells are entered with the correct number a popup window will be displayed
congratulating the user on completing the game. The "New Game" button can be used
to restart / generate a new board.

### TDD
The creation of test cases was a bit difficult to create as you need to know
the solution of the classes and methods before implementing the solution. But, 
although it was a bit difficult, it was a great learning experience trying out 
Test Driven Development. It makes one think about the code in a different way. 
When developing previous projects (not utilizing TDD), we used to create only 
a couple of classes that contained loads of private methods with extensive 
functionality. But, with TDD, that's not very optimal since a lot of the code 
becomes untestable. Therefor, in this project, we have got to practice on 
creating more classes, where each class is only focused on pretty much one thing. 
Therefor, we'd argue that Test Driven Development not only increases the percentage 
amount of code that is being tested, but also helps to make the code loosely coupled 
and with high cohesion. This leads to the code being a lot cleaner, and new functions 
and features can be more easily added. In other words, it makes the code better!

### Kanban
The use of kanban helped se what progress was left for development and what the other
student was working on or the state of the reviews. This helped gain experience in how
to create cards and how to use a kanban board to display the state of implementation
that is performed. It also makes it easy to display if an implementation failed review
as it is moved back into the backlog and has a ISSUE label that describes that something
needs to be fixed with it before it can be completed and implemented in the complete program.

### Bitbucket
In this project we got a good learning experience about pull requests and how to review
other peoples work. We also learned about working with feature branches where each implementation
towards the finished product was conducted in a separate branch. When the implementation
of that branch was completed a pull request was made so the other person had to review the
work and if it passed they merge the branch. The pull request specified what branch
the feature branch was going to merge into.

In this project we also decided to use a dev branch for completing our first implementation
of the sudoku game. This was to think of a more realistic scenario where we got a separate
branch for implementing new solutions and the master branch is the live version of the
program. In the live branch we might not want to push every single small increment that is
performed from the feature branches. So the dev branch was used as a staging branch
to check that all features for the next part is completed and conforms to stated parameters
before being pushed to the live branch.