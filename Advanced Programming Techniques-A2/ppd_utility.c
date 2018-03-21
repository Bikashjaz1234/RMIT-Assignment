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
    char *token, *priceToken, *ptr, *inputID;
    struct ppd_stock * newStock;
    struct ppd_node * checkRepeatNode;
    long stockOnHand;
    long stockDollar;
    long stockCent;
    
    /*Open file*/
    stockFile = fopen(filename, "r");
    newStock = NULL;
    
    /*Check the file is exist or not*/
    /*if(stockFile == NULL || strcmp(filename, "stock.dat") != 0){*/
    if(stockFile == NULL){
    	printf("stock file is not exist, please check it!\n");
    	return FALSE;
    	
    }
    
    /*Read the file*/
    while(fgets(stockLine, STOCK_LINE_LENGTH + ENDCHAR, stockFile) != NULL){
    	newStock = malloc(sizeof(struct ppd_stock));
    	/*split the line by "|", get every thing*/
    	/* get the first token, ID */
    	token = strtok(stockLine, STOCK_SPLIT);
    	inputID = token;
    	while (token != NULL){
    		/*chect ID format is valid or not*/
    		if(token[FIRSTCHAR] != 'I' || token == NULL || (strlen(token)) > (IDLEN + ENDCHAR)){
    			printf("Load Stock file failed!! Stock file's format is not correct! Load ID data is failed!");
    			return FALSE;
    		}
    		strcpy(newStock->id, token);
    		/*get the next, token Name*/
    		token = strtok(NULL, STOCK_SPLIT);
    		
    		/*valid name token, check is valid or not*/
    		if((strlen(token)) > (NAMELEN + ENDCHAR) || token == NULL){
    			printf("Load Stock file failed!! The name value \"%s\" is not valid.\n", token);
    			return FALSE;
    		}
    		
    		strcpy(newStock->name, token);
    		
    		/*get the desc*/
    		token = strtok(NULL, STOCK_SPLIT);
    		
    		/*valid description token, check is valid or not*/
    		if(token == NULL || (strlen(token)) > (DESCLEN + ENDCHAR)) {
    			printf("Load Stock file failed!! The description value \"%s\" is not valid.\n", token);
    			return FALSE;
    		}
    		
    		strcpy(newStock->desc, token);
    		
    		/*get the price*/
    		token = strtok(NULL, STOCK_SPLIT);
    		
    		/*valid price token, check is exist or not*/
    		if(token == NULL){
    			printf("Load Stock file failed!! The price value \"%s\" is not valid.\n", token);
    			return FALSE;
    		}
    		priceToken = token;
    		
    		/*get the on_hand*/
    		token = strtok(NULL, STOCK_SPLIT);
    		

    		/*valid on_hand token, check is exist or not*/
    		if(token == NULL){
    			printf("Load Stock file failed!! The on_hand value \"%s\" is not valid.\n", token);
    			return FALSE;
    		}
    		
    		stockOnHand = strtol(token, &ptr, 10);
    			/*valid on_hand token, check is valid or not*/
    		if (stockOnHand < 0 || strcmp(ptr, "\n") != 0){
    			printf("Load Stock file failed!! The on_hand value is not valid.\n");
    			return FALSE;
    		}
    		
    		newStock->on_hand = stockOnHand;
    		
    		/*split the price to dollar and cents*/
    		priceToken = strtok(priceToken, PRICE_SPLIT);
    		
    		/*check price token is exist or not*/
    		if(priceToken == NULL){
    			printf("Load Stock file failed!! The price value is not valid.\n");
    			return FALSE;
    		}
    		
    		while (priceToken != NULL){
    			stockDollar = strtol(priceToken, &ptr, 10);
    			
    			/*check price's dollar is valid or not*/
    			if(stockDollar < 0){
    				printf("Load Stock file failed!! The price value is not valid1.\n");
    				return FALSE;
    			}
    			
    			newStock->price.dollars = stockDollar;
    			priceToken = strtok(NULL, PRICE_SPLIT);
    			stockCent = strtol(priceToken, &ptr, 10);
    			
    			/*check price's dollar is valid or not*/
    			if(stockCent < 0 || stockCent % 5 != 0){
    				printf("Load Stock file failed!! The price value is not valid.\n");
    				return FALSE;
    			}

    			newStock->price.cents = stockCent;
    			priceToken = strtok(NULL, PRICE_SPLIT);
    		}
    		
    		/*Check the ID is exist or not*/
    		checkRepeatNode = searchStock(system, inputID);
    		if(checkRepeatNode != NULL){
    			printf("Load Stock file failed!! The item ID already exist, please check it.\n");
    			return FALSE;
    		}
    		
    		/*write the data to the list*/
    		readToLinkedList(system->item_list, newStock);
    		token = strtok(NULL, STOCK_SPLIT);
    	}
    
    }
    fclose(stockFile);
    return TRUE;
}

