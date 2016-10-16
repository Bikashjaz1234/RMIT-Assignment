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
#ifndef PPD_SHARED
#define PPD_SHARED
/**
 * The character that use to split stock file
 **/
#define STOCK_SPLIT "|"
/**
 * The character that use to split coin file
 **/
#define COIN_SPLIT ","
/**
 * The character that use to split price (4.40)
 **/
#define PRICE_SPLIT "."
/**
 * The character that use to check stock file's ID is correct or not
 **/
#define LASTID_SPLIT "I"
/**
 * datatype to represent a boolean value within the system
 **/
typedef enum truefalse
{
    /**
     * the constant for false
     **/
    FALSE, 
    /**
     * the constant for true
     **/
    TRUE
} BOOLEAN;
#endif
