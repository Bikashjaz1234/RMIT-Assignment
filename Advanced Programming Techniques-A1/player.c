/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/
#include "player.h"
#include "game.h"
#include <ctype.h>

/**
 * These two functions initialise the player structure at the beginning of the 
 * game. In both cases, you should initialise the whole structure to known safe
 * values and then prompt the user for their name. In the init_first_player() 
 * function you should then select a color at random for this player using the 
 * rand() function. This token should be assigned to the first player's token and
 * also returned via the token pointer. In init_second_player() you should just
 * test the value of token and assign the opposite color to the second player.
 **/
BOOLEAN init_first_player(struct player* first, enum cell * token)
{
	/*init first player, first check the length, if more than 20, it is not a valid name,
	 *then delete the return, then set score = 0, and using rand() to set a random token.
	 */
	char firstPlayer[60];
	int colorNo;
	printf("Please enter the first player's name:");
	fgets(firstPlayer, 60, stdin);
	if (strlen(firstPlayer) > 21){
		printf("Your name is too long, Please less than 20 characters!\n");
		return init_first_player(first, token);
	}
	/*delete the return*/
	if (firstPlayer[strlen(firstPlayer) - 1] == '\n'){
		firstPlayer[strlen(firstPlayer) - 1] = '\0';
	}
	strcpy(first->name, firstPlayer);
	first->score = 0;
	colorNo = 0;
	/*set the random color*/
	srand ( time(NULL) );
	colorNo = rand() % 2;
	if (colorNo + 1 == 1){
		*token = RED;
		first->token = RED;
	}else{
		*token = BLUE;
		first->token = BLUE;
	}
	return TRUE;
}

BOOLEAN init_second_player(struct player * second, enum cell token)
{
	/*init second player, first check the length, if more than 20, it is not a valid name,
	 *then delete the return, then set score = 0, and set another token to second player.
	 */
	char secondPlayer[60];
	printf("Please enter the second player's name:");
	fgets(secondPlayer,60,stdin);
	if (strlen(secondPlayer) > 21){
		printf("Your name is too long, Please less than 20 characters!\n");
		return init_second_player(second, token);
	}
	if (secondPlayer[strlen(secondPlayer) - 1] == '\n'){
		secondPlayer[strlen(secondPlayer) - 1] = '\0';
	}
	strcpy(second->name, secondPlayer);
	second->score = 0;
	if (token == RED){
		second->token = BLUE;
	}else{
		second->token = RED;
	}
	return TRUE;
	
}

/**
 * prompts the user for a comma-separate pair of coordinates for a move in the
 * game. Each move, if valid, must capture at least one enemy piece by 
 * surrounding a sequence of one or more enemy pieces with two pieces of our 
 * own: one being the new piece to be placed and the other a piece already 
 * on the board. This function then validates that a valid move has been entered
 * calls the apply_move function to do the actual work of capturing pieces.
 **/
BOOLEAN make_move(struct player * human, game_board board)
{
	/* get user input, and check the input is valid or not. then split the input to 2 numbers, then check the number is valid or not,
	 * then pass the x and y to the apply_move() function, and check it. if it is true, this is a valid move, move it. otherwise, return false.
	 */
	unsigned y;
	unsigned x;
	enum cell player_token;
	char moveHuman[4];
	const char split[2] = ",";
	char* moveToken;
	BOOLEAN moveBoolean;
	printf("Current user is, %s\n", human->name);
	printf("Please input the position that you want to move (x,y). E.g: 1,2: ");
	/*if input "return", "ctrl-d", it will return main menu*/
	if(fgets(moveHuman,BUFF_LEN+1,stdin) == NULL){
		printf("The game is stoped, Return the main menu.....\n");
		return RTM;
	}else if(strcmp(moveHuman, "\n") == 0){
		printf("The game is stoped, Return the main menu.....\n");
		return RTM;
	}else{
		/*display user input*/
		printf("You input is: %s\n", moveHuman);
		/*split it, and set the first number to X*/
		moveToken = strtok(moveHuman, split);
		/*check the input, it is digit and the size*/
		if (isdigit(*moveToken) == 0 || strlen(moveToken) != 1){
			printf("It is a invalid input, Please follow the format, and the number from 1 to 8\n");
			return FALSE;
		}else{
			x = atoi(moveToken) - 1;
			moveToken = strtok(NULL, split);
		}
		if (isdigit(*moveToken) == 0){
			  printf("It is a invalid input, Please follow the format, and the number from 1 to 8\n");
				return FALSE;
			}else{
				y = atoi(moveToken) - 1;
			}
	}
	player_token = human->token;
	/*pass the X,Y to the apply_move, and check it is vaild or not*/
	moveBoolean = apply_move(board, y, x, player_token);
	if (moveBoolean == TRUE){
		return TRUE;
	}else{
		printf("It is a invalid move! Please try again!\n");
		return FALSE;
	}
}
