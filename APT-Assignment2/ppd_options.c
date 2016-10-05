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

#include "ppd_options.h"
#include "ppd_utility.h"

/**
 * @file ppd_options.c this is where you need to implement the main 
 * options for your program. You may however have the actual work done
 * in functions defined elsewhere. 
 * @note if there is an error you should handle it within the function
 * and not simply return FALSE unless it is a fatal error for the 
 * task at hand. You want people to use your software, afterall, and
 * badly behaving software doesn't get used.
 **/

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true as this particular function should never fail.
 **/
BOOLEAN display_items(struct ppd_system * system)
{
	int maxLength;
	int availLength;
	int priceLength;
	int i;
	struct ppd_node *displayItem = system->item_list->head;
	
	maxLength = 0;
	/*get the "avaiable" length*/
	availLength = strlen("Available");
	
	/*get the "price" length*/
	priceLength = strlen("Price");
	
	
	
	/*Check there has data or not*/
	if(displayItem == NULL){
		printf("Do not have any data!\n");
		return FALSE;
	}
	
	/*Get the maxLength of the name*/
	maxLength = getTheLength(displayItem);
	
	/*print Item*/
	printf("\nItems Menu\n");
	printf("\n");
	printf("%-*s ", IDLEN, "ID");
  printf("| %-*s ", maxLength, "Name");
  printf("| Available | Price \n");
  for (i=0; i <= IDLEN + maxLength + availLength + priceLength + SPACETIMES; i++){
		printf("-");
	}
	printf("\n");
	/*Using while loop to print the item*/
	while(displayItem != NULL)
	{
		printf("%-*s ", IDLEN, displayItem->data->id);
		printf("| %-*s ", maxLength, displayItem->data->name);
		printf("| %-*d ", availLength, displayItem->data->on_hand);
		printf("| $%d.%02u \n", displayItem->data->price.dollars, displayItem->data->price.cents);
		
		displayItem = displayItem->next;
	}
    
    return TRUE;

}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true when a purchase succeeds and false when it does not
 **/
