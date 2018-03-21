package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ship.Ship;
import world.World;

/**
 * Monte Carlo guess player (task C). Please implement this class.
 *
 * @author Youhan, Jeffrey
 * @author Harold Zang
 */
public class MonteCarloGuessPlayer implements Player {
	private World world;
	private HashMap<String, Status[][]> shipMap;
	private ArrayList<World.ShipLocation> shipLocations, tempArrayList;
	private ArrayList<Ship> opptShips;
	private Status[][] boardStatus, shipStatus;
	private ArrayList<World.Coordinate> highestCounts;
	private ArrayList<Guess> huntingHistory;
	private Random random = new Random();
	// private World.Coordinate priorityCdn = null;
	private ArrayList<Guess> parDatas, verDatas;

	@Override
	public void initialisePlayer(World world) {
		highestCounts = new ArrayList<World.Coordinate>();
		huntingHistory = new ArrayList<Guess>();
		this.world = world;
		shipMap = new HashMap<String, Status[][]>();
		this.parDatas = new ArrayList<Guess>();
		this.verDatas = new ArrayList<Guess>();
		boardStatus = new Status[world.numRow][world.numColumn];

		shipLocations = world.shipLocations;
		tempArrayList = (ArrayList<World.ShipLocation>) shipLocations.clone();
		;
		// create opponent ships list
		opptShips = new ArrayList<Ship>();
		for (int i = 0; i < this.tempArrayList.size(); i++) {
			opptShips.add(tempArrayList.get(i).ship);
		}

		// create world Coordinate and init count to 0
		for (int x = 0; x < world.numRow; x++) {
			for (int y = 0; y < world.numColumn; y++) {
				Status status = new Status();
				status.isShot = false;
				status.count = 0;
				this.boardStatus[x][y] = status;
				// init shipMap base one real ship number
				for (int i = 0; i < shipLocations.size(); i++) {
					String shipName = shipLocations.get(i).ship.name();
					shipStatus = shipMap.get(shipName);
					status = new Status();
					status.isShot = false;
					status.count = 0;
					if (shipStatus == null) {
						shipStatus = new Status[world.numRow][world.numColumn];
						shipMap.put(shipName, shipStatus);
					}
					shipStatus[x][y] = status;
				}

			}
		}
		this.calculatePosibilityCount();
	} // end of initialisePlayer()

	@Override
	public Answer getAnswer(Guess guess) {
		Answer answer = new Answer();
		ArrayList<World.Coordinate> cdns;
		World.Coordinate cdn;
		Ship ship;
		for (int i = 0; i < this.shipLocations.size(); i++) {
			cdns = this.shipLocations.get(i).coordinates;
			ship = this.shipLocations.get(i).ship;
			for (int m = 0; m < cdns.size(); m++) {
				cdn = cdns.get(m);
				if (cdn.row == guess.row && cdn.column == guess.column) {
					answer.isHit = true;
					cdns.remove(m);
					// check whether current ship is sunk
					if (cdns.isEmpty()) {
						answer.shipSunk = ship;
						this.shipLocations.remove(i);
						return answer;
					}
				}
			}
		}// end for loop

		return answer;
	} // end of getAnswer()

