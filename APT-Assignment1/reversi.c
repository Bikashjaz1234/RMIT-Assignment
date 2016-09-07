/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/

#include "reversi.h"
#include <stdio.h>

/**
 * the hear of the program. The main() function in particular should 
 * manage the main menu for the program.
 **/
int main(void)
{
	  char menu[2];
    int flagmenu = 1;
    char *pointer;
    /*char firstlayer[20];*/
    score scrboard[MAX_SCORES];
    int newmenu;
    struct player human, computer, *winner = NULL;
    /*This is a copy*/
    /*struct player human, computer, *winner = NULL;*/
    /* initialise the scoreboard */
    init_scoreboard(scrboard);
    /* in a loop: display the main menu */
    
    while (flagmenu > 0){
    	printf("Welcome to Reversi!\n");
      printf("===================\n");
      printf("Select an option:\n");
      printf("1. Play a game\n");
      printf("2. Display High Scores\n");
      printf("3. Quit the program\n");
      printf("Please enter your choice:\n");
    	fgets(menu, 2, stdin);
    	newmenu = (int) strtol(menu, &pointer, 10);
    	read_rest_of_line();
    /* get the user's selection from the main menu */
      	switch (newmenu){   	 
      		case 1:
      			printf("Begin the Game!\n");
      			winner = play_game(&human, &computer);
      			if (winner != NULL){
      				/* play a game and add the winner to the scoreboard */ 
      				add_to_scoreboard(scrboard, winner);
      				printf("There has a Winner!\n");
      			}
      			break;
      		case 2:
      			/* display scores */
      			display_scores(scrboard);
      			break;
      		case 3:
      			/* quit the program */
      			printf("Bye~!\n");
      			flagmenu = 0;
      			break;
      		default:
      			printf("Please choose from 1 to 3\n");
        }
    	
    }

    
               

    return EXIT_SUCCESS;
}

