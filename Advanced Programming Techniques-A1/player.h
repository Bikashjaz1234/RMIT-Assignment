/***********************************************************************
 * COSC1076 - Advanced Programming Techniques
 * Semester 2 2016 Assignment #1 
 * Full Name        : Siyu Zang (Harold)
 * Student Number   : S3534987
 * Course Code      : COSC1076
 * Program Code     : EDIT HERE
 * Start up code provided by Paul Miller 
 **********************************************************************/
#include "shared.h"
#include "gameboard.h"
#include "utility.h"

#ifndef PLAYER_H
#define PLAYER_H
/* the maximum length of a player name */
#define NAMELEN 20
/* the number of characters allowed when the user is entering the coordinates
 * for a game
 */

/* what are the attributes of a player? They have a score, a token (either BLUE
 * or RED), and a score
 */
struct player
{
    char name[NAMELEN + 1];
    enum cell token;
    unsigned score;
};

BOOLEAN init_first_player(struct player* human, enum cell * token);
BOOLEAN init_second_player(struct player * computer, enum cell token);
BOOLEAN make_move(struct player * player, game_board board);

#endif /* ifndef PLAYER_H */