	@Override
	public Guess makeGuess() {
		// To find out coordinates with high possibility count
		int highestCount = 0;
		World.Coordinate cdn;
		Status status = null;
		Guess historyGuess, guess;
		int xCdn, yCdn, newXCdn, newYCdn;

		cdn = this.world.new Coordinate();

		// ---- start of hunting mode --
		if (huntingHistory.size() > 1) {
			Guess historyGuess1, historyGuess2;
			historyGuess1 = huntingHistory.get(0);
			historyGuess2 = huntingHistory.get(huntingHistory.size() - 1);

			// fire to east direction
			if (historyGuess1.row < historyGuess2.row) {
				cdn = this.world.new Coordinate();
				cdn.row = historyGuess2.row;
				cdn.column = historyGuess2.column;
				guess = this.fireToEast(cdn);
				if (guess != null) {
					return guess;
				}
				// fire to opposite direction
				cdn.row = historyGuess1.row;
				cdn.column = historyGuess1.column;
				guess = this.fireToWest(cdn);

				if (guess != null) {
					return guess;
				}
			}
			// fire to west direction
			else if (historyGuess1.row > historyGuess2.row) {
				cdn = this.world.new Coordinate();
				cdn.row = historyGuess2.row;
				cdn.column = historyGuess2.column;
				guess = this.fireToWest(cdn);
				if (guess != null) {
					return guess;
				}

				cdn.row = historyGuess1.row;
				cdn.column = historyGuess1.column;
				guess = this.fireToEast(cdn);

				if (guess != null) {
					return guess;
				}
			}
			// fire to north direction
			else if (historyGuess1.column > historyGuess2.column) {
				cdn = this.world.new Coordinate();
				cdn.row = historyGuess2.row;
				cdn.column = historyGuess2.column;
				guess = this.fireToNorth(cdn);
				if (guess != null) {
					return guess;
				}

				cdn.row = historyGuess1.row;
				cdn.column = historyGuess1.column;
				guess = this.fireToSouth(cdn);

				if (guess != null) {
					return guess;
				}
			}
			// fire to south direction
			else if (historyGuess1.column < historyGuess2.column) {
				cdn = this.world.new Coordinate();
				cdn.row = historyGuess2.row;
				cdn.column = historyGuess2.column;
				guess = this.fireToSouth(cdn);
				if (guess != null) {
					return guess;
				}

				cdn.row = historyGuess1.row;
				cdn.column = historyGuess1.column;
				guess = this.fireToNorth(cdn);

				if (guess != null) {
					return guess;
				}
			}

			// copy hunting history
			for (int i = 0; i < huntingHistory.size(); i++) {
				this.parDatas.add(huntingHistory.get(i));
			}
			this.huntingHistory.clear();
			this.huntingHistory.add(this.parDatas.get(0));
			this.parDatas.remove(0);
		} // end of huntingHistory size == 1 check

		// previous guess hits the ship
		if (this.huntingHistory.size() == 1) {
			// to find out which direction we can choose to shot
			historyGuess = huntingHistory.get(0);
			xCdn = historyGuess.row;
			yCdn = historyGuess.column;

			// check east direction
			newXCdn = xCdn + 1;
			if (newXCdn < this.world.numRow) {
				if (!this.boardStatus[newXCdn][yCdn].isShot) {
					status = this.boardStatus[newXCdn][yCdn];
					cdn.row = newXCdn;
					cdn.column = yCdn;
				}
			}
			newXCdn = xCdn;

			// check south direction
			newYCdn = yCdn + 1;
			if (newYCdn < this.world.numColumn) {
				if (!this.boardStatus[xCdn][newYCdn].isShot) {
					if (status == null) {
						if (!this.boardStatus[xCdn][newYCdn].isShot) {
							status = this.boardStatus[xCdn][newYCdn];
							cdn.row = xCdn;
							cdn.column = newYCdn;
						}
					}
					if (status.count < this.boardStatus[xCdn][newYCdn].count) {
						status = this.boardStatus[xCdn][newYCdn];
						cdn.row = xCdn;
						cdn.column = newYCdn;
					}
				}
			}
			newYCdn = yCdn;

			// check west direction
			newXCdn = xCdn - 1;
			if (newXCdn >= 0) {
				if (!this.boardStatus[newXCdn][yCdn].isShot) {
					if (status == null) {
						status = this.boardStatus[newXCdn][yCdn];
						cdn.row = newXCdn;
						cdn.column = yCdn;
					} else if (status.count < this.boardStatus[newXCdn][yCdn].count) {
						status = this.boardStatus[newXCdn][yCdn];
						cdn.row = newXCdn;
						cdn.column = yCdn;
					}
				}
			}
			newXCdn = xCdn;

			// check north direction
			newYCdn = yCdn - 1;
			if (newYCdn >= 0) {
				if (!this.boardStatus[xCdn][newYCdn].isShot) {
					if (status == null) {
						status = this.boardStatus[xCdn][newYCdn];
						cdn.row = xCdn;
						cdn.column = newYCdn;
					} else if (status.count < this.boardStatus[xCdn][newYCdn].count) {
						status = this.boardStatus[xCdn][newYCdn];
						cdn.row = xCdn;
						cdn.column = newYCdn;
					}
				}
			}

			if (status != null) {
				guess = new Guess();
				guess.column = cdn.column;
				guess.row = cdn.row;

				status.isShot = true;
				status.count = 0;
				return guess;
			}

		} // end of history arrayList size == 1 check
			// ---- end of hunting mode -----

		if (huntingHistory.size() > 0) {
			huntingHistory.clear();
		}

		this.calculatePosibilityCount();
		// if no hunting history
		for (int x = 0; x < this.world.numRow; x++) {
			for (int y = 0; y < this.world.numColumn; y++) {

				if (this.boardStatus[x][y].count == 0) {
					continue;
				}

				if (this.boardStatus[x][y].count > highestCount) {
					highestCounts.clear();
					cdn = this.world.new Coordinate();
					cdn.row = x;
					cdn.column = y;
					highestCounts.add(cdn);
					highestCount = this.boardStatus[x][y].count;
				} else if (this.boardStatus[x][y].count == highestCount) {
					cdn = this.world.new Coordinate();
					cdn.row = x;
					cdn.column = y;
					highestCounts.add(cdn);
				} else {
					continue;
				}
			}
		}

		if (highestCounts.isEmpty()) {
			return null;
		}

		// randomly choose a highest coordinate
		guess = new Guess();
		cdn = highestCounts.get(random.nextInt(highestCounts.size()));

		guess.column = cdn.column;
		guess.row = cdn.row;

		status = this.boardStatus[cdn.row][cdn.column];
		status.count = 0;
		status.isShot = true;

		highestCounts.clear();
		return guess;
	} // end of makeGuess()

