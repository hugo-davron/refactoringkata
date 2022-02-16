package com.hudavron;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Trivia {

    ArrayList<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
	private static final String SPORTS = "Sports";
	private static final String SCIENCE = "Science";
	private static final Logger LOGGER = Logger.getLogger("Trivia");
    
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Trivia(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast((SCIENCE + "Question " + i));
			sportsQuestions.addLast((SPORTS + "Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    LOGGER.info(playerName + " was added");
	    LOGGER.info("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		LOGGER.info(players.get(currentPlayer) + " is the current player");
		LOGGER.info("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				LOGGER.info(players.get(currentPlayer) + " is getting out of the penalty box");
				placeRoll(roll);
			} else {
				LOGGER.info(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			placeRoll(roll);
		}
		
	}

	private void placeRoll(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		LOGGER.info(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		LOGGER.info("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			LOGGER.info(popQuestions.removeFirst());
		if (currentCategory().equals(SCIENCE))
			LOGGER.info(scienceQuestions.removeFirst());
		if (currentCategory().equals(SPORTS))
			LOGGER.info(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			LOGGER.info(rockQuestions.removeFirst());
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9) return SCIENCE;
		if (places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10) return SPORTS;
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				LOGGER.info("Answer was correct!!!!");
				return pursePlus();
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			LOGGER.info("Answer was corrent!!!!");
			return pursePlus();
		}
	}

	private boolean pursePlus() {
		purses[currentPlayer]++;
		LOGGER.info(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer(){
		LOGGER.info("Question was incorrectly answered");
		LOGGER.info(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
