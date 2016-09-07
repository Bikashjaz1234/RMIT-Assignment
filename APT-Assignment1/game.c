/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/
#include "game.h"

/**
 * The heart of the game itself. You should do ALL initialisation required 
 * for the game in here or call function required for that purpose. For example
 * both players should be initialised from here, you should seed the random 
 * number generator, that kind of thing. 
 *
 * Next, you should set the initial player - the initial player is the player 
 * whose token was set by initialisation to RED. 
 *
 * Next, you will need to manage the game loop in here. In each loop of the 
 * game, you should display the game board, including player scores 
 * display whose turn it is, etc in the format specified in the assignment
 * specification. Finally at the end of each turn you will calculate the score 
 * for each player you will calculate their score and store it and then you 
 * will need to swap the current player and other player 
 * using the swap_player() function. 
 * 
 * A game will end when either player presses enter or ctrl-d on a newline. 
 * 
 * When you detect a request to end the game, you should do a final calculation
 * of the scores for the two players and return the player with the highest
 * score.
 **/
struct player * play_game(struct player * first, struct player * second)
{

	game_board board;
	enum cell token;
	int playGameFlat;
	BOOLEAN makeMoveBoolean;
	struct player * current, *other, *winner;
	current = first;
	other = second;
	init_game_board(board);
	init_first_player(current, &token);
	init_second_player(other, token);
	/*Using IF to switch, and make sure that Red token is the first*/
	if (first->token == BLUE){
		swap_players(&first, &second);
	}
	display_board(board, first, second);
	/*Set a Flag, and using While loop to continue play the game, until user quit game*/
	playGameFlat = 1;
	while (playGameFlat > 0){
		makeMoveBoolean = make_move(first, board);
		if (makeMoveBoolean == TRUE){
			swap_players(&first, &second);
			first->score = game_score(board, first->token);
			second->score = game_score(board, second->token);
			display_board(board, first, second);
		}else if (makeMoveBoolean == RTM){
			if(first->score > second->score){
				winner = first;
			}else if (second->score > first->score){
				winner = second;
			}else{
				winner = NULL;
			}
			return winner;
		}else{
			display_board(board, first, second);
		}
	}
	return 0;
}

/**
 * does the work of applying the move requested by the user to the game board.
 * It iterates over all the directions from the coordinate specified to see
 * whether there are any pieces that can be captured. If there are no pieces
 * that can be captured in any direction, it is an invalid move.
 **/
BOOLEAN apply_move(game_board board, unsigned y, unsigned x,
                   enum cell player_token)
{
	/*enum direction dir;*/
	/*Set multiple variables, and check each position to makesure it is valid input*/
	unsigned captured_pieces;
	int row;
	int moveX;
	int moveY;
	int i;
	int south;
	int north;
	int east;
	int west;
	int westNorth;
	int eastSouth;
	int eastNorth;
	int westSouth;
	captured_pieces = 0;
	south = 1;
	north = 1;
	east = 1;
	west = 1;
	westNorth = 1;
	eastSouth = 1;
	eastNorth = 1;
	westSouth = 1;
	
	/*Begin Code*/
	/*First check the input value, if more than 8 or less than 0, it is a invalid input*/
	if (x >= 8 || x < 0){
		printf("X value cannot bigger than 8 or less than 1!\n");
		printf("Please Try Again!\n");
		return FALSE;
	}else if(y >= 8 || y < 0){
		printf("Y value cannot bigger than 8! or less than 1!\n");
		printf("Please Try Again!\n");
		return FALSE;
	/*second check current position, if it not blank, it is a invalid input*/
	}else if(board[x][y] != BLANK){
		printf("This position already has a token!\n");
		printf("Please change another position\n");
		return FALSE;
	}else{
		moveX = x;
		moveY = y;
		/*Check South Position*/
		/* Using Myself algorithm. 
		 *1. check the input, it is more than 8 or less than 0
		 *2. check the input position, and then check around 8 direction (east, west, notrh...etc.)
		 *3. if a direction has another color token, then check the next token, if the next token is same color with current user,
		 *it is a vaild input, and calculate the capture token. 
		 *4. then using for loop to change the token color.
		 *5. then recored the score to the capture variable.
		*/
		if (moveY + 1 > 8){
			captured_pieces = 0;
			moveY = y;
		}else if (board[x][moveY + 1] == BLANK){
				captured_pieces = 0;
				moveY = y;
			}else if (board[x][moveY + 1] != player_token){
				moveY = y + 1;
				for (row = y; row < 8; row++){
					if (board[x][moveY] != player_token){
						moveY = moveY + 1;
						captured_pieces = captured_pieces + 1;
						if(board[x][moveY] == BLANK){
							captured_pieces = 0;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[x][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[x][moveY - 1] = RED;
								  moveY--;
								  
							}
							south = 0;
						}
						}else{
							if (board[x][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[x][moveY - 1] = BLUE;
								  moveY--;
							}
							south = 0;
						}
						}
					}
				}
				
			}
		
