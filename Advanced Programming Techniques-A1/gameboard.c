/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/
#include "gameboard.h"
#include "player.h"

/**
 * initialise the game board to be consistent with the screenshot provided
 * in your assignment specification. 
 *
 * All squares should be initialised to be blank other than the four
 * squares in the middle of the board. In that case they are initialised to 
 * appropriate colored tokens
 **/
void init_game_board(game_board board)
{
	/*using two for loops to set game_board all are blank, and then, set 4 position is red and blue*/
	int i;
	int j;
	for (j = 0; j < BOARD_HEIGHT; j++){
		for (i = 0; i < BOARD_WIDTH; i++){
			board[j][i] = BLANK;
		}
	}
  board[3][3] = RED;
	board[3][4] = BLUE;
	board[4][3] = BLUE;
	board[4][4] = RED;
}

/**
 * display the board as specified in the assignment specification. You should 
 * make every effort to create a board consistent with the screenshots in the 
 * assignment specification. 
 **/
void display_board(game_board board, struct player * first,
                   struct player * second)
{
	int i;
	int j;
	int k;
	/*Print the player detail*/
	printf("====================================================================\n");
	printf("Player One's Detail\n");
	printf("--------------------------------------------------------------------\n");
	printf("Name: %s", first->name);
	for (k = 0; k <= (20 - strlen(first->name)); k++){
		printf(" ");
	}
	printf("Score: %d\t", first->score);
	printf("Token Clolr: ");
	if (first->token == BLUE){
		printf("%s", COLOR_BLUE);
		printf(" %c \n", 'O');
		printf("%s", COLOR_RESET);
	}else{
		printf("%s", COLOR_RED);
    printf(" %c \n", 'O');                    
    printf("%s", COLOR_RESET);
	}
    printf("--------------------------------------------------------------------\n");
    printf("Name: %s", second->name);
    for (k = 0; k <= (20 - strlen(second->name)); k++){
    	printf(" ");
    }
    printf("Score: %d\t", second->score);
    printf("Token Clolr: ");
        if (second->token == RED){
                printf("%s", COLOR_RED);
                printf(" %c \n", 'O');
                printf("%s", COLOR_RESET);
        }else{
                printf("%s", COLOR_BLUE);
                printf(" %c \n", 'O');
                printf("%s", COLOR_RESET);
        }
        printf("--------------------------------------------------------------------\n");
  /*print game_board */
  /* first line is not in the loop*/
	printf("%4d%4d%4d%4d%4d%4d%4d%4d\n", 1, 2, 3, 4, 5, 6, 7, 8);
	printf("==================================\n");
	/* using for loop to print the board, and check the token, if it is a red token, print red token*/
	for (i = 0; i < BOARD_HEIGHT; i++){
		printf("%1d|", i+1);
		for (j = 0; j < BOARD_WIDTH; j++){
			switch(board[j][i]){
				case BLANK:
					printf("   |");
					break;
				case RED:
					printf("%s", COLOR_RED);
					printf(" %c ", 'O');
					printf("%s", COLOR_RESET);
					printf("|");
					break;
				case BLUE:
					printf("%s", COLOR_BLUE);
					printf(" %c ", 'O');
					printf("%s", COLOR_RESET);
					printf("|");
					break;
				default:
					printf("   |");
					break;
				}
			}
			printf("\n");
			printf("----------------------------------\n");
			}
}

