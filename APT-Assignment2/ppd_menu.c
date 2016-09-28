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

#include "ppd_menu.h"
/**
 * @file ppd_menu.c handles the initialised and management of the menu 
 * array
 **/

/**
 * @param menu the menu item array to initialise
 **/
void init_menu( struct menu_item* menu)
{
	
	int i;
	
	
	char* menuItem[] = 
	{
	"1.Display Items", "2.Purchase Items", "3.Save and Exit", "4.Add Item", 
	"5.Remove Item", "6.Display Coins", "7.Reset Stock", "8.Reset Coins", "9.Abort Program"
	};
  
  BOOLEAN (*menu_function[])(struct ppd_system*) = 
  {
  	display_items, purchase_item, save_system, add_item, 
  	remove_item, display_coins, reset_stock, reset_coins, abort_program 
  };
  
  for (i = 0; i < NUM_MENU_ITEMS; i++){
  	strcpy(menu[i].name, menuItem[i]);
  	menu[i].function = menu_function[i];
  }
  
}

/*Display Menu Function*/
void display_menu( struct menu_item* menu){
	int j;
	
	printf("\nMain Menu:\n");
	printf("=========================\n");
	for (j = 0; j < NUM_USER; j++){
		printf("%s\n", menu[j].name);
	}
	
	printf("Administrator-Only Menu:\n");
	for (j = NUM_USER; j < NUM_MENU_ITEMS; j++){
		printf("%s\n", menu[j].name);
	}
	printf("Select your option (1-9):\n");
	printf("=========================\n");
}

/**
 * @return a menu_function that defines how to perform the user's
 * selection
 **/
menu_function get_menu_choice(struct menu_item * menu)
{
	char menuInput[INPUT_BUFFER];
  char *pointer;
  int newmenu;
  
  display_menu(menu);
  fgets(menuInput, INPUT_BUFFER + ENDCHAR, stdin);
   /*Validation input size*/
  if (strlen(menuInput) > 3){
  	printf("Please input a valid number! (1-9)\n");
  	return get_menu_choice(menu);
  }
  newmenu = (int) strtol(menuInput, &pointer, 10);
  newmenu = newmenu - 1;
  
  /*Validation input number*/
 if (newmenu > NUM_MENU_ITEMS-1 || newmenu < 0){
  	printf("Please input a valid number! (1-9)\n");
  	return get_menu_choice(menu);
  }
  return menu[newmenu].function;
}