/**
 * loads the contents of the coins file into the system. This needs to
 * be implemented as part 1 of requirement 18.
 **/
BOOLEAN load_coins(struct ppd_system * system, const char * filename)
{
 
    FILE* coinsFile;
    char coinsLine[COIN_LINE];
    char *token, *ptr;
    struct coin loadCoins;
    int denomNumber;
    int coinCount;
    
    /*Open file*/
    coinsFile = fopen(filename, "r");
    
    denomNumber = NUM_DENOMS;
    
    
    /*Check the file is exist or not*/
    if(coinsFile == NULL){
    	printf("coins file is not exist, please check it!\n");
    	return FALSE;
    }
    
      while(fgets(coinsLine, COIN_LINE + ENDCHAR, coinsFile) != NULL){
    	token = strtok(coinsLine, COIN_SPLIT);
    	
    	/*check denomination value is exist or not*/
    	if(token == NULL){
    		printf("Load Coins file failed!! The denomination value is not valid.\n");
    		return FALSE;
    	}
    	
    	loadCoins.denom = TransferDenom(denomNumber);
    	token = strtok(NULL, COIN_SPLIT);
    	
    	/*check count value is exist or not*/
    	if(token == NULL || strcmp(token, "\n") == 0){
    		printf("Load Coins file failed!! The count value is not valid.\n");
    		return FALSE;
    	}
    	
    	coinCount = strtol(token, &ptr, 10);
    	/*check the count value is less than 0 or not*/
    	if(coinCount < 0){
    		printf("Load Coins file failed!! The count value is not valid. It cannot less than 0.\n");
    		return FALSE;
    	}
    	loadCoins.count = coinCount;
    	denomNumber--;
    	
    	/*check the how many argument there are*/
    	if((strtok(NULL, COIN_SPLIT) != NULL)){
    		printf("Load Coins file failed!! The file's format is not correct, there are more than 2 arguments.\n");
    		return FALSE;
    	}
    	
    	/*chech there have repeat denom number or not*/
    	if (denomNumber < 0){
    		printf("Load Coins file failed!! The file's format is not correct, you have repeat denom number.\n");
    		return FALSE;
    	}
    	
    	system->cash_register[denomNumber] = loadCoins;
    }
    fclose(coinsFile);
    return TRUE;

}

/**
 * @param system a pointer to a @ref ppd_system struct that holds all 
 * the data for the system we are creating
 **/
void system_free(struct ppd_system * system)
{
	/*set 2 variables for current Node and next node*/
	struct ppd_node * currentNode;
	struct ppd_node * nextNode;
	
	/*set cuttent node value and set next node is null*/
	currentNode = system->item_list->head;
	nextNode = NULL;
	
	/*using while loop to free all node*/
	while(currentNode != NULL)
	{
		/*free the current node's data*/
		free(currentNode->data);	
		nextNode = currentNode->next;
		/*free current node*/
		free(currentNode);
		currentNode = nextNode;		
	}
	
	/*free system list*/
	free(system->item_list);	
	

}