BOOLEAN purchase_item(struct ppd_system * system)
{
    char * inputID;
    struct ppd_node * purNode;
    int delReturn;
    char purchaseInput[PUR_BUFFER];
    char moneyInput[MONEY_BUFFER];
    /*User input money*/
    int money;
    /*item's Money*/
    int itemMoney;
    /*after input, the remain Money*/
    float remainMoney;
    /*refund money*/
    float refundMoney;
    char * moneyPtr;
    /*creat a flag for check input*/
    int flagCheck;
    
    refundMoney = 0;
    flagCheck = 0;
    printf("\nPurchase Item\n");
    printf("--------------\n");
    printf("Please enter the id of the item you wish to purchase:");
    fgets(purchaseInput, PUR_BUFFER + ENDCHAR, stdin);
    delReturn = strlen(purchaseInput);
    if(strcmp(purchaseInput, "\n") == 0){
    		printf("Exit Purchase Item Function! Return to Main menu\n");
    		return TRUE;
    	}
    /*delete input 'return' character*/
    if(purchaseInput[delReturn - 1]=='\n'){
    	purchaseInput[delReturn - 1]=0;
    }
    inputID = purchaseInput;
    purNode = searchStock(system, inputID);
    
    
    if(purNode != NULL){
    	/*Check the amount*/
    	if(purNode->data->on_hand == 0){
    		printf("You have selected \"%s\" amount is 0, Please come back soon, or choose other items!", purNode->data->name);
    		return purchase_item(system);
    	}
    	printf("You have selected \"%s   %s\".  This will cost you $%d.%02u.\n", purNode->data->name, purNode->data->desc, purNode->data->price.dollars, purNode->data->price.cents);
    	printf("Please hand over the money  type in the value of each note/coin in cents.\n");
    	printf("Press enter on a new and empty line to cancel this purchase:\n");
    	printf("You still need to give us:$%d.%02u:", purNode->data->price.dollars, purNode->data->price.cents);
    	/*Change Item money's format from 3.50 to 350*/
    	itemMoney = purNode->data->price.dollars * 100 + purNode->data->price.cents;
    	fgets(moneyInput, MONEY_BUFFER + ENDCHAR, stdin);
    	
    	/*if user input new line, exit prtchare function*/
    	if(strcmp(moneyInput, "\n") == 0){
    		printf("It will go to the menu, and you will receive refund $%.2f\n", refundMoney);
    		return TRUE;
    	}
    	
    	money = (int) strtol(moneyInput, &moneyPtr, 10);
    	remainMoney = (float)itemMoney;
    	while (remainMoney > 0){
    		if(money%5 == 0 && checkCoin(money)){
    			itemMoney = itemMoney - money;
    			remainMoney = (float)itemMoney / 100;
    			if(remainMoney <= 0){
    				break;
    			}else{
    					printf("You still need to give us:$%.2f:", remainMoney);
    					fgets(moneyInput, MONEY_BUFFER + ENDCHAR, stdin);
    					if(strcmp(moneyInput, "\n") == 0){
    						itemMoney = purNode->data->price.dollars * 100 + purNode->data->price.cents;
    						refundMoney = itemMoney / 100 - remainMoney;
    						printf("It will go to the menu, and you will receive refund $%.2f\n", refundMoney);
    						return TRUE;
    					}
    					money = (int) strtol(moneyInput, &moneyPtr, 10);   				
    			}
    		}else{
    			printf("Invalid Input! Please input again.\n");
    			if (flagCheck == 0){
    				remainMoney = (float)remainMoney / 100;
    				printf("You still need to give us:$%.2f:", remainMoney);
    			}else{
    				printf("You still need to give us:$%.2f:", remainMoney);
    			}
    			fgets(moneyInput, MONEY_BUFFER + ENDCHAR, stdin);
    			money = (int) strtol(moneyInput, &moneyPtr, 10);
    			flagCheck++;
    		}
    }
    if (remainMoney != 0){
    	remainMoney = remainMoney * (-1);
    }else{
    	remainMoney = remainMoney;
    }
    purNode->data->on_hand = purNode->data->on_hand - 1;
    printf("Thank you. Here is your %s, and your change of $%.2f.\n", purNode->data->name, remainMoney);
    }else{
    	printf("Sorry, This item is not exist! Please Input correct Number!");
    	return purchase_item(system);
    }
    
    
    return TRUE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true when a save succeeds and false when it does not
 **/
BOOLEAN save_system(struct ppd_system * system)
{
    FILE * stock_file;
    FILE * coin_file;
    struct ppd_node * current_stock;
    int i;
    
    current_stock = system->item_list->head;
    
    stock_file = fopen(system->stock_file_name, "w");
    coin_file = fopen(system->coin_file_name, "w");
    
    /*Check file is exist or not*/
    if(stock_file == NULL){
    	printf("Stock File is not exist!");
    	return FALSE;
    }
    
    /*Check file is exist or not*/
    if(coin_file == NULL){
    	printf("Coin File is not exist!");
    	return FALSE;
    }
    
    
    /*use while loop to write stock file*/
    while(current_stock != NULL){
    	
    	/*write the data to file by using fprintf*/
    	fprintf(stock_file, "%s|%s|%s|%d.%02d|%d\n", current_stock->data->id, current_stock->data->name, current_stock->data->desc, current_stock->data->price.dollars, current_stock->data->price.cents, current_stock->data->on_hand);
    	
    	current_stock = current_stock->next;
    	
    }

    /*use for loop to write coin file*/
    for(i = NUM_DENOMS - 1; i >= 0; i--){
    	  	
    	/*write the data to file by using fprintf*/
    	fprintf(coin_file, "%d,%d\n",system->cash_register[i].denom, system->cash_register[i].count);
    		
    }
    
    /*Friendly display information*/
    printf("Save file Successfully!\n");
    
    fclose(stock_file);
    fclose(coin_file);
    
    return FALSE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true when adding an item succeeds and false when it does not
 **/
BOOLEAN add_item(struct ppd_system * system)
{
		char inputID[IDLEN + ENDCHAR];
   	char inputName[NAMELEN + ENDCHAR];
   	char inputDesc[DESCLEN + ENDCHAR];
   	char inputPrice[PRICELEN + ENDCHAR];
   	char * inputToken, *ptr;
   	int inputDollar, inputCent, delReturn, id;
   	struct ppd_stock * addStockItem;
   	
   	/*get the last ID Number and generate the new ID*/
    id = lastStockID(system);
    id++;
    
    /*Format the ID, make sure it become to Ixxxx */
    sprintf(inputID, "I%04d", id);
    
    printf("This new meal item will have the Item id of %s\n", inputID);
   	addStockItem = malloc(sizeof(struct ppd_stock));
   	printf("Enter the item name: ");
   	fgets(inputName, NAMELEN + ENDCHAR, stdin);
   	printf("Enter the item description: ");
   	fgets(inputDesc, DESCLEN + ENDCHAR, stdin);
   	printf("Enter the price for this item: ");
   	fgets(inputPrice, PRICELEN + ENDCHAR, stdin);
    
    
    /*delete name 'return' character*/
    delReturn = strlen(inputName);
    if(inputName[delReturn - 1]=='\n'){
    	inputName[delReturn - 1]=0;
    }
    /*delete desc 'return' character*/
    delReturn = strlen(inputDesc);
    if(inputDesc[delReturn - 1]=='\n'){
    	inputDesc[delReturn - 1]=0;
    }
    /*delete desc 'return' character*/
    delReturn = strlen(inputPrice);
    if(inputPrice[delReturn - 1]=='\n'){
    	inputPrice[delReturn - 1]=0;
    }
    
    /*split the price to dollar and cents*/
    inputToken = inputPrice;
    inputToken = strtok(inputToken, PRICE_SPLIT);
    		while (inputToken != NULL){
    			inputDollar = strtol(inputToken, &ptr, 10);
    			/*check dollar's value*/
    			if(inputDollar < 0){
    				printf("Add new item is faild! The price is not valid!\n");
    				printf("Return Main Menu!\n");
    				return TRUE;
    			}
    			
    			inputToken = strtok(NULL, PRICE_SPLIT);
    			/*check next token is exist or not*/
    			if(inputToken == NULL){
    				printf("Add new item is faild! The price is not valid!\n");
    				printf("Return Main Menu!\n");
    				return TRUE;
    			}
    			
    			inputCent = strtol(inputToken, &ptr, 10);
    			/*check cents value*/
    			if(inputCent < 0 || inputCent % 5 != 0){
    				printf("Add new item is faild! The price is not valid!\n");
    				printf("Return Main Menu!\n");
    				return TRUE;
    			}
    			
    			inputToken = strtok(NULL, PRICE_SPLIT);
    		}
    
    
    
    /*add the item data to the stock*/
    strcpy(addStockItem->id, inputID);
    strcpy(addStockItem->name, inputName);
    strcpy(addStockItem->desc, inputDesc);
    addStockItem->price.dollars = inputDollar;
    addStockItem->price.cents = inputCent;
    addStockItem->on_hand = DEFAULT_STOCK_LEVEL;
    
    /*add Function to add the item to the list*/
    addItem(system->item_list, addStockItem);

    return TRUE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true when removing an item succeeds and false when it does not
 **/
BOOLEAN remove_item(struct ppd_system * system)
{
	
		struct ppd_node * removeNode;
		char removeInputID[IDLEN + ENDCHAR];
		int delReturn;
		
    printf("Enter the item id of the item to remove from the menu: ");
    fgets(removeInputID, NAMELEN + ENDCHAR, stdin);
    /*delete name 'return' character*/
    delReturn = strlen(removeInputID);
    if(removeInputID[delReturn - 1]=='\n'){
    	removeInputID[delReturn - 1]=0;
    }
    
    
    removeNode = searchStock(system, removeInputID);
    if(removeNode != NULL){
    	removeItem(system->item_list, removeInputID);
    }else{
    	printf("Sorry, This item is not exist! Please Input correct Number!\n");
    	
    }
    
    return TRUE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true as this function cannot fail.
 **/
BOOLEAN reset_stock(struct ppd_system * system)
{
    struct ppd_node * resetStock;
    int i;
    
    resetStock = system->item_list->head;
    
    for(i = 1; i <= system->item_list->count; i++){
    	resetStock->data->on_hand = DEFAULT_STOCK_LEVEL;
    	
    	resetStock = resetStock->next;
    }
    
    printf("Reset Stock successful!");
    
    return TRUE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true as this function cannot fail.
 **/
BOOLEAN reset_coins(struct ppd_system * system)
{
    
    int i;
    
    for(i = 0; i < NUM_DENOMS; i++){
    	
    	system->cash_register[i].count = DEFAULT_COIN_COUNT;
    	
    }
    
    printf("Reset Coins successful!");
    
    return TRUE;
}

/**
 * @param system a pointer to a  ppd_system struct that contains 
 * all the information for managing the system.
 * @return true as this function cannot fail
 **/
BOOLEAN display_coins(struct ppd_system * system)
{
    int i;
    
    printf("\nCoins Summary\n");
    printf("-------------\n");
    printf("Denomination \t| Count\n");
    printf("\n");
    /*Use for loop to display the coins*/
    for(i = 0; i < NUM_DENOMS; i++){
    	/*chect if denom less than 50, print cents, if more than 50, print dollar*/
    	if (system->cash_register[i].denom <= 50){
    		printf("%d ", system->cash_register[i].denom);
    		printf("Cents \t");
    		printf("| %d\n", system->cash_register[i].count);
    	}else{
    		printf("%d dollar\t| %d\n",system->cash_register[i].denom/100, system->cash_register[i].count);
    	}
    	
    }
    return TRUE;
}

BOOLEAN abort_program(struct ppd_system * system)
{
    printf("Free System.....You Exit the program.\n");
    system_free(system);
    return FALSE;
}