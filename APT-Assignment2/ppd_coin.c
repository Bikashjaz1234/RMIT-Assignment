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
#include "ppd_coin.h"
#include "ppd_stock.h"
 /**
  * @file ppd_coin.c implement functions here for managing coins in the
  * "cash register" contained in the @ref ppd_system struct.
  **/
BOOLEAN init_coin(struct ppd_system * system)
{
	/*create a temp coin struct for init coin*/
	struct coin tempCoin;
	int i;
	
	/*set the temp coin struct do not have any value*/
	tempCoin.denom = 0;
	tempCoin.count = 0;
	
	/*use for loop to whole array's data*/
	for(i = 0; i < NUM_DENOMS; i++){
		
		system->cash_register[i] = tempCoin;
		
	}
	
	return TRUE;
}

enum denomination TransferDenom(int inputNumber)
{
	/*use switch loop to Transfer denom from words to number*/
	switch(inputNumber){
		case 1:
			return 5;
			break;
		case 2:
			return 10;
			break;
		case 3:
			return 20;
			break;
		case 4:
			return 50;
			break;
		case 5:
			return 100;
			break;
		case 6:
			return 200;
			break;
		case 7:
			return 500;
			break;
		case 8:
			return 1000;
			break;
		}
	return TRUE;

}
