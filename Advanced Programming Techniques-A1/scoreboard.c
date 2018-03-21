/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/

#include "scoreboard.h"

/**
 * initalise the scoreboard so that the scores for each element of the array
 * is set to 0.
 **/
void init_scoreboard(score scores[MAX_SCORES])
{
	int i;
	for (i = 0; i <= MAX_SCORES - 1; i++){
		scores[i].score=0;
	}
}


/**
 * insert the top scorer from the last game played in sorted order according
 * to their score 
 **/
BOOLEAN add_to_scoreboard(score scores[MAX_SCORES], struct player * winner)
{
	int i;
	int j;
	/* add the score to the score board. and check the score, if winner score more than current score,
	 * using for loop to move the old score to order score
	 */
	for(i = 0; i <= MAX_SCORES - 1; i++){
		if (winner->score > scores[i].score){
			for (j = MAX_SCORES - 1; j >= i; j--){
				scores[j] = scores[j - 1];
			}
			scores[i] = *winner;
			break;
		}
		
	}
	return TRUE;
}

/**
 * display the scores in the scoreboard according to their order. Your output
 * should match that provided in the assignment specification.
 **/
void display_scores(score scores[MAX_SCORES])
{
	int i;
	int j;
	/*display score board, and using for loop to print it.*/
	printf("Reversi - Top Scores\n");
	printf("============================\n");
	printf("----------------------------\n");
	printf("Name                 | Score\n");
	printf("----------------------------\n");
	for (i = 0; i < MAX_SCORES; i++){
		if(scores[i].score != 0){
			printf("%s", scores[i].name);
			for (j = 0; j <= (20 - strlen(scores[i].name)); j++){
				printf(" ");
			}
			printf("| %d\n", scores[i].score);
		}else{
			break;
		}
	}
	
	
}
