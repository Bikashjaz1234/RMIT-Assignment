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
#include "ppd_stock.h"

 /**
  * @file ppd_stock.c this is the file where you will implement the 
  * interface functions for managing the stock list.
  **/

/*function for init stock*/
BOOLEAN init_stock(struct ppd_system * system)
{
	struct ppd_list * stockList;
	system->item_list = malloc(sizeof(*stockList));
	
	/*make the stock is NULL*/
	stockList = system->item_list;
	if(stockList != NULL)
		{
			stockList->head = NULL;
			stockList->count = 0;
		}
		
		return TRUE;
}

/*function for read file and add it to the link list*/
BOOLEAN readToLinkedList(struct ppd_list * stock_list, struct ppd_stock * newStock)
{
	struct ppd_node * currentNode;
	
	/*if the head is null, this means that the list is empty, and add the data from the head*/
	if (stock_list->head == NULL){
		stock_list->head = malloc(sizeof(struct ppd_node));
		stock_list->head->data = newStock;
		stock_list->head->next = NULL;
	}else{
		/*if the head is not null, this means that the list has data, and find the end, and then add data*/
		currentNode = stock_list->head;
		
		while(currentNode->next !=  NULL){
			currentNode = currentNode->next;
			
		}
		
		currentNode->next = malloc(sizeof(struct ppd_node));
		currentNode->next->data = newStock;
		currentNode->next->next = NULL;
	}
	stock_list->count++;
	
	return TRUE;
	
}

/*function for Get the maxLength of the name, therefore, when I display the item, the format will be correct*/
int getTheLength(struct ppd_node * displayItem)
{
	int currentLength;
	int nextLength;
	currentLength = 0;
	nextLength = 0;
	
	currentLength = strlen(displayItem->data->name);
	
	/*use while loop to find that which name's length is the longgest*/
	while(displayItem != NULL){
		if (currentLength < nextLength){
			currentLength = nextLength;
		}else{
			currentLength = currentLength;
		}
		if(displayItem->next != NULL){
		nextLength = strlen(displayItem->next->data->name);
		}else{
			nextLength = 0;
		}
		displayItem = displayItem->next;
	}
	
	return currentLength;
	
}

/*function for search stock*/
struct ppd_node * searchStock(struct ppd_system * system, char * inputID)
{
	struct ppd_node * searchStock;
	
	searchStock = system->item_list->head;
	
	/*search by ID, if the stock is exist return it*/
	while(searchStock != NULL){
		if(strcmp(inputID, searchStock->data->id) == 0){
			return searchStock;
		}else{
			searchStock = searchStock->next;
		}
	}
	searchStock = NULL;
	return searchStock;
}

/*function for find the last stock id, therefore when user add item, it will automatally generate a item ID*/
int lastStockID(struct ppd_system * system)
{
	struct ppd_node * current;
	struct ppd_node * temp;
	char lastID[IDLEN + ENDCHAR];
	char * token, * pointer;
	int lastIDNumbe;
	
	current = system->item_list->head;
	
	/*temp = malloc(sizeof(struct ppd_node));
	*/
	/*use while loop to get the last ID number*/
	while(current != NULL){
		
		temp = current;
		
		current = current->next;
	}
	
	strcpy(lastID, temp->data->id);
	/*use strtok to split the id to the number (int)*/
	token = strtok(lastID, LASTID_SPLIT);
	lastIDNumbe = (int) strtol(token, &pointer, 10);
	/*return the id to the main function*/
	return lastIDNumbe;
	
}

/*function for add item, it is similar with readToList function*/
BOOLEAN addItem(struct ppd_list * stock_list, struct ppd_stock * newStock)
{
	struct ppd_node * currentNode;
	
	/*if the head is null, this means that the list is empty, and add the data from the head*/
	if (stock_list->head == NULL){
		stock_list->head = malloc(sizeof(struct ppd_node));
		stock_list->head->data = newStock;
		stock_list->head->next = NULL;
	}else{
		currentNode = stock_list->head;
		/*if the head is not null, this means that the list has data, and find the end, and then add data*/
		while(currentNode->next !=  NULL){
			currentNode = currentNode->next;
			
		}
		
		currentNode->next = malloc(sizeof(struct ppd_node));
		currentNode->next->data = newStock;
		currentNode->next->next = NULL;
	}
	stock_list->count++;
	
	return TRUE;
	
}

/*function for remove item*/
BOOLEAN removeItem(struct ppd_list * stock_list, char * removeID)
{
	struct ppd_node * previousStockNode, * currentStockNode;
	previousStockNode = NULL;
	currentStockNode = stock_list->head;
	
	/*use while loop to find the stock that user want to remove*/
	while (currentStockNode != NULL){
		/*if remove ID and current id is same, stop loop*/
		if(strcmp(removeID, currentStockNode->data->id) == 0){
			break;
		}
		/*else continue searching*/
		previousStockNode = currentStockNode;
		currentStockNode = currentStockNode->next;
		}
		
		if(currentStockNode == NULL)
    {
        return FALSE;
    }
    
    /*remove item*/
    if(previousStockNode == NULL)
    {
    		printf("\"%s  %s    %s\" has been removed from the system.\n", currentStockNode->data->id, currentStockNode->data->name, currentStockNode->data->desc);
        stock_list->head = currentStockNode->next;
    }
    else
    {
    		printf("\"%s  %s    %s\" has been removed from the system.\n", currentStockNode->data->id, currentStockNode->data->name, currentStockNode->data->desc);
        previousStockNode->next = currentStockNode->next;
    }
    stock_list->count--;
    
    /*free memory*/
    free(currentStockNode->data);
    free(currentStockNode);
    
	return TRUE;
}