	@Override
	public void update(Guess guess, Answer answer) {

		if (answer.shipSunk == null) {
			if (answer.isHit) {
				this.huntingHistory.add(guess);
			}
		} else {
			Ship opptShip;
			// remove sunk ship
			for (int i = 0; i < this.opptShips.size(); i++) {
				opptShip = this.opptShips.get(i);
				if (opptShip.name().equals(answer.shipSunk.name())) {
					this.opptShips.remove(i);
				}
			}

			// remove coordinate of current sunk ship
			if (guess.row < this.huntingHistory.get(0).row) {
				z: for (int m = 1; m < answer.shipSunk.len(); m++) {
					for (int n = 0; n < this.huntingHistory.size(); n++) {
						if (guess.row + m == huntingHistory.get(n).row) {
							huntingHistory.remove(n);
							continue z;
						}
					}
				}
			} else if (guess.row > this.huntingHistory.get(0).row) {
				z: for (int m = 1; m < answer.shipSunk.len(); m++) {
					for (int n = 0; n < this.huntingHistory.size(); n++) {
						if (guess.row - m == huntingHistory.get(n).row) {
							huntingHistory.remove(n);
							continue z;
						}
					}
				}
			} else if (guess.column < this.huntingHistory.get(0).column) {
				z: for(int m = 1; m < answer.shipSunk.len(); m++){
					for(int n = 0; n < this.huntingHistory.size(); n++){
						if(guess.column + m == this.huntingHistory.get(n).column){
							huntingHistory.remove(n);
							continue z;
						}
					}
				}
			}else if(guess.column > this.huntingHistory.get(0).column){
				z: for(int m = 1; m < answer.shipSunk.len(); m++){
					for(int n = 0; n < this.huntingHistory.size(); n++){
						if(guess.column - m == this.huntingHistory.get(n).column){
							huntingHistory.remove(n);
							continue z;
						}
					}
				}
			}

			// copy vertical ship coordinate
			if (huntingHistory.size() > 0) {
				for (Guess temp : this.huntingHistory) {
					this.verDatas.add(temp);
				}
				this.huntingHistory.clear();
			}

			if (this.verDatas.size() > 0) {
				huntingHistory.add(this.verDatas.get(0));
				this.verDatas.remove(0);
			} else if (this.parDatas.size() > 0) {
				this.huntingHistory.add(this.parDatas.get(0));
				this.parDatas.remove(0);
			}
		}
		this.calculatePosibilityCount();
	} // end of update()

	@Override
	public boolean noRemainingShips() {
		return this.shipLocations.isEmpty();
	} // end of noRemainingShips()
	
	private void resetBoardDatas(){
		for(int m = 0; m < this.world.numRow; m++){
			for(int n = 0; n < this.world.numColumn; n++){
				this.boardStatus[m][n].count = 0;
			}
		}
	}
	
	/**
	 * calculate possibility count of each coordinate
	 * */
	private void calculatePosibilityCount() {
		Status status;
		ArrayList<Status> tempAl = new ArrayList<Status>();
		ArrayList<Status> finalAl = new ArrayList<Status>();
		Ship ship;
		
		this.resetBoardDatas();

		for (int i = 0; i < this.opptShips.size(); i++) {
			ship = this.opptShips.get(i);
			String shipName = ship.name();
			shipStatus = this.shipMap.get(shipName);
			for (int x = 0; x < this.world.numRow; x++) {
				for (int y = 0; y < this.world.numColumn; y++) {
					status = this.boardStatus[x][y];
					// check current coordinate
					if (!status.isShot) {
						finalAl.add(status);
						// check whether current point can have a car
						// check row direction
						for (int m = 1; m < ship.len(); m++) {
							// go right
							if (x + m < world.numRow) {
								if (boardStatus[x + m][y].isShot) {
									tempAl.clear();
									break;
								}
								tempAl.add(this.boardStatus[x + m][y]);
							} else {
								tempAl.clear();
								break;
							}
						}

						// add tempData to arrayList we will update
						if (tempAl.size() > 0) {
							for (int m = 0; m < tempAl.size(); m++) {
								finalAl.add(tempAl.get(m));
							}
							tempAl.clear();
						}

						// check right direction
						for (int m = 1; m < ship.len(); m++) {
							if (x - m >= 0) {
								if (boardStatus[x - m][y].isShot) {
									tempAl.clear();
									break;
								}
								tempAl.add(boardStatus[x - m][y]);
							} else {
								tempAl.clear();
								break;
							}
						}

						if (tempAl.size() > 0) {
							for (int m = 0; m < tempAl.size(); m++) {
								finalAl.add(tempAl.get(m));
							}
							tempAl.clear();
						}

						// check down direction
						for (int m = 1; m < ship.len(); m++) {
							if (y + m < this.world.numColumn) {
								if (boardStatus[x][y + m].isShot) {
									tempAl.clear();
									break;
								}
								tempAl.add(boardStatus[x][y + m]);
							} else {
								tempAl.clear();
								break;
							}
						}

						if (tempAl.size() > 0) {
							for (int m = 0; m < tempAl.size(); m++) {
								finalAl.add(tempAl.get(m));
							}
							tempAl.clear();
						}

						// check down direction
						for (int m = 1; m < ship.len(); m++) {
							if (y - m >= 0) {
								if (boardStatus[x][y - m].isShot) {
									tempAl.clear();
									break;
								}
								tempAl.add(boardStatus[x][y - m]);
							} else {
								tempAl.clear();
								break;
							}
						}

						if (tempAl.size() > 0) {
							for (int m = 0; m < tempAl.size(); m++) {
								finalAl.add(tempAl.get(m));
							}
							tempAl.clear();
						}
					}
				}// end of current coordinate check
			}

			if (finalAl.size() > 0) {
				for (int m = 0; m < finalAl.size(); m++) {
					finalAl.get(m).count++;
				}
				finalAl.clear();
			}
		}
	}

