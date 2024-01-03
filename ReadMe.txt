This is the description of snake and ladder game

Rules:
1. Minimum board size should be greater than 5
2. Minimum player count should be greater than 1
3. Every time a random number will be generated to move in board
4. Snakes and Ladders are called "Powers" in this code
5. Player should get max dice number to move their symbol in board

Flow:
-> Until player gets maximum number they can't start game
-> If user got max number he/she will get another chance to play
-> If user needs to move a cell which is already occupied by another player then also he/she will get a chance
-> Who ever first reaches the board last cell will be considered as winner

'~' -> indicates Snake in that particular cell
'#' -> indicated Ladder in that particular cell

Board looks like below: Sample board of 6*6 size

Player Symbol - 'X'

   0     1     2     3      4    5
0 |  |  |# |  |  |  |  |  |  |  |# |
1 |  |  |  |  | X|  |  |  |# |  |  |
2 |  |  |# |  |  |  |  |  |  |  |  |
3 |~ |  |  |  |  |  |~ |  |  |  |  |
4 |  |  |  |  |~ |  |  |  |  |  |# |
5 |  |  |# |  |  |  |  |  |  |  |  |   -> which player reaches to point 5,5 considered as winner



To Play in command line:
1. Start program form main function in App class.
2. Enter any key during your turn it will show a random number which you got
3. If you got max dice number your game will start
4. Go on and continue to play
5. Until you win :)


Happy Coding........... :)