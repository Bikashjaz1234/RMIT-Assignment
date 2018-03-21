/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #2
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller
 * Some codes are adopted here with permission by an anonymous author
 ***********************************************************************/

#include "ppd_main.h"
#include "ppd_menu.h"
#include "ppd_options.h"
#include "ppd_utility.h"
#include <stdio.h>
/**
 * @file ppd_main.c contains the main function implementation and any 
 * helper functions for main such as a display_usage() function.
 **/

/**
 * manages the running of the program, initialises data structures, loads
 * data and handles the processing of options. The bulk of this function
 * should simply be calling other functions to get the job done.
 **/
int main(int argc, char **argv)
{
	const char * inputCoinFile;
	const char * inputStockFile;
	BOOLEAN menuFlag;
	BOOLEAN loadStock;
	BOOLEAN loadCoins;
    
    struct menu_item menu[NUM_MENU_ITEMS];

    /* represents the data structures to manage the system */
    struct ppd_system system;
    (void)system;
    
    /* init the system */
		system_init(&system);
		
    /* load data */
    inputStockFile = argv[STOCKFILE];
    inputCoinFile = argv[COINFILE];
    
    loadStock = load_stock(&system, inputStockFile);
    loadCoins = load_coins(&system, inputCoinFile);
    
    /*If file is not valid, exit the program*/
    if(loadStock == FALSE || loadCoins == FALSE){
    	printf("Because the file is not valid, the program is stoped. Please input valid file.\n");
    	return FALSE;
    }
    
    system.coin_file_name = inputCoinFile;
    system.stock_file_name = inputStockFile;
    /* test if everything has been initialised correctly */

    /* initialise the menu system */
    init_menu(menu);
    menuFlag = TRUE;
    while(menuFlag == TRUE){
    menuFlag = get_menu_choice(menu)(&system);
    }

    return EXIT_SUCCESS;
}
