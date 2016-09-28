/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #2
 * Full Name        : EDIT HERE
 * Student Number   : EDIT HERE
 * Course Code      : EDIT HERE
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller
 * Some codes are adopted here with permission by an anonymous author
 ***********************************************************************/

#include "ppd_utility.h"
#include <string.h>
#include <stdio.h>
/**
 * @file ppd_utility.c contains implementations of important functions for
 * the system. If you are not sure where to implement a new function, 
 * here is probably a good spot.
 **/

void read_rest_of_line(void)
{
    int ch;
    /* keep retrieving characters from stdin until we
     * are at the end of the buffer
     */
    while(ch = getc(stdin), ch!='\n' && ch != EOF)
        ;
    /* reset error flags on stdin */
    clearerr(stdin);
}

/**
 * @param system a pointer to a @ref ppd_system struct that holds all 
 * the data for the system we are creating
 **/
BOOLEAN system_init(struct ppd_system * system)
{
    /*
     * Please delete this default return value once this function has 
     * been implemented. Please note that it is convention that until
     * a function has been implemented it should return FALSE
     */
    init_coin(system);
    
    init_stock(system);
    
    system->coin_file_name = "";
    system->stock_file_name = "";
    return TRUE;
}

/**
 * loads the stock file's data into the system. This needs to be 
 * implemented as part of requirement 2 of the assignment specification.
 **/
BOOLEAN load_stock(struct ppd_system * system, const char * filename)
{
    FILE* stockFile;
    char stockLine[STOCK_LINE_LENGTH];
    char *token, *priceToken, *ptr;
    struct ppd_stock * newStock;
    long stockPrice;
    long stockDollar;
    long stockCent;
    
    /*Open file*/
    stockFile = fopen(filename, "r");
    newStock = NULL;
    
    /*Check the file is exist or not*/
    if(stockFile == NULL || strcmp(filename, "stock.dat") != 0){
    	printf("stock.dat is not exist, please check it!");
    	return FALSE;
    }
    
    
    /*Read the file*/
    while(fgets(stockLine, STOCK_LINE_LENGTH + ENDCHAR, stockFile) != NULL){
    	newStock = malloc(sizeof(struct ppd_stock));
    	/*split the line by "|", get every thing*/
    	/* get the first token, ID */
    	token = strtok(stockLine, STOCK_SPLIT);
    	while (token != NULL){
    		/*chect ID format is valid or not*/
    		if(token[FIRSTCHAR] != 'I' || token == NULL || (strlen(token)) > (IDLEN + ENDCHAR)){
    			printf("Stock.dat file's format is not correct! Load ID data is failed!");
    			return FALSE;
    		}
    		strcpy(newStock->id, token);
    		/*get the next, token Name*/
    		token = strtok(NULL, STOCK_SPLIT);
    		strcpy(newStock->name, token);
    		
    		/*get the desc*/
    		token = strtok(NULL, STOCK_SPLIT);
    		strcpy(newStock->desc, token);
    		
    		/*get the price*/
    		token = strtok(NULL, STOCK_SPLIT);
    		priceToken = token;
    		
    		/*get the on_hand*/
    		token = strtok(NULL, STOCK_SPLIT);
    		stockPrice = strtol(token, &ptr, 10);
    		newStock->on_hand = stockPrice;
    		
    		/*split the price to dollar and cents*/
    		priceToken = strtok(priceToken, PRICE_SPLIT);
    		while (priceToken != NULL){
    			stockDollar = strtol(priceToken, &ptr, 10);
    			newStock->price.dollars = stockDollar;
    			priceToken = strtok(NULL, PRICE_SPLIT);
    			stockCent = strtol(priceToken, &ptr, 10);
    			newStock->price.cents = stockCent;
    			priceToken = strtok(NULL, PRICE_SPLIT);
    		}
    		readToLinkedList(system->item_list, newStock);
    		token = strtok(NULL, STOCK_SPLIT);
    	}
    	

    	
    	
    }
    
    
    return TRUE;
}

/**
 * loads the contents of the coins file into the system. This needs to
 * be implemented as part 1 of requirement 18.
 **/
BOOLEAN load_coins(struct ppd_system * system, const char * filename)
{
    /*
     * Please delete this default return value once this function has 
     * been implemented. Please note that it is convention that until
     * a function has been implemented it should return FALSE
     */
    FILE* coinsFile;
    /*Open file*/
    coinsFile = fopen(filename, "r");
    
    /*Check the file is exist or not*/
    if(coinsFile == NULL || strcmp(filename, "coins.dat") != 0){
    	printf("coins.dat is not exist, please check it!");
    	return FALSE;
    }
    
    
    return FALSE;

}

/**
 * @param system a pointer to a @ref ppd_system struct that holds all 
 * the data for the system we are creating
 **/
void system_free(struct ppd_system * system)
{

}