			/*Check North Position*/
			moveX = x;
			moveY = y;
			if (moveY - 1 < 0){
				captured_pieces = 0;
				moveY = y;
			}else if (board[x][moveY - 1] == BLANK){
				captured_pieces = 0;
				moveX = x;
			}else if (board[x][moveY - 1] != player_token){
				moveY = y - 1;
				for (row = y; row >= 0; row--){
					if (board[x][moveY] != player_token){
						moveY = moveY - 1;
						captured_pieces = captured_pieces + 1;
						if(board[x][moveY] == BLANK){
							captured_pieces = 0;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[x][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[x][moveY + 1] = RED;
								  moveY++;
							}
							north = 0;

						}
						}else{
							if (board[x][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[x][moveY + 1] = BLUE;
								  moveY++;
							}
							north = 0;

						}
						}
					}
				}
				
			}
			/*Check West Position*/
			moveX = x;
			moveY = y;
			if (moveX - 1 < 0){
				captured_pieces = 0;
				moveX = x;
			}else if (board[moveX - 1][y] == BLANK){
				captured_pieces = 0;
				moveX = x;
			}else if  (board[moveX - 1][y] != player_token){
				moveX = x - 1;
				for (row = x; row > 0; row--){
					if (board[moveX][y] != player_token){
						moveX = moveX - 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][y] == BLANK){
							captured_pieces = 0;
							moveX = x;
							break;
						}
						if (player_token == RED){
							if (board[moveX][y] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][y] = RED;
								  moveX++;
							}
							west = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][y] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][y] = BLUE;
								  moveX++;
							}
							west = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
			
			/*Check East Position*/
			moveX = x;
			moveY = y;
			if (moveX + 1 > 8){
				captured_pieces = 0;
				moveX = x;
			}else if (board[moveX + 1][y] == BLANK){
				captured_pieces = 0;
				moveX = x;
			}else if  (board[moveX + 1][y] != player_token){
				moveX = x + 1;
				for (row = x; row < 8; row++){
					if (board[moveX][y] != player_token){
						moveX = moveX + 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][y] == BLANK){
							captured_pieces = 0;
							moveX = x;
							break;
						}
						if (player_token == RED){
							if (board[moveX][y] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][y] = RED;
								  moveX--;
							}
							east = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][y] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][y] = BLUE;
								  moveX--;
							}
							east = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
			/*Check West-North Position*/
			moveX = x;
			moveY = y;
			if (moveY - 1 < 0 || moveY - 1 < 0){
				captured_pieces = 0;
				moveY = y;
			}else if (board[moveX - 1][moveY - 1] == BLANK){
				captured_pieces = 0;
				moveX = x;
			}else if  (board[moveX - 1][moveY - 1] != player_token){
				moveX = x - 1;
				moveY = y - 1;
				for (row = x; row > 0; row--){
					if (board[moveX][moveY] != player_token){
						moveX = moveX - 1;
						moveY = moveY - 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][moveY] == BLANK){
							captured_pieces = 0;
							moveX = x;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[moveX][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][moveY + 1] = RED;
								  moveX++;
								  moveY++;
							}
							westNorth = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][moveY + 1] = BLUE;
								  moveX++;
								  moveY++;
							}
							westNorth = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
			
			/*Check East-South Position*/
			moveX = x;
			moveY = y;
			if (moveY + 1 > 8 || moveY + 1 > 8){
				captured_pieces = 0;
				moveY = y;
				moveX = x;
			}else if (board[moveX + 1][moveY + 1] == BLANK){
				captured_pieces = 0;
				moveX = x;
				moveY = y;
			}else if  (board[moveX + 1][moveY + 1] != player_token){
				moveX = x + 1;
				moveY = y + 1;
				for (row = x; row < 8; row++){
					if (board[moveX][moveY] != player_token){
						moveX = moveX + 1;
						moveY = moveY + 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][moveY] == BLANK){
							captured_pieces = 0;
							moveX = x;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[moveX][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][moveY - 1] = RED;
								  moveX--;
								  moveY--;
							}
							eastSouth = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][moveY - 1] = BLUE;
								  moveX--;
								  moveY--;
							}
							eastSouth = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
			
			/*Check East-North Position*/
			moveX = x;
			moveY = y;
			if (moveX + 1 > 8 || moveY - 1 < 0){
				captured_pieces = 0;
				moveY = y;
				moveX = x;
			}else if (board[moveX + 1][moveY - 1] == BLANK){
				captured_pieces = 0;
				moveX = x;
				moveY = y;
			}else if  (board[moveX + 1][moveY - 1] != player_token){
				moveX = x + 1;
				moveY = y - 1;
				for (row = y; row > 0; row--){
					if (board[moveX][moveY] != player_token){
						moveX = moveX + 1;
						moveY = moveY - 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][moveY] == BLANK){
							captured_pieces = 0;
							moveX = x;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[moveX][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][moveY + 1] = RED;
								  moveX--;
								  moveY++;
							}
							eastNorth = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX - 1][moveY + 1] = BLUE;
								  moveX--;
								  moveY++;
							}
							eastNorth = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
			/*Check westSouth Position*/
			moveX = x;
			moveY = y;
			if (moveY + 1 > 8 || moveX - 1 < 0){
				captured_pieces = 0;
				moveY = y;
				moveX = x;
			}else if (board[moveX - 1][moveY + 1] == BLANK){
				captured_pieces = 0;
				moveX = x;
				moveY = y;
			}else if  (board[moveX - 1][moveY + 1] != player_token){
				moveX = x - 1;
				moveY = y + 1;
				for (row = y; row < 8; row++){
					if (board[moveX][moveY] != player_token){
						moveX = moveX - 1;
						moveY = moveY + 1;
						captured_pieces = captured_pieces + 1;
						if(board[moveX][moveY] == BLANK){
							captured_pieces = 0;
							moveX = x;
							moveY = y;
							break;
						}
						if (player_token == RED){
							if (board[moveX][moveY] == RED){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][moveY - 1] = RED;
								  moveX++;
								  moveY--;
							}
							westSouth = 0;
							/*return TRUE;*/
						}
						}else{
							if (board[moveX][moveY] == BLUE){
								board[x][y] = player_token;
								for (i = 0; i <= captured_pieces; i++){
									board[moveX + 1][moveY - 1] = BLUE;
								  moveX++;
								  moveY--;
							}
							westSouth = 0;
							/*return TRUE;*/
						}
						}
					}
				}
				
			}
		/*check position flag, if there has any flag is 0, it is a valid input */
		if (south == 0 || north == 0 || east == 0 || west == 0 || westNorth == 0 || eastSouth == 0 || eastNorth == 0 || westSouth == 0){
			return TRUE;
		/* check position flag, if all flag is 1, it is a invalid input */
		}else if(south == 1 && north == 1 && east == 1 && west == 1 && westNorth == 1 && eastSouth == 1 && eastNorth == 1 && westSouth == 1){
		return FALSE;
	}else{
		return FALSE;
	}
	}
}

/**
 * simply count up how many locations contain the player_token 
 * specified on the game_board.
 **/
unsigned game_score(game_board board, enum cell player_token)
{
	/*calculate the score by check player_token in the gameboard*/
	 int i;
	 int j;
	 int score;
	 score = 0;
	 
	 for (i = 0; i < BOARD_HEIGHT; i++) {
		for (j = 0; j < BOARD_WIDTH; j++) {
			if (board[i][j] == player_token){
				score = score + 1;
			}
		}
	}
	return score;
}

/**
 * swap the two player pointers passed in so that first points to the player
 * pointer that originally contained the second player and vice versa.
 **/
void swap_players(struct player ** first, struct player ** second)
{
	/*switch user by setting temp user*/
	struct player* tempPlayer;
	tempPlayer = *second;
	*second = *first;
	*first = tempPlayer;	
}