	/**
	 * Fire to East direction (right direction). direction base on our
	 * requirement.
	 * 
	 * @param startCdn
	 *            start coordinate for this hit, current hit will hit will hit
	 *            to a coordinate next to it
	 * 
	 * @return Guess a Guess instance which at right side of startCdn
	 * */
	private Guess fireToEast(World.Coordinate startCdn) {
		Status status;
		if (startCdn.row + 1 < this.world.numRow) {
			if (!this.boardStatus[startCdn.row + 1][startCdn.column].isShot) {
				Guess guess = new Guess();
				guess.row = startCdn.row + 1;
				guess.column = startCdn.column;
				status = this.boardStatus[guess.row][guess.column];
				status.count = 0;
				status.isShot = true;
				return guess;
			}
		}
		return null;
	}

	/**
	 * Fire to West direction (left direction). direction base on our
	 * requirement.
	 * 
	 * @param startCdn
	 *            start coordinate for this hit, current hit will hit will hit
	 *            to a coordinate next to it
	 * 
	 * @return Guess a Guess instance which at right side of startCdn
	 * */
	private Guess fireToWest(World.Coordinate startCdn) {
		Status status;
		if (startCdn.row - 1 >= 0) {
			if (!this.boardStatus[startCdn.row - 1][startCdn.column].isShot) {
				Guess guess = new Guess();
				guess.row = startCdn.row - 1;
				guess.column = startCdn.column;
				status = this.boardStatus[guess.row][guess.column];
				status.count = 0;
				status.isShot = true;
				return guess;
			}
		}
		return null;
	}

	/**
	 * Fire to North direction (up direction). direction base on our
	 * requirement.
	 * 
	 * @param startCdn
	 *            start coordinate for this hit, current hit will hit will hit
	 *            to a coordinate next to it
	 * 
	 * @return Guess a Guess instance which at right side of startCdn
	 * */
	private Guess fireToNorth(World.Coordinate startCdn) {
		Status status;
		if (startCdn.column - 1 >= 0) {
			if (!this.boardStatus[startCdn.row][startCdn.column - 1].isShot) {
				Guess guess = new Guess();
				guess.row = startCdn.row;
				guess.column = startCdn.column - 1;
				status = this.boardStatus[guess.row][guess.column];
				status.count = 0;
				status.isShot = true;
				return guess;
			}
		}
		return null;
	}

	/**
	 * Fire to South direction (down direction). direction base on our
	 * requirement.
	 * 
	 * @param startCdn
	 *            start coordinate for this hit, current hit will hit will hit
	 *            to a coordinate next to it
	 * 
	 * @return Guess a Guess instance which at right side of startCdn
	 * */
	private Guess fireToSouth(World.Coordinate startCdn) {
		Status status;
		if (startCdn.column + 1 < this.world.numColumn) {
			if (!this.boardStatus[startCdn.row][startCdn.column + 1].isShot) {
				Guess guess = new Guess();
				guess.row = startCdn.row;
				guess.column = startCdn.column + 1;
				status = this.boardStatus[guess.row][guess.column];
				status.count = 0;
				status.isShot = true;
				return guess;
			}
		}
		return null;
	}

	class Status {
		boolean isShot;
		int count;
	}
} // end of class MonteCarloGuessPlayer