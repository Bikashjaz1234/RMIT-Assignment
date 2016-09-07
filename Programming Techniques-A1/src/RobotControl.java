import javax.swing.*;

class RobotControl
{
   private Robot r;
   public RobotControl(Robot r)
   {
       this.r = r;
   }

   public void control(int barHeights[], int blockHeights[])
   {
	 sampleControlMechanism(barHeights,blockHeights);
   }
   
   
   public void sampleControlMechanism(int barHeights[], int blockHeights[])
   {
	 int h = 2;         		// Initial height of arm 1
	 int w = 1;         		// Initial width of arm 2  
	 int d = 0;         		// Initial depth of arm 3
	 int sourceHt = 0;  		// the whole block height
	 int currentBlockNumber = blockHeights.length - 1; //Calculate the current Block number
	 int currentBarNumber = 0;  //find which bar should be put the large block
	 int smallBlockTimes = 0;	//How many times the small Block move to the right position
	 int midBlockTimes = 0;		//How many times the mid Block move to the right position
	 int largeBlockTimes = 0;	//How many times the large Block move to the right position
	 int barLengths = 0; 		//the longest bar length
	 int secBarLengths = 0;		//the second long bar length
	 int theHArmHeight = 0; 	//the height that the H arm should reach
	 int tag = 0; 				//A tag for calculate the second long bar
	 int blockHt = 3; 			//Initial Block heights
 
	 // use for loop to get the number of the every blockHeights, and calculate the high of the all block.
	 for (int i = 0; i < blockHeights.length; i++)
	 {
		 sourceHt = sourceHt + blockHeights[i];
	 }

     //use while loop to move all blocks.
     while (sourceHt > 0)
     {
    	 barLengths = 0;
    	 //Find which bar is the longest bar
    	 for (int i = 0; i < barHeights.length; i++)
    	 {
    		 if (barHeights[i] > barLengths)
    		 {
    			 barLengths = barHeights[i];
    			 tag = i;
    		 }
    	 }
    	  //Find which bar is the second long bar
    	 for (int i = 0; i < barHeights.length; i++)
    	 {
    		 if (i != tag)
    		 {
    			 if (barHeights[i] > secBarLengths)
        		 {
    				 secBarLengths = barHeights[i];
        		 }
    		 }
    		 
    	 }
    	 
    	 //if whole block length is Longer than bar length, reach to the block length + 1
    	 /*
    	  * Consider the bar length, the whole block lengths and the each block height.
    	  * If the whole block length is longer than bar length and the block length + current bar length is longer than bar length, the H arm will reach to whole block length.
    	  * If the whole block length is longer than bar length and the block length + current bar length is shorter than bar length, the H arm will reach to barLengths + blockHeights[currentBlockNumber] + 1. (1 is the W arm height)
    	  */
    	 if (barLengths < sourceHt)
		 {
    		 //If the whole block length is longer than bar length and the block length + current bar length is longer than bar length, the H arm will reach to whole block length.
			 int heightoftop = sourceHt;
			 if (h <= heightoftop + 1)
			 {
				 while ( h < heightoftop + 1 ) 
			     {
			         r.up();     
			         h++;
			     }
			 }
			 //If the whole block length is longer than bar length and the block length + current bar length is shorter than bar length, the H arm will reach to barLengths + blockHeights[currentBlockNumber] + 1. (1 is the W arm height)
			 if (d < barLengths + blockHeights[currentBlockNumber] + 1)
			 {
				 while (h < barHeights[currentBarNumber] + blockHeights[currentBlockNumber] + 1)
				 {
					 r.up();     
			         h++;
				 }
			 }
			 
		 }
		 //if the longest bar length is longer than whole block length
		 else if(barLengths >= sourceHt)
		 {
			 //consider the bar length, because the bar length will change if there has a large Block.
			 if (largeBlockTimes != 0)
			 {
				 //for minimize the movement. 
				 //if the h bar length shorter than bar length, move up.
				 if (h < barLengths)
				 {
					 int heightoftop = barLengths + 3;
					 while ( h < heightoftop) 
				     {
				         r.up();     
				         h++;
				     } 
				 }
			 }
			 //for minimize the movement.
			 //consider the D arm, the longest bar length, block height and W arm height.
			 //if H arm is shorter than the longest bar length + current block length + W arm height, the H arm will increase.
			 else if (d < barLengths + blockHeights[currentBlockNumber] + 1)
			 {
				 while (h < barLengths + blockHeights[currentBlockNumber] + 1)
				 {
					 r.up();     
			         h++;
				 }
			 }
			 //if H arm is longer than the longest bar length, the H arm reach to the longest bar length.
			 else
			 {
				 int heightoftop = barLengths;
				 while ( h < heightoftop) 
			     {
			         r.up();     
			         h++;
			     }
			 }
		 }
    	 
	     //How much steps the w arm need to move
	     int extendAmt = 10;
	
	     //Move the extend arm to the right side.
	     while ( w < extendAmt )
	     {
	        r.extend();
	        w++;
	     }
	
	     //Move the D arm lower to pick up the block
	     while ( h - d > sourceHt + 1)   
	     {
	        r.lower();
	        d++;
	     }

	     //Picking the block 
	     r.pick();

	     //Get current block height
	     blockHt = blockHeights[currentBlockNumber];
	
	     // When pick the block, the height of source will decreases
	     sourceHt = sourceHt - blockHt;
	
	     /*
	      * The Algorithm for minimum movement.
	      * The core of minimum movement is every movement is keep close to The longest bar!!
	      * Consider multiple circumstances.
	      * 1. The first bar is the longest bar.
	      * 2. The longest bar in the middle.
	      * 3. The longest bar in the end.
	      * 4. The block height is shorter than bar length.
	      * 5. The longest bar maybe will change.
	      * 6. The whole block heights maybe is longer than bar length
	      * 7. After pick the block the D arm should not every time reach to the top.
	      * 8. After drop the block the D arm should not every time reach to the top.
	      * 9. Different block size may lead to different circumstances.
	      */
	     //The Algorithm for the longest bar length is 10 and current block height is 3.
	     if (barLengths == 10 && blockHeights[currentBlockNumber] == 3 )
	     {
	    	 //Consider the end bar is the second long bar. If the end bar is the second long bar, the D arm need to raise.
	    	 if (barHeights[3] == 6 || barHeights[4] == 6 || barHeights[3] == 7 || barHeights[4] == 7)
	    	 {
	    		 //Find which bar is longest bar between to end bars.
	    		 if (barHeights[3] > barHeights[4])
	    		 {
	    			 while ( d > h - barHeights[3] - blockHeights[currentBlockNumber] -1)
				     {
				         r.raise();
				         d--;
				     }
	    		 }
	    		 else
	    		 {
	    			 while ( d > h - barHeights[4] - blockHeights[currentBlockNumber] -1)
				     {
				         r.raise();
				         d--;
				     }
	    		 }
	    		  
	    	 }
	    	 //if the end bar is not the second long bar, consider the second long bar, and judge the D arm raise or not. 
	    	 else if ( h - d < secBarLengths + blockHeights[currentBlockNumber] + 1)
		     {
		    	 while ( d > 0)
			     {
			         r.raise();
			         d--;
			     } 
		     }
	     }
	     
	     //The Algorithm for the largest bar on the middle
	     else if (largeBlockTimes > 0 && barLengths == 7)
	     {
	    	 while ( d > h - barLengths - blockHeights[currentBlockNumber] - 1)
		     {
		         r.raise();
		         d--;
		     }
	     }
	     //The Algorithm for move the small block.
	     else if (largeBlockTimes == 0 && smallBlockTimes !=0)
	     {
	    	 if (sourceHt >= barLengths)
	    	 {
	    		 while ( d > sourceHt)
			     {
			         r.raise();
			         d--;
			     }
	    	 }
	    	 //Consider that the last block is the small block
	    	 else if (sourceHt == 0)
	    	 {
		    	 while ( d > h - barLengths - blockHeights[currentBlockNumber] - 1)
			     {
			         r.raise();
			         d--;
			     }
	    	 }
	    	 //consider other circumstances
	    	 else
	    	 {
		    	 while ( d > 0)
			     {
			         r.raise();
			         d--;
			     }
	    	 }
	     }
	     //The Algorithm for move the middle block
	     else if (largeBlockTimes == 0 && smallBlockTimes == 0)
	     {
	    	 //Consider the whole block height is longer than bar length.
	    	 if (sourceHt >= barLengths)
	    	 {
	    		 while ( d > sourceHt - 1)
			     {
			         r.raise();
			         d--;
			     }
	    	 }
	    	 //Consider the whole block height is shorter than bar length, and it is not the last block.
	    	 else if (sourceHt < barLengths && sourceHt != 0)
	    	 {
	    		 if (d != 0)
	    		 {
	    			 while ( d > h - barLengths - blockHeights[currentBlockNumber] - 1)
				     {
				         r.raise();
				         d--;
				     }
	    		 }
	    	 }
	    	 //Consider the middle block is the last block.
	    	 else if (sourceHt == 0)
	    	 {
		    	 while ( d > 0)
			     {
			         r.raise();
			         d--;
			     }
	    	 }
	     }
	     //Consider movement the small block when already moved the large block and H arm is higher than the longest bar length.
	     else if(largeBlockTimes > 0 && blockHeights[currentBlockNumber] == 1 && h > (barLengths + blockHeights[currentBlockNumber] + 1) )
	     {
	    	 while ( d > h - barLengths - blockHeights[currentBlockNumber] - 1)
		     {
		         r.raise();
		         d--;
		     } 
	     }
	     //The Algorithm for other circumstances.
	     else
	     {
	    	 while ( d > 0)
		     {
		         r.raise();
		         d--;
		     }  
	     }
	     
	     int contractAmt = 7;
  
	     //The Algorithm for blockHeight is 1 
	     if (blockHt == 1)
	     {
	    	 /*
	    	  * Firstly, consider that the large block move times, if it moved more than one time,
	    	  * then, the bar length will increase. And the H arm need to reach bar length + large block length + current block length + 1 (the arm length).
	    	  * if the large block do not move,  the H arm need to reach bar length + current block length + 1 (the arm length).
	    	  * In the end, also need to consider that every time the place, which put the small block, will increase. so, set a variable, and every time + 1.
	    	  */
	    	 if (largeBlockTimes != 0)
	    	 {
	    		 //if the h arm shorter than the longest bar length + current block size + 1, then move up.
		    	 if (h < barLengths + blockHeights[currentBlockNumber] + 1)
		    	 {
		    		 while (h < barLengths + blockHeights[currentBlockNumber] + 1)
		    		 {
		    			 r.up();
			    		 h++; 
		    		 }
		    	 }
	    	 }
	    	 //Setting the second arm, because the small block is put in the first position.
	    	 contractAmt = 9;
	    	 
	    	 //Move the W arm to the right position.
	    	 //using method calculate how much the W arm need to be contracted
	    	 w = contractArm(contractAmt, w);
	    	 
	    	 //Move the block to the correct place
	    	 while (d < (h - 1) - blockHt - smallBlockTimes)
				{
					r.lower();
					d++;
				}
	    	 
	    	 //Drop the block
	    	 r.drop();
	    	 
	    	 /*
	    	  * The Algorithm for minimum movement.
	    	  * The Algorithm for D arm.
	    	  * Consider multiple circumstances.
	    	  * 1. If the block is not the last block.
	    	  * 2. If the block is the last block.
	    	  * 3. If do not move the large block.
	    	  * 4. If moved the large block.
	    	  * 5. If the bar length is longer than whole block height.
	    	  * 6. If the bar length is shorter than whole block height.
	    	  */
	    	 //If the block is not the last block.
	    	 if (sourceHt != 0)
	    	 {
	    		 //If do not move the large block.
	    		 if (largeBlockTimes == 0)
	    		 {
	    			 //If the bar length is longer than whole block height.
	    			 if (barLengths >= sourceHt)
	    			 {
	    				 while (d > h - barLengths - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			 //If the bar length is shorter than whole block height.
	    			 else
	    			 {
	    				 while (d > h - sourceHt - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			 
	    		 }
	    		 //If moved the large block.
	    		 else
	    		 {
	    			//If the bar length is longer than whole block height.
	    			 if (barLengths >= sourceHt)
	    			 {
	    				 while (d > h - barLengths - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			//If the bar length is shorter than whole block height.
	    			 else
	    			 {
	    				 while (d > h - sourceHt - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    		 }
	    		 
	    	 }
	    	 //If the block is the last block. Do not move.
	    	 else
	    	 {
	    		 break;
	    	 }
	    	 //Every time the block number will decrease 1
	    	 currentBlockNumber = currentBlockNumber - 1;
	    	 //Due to every time the "bar" which put the small block will increase, therefore, use a variable to calculate it.
	    	 smallBlockTimes = smallBlockTimes + 1;
	     }
	     
	     //The Algorithm for the blockHeight is 2
	     else if (blockHt == 2)
	     { 
	    	 /*
	    	  * Firstly, consider that the large block move times, if it moved more than one time,
	    	  * then, the bar length will increase. And the H arm need to reach bar length + large block length + current block length + 1 (the arm length).
	    	  * if the large block do not move, the H arm need to reach bar length + current block length + 1 (the arm length).
	    	  * In the end, also need to consider that every time the place, which put the middle block, will increase. so, set a variable, and every time + 2. 
	    	  */
	    	 if (largeBlockTimes != 0)
	    	 {
		    	 if (h < barLengths + blockHeights[currentBlockNumber] + 1)
		    	 {
		    		 while (h < barLengths + blockHeights[currentBlockNumber] + 1)
		    		 {
			    		 r.up();
			    		 h++;
		    		 }
		    	 }
	    	 }
	    	 else
	    	 {
	    		 if (h < barHeights[largeBlockTimes] + blockHeights[currentBlockNumber] + 1)
		    	 {
	    			 while (h < barHeights[largeBlockTimes] + blockHeights[currentBlockNumber] + 1)
	    			 {
	    				 r.up();
			    		 h++;
	    			 }
		    	 }
	    	 }
	    	//Setting the second arm, because the small block is put in the second position.
	    	 contractAmt = 8;
	    	 
	    	//Move the W arm to the right position.
	    	//using method calculate how much the W arm need to be contracted
	    	 w = contractArm(contractAmt, w);
	    	 
	    	//Move the block to the correct place
	    	 while (d < (h - 1) - blockHt - midBlockTimes)
				{
					r.lower();
					d++;
				}
	    	 
	    	 //Drop the block
	    	 r.drop();
	    	 
	    	 /*
	    	  * The Algorithm for minimum movement.
	    	  * The Algorithm for D arm.
	    	  * Consider multiple circumstances.
	    	  * 1. If the block is not the last block.
	    	  * 2. If the block is the last block.
	    	  * 3. If do not move the large block.
	    	  * 4. If moved the large block.
	    	  * 5. If the bar length is longer than whole block height.
	    	  * 6. If the bar length is shorter than whole block height.
	    	  */
	    	 //If the block is not the last block.
	    	 if (sourceHt != 0)
	    	 {
	    		 //If do not move the large block.
	    		 if (largeBlockTimes == 0)
	    		 {
	    			 //If the bar length is longer than whole block height.
	    			 if (barLengths >= sourceHt)
	    			 {
	    				 while (d > h - barHeights[largeBlockTimes] - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			 //If the bar length is shorter than whole block height.
	    			 else
	    			 {
	    				 while (d > h - sourceHt - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			 
	    		 }
	    		 //If moved the large block.
	    		 else
	    		 {
	    			 //If the bar length is longer than whole block height.
	    			 if (barLengths >= sourceHt)
	    			 {
	    				 while (d > h - barLengths - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    			 //If the bar length is shorter than whole block height.
	    			 else
	    			 {
	    				 while (d > h - sourceHt - 1)
		    			 {
		    				 r.raise();
								d--;
		    			 }
	    			 }
	    		 }
	    		 
	    	 }
	    	 //If the block is the last block. Do not move
	    	 else
	    	 {
	    		 break;
	    	 }
	    	 
	    	//Every time the block number will decrease 1
	    	 currentBlockNumber = currentBlockNumber - 1;
	    	//Due to every time the "bar" which put the middle block will increase, therefore, use a variable to calculate it.
	    	 midBlockTimes = midBlockTimes + 2;
	     }
	    //The Algorithm for the blockHeight is 3
	     else if (blockHt == 3)
	     { 
	    	 /*
	    	  * Firstly, consider how many times that the large block moved, because every time the large block put in different place, and it also will increase the bar length.
	    	  * Then, if it is the first time move the large block, the H arm need to reach the highest bar length + current block length + 1 (arm length).
	    	  * if it is not the first time move, the H arm need to reach the current bar length + current block length + 1.
	    	  * In the end, also need to consider that every time, the large block put in different place. so, set a variable, and every time + 1.
	    	  */
	    	//consider the bar height, because the bar height will increase if move the large block.
	    	 if (largeBlockTimes == 0)
	    	 {
	    		 //Using method to calculate the height that the H arm should reach
	    		 theHArmHeight = caculateHArmForLargeBlock(largeBlockTimes, barLengths, blockHeights, currentBlockNumber, barHeights, currentBarNumber);
	    		 if (h <= theHArmHeight)
		    	 {
	    			 h = goesUpForLargeBlock(h, theHArmHeight);
		    	 } 
	    	 }
	    	 else
	    	 {
	    		//Using method to calculate the height that the H arm should reach
	    		 theHArmHeight = caculateHArmForLargeBlock(largeBlockTimes, barLengths, blockHeights, currentBlockNumber, barHeights, currentBarNumber);
	    		 if (h <= theHArmHeight)
		    	 {
	    			 h = goesUpForLargeBlock(h, theHArmHeight);
		    	 }
	    	 }
	    	 
	    	 //calculate the number of the contract and which bar need to put. Because the large block need to put in different bar.
	    	 contractAmt = 7 - largeBlockTimes;
	    	 //record how many time that the large block moved, because Every time the arm will decrease 1, and the bar will increase.
	    	 largeBlockTimes = largeBlockTimes + 1;
	    	 
	    	 //Move the W arm to the right position.
	    	 //using method calculate how much the W arm need to be contracted
	    	 w = contractArm(contractAmt, w);
	    	 
	    	 //A Algorithm for minimum movement.
	    	 //A Algorithm for do a judgment for decrease the H arm or decrease the D arm
	    	 if (h > barLengths + blockHeights[currentBlockNumber] + 1 && barHeights[currentBarNumber] == 7)
	    	 {
	    		 while (h > barHeights[currentBarNumber] + blockHeights[currentBlockNumber] + d + 1)
	    		 {
	    			 r.down();
	    			 h--;
	    		 }
	    	 }
	    	 else
	    	 {
	    		 while (h - d - blockHt > barHeights[currentBarNumber] + 1)
					{
						r.lower();
						d++;
					}
	    	 }
	    	 
	    	 //Drop the block
	    	 r.drop();
	    	 barHeights[currentBarNumber] = barHeights[currentBarNumber] + 3;
	    	 
	    	 /*
	    	  * The Algorithm for minimum movement.
	    	  * The Algorithm for D arm.
	    	  * Consider multiple circumstances.
	    	  * 1. If the block is not the last block.
	    	  * 2. If the block is the last block.
	    	  * 3. If do not move the large block.
	    	  * 4. If moved the large block.
	    	  * 5. If the bar length is longer than whole block height.
	    	  * 6. If the bar length is shorter than whole block height.
	    	  */
	    	 //If the block is not the last block.
	    	 if (sourceHt != 0)
	    	 {
	    		 //If moved the large block, and the Height bar length is 10
	    		 if (largeBlockTimes > 0 && barHeights[0] == 10)
	    		 {
	    			 if (h - d < secBarLengths + 1)
	    			 {
	    				 while (d > h - secBarLengths + 1)
	 					{
	 						r.raise();
	 						d--;
	 					} 
	    			 }
	    		 }
	    		 else
	    		 {
	    			//If the bar length is shorter than whole block height.
	    			 if (sourceHt >= barLengths)
	    			 {
	    				 while (d > h - sourceHt - 1)
		 					{
		 						r.raise();
		 						d--;
		 					}
	    			 }
	    			 //If the bar length is shorter than whole block height.
	    			 else
	    			 {
	    				 while (d > h - barLengths - 1)
		 					{
		 						r.raise();
		 						d--;
		 					}
	    			 }
	    		 }
	    		 
	    	 }
	    	 //If the block is the last block, do not move
	    	 else
	    	 {
	    		 break;
	    	 }
	    	 
	    	 //Every time the bar number will add 1
	    	 currentBarNumber = currentBarNumber + 1;
	    	 //Every time the block number will decrease 1
	    	 currentBlockNumber = currentBlockNumber - 1;    	 
	     }
     }
   }
   
   //a method: when move the large block calculate the height that H arm should reach
   public int caculateHArmForLargeBlock(int largeBlockTimes, int barLengths, int blockHeights[], int currentBlockNumber, int barHeights[], int currentBarNumber)
   {
	   if (largeBlockTimes == 0)
  	 {
		 //if it is the first time move the large block, it just go to the highest bar Length + block Height.
		 int hArmHeight = 0;
		 hArmHeight = barLengths + blockHeights[currentBlockNumber] + 1;
		 return hArmHeight;
  	 }
	   //however, if it is not the first time move the large block, it have to go to current bar length + block Height. 
	   else
	   {
		   int hArmHeight = 0;
		   hArmHeight = barHeights[currentBarNumber] + blockHeights[currentBlockNumber] + 1;
		   return hArmHeight;
	   }
   }
   
   //a method: calculate how much the W arm need to be contracted
   public int contractArm(int contractAmt, int w)
   {
	   while ( contractAmt > 0 )
		{
			r.contract();
			contractAmt--;
			w--;
		}
	   return w;
   }
   
   //a method: calculate how much that the H arm need to be gone up.
   public int goesUpForLargeBlock(int h, int theHArmHeight)
   {
	   while ( h < theHArmHeight)
		 {
  		 r.up();
  		 h++; 
		 }
	   return h;
   }
}