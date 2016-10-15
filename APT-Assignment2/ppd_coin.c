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

BOOLEAN checkCoin(int inputCoin)
{
	/*use array to store coin's type*/
	int check[] = {5, 10, 20, 50, 100, 200, 500, 1000};
	int i;
	float formatCoin;
	
	/*use for loop to check input coin is valid or not*/
	for (i = 0; i < NUM_DENOMS; i++){
		if (check[i] == inputCoin){
			return TRUE;
		}
	}
	formatCoin = (float) inputCoin / 100;
	printf("$%.02f is not a valid denomination of money!\n", formatCoin);
	return FALSE;
}

int* calCoin(struct ppd_system * system, float remainMoney)
{

	int calculate[] = {5, 10, 20, 50, 100, 200, 500, 1000};
	int calRemainMoney;
	static int remainArray[REMAIN_LEN];
	/*Create i and k variable for "for loop"*/
	int i, calTimes, k;
	
	/*set calculate remain money array is 0*/
	for (k = 0; k < REMAIN_LEN; k++){
		remainArray[k] = 0;
	}
	
	/*set a variable for calcualte how many time it minus, and each time store the number to the array*/
	calTimes = 0;
	
	/*chage the format from 1.50 to 150*/
	remainMoney = remainMoney * (float)100;
	calRemainMoney = (int) remainMoney;
	
	/*use while loop to calculate change*/
	while (calRemainMoney > 0){
		for (i = 0; i < NUM_DENOMS; i++){
			/*compare the remain money, find which denom is bigger than it, and use previous one to minus the remain money*/
			if (calRemainMoney < calculate[i] && i >0){
				/*if current coine count is 0, do a for loop, find which previous one is not. (Because maybe 1$, 50c count are 0 at same time*/
				if(system->cash_register[i - 1].count == 0){
					for(k = 1; k <= i; k++){
						/*if previous one is not 0, do calculate*/
						if(system->cash_register[i - k].count != 0){
							calRemainMoney = calRemainMoney -  calculate[i - k];
							system->cash_register[i - k].count--;
							/*store the previous denom to the array, which is change*/
							remainArray[calTimes] = calculate[i-k];
							calTimes++;
							break;
						}
					}
					break;
				}else{
					calRemainMoney = calRemainMoney -  calculate[i - 1];
					/*if use the coine, minus one from the system*/
					system->cash_register[i - 1].count--;
					/*store the previous denom to the array, which is change*/
					remainArray[calTimes] = calculate[i-1];
					calTimes++;
					break;
			}
			/*if the remain money is 5, store the 5 to the array*/
			}else if (calRemainMoney == calculate[0]){
				calRemainMoney = calRemainMoney - calculate[0];
				remainArray[calTimes] = calculate[0];
				/*if use the coine, minus one from the system*/
				system->cash_register[0].count--;
				break;
			}
		} 
	}

	return remainArray;
}