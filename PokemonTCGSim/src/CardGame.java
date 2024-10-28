import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * CardGame --- program that uses Player objects and user input from Scanner
 * to play a 2 player game of the Pokémon Trading Card Game
 * @author Nick Domenico
 */
public class CardGame extends Player {

	Random rand = new Random();
	Scanner in = new Scanner(System.in);
	
	// the two Player objects
	Player player1 = new Player("Player 1");
	Player player2 = new Player("Player 2");
	
	/**
	 *  references to Player objects that will be 
	 *  switched between player1 and player2 as the game progresses
	 */
	private Player player;
	private Player opponent;
	
	// instance variables
	private int currentTurn = 0;
	private boolean energyAttached = false;
	private boolean supporterUsed = false;
	private boolean retreated = false;
	private int recoveryTurn = 0;
	
	// getters and setters
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer() {
		player = player1;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public void setOpponent() {
		opponent = player2;
	}
	
	public void setCurrentTurn(int turn) {
		currentTurn = turn;
	}
	
	
	/**
	 * Method that will be called from main to start the game
	 */
	public void startGame() {
		
		int playerCallingCoinFlip = rand.nextInt(2) + 1;
		
		if (playerCallingCoinFlip == 1) {
			callCoinFlip(player1, player2);
		}
		else if (playerCallingCoinFlip == 2) {
			callCoinFlip(player2, player1);
		}
		
	
		setUp(player1, 1);
		setUp(player2, 2);
		
		drawMulligans(player1, player2);
		drawMulligans(player2, player1);
		
		player1.drawPrizes();
		player2.drawPrizes();
		
		player1.selectStartingPokémon();
		player2.selectStartingPokémon();
		
		startTurn();
	}
	
	
	/**
	 * Prompts either player1 or player2 to call the opening coin 
	 * flip as heads or tails to determine which player will go first.
	 * 
	 * Whichever player wins the coin flip will then decide if they want
	 * to go first or second.
	 * @param playerCalling
	 * @param otherPlayer
	 */
	public void callCoinFlip(Player playerCalling, Player otherPlayer) {
		
		int coinFlipCall;
		int choice;
		
		
		System.out.println(playerCalling.getPlayerName() + ", call the starting coin flip (Input 1 for heads, 2 for tails)");
		coinFlipCall = in.nextInt();

		
		int coinFlipResult = flipCoin();

		// playerCalling wins the coin flip
		if (coinFlipCall == coinFlipResult) {

			do {
				System.out.println("\n" + playerCalling.getPlayerName() + " won the coin flip \nWould you like to go 1st? (Input 1 for yes, 0 for no)");
				choice = in.nextInt();
			} while (choice != 0 && choice != 1);
			
			if (choice == 1) {
				player = playerCalling;
				opponent = otherPlayer;
			}
			else {
				player = otherPlayer;
				opponent = playerCalling;
			}
		}
		
		// otherPlayer wins the coin flip
		else {
			do {
				System.out.println("\n" + otherPlayer.getPlayerName() + " won the coin flip \nWould you like to go 1st? (Input 1 for yes, 0 for no)");
				choice = in.nextInt();
			} while (choice != 0 && choice != 1);
			
			if (choice == 1) {
				player = otherPlayer;
				opponent = playerCalling;
			}
			else {
				player = playerCalling;
				opponent = otherPlayer;
			}
		}
	}
	
	
	/**
	 * Initializes the deck and draws an opening hand for player.
	 * deckNumber determines whether player will have the Cinderace V
	 * deck or the Pikachu V deck.
	 * @param player
	 * @param deckNumber
	 */
	public void setUp(Player player, int deckNumber) {
		if (deckNumber == 1) {
			player.fillDeckCinderace();
		}
		else if (deckNumber == 2) {
			player.fillDeckPikachu();
		}
		player.drawHand();

		boolean basicInHand = player.checkHand();
		
		while (basicInHand == false) {
			player.addToMulligansDrawn();
			player.resetHand();
			player.resetDeck();
			if (deckNumber == 1) {
				player.fillDeckCinderace();
			}
			else if (deckNumber == 2) {
				player.fillDeckPikachu();
			}
			player.drawHand();
			basicInHand = player.checkHand();
		}
		
	}	
	
	
	/**
	 * Prompts player if they want to draw cards 
	 * for the mulligans that opponent drew
	 * @param player
	 * @param opponent
	 */
	public void drawMulligans(Player player, Player opponent) {
		if (opponent.getMulligansDrawn() != 0) {
			System.out.println("\n" + opponent.getPlayerName() + " drew " + opponent.getMulligansDrawn() + " mulligans.");
			System.out.println(player.getPlayerName() + ", would you like to draw for mulligans? (Input 1 for yes, 0 for no)");
			int player1Choice = in.nextInt();
			if (player1Choice == 1) {
				player.drawCards(opponent.getMulligansDrawn());
			}
		}
	}
	
	
	/**
	 * Starts a turn of the game by first checking to see if player 
	 * needs to promote a new Active Pokémon, then checking to see if 
	 * they have cards in their deck to draw.
	 * 
	 * If player has no more Pokémon on the field, they lose, or if player
	 * has no more cards in their deck, they also lose.
	 * 
	 * This is also where cantAttackStatus is reset, only used for 
	 * Boltund's attack2Effect()
	 */
	public void startTurn() {
		currentTurn++;
		
		if (player.getActiveSpot().size() == 0) {
			if (player.getBench().size() == 0) {
				System.out.println("\n" + player.getPlayerName() + " has no more Pokémon on the field");
				opponent.winGame();
			}
			player.promoteNewActive();
		}
		
		System.out.println("\nTurn " + currentTurn + ", " + player.getPlayerName() + "'s turn:");
		
		if (player.getActiveSpot().get(0).getCantAttackStatus() == true && currentTurn == recoveryTurn) {
			player.getActiveSpot().get(0).setCantAttackStatus(false);
			System.out.println("\n" + player.getActiveSpot().get(0) + " can attack again");
		}
		
		if (player.getDeck().size() == 0) {
			System.out.println("\n" + player.getPlayerName() + " has no cards in their deck");
			opponent.winGame();
		}
		
		player.drawCards(1);
		turnActions();
		
	}
	
	
	/**
	 * Prompts player to select an action that they can take during their turn. 
	 * They can either inspect an area on the board, play a Card from their hand,
	 * retreat their Active Pokémon, use and attack, or end their turn.
	 */
	public void turnActions() {
		System.out.println("\n" + player.getPlayerName() + ", what would you like to do? (Input the number corresponding to the action) \n[1] - Inspect an area of the board \n[2] - Play a card from your hand \n[3] - Retreat your Active Pokémon \n[4] - Use an attack \n[5] - End your turn");
		int playerAction = in.nextInt();
		if (playerAction == 1) {
			inspectBoardAreaChoice();
		}
		else if (playerAction == 2) {
			playACard();
		}
		else if (playerAction == 3) {
			retreatActive();
		}
		else if (playerAction == 4) {
			if (currentTurn == 1) {
				System.out.println("\nYou can't attack on your first turn if you go first");
				turnActions();
			}
			useAttack();
		}
		else if (playerAction == 5) {
			endTurn();
		}
	}
	
	
	/**
	 * Prompt for player to select which area of the board 
	 * they would like to inspect after chosing to from turnActions()
	 */
	public void inspectBoardAreaChoice() {
		System.out.println("\nWhat would you like to inspect? (Input the number corresponding to the option)\n[1] - Your Active Pokémon \n[2] - Your Benched Pokémon \n[3] - Your hand \n[4] - Your Discard Pile \n[5] - Your deck \n[6] - Your Prize Cards \n[7] - Your opponent's Active Pokémon \n[8] - Your opponent's Benched Pokémon \n[9] - Your opponent's hand \n[10] - Your opponent's Discard Pile \n[11] - Your opponent's deck \n[12] - Your opponent's Prize Cards \n[0] - Go back");
		int playerChoice = in.nextInt();
		if (playerChoice == 1) {
			System.out.println("\n" + player.getActiveSpot().get(0).getAllCardText());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 2) {
			inspectBoardArea(player.getBench());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 3) {
			inspectBoardArea(player.getHand());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 4) {
			inspectBoardArea(player.getDiscardPile());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 5) {
			System.out.println("\nYou have " + player.getDeck().size() + " cards in your deck");
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 6) {
			System.out.println("\nYou have " + player.getPrizeCards().size() + " Prize Cards remaining");
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 7) {
			System.out.println("\n" + opponent.getActiveSpot().get(0).getAllCardText());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 8) {
			inspectBoardArea(opponent.getBench());
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 9) {
			System.out.println("\nYour opponent has " + opponent.getHand().size() + " cards in their hand");
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 10) {
			inspectBoardArea(opponent.getDiscardPile());
		}
		else if (playerChoice == 11) {
			System.out.println("\nYour opponent has " + opponent.getDeck().size() + " cards in their deck");
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 12) {
			System.out.println("\nYour opponent has " + opponent.getPrizeCards().size() + " Prize Cards remaining");
			inspectBoardAreaChoice();
		}
		else if (playerChoice == 0) {
			turnActions();
		}
	}
	
	
	/**
	 * Called from inspectBoardAreaChoice()
	 * Displays all of the Cards in area if 
	 * that area is visible to the player.
	 * @param area
	 */
	public void inspectBoardArea(ArrayList<Card> area) {
		int playerChoice;
		if (area.size() == 0) {
			System.out.println("\nThere are no cards there");
			inspectBoardAreaChoice();
		}
		else if (area.size() == 1) {
			System.out.println("\n" + area + "\nWould you like to inspect the card? (Input 1 for yes, 0 for no)");
			playerChoice = in.nextInt();
			if (playerChoice == 1) {
				System.out.println("\n" + area.get(0).getAllCardText());
				inspectBoardAreaChoice();
			}
			inspectBoardAreaChoice();
		}
		else {
			System.out.println("\n" + area + "\nTo inspect a card, input 1-" + area.size() + ". To go back, input 0");
			playerChoice = in.nextInt();
			if (playerChoice == 0) {
				inspectBoardAreaChoice();
			}
			else {
				System.out.println("\n" + area.get(playerChoice - 1).getAllCardText());
				inspectBoardArea(area);
			}
		}
		
	}
	
	
	/**
	 * Prompts player to select a Card from an area
	 * @param area
	 */
	public int selectCard(ArrayList<Card> area) {
		System.out.println("\n" + area);
		System.out.println("Select a card (Input 1-" + area.size() + " to select, or input 0 to go back)");
		int cardSelected = in.nextInt();
		if (cardSelected == 0) {
			turnActions();
		}
		return cardSelected - 1;
	}
	
	
	/**
	 * Prompts player to select a Card from their hand to play, 
	 * then uses the effect of that Card based on the type
	 */
	public void playACard() {
		
		int cardSelectedIndex = selectCard(player.getHand());
		Card cardSelected = player.getHand().get(cardSelectedIndex);
		
		// play Basic Pokémon onto the Bench
		if (cardSelected instanceof Pokémon && cardSelected.getEvolutionStage() == 0) {
			if (player.getBench().size() > 5) {
				System.out.println("\nYour Bench is already full");
				turnActions();
			}
			
			player.getBench().add(cardSelected);
			player.getHand().remove(cardSelectedIndex);
			
			System.out.println("\nPut " + cardSelected + " on the Bench");
			turnActions();
		}
		// play an evolution Pokémon
		else if (cardSelected instanceof Pokémon && cardSelected.getEvolutionStage() != 0) {
			evolvePokémonSelection(player.getHand().get(cardSelectedIndex), cardSelectedIndex);
		}
		// play an Item
		else if (cardSelected instanceof Item) {
			System.out.println("\nUsed a " + cardSelected);
			itemEffects(cardSelected.itemEffect());
			player.discardCard(player.getHand(), cardSelectedIndex);
			turnActions();
		}
		// play a Supporter
		else if (cardSelected instanceof Supporter && supporterUsed == false) {
			if (currentTurn == 1) {
				System.out.println("\nYou can't use a Supporter on your first turn if you go first");
				turnActions();
			}
			System.out.println("\nUsed " + cardSelected);
			
			// this if statement is only used when playing Shauna. Since her effect shuffles your hand into your deck, you need to discard her first and then activate the effect, or else you will shuffle Shauna back into the deck and then discard a different card at the index where Shauna was
			if (cardSelected.supporterEffect() == 4) {
				player.discardCard(player.getHand(), cardSelectedIndex);
				supporterEffects(cardSelected.supporterEffect());
				supporterUsed = true;
				turnActions();
			}
			supporterEffects(cardSelected.supporterEffect());
			player.discardCard(player.getHand(), cardSelectedIndex);
			supporterUsed = true;
			turnActions();
		}
		// player already used Supporter this turn
		else if (cardSelected instanceof Supporter && supporterUsed == true) {
			System.out.println("\nYou have already used a Supporter this turn");
			turnActions();
		}
		// attach an Energy to a Pokémon
		else if (cardSelected instanceof Energy && energyAttached == false) {
			 attachEnergySelection(cardSelectedIndex);
			
		}
		// player already attached an Energy this turn
		else if (cardSelected instanceof Energy && energyAttached == true) {
			System.out.println("\nYou have already attached an Energy this turn");
			turnActions();
		}
	}
	
	
	/**
	 * Uses the effect of an Item Card based on itemEffectNumber
	 * Each number 1-6 corresponds to the 6 different Item Cards present across both decks
	 * @param itemEffectNumber
	 */
	public void itemEffects(int itemEffectNumber) {
		
		// Energy Recycler - shuffles up to 5 Basic Energies from discard back into deck
		if (itemEffectNumber == 1) { 
			
			int energiesInDiscard = 0;
			
			for (Card singleCard : player.getDiscardPile()) {
				if (singleCard instanceof BasicEnergy) {
					energiesInDiscard++;
				}
			}
			
			if (energiesInDiscard == 0) {
				
				System.out.println("\nYou have no energies in your Discard Pile, Energy Recycler was not used");
				turnActions();
				
			}
			
			int playerChoice = 0;
			
			if (energiesInDiscard <= 5) {
				System.out.println("\nYou have " + energiesInDiscard + " Basic Energies in your Discard Pile, how many would you like to shuffle back into your deck? \n(Input 1-" + energiesInDiscard + ")");
				
				playerChoice = in.nextInt();
				
				while (playerChoice > energiesInDiscard) {
					System.out.println("\nYou only have " + energiesInDiscard + " Basic Energies in your Discard Pile");
					playerChoice = in.nextInt();
				}
				
			}
			
			else if (energiesInDiscard > 5) {
				System.out.println("\nYou have " + energiesInDiscard + " Basic Energies in your Discard Pile, how many would you like to shuffle back into your deck? \n(Input 1-5)");
				
				playerChoice = in.nextInt();
				
				while (playerChoice > 5) {
					System.out.println("\nYou can only shuffle up to 5 Energies back into your deck");
					playerChoice = in.nextInt();
				}
			}
			
			System.out.println("\nShuffled " + playerChoice + " Energies back into your deck");

			for (int i = 0; i < playerChoice; i++) {
				for (int j = 0; j < player.getDiscardPile().size(); j++) {
					if (player.getDiscardPile().get(j) instanceof BasicEnergy) {
						player.getDeck().add(player.getDiscardPile().get(j));
						player.getDiscardPile().remove(j);
						break;
					}
				}
		
			}
			
		}
		
		// Energy Retrieval - puts up to 2 Basic Energies from discard into hand
		else if (itemEffectNumber == 2) { 
			
			int energiesInDiscard = 0;
			
			for (Card singleCard : player.getDiscardPile()) {
				if (singleCard instanceof BasicEnergy) {
					energiesInDiscard++;
				}
			}
			
			if (energiesInDiscard == 0) {
				
				System.out.println("\nYou have no energies in your Discard Pile, Energy Retrieval was not used");
				turnActions();
				
			}
			
			int playerChoice = 0;
			
			if (energiesInDiscard <= 2) {
				System.out.println("\nYou have " + energiesInDiscard + " Basic Energies in your Discard Pile, how many would you like to put into your hand? \n(Input 1-" + energiesInDiscard + ")");
				
				playerChoice = in.nextInt();
				
				while (playerChoice > energiesInDiscard) {
					System.out.println("\nYou only have " + energiesInDiscard + " Basic Energies in your Discard Pile");
					playerChoice = in.nextInt();
				}
				
			}
			
			else if (energiesInDiscard > 2) {
				System.out.println("\nYou have " + energiesInDiscard + " Basic Energies in your Discard Pile, how many would you like to put into your hand? \n(Input 1-2)");
				
				playerChoice = in.nextInt();
				
				while (playerChoice > 2) {
					System.out.println("\nYou can only put up to 2 Energies into your hand");
					playerChoice = in.nextInt();
				}
			}
			
			System.out.println("Put " + playerChoice + " Energies back into your hand");
			
			for (int i = 0; i < playerChoice; i++) {
				for (int j = 0; j < player.getDiscardPile().size(); j++) {
					if (player.getDiscardPile().get(j) instanceof BasicEnergy) {
						player.getHand().add(player.getDiscardPile().get(j));
						player.getDiscardPile().remove(j);
						break;
					}
				}
		
			}
			
		}
		
		/**
		 *  Great Ball - draws 7 cards from the deck, if a Pokémon was drawn in this way, 
		 *  player can put one into their hand. All other cards are shuffled back into deck
		 */
		else if (itemEffectNumber == 3) { 
			
			ArrayList<Card> greatBallSelection = new ArrayList<>();
			ArrayList<Integer> cardsInDeckIndices = new ArrayList<>();
			
			if (player.getDeck().size() >= 7) {
				
				for (int i = 0; i < 7; i++) {
					int cardToTakeIndex = rand.nextInt(player.getDeck().size());
					greatBallSelection.add(player.getDeck().get(cardToTakeIndex));
					cardsInDeckIndices.add(cardToTakeIndex);
					player.getDeck().remove(cardToTakeIndex);
				}
				
			}
			
			else if (player.getDeck().size() < 7) {
				
				int deckSize = player.getDeck().size();
				
				for (int i = 0; i < deckSize; i++) {
					int cardToTakeIndex = rand.nextInt(player.getDeck().size());
					greatBallSelection.add(player.getDeck().get(cardToTakeIndex));
					cardsInDeckIndices.add(cardToTakeIndex);
					player.getDeck().remove(cardToTakeIndex);
				}
				
			}
			
			boolean PokémonFound = false;
			
			for (Card singleCard : greatBallSelection) {
				if (singleCard instanceof Pokémon) {
					PokémonFound = true;
					break;
				}
			}
			
			if (PokémonFound == false) {
				
				for (Card singleCard : greatBallSelection) {
					player.getDeck().add(singleCard);
	;			}
				
				System.out.println("\nYou did not find a Pokémon with Great Ball's effect \n" + greatBallSelection + " \n\nShuffled the cards back into deck");
				
			}
			
			else if (PokémonFound == true) {
				
				System.out.println("\nYou drew these cards with Great Ball's effect: \n" + greatBallSelection + "\nWould you like to put a Pokémon into your hand? (Input 1 for yes, 0 for no)");
				
				int playerChoice = in.nextInt();
				
				if (playerChoice == 1) {
					System.out.println("\nSelect a Pokémon to put into your hand");
					int cardSelectedIndex = player.selectCard(greatBallSelection);
					
					while (!(greatBallSelection.get(cardSelectedIndex) instanceof Pokémon)) {
						System.out.println("\nThat is not a Pokémon \n\nSelect a Pokémon to put into your hand");
						cardSelectedIndex = player.selectCard(greatBallSelection);
					}
					
					System.out.println("\nPut a " + greatBallSelection.get(cardSelectedIndex) + " into your hand, shuffled the other cards back into your deck");
					
					player.getHand().add(greatBallSelection.get(cardSelectedIndex));
					greatBallSelection.remove(cardSelectedIndex);
					
					for (Card singleCard : greatBallSelection) {
						player.getDeck().add(singleCard);
					}
					
				}
				
				else if (playerChoice == 0) {
					for (Card singleCard : greatBallSelection) {
						player.getDeck().add(singleCard);
					}
					System.out.println("\nShuffled the cards back into your deck");
				}
			}

		}
		
		/**
		 *  Pokémon Catcher - flips a coin, if heads, player can switch 
		 *  their opponent's Active Pokémon with one of their Benched
		 */
		else if (itemEffectNumber == 4) { // Pokémon Catcher
			
			if (opponent.getBench().size() == 0) {
				
				System.out.println("\nYour opponent has no Benched Pokémon, Pokémon Catcher was not used");
				turnActions();
				
			}
			
			int coinFlipResult = player.flipCoin();
			
			if (coinFlipResult == 1) {
				
				System.out.println("\nChoose one of your opponent's Benched Pokémon to switch into their active");
				int benchedPokémonIndex = player.selectCard(opponent.getBench());
				
				System.out.println("\nSwitched opponent's Active " + opponent.getActiveSpot().get(0) + " with their Benched " + opponent.getBench().get(benchedPokémonIndex));
				
				opponent.getActiveSpot().add(opponent.getBench().get(benchedPokémonIndex));
				opponent.getBench().add(opponent.getActiveSpot().get(0));
				opponent.getActiveSpot().remove(0);
				opponent.getBench().remove(benchedPokémonIndex);
				
			}
			
			else if (coinFlipResult == 2) {
				System.out.println("\nPokémon Catcher failed");
			}
			
		}
		
		// Potion - heals 30 damage from one of player's Pokémon
		else if (itemEffectNumber == 5) { 
			
			System.out.println("\nDo you want to heal your Active Pokémon or one on your Bench? (Input 1 for Active, or 2 for Bench");
			
			int playerChoice = in.nextInt();
			
			if (playerChoice == 1) {
				
				if (player.getActiveSpot().get(0).getCurrentDamage() == 0) {
					System.out.println("\nYour Active " + player.getActiveSpot().get(0) + " is not damaged, Potion was not used");
					turnActions();
				}
				player.getActiveSpot().get(0).healDamage(30);
				
			}
			
			else if (playerChoice == 2) {
				
				System.out.println("\nChoose a Benched Pokémon to heal");
				
				int benchedPokémonIndex = player.selectCard(player.getBench());
				
				if (player.getBench().get(benchedPokémonIndex).getCurrentDamage() == 0) {
					System.out.println("\nYour Active " + player.getActiveSpot().get(0) + " is not damaged, Potion was not used");
					turnActions();
				}
				player.getBench().get(benchedPokémonIndex).healDamage(30);
				
			}
			
		}
		
		/**
		 *  Switch - switches player's Active Pokémon for one of 
		 *  their Benched without using Energy to retreat
		 */
		else if (itemEffectNumber == 6) {
			
			if (player.getBench().size() == 0) {
				System.out.println("\nYou have no Pokémon on your Bench, Switch was not used");
				turnActions();
			}
			
			else if (player.getBench().size() != 0) {
				System.out.println("\nSelect a Benched Pokémon to switch with your Active");
				
				int benchedPokémonIndex = player.selectCard(player.getBench());
				
				player.getActiveSpot().add(player.getBench().get(benchedPokémonIndex));
				player.getBench().add(player.getActiveSpot().get(0));
				player.getActiveSpot().remove(0);
				player.getBench().remove(benchedPokémonIndex);
			}

		}
		
	}
	
	
	/**
	 * Uses the effect of an Supporter Card based on supporterEffectNumber
	 * Each number 1-5 corresponds to the 5 different Supporter Cards present across both decks
	 * @param supporterEffectNumber
	 */
	public void supporterEffects(int supporterEffectNumber) {
		
		// Boss's Orders - player switches opponent's Active Pokémon for one of their Benched
		if (supporterEffectNumber == 1) {
			
			if (opponent.getBench().size() == 0) {
				System.out.println("\nYour opponent has no Benched Pokémon, Boss's Orders was not used");
				turnActions();
			}
			
			
			
			System.out.println("\nChoose one of your opponent's Benched Pokémon to switch into their active");
			int benchedPokémonIndex = player.selectCard(opponent.getBench());
			
			System.out.println("\nSwitched opponent's Active " + opponent.getActiveSpot().get(0) + " with their Benched " + opponent.getBench().get(benchedPokémonIndex));
			
			opponent.getActiveSpot().add(opponent.getBench().get(benchedPokémonIndex));
			opponent.getBench().add(opponent.getActiveSpot().get(0));
			opponent.getActiveSpot().remove(0);
			opponent.getBench().remove(benchedPokémonIndex);
			
		}
		
		/**
		 *  Bug Catcher - player draws 2 Cards from their deck then
		 *  flips a coin, if heads, they draw 2 more Cards
		 */
		if (supporterEffectNumber == 2) { 
			
			player.drawCards(2);
			
			if (player.flipCoin() == 1) {
				player.drawCards(2);
			}
			
		}
		
		// Hop - player draws 3 Cards from their deck
		if (supporterEffectNumber == 3) { 
			
			player.drawCards(3);
			
		}
		
		// Shauna - player shuffles their hand into their deck, then draw 5 cards
		if (supporterEffectNumber == 4) { 
			
			for (int i = 0; i < player.getHand().size(); i++) {
				player.getDeck().add(player.getHand().get(i));
			}
			player.getHand().clear();
			
			System.out.println("\nShuffled your hand into your deck");
			
			player.drawCards(5);
			
		}
		
		/**
		 * Sonia - player searches their deck for either up to 2 
		 * Basic Energy, or 2 Basic Pokémon and puts them into their hand
		 */
		if (supporterEffectNumber == 5) { 
			
			ArrayList<Card> soniaSelectionPokémon = new ArrayList<>();
			ArrayList<Integer> PokémonInDeckIndices = new ArrayList<>();
			
			ArrayList<Card> soniaSelectionEnergies = new ArrayList<>();
			ArrayList<Integer> energiesInDeckIndices = new ArrayList<>();
			
			for (int i = 0; i < player.getDeck().size(); i++) {
				if (player.getDeck().get(i) instanceof Pokémon && player.getDeck().get(i).getEvolutionStage() == 0) {
					soniaSelectionPokémon.add(player.getDeck().get(i));
					PokémonInDeckIndices.add(i);
				}
				if (player.getDeck().get(i) instanceof BasicEnergy) {
					soniaSelectionEnergies.add(player.getDeck().get(i));
					energiesInDeckIndices.add(i);
				}
			}
			
			
			if (soniaSelectionPokémon.size() == 0 && soniaSelectionEnergies.size() == 0) {
				System.out.println("\nYou did not find any Basic Pokémon or Basic Energies with Sonia's effect");
			}
			
			else if (soniaSelectionPokémon.size() == 0 && soniaSelectionEnergies.size() != 0) {
				
				System.out.println("\nYou found " + soniaSelectionEnergies.size() + " Basic Energies with Sonia's effect \nHow many would you like to put into your hand? (Input 0-2)");
				int playerInput = in.nextInt();
				
				for (int i = 0; i < playerInput; i++) {
					
					System.out.println("\nYou put a " + soniaSelectionEnergies.get(0) + " into your hand");
					int cardInDeckIndex = energiesInDeckIndices.get(0);
					
					player.getHand().add(soniaSelectionEnergies.get(0));
					player.getDeck().remove(cardInDeckIndex);
					soniaSelectionEnergies.remove(0);
					energiesInDeckIndices.remove(0);
					
				}
				
				if (playerInput == 0) {
					System.out.println("\nYou did not put any cards into your hand");
				}
				
			}
			
			else if (soniaSelectionPokémon.size() != 0 && soniaSelectionEnergies.size() == 0) {
				
				System.out.println("\nYou found these Basic Pokémon with Sonia's effect: " + soniaSelectionPokémon + "\nHow many would you like to put into your hand? (Input 0-2)");
				int playerInput = in.nextInt();
				
				for (int i = 0; i < playerInput; i++) {
					
					int cardSelectedIndex = player.selectCard(soniaSelectionPokémon);
					System.out.println("\nYou put a " + soniaSelectionPokémon.get(cardSelectedIndex) + " into your hand");
					
					int cardInDeckIndex = PokémonInDeckIndices.get(cardSelectedIndex);
					
					player.getHand().add(soniaSelectionPokémon.get(cardSelectedIndex));
					player.getDeck().remove(cardInDeckIndex);
					soniaSelectionPokémon.remove(cardSelectedIndex);
					PokémonInDeckIndices.remove(cardSelectedIndex);
					
				}
				
				if (playerInput == 0) {
					System.out.println("\nYou did not put any cards into your hand");
				}
				
			}
			
			else if (soniaSelectionPokémon.size() != 0 && soniaSelectionEnergies.size() != 0) {
				
				System.out.println("\nYou found these Basic Pokémon with Sonia's effect: " + soniaSelectionPokémon);
				System.out.println("\nYou found " + soniaSelectionEnergies.size() + " Basic Energies with Sonia's effect");
				System.out.println("\nWould you like to put Basic Pokémon, Basic Energies, or neither into your hand? (Input 1 for Pokémon, 2 for Energies, 3 for neither)");
				
				int playerSelection = in.nextInt();
				
				if (playerSelection == 1) {
					System.out.println("\nHow many would you like to put into your hand? (Input 1-2)");
					int playerInput = in.nextInt();
				
					for (int i = 0; i < playerInput; i++) {
					
						int cardSelectedIndex = player.selectCard(soniaSelectionPokémon);
						System.out.println("\nYou put a " + soniaSelectionPokémon.get(cardSelectedIndex) + " into your hand");
						int cardInDeckIndex = PokémonInDeckIndices.get(cardSelectedIndex);
						
						player.getHand().add(soniaSelectionPokémon.get(cardSelectedIndex));
						player.getDeck().remove(cardInDeckIndex);
						soniaSelectionPokémon.remove(cardSelectedIndex);
						PokémonInDeckIndices.remove(cardSelectedIndex);
					
					}
				}
				
				else if (playerSelection == 2) {
					System.out.println("\nHow many would you like to put into your hand? (Input 1-2)");
					int playerInput = in.nextInt();
				
					for (int i = 0; i < playerInput; i++) {
					
						System.out.println("\nYou put a " + soniaSelectionEnergies.get(0) + " into your hand");
						int cardInDeckIndex = energiesInDeckIndices.get(0);
						
						player.getHand().add(soniaSelectionEnergies.get(0));
						player.getDeck().remove(cardInDeckIndex);
						soniaSelectionEnergies.remove(0);
						energiesInDeckIndices.remove(0);
						
					}
				}
				else if (playerSelection == 3) {
					System.out.println("\nYou did not put any cards into your hand");
				}
				
			}
			
		}
		
	}
	
	
	/**
	 * Prompts player to select a Pokémon, PokémonToEvolveIntoIndex, that 
	 * they have in play to evolve into the evolution Pokémon, PokémonToEvolveInto, 
	 * that they are playing from their hand
	 * @param PokémonToEvolveInto
	 * @param PokémonToEvolveIntoIndex
	 */
	public void evolvePokémonSelection(Card PokémonToEvolveInto, int PokémonToEvolveIntoIndex) {
		
		String preEvolution = PokémonToEvolveInto.getPreEvolutions().get(0);
		
		System.out.println("\nWhich of your Pokémon would you like to evolve? \nInput 1 for Active Pokémon, 2 for Benched Pokémon");
		
		int playerChoice = in.nextInt();
		ArrayList<Card> evolutionArea;
		
		if (playerChoice == 1) {
			evolutionArea = player.getActiveSpot();
		}
		else {
			evolutionArea = player.getBench();
		}
		
		int cardSelectedIndex = selectCard(evolutionArea);
		
		if (evolutionArea.get(cardSelectedIndex).getCardName() == preEvolution && evolutionArea.get(cardSelectedIndex).getTurnsInPlay() != 0) {
			if (currentTurn == 1 || currentTurn == 2) {
				System.out.println("\nYou can't evolve a Pokémon on your first turn");
				turnActions();
			}
			else {
				evolvePokémon(evolutionArea, cardSelectedIndex, PokémonToEvolveIntoIndex);
			}
		}
		else if (evolutionArea.get(cardSelectedIndex).getCardName() != preEvolution) {
			System.out.println("\nThat Pokémon doesn't evolve into " + PokémonToEvolveInto);
			turnActions();
		}
		else if (evolutionArea.get(cardSelectedIndex).getTurnsInPlay() == 0) {
			System.out.println("\nThat Pokémon hasn't been in play for a turn yet");
			turnActions();
		}
		
	}
	
	
	/**
	 * Evolves a Pokémon at areaOfPreEvolution.get(CardBeingEvolvedIntoIndex) into
	 * the evolution Pokémon at player.getHand().get(cardToEvolveIntoIndex)
	 * All attached Energies and current damage stay on the evolution Pokémon
	 * @param areaOfPreEvolution
	 * @param cardBeingEvolvedIndex
	 * @param cardToEvolveIntoIndex
	 */
	public void evolvePokémon(ArrayList<Card> areaOfPreEvolution, int cardBeingEvolvedIndex, int cardToEvolveIntoIndex) {
		
		Card preEvolution = areaOfPreEvolution.get(cardBeingEvolvedIndex);
		Card evolution = player.getHand().get(cardToEvolveIntoIndex);
		
		System.out.println("\nYou evolved your " + preEvolution + " into " + evolution);
		
		evolution.addDamage(preEvolution.getCurrentDamage());
		evolution.setAttachedEnergies(preEvolution.getAttachedEnergies());
		evolution.setEvolvedFrom(preEvolution);
		if (evolution.getEvolutionStage() == 2) {
			evolution.setEvolvedFrom(preEvolution.getEvolvedFrom().get(0));
		}
		
		areaOfPreEvolution.add(evolution);
		areaOfPreEvolution.remove(cardBeingEvolvedIndex);
		player.getHand().remove(cardToEvolveIntoIndex);
		
		turnActions();
		
	}
	
	
	/**
	 * Prompts player to select a Pokémon (either Active or Benched) to attach the Energy Card that
	 * they are playing from their hand at cardSelectedIndex
	 * @param cardSelectedIndex
	 */
	public void attachEnergySelection(int cardSelectedIndex) {
		
		System.out.println("\nSelect a Pokémon to attach the Energy to\nInput 1 for Active Pokémon, 2 for Benched Pokémon");
		
		int playerChoice = in.nextInt();
		
		if (playerChoice == 1) {
			attachEnergy(player.getActiveSpot(), 0, player.getHand(), cardSelectedIndex);
			
		}
		else if (playerChoice == 2 && player.getBench().size() == 0) {
			System.out.println("\nYou have no Pokémon on your Bench");
			attachEnergySelection(cardSelectedIndex);
		}
		else if (playerChoice == 2) {
			int attachToIndex = selectCard(player.getBench()); //will be the card index
			attachEnergy(player.getBench(), attachToIndex, player.getHand(), cardSelectedIndex);
		}
		
	}
	
	/**
	 * Attaches an Energy Card in areaOfEnergy to a Pokémon in areaOfPokémon
	 * @param areaOfPokémon
	 * @param PokémonToAttachToIndex
	 * @param areaOfEnergy
	 * @param energyBeingAttachedIndex
	 */
	public void attachEnergy(ArrayList<Card> areaOfPokémon, int PokémonToAttachToIndex, ArrayList<Card> areaOfEnergy, int energyBeingAttachedIndex) {
		
		Card energyBeingAttached = areaOfEnergy.get(energyBeingAttachedIndex);
		
		if (areaOfEnergy == player.getHand()) {
			System.out.println("\nAttached a " + areaOfEnergy.get(energyBeingAttachedIndex) + " from your hand to " + areaOfPokémon.get(PokémonToAttachToIndex));
		}
		
		else if (areaOfEnergy == player.getDeck()) {
			System.out.println("\nAttached a " + areaOfEnergy.get(energyBeingAttachedIndex) + " from your deck to " + areaOfPokémon.get(PokémonToAttachToIndex));
		}

		areaOfPokémon.get(PokémonToAttachToIndex).addAttachedEnergy(energyBeingAttached);
		areaOfEnergy.remove(energyBeingAttachedIndex);
		
		if (areaOfEnergy == player.getHand()) {
			energyAttached = true;
			turnActions();
		}
		
	}
	
	
	/**
	 * First, checks to see if player's Bench is empty or if player 
	 * has already retreated this turn, if either are true, goes back
	 * to turnActions()
	 * 
	 * If neither of those conditions are met, checks to see if player's 
	 * Active Pokémon has enough Energy attached to meet their Retreat Cost, 
	 * if they don't also returns to turnActions()
	 * 
	 * If they do meet the retreat cost, prompts player to select a Benched 
	 * Pokémon to retreat into, then switches their Pokémon, discarding the 
	 * required Energy
	 */
	public void retreatActive() {
		
		if (player.getBench().size() == 0) {
			System.out.println("\nYou have no Benched Pokémon to retreat into");
			turnActions();
		}
		else if (retreated == true) {
			System.out.println("\nYou have already retreated this turn");
			turnActions();
		}
		else {
			if (player.getActiveSpot().get(0).getAttachedEnergies().size() >= player.getActiveSpot().get(0).getRetreatCost()) {
				System.out.println("\nSelect a Benched Pokémon to retreat into");
				
				int playerChoice = selectCard(player.getBench());
				
				for (int i = 0; i < player.getActiveSpot().get(0).getRetreatCost(); i++) {
					player.discardCard(player.getActiveSpot().get(0).getAttachedEnergies(), 0);
				}
				
				Card initialBench = player.getBench().get(playerChoice);
				Card initialActive = player.getActiveSpot().get(0);
				
				System.out.println("\nRetreated " + initialActive + " to the Bench, " + initialBench + " is now your Active Pokémon");
				
				if (player.getActiveSpot().get(0).getCantAttackStatus() == true) {
					player.getActiveSpot().get(0).setCantAttackStatus(false);
					System.out.println("\n" + initialActive + " can attack again");
				}
				
				player.getBench().add(initialActive);
				player.getActiveSpot().remove(0);
				player.getActiveSpot().add(initialBench);
				player.getBench().remove(playerChoice);
				
				retreated = true;
				turnActions();
			}
			else {
				System.out.println("\nYou don't have enough Energy attached to retreat");
				turnActions();
			}
		}
		
	}
	
	
	/**
	 * Prompts player to select an attack on their Active Pokémon to use
	 * 
	 * If they meet the Energy requirements, uses the attack and the attack's 
	 * secondary effect if applicable
	 * 
	 * If they don't have enough Energy attached, returns to turnActions()
	 */
	public void useAttack() {
		
		// checks to see if Active Pokémon has the cantAttackStatus, only used for Boltund
		if (player.getActiveSpot().get(0).getCantAttackStatus() == true) {
			System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " can't attack this turn");
			turnActions();
		}
		
		if (player.getActiveSpot().get(0).getAttack2Text() == null) {
			
			System.out.println("\nWhich attack would you like to use? (Input 1 or 2)\n" + player.getActiveSpot().get(0).getAttack1Text());
			
			int playerChoice1 = in.nextInt();
			
			if (playerChoice1 == 1) {
				if (player.getActiveSpot().get(0).getAttachedEnergies().size() < player.getActiveSpot().get(0).getAttack1EnergyCost()) {
					System.out.println("\nYou don't have enough energy attached to use that attack");
					turnActions();
				}
				
				System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " used " + player.getActiveSpot().get(0).getAttack1Name());
				dealDamageActive(player.getActiveSpot().get(0).attack1Damage());
				
				if (player.getActiveSpot().get(0).attack1Effect() != 0) {
					attackEffect(player.getActiveSpot().get(0).attack1Effect());
				}
				
				endTurn();
			}
			
			else if (playerChoice1 == 2) {
				System.out.println("\nYour active Pokémon only has 1 attack");
				turnActions();
			}
		}
		
		else {
			System.out.println("\nWhich attack would you like to use? (Input 1 or 2)\n" + player.getActiveSpot().get(0).getAttack1Text() + "\n" + player.getActiveSpot().get(0).getAttack2Text());
			
			int playerChoice2 = in.nextInt();
			
			if (playerChoice2 == 1) {
				
				if (player.getActiveSpot().get(0).getAttachedEnergies().size() < player.getActiveSpot().get(0).getAttack1EnergyCost()) {
					System.out.println("\nYou don't have enough energy attached to use that attack");
					turnActions();
				}
				
				System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " used " + player.getActiveSpot().get(0).getAttack1Name());
				dealDamageActive(player.getActiveSpot().get(0).attack1Damage());
				
				if (player.getActiveSpot().get(0).attack1Effect() != 0) {
					attackEffect(player.getActiveSpot().get(0).attack1Effect());
				}
				
				endTurn();
			}
			else if (playerChoice2 == 2) {
				
				if (player.getActiveSpot().get(0).getAttachedEnergies().size() < player.getActiveSpot().get(0).getAttack2EnergyCost()) {
					System.out.println("\nYou don't have enough energy attached to use that attack");
					turnActions();
				}
				
				System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " used " + player.getActiveSpot().get(0).getAttack2Name());
				dealDamageActive(player.getActiveSpot().get(0).attack2Damage());
				
				if (player.getActiveSpot().get(0).attack2Effect() != 0) {
					attackEffect(player.getActiveSpot().get(0).attack2Effect());
				}
				
				endTurn();
			}
		}
	}
	
	
	/**
	 * Deals damage to opponent's Active Pokémon based on damageValue of attack used
	 * @param damageValue
	 */
	public void dealDamageActive(int damageValue) {
		
		if (damageValue != 0) {
			System.out.println("\nOpponent's " + opponent.getActiveSpot().get(0).getCardName() + " took " + damageValue + " damage");
			
			opponent.getActiveSpot().get(0).addDamage(damageValue);
		}
		
	}
	
	
	/**
	 * Deals damage to opponent's Benched Pokémon at benchedPokémonIndex
	 * based on damageValue of attack used
	 *  
	 * Note: the only card able to target Benched Pokémon in either deck is Morpeko
	 * @param damageValue
	 * @param benchedPokémonIndex
	 */
	public void dealDamageBenched(int damageValue, int benchedPokémonIndex) {
		
		System.out.println("\nOpponent's benched " + opponent.getBench().get(benchedPokémonIndex).getCardName() + " took " + damageValue + " damage");
		
		opponent.getBench().get(benchedPokémonIndex).addDamage(damageValue);
		
	}
	
	
	/**
	 * Uses the secondary effect of a Pokémon's attack based on attackEffectNumber
	 * Each number 1-8 corresponds to the 8 different secondary attack effects 
	 * present across both decks
	 * @param attackEffectNumber
	 */
	public void attackEffect(int attackEffectNumber) {
		
		System.out.println("\nEffect of attack:");
		
		/**
		 * Used for Victini's Quick Draw
		 * Draws a card
		 */
		if (attackEffectNumber == 1) { 
			player.drawCards(1);
		}
		
		/**
		 * Used for Larvesta's Flame Charge
		 * Searches deck for an Energy and attaches it to Active
		 */
		else if (attackEffectNumber == 2) { 
			
			boolean energyFound = false;
			for (int i = 0; i < player.getDeck().size(); i++) {
				if (player.getDeck().get(i) instanceof BasicEnergy) {
					attachEnergy(player.getActiveSpot(), 0,  player.getDeck(), i);
					energyFound = true;
					break;
				}
			}
			if (energyFound == false) {
				System.out.println("\nYou had no Energies in your deck to attach");
			}
	
		}
		
		/**
		 * Used for Pikachu V's Charge
		 * Searches deck for up to 2 Energy and attaches them to Active
		 */
		else if (attackEffectNumber == 3) {
			
			int initialEnergiesAttached = player.getActiveSpot().get(0).getAttachedEnergies().size();
			
			System.out.println("\nHow many Energies would you like to attach from your deck? (Input 1 or 2)");
			int playerChoice = in.nextInt();
			
			if (playerChoice == 1) {
				for (int i = 0; i < player.getDeck().size(); i++) {
					if (player.getDeck().get(i) instanceof BasicEnergy) {
						attachEnergy(player.getActiveSpot(), 0,  player.getDeck(), i);
						endTurn();
					}
				}
				System.out.println("\nYou had no Energies in your deck to attach");

			}
			
			else if (playerChoice == 2) {
				for (int i = 0; i < player.getDeck().size(); i++) {
					if (player.getDeck().get(i) instanceof BasicEnergy) {
						attachEnergy(player.getActiveSpot(), 0,  player.getDeck(), i);
						break;
					}
				}
				
				if (player.getActiveSpot().get(0).getAttachedEnergies().size() == initialEnergiesAttached) {
					System.out.println("\nYou had no Energies in your deck to attach");
					endTurn();
				}
				
				for (int i = 0; i < player.getDeck().size(); i++) {
					if (player.getDeck().get(i) instanceof BasicEnergy) {
						attachEnergy(player.getActiveSpot(), 0,  player.getDeck(), i);
						endTurn();
					}
				}
				System.out.println("\nYou only had 1 Energy in your deck to attach");
				
			}
			
		}
		
		/**
		 * Used for Cinderace V's Blaze Kick, and Volcarona and Turtonator's Fire Spin
		 * Discards 2 Energy from Active
		 */
		else if (attackEffectNumber == 4) { 
			
			player.getDiscardPile().add(player.getActiveSpot().get(0).getAttachedEnergies().get(0));
			player.getActiveSpot().get(0).getAttachedEnergies().remove(0);
			player.getDiscardPile().add(player.getActiveSpot().get(0).getAttachedEnergies().get(0));
			player.getActiveSpot().get(0).getAttachedEnergies().remove(0);
			
			System.out.println("\nDiscarded 2 Energies from " + player.getActiveSpot().get(0).getCardName());
			
		}
		
		/**
		 * Used for Pikachu V's Thunderbolt
		 * Discards all Energy from Active
		 */
		else if (attackEffectNumber == 5) { 
			
			for (Card singleCard : player.getActiveSpot().get(0).getAttachedEnergies()) {
				player.getDiscardPile().add(singleCard);
			}
			player.getActiveSpot().get(0).getAttachedEnergies().clear();
			
			System.out.println("\nDiscarded all Energies from " + player.getActiveSpot().get(0).getCardName());
			
		}
		
		/**
		 * Used for Morpeko's Targeted Spark
		 * Prompts player to select if they want to damage opponent's Active 
		 * or one of their Benched, then calls damageActive or damageBenched accordingly
		 */
		else if (attackEffectNumber == 6) { // used for Morpeko's Targeted Spark
			
			System.out.println("\nWould you like to deal damage to your opponent's Active Pokémon, or one of their Benched Pokémon? (Input 1 for Active, 2 for Benched)");
			int playerChoice = in.nextInt();
			
			if (playerChoice == 1) {
				dealDamageActive(30);
			}
		
			else {
				System.out.println("\nWhich of your opponent's benched Pokémon would you like to damage?");
				dealDamageBenched(30, selectCard(opponent.getBench()));
			}
			
		}
		
		/**
		 * Used for Zeraora's Wild Charge
		 * Active Pokémon does 20 damage to itself
		 */
		else if (attackEffectNumber == 7) { 
			
			player.getActiveSpot().get(0).addDamage(20);
			System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " did 20 damage to itself");
			
		}
		
		/**
		 * Used for Boltund's Electrodash
		 * Sets Active Pokémon's cantAttackStatus to true, then sets recoveryTurn 
		 * to the currentTurn + 4, which will be 2 turns later for player
		 * 
		 * On recoveryTurn, that Pokémon will be able to attack again
		 */
		else if (attackEffectNumber == 8) { 
	
			player.getActiveSpot().get(0).setCantAttackStatus(true);
			System.out.println("\n" + player.getActiveSpot().get(0).getCardName() + " can't attack during your next turn");
			recoveryTurn = currentTurn + 4;
			
		}
		
	}
	
	
	/**
	 * Ends the current turn
	 * 
	 * Checks all of player's and opponent's Pokémon for any 
	 * that have been knocked out and draws the corresponding 
	 * amount of Prize Cards for the Player who took the KO
	 * 
	 * Then, adds a turn in play for player's Active
	 * 
	 * Then checks both Player's Prize Cards to see if all have been taken,
	 * if so, that Player wins the game
	 * 
	 * Finally switches player and opponent, resets all of the turn instance 
	 * variables to false (for Supporters and Energy attachments), and 
	 * starts the next turn
	 */
	public void endTurn() {
		
		if (player.checkKnockedOut(player.getActiveSpot().get(0)) == true) {
			
			int prizeCardsDrawn = 1;
			
			if (player.getActiveSpot().get(0) instanceof PokémonV) {
				prizeCardsDrawn = 2;
			}
			
			player.knockOut(player.getActiveSpot(), 0);
			
			for (int i = 0; i < prizeCardsDrawn; i++) {
				opponent.drawPrizeCard();
			}
			
		}
		
		for (int i = 0; i < player.getBench().size(); i++) {
			
			if (player.checkKnockedOut(player.getBench().get(i)) == true) {
				
				int prizeCardsDrawn = 1;
				
				if (player.getBench().get(i) instanceof PokémonV) {
					prizeCardsDrawn = 2;
				}
				
				player.knockOut(player.getBench(), i);
				
				for (int j = 0; j < prizeCardsDrawn; j++) {
					opponent.drawPrizeCard();
				}
			}
		}
		
		if (opponent.checkKnockedOut(opponent.getActiveSpot().get(0)) == true) {
			
			int prizeCardsDrawn = 1;
			
			if (opponent.getActiveSpot().get(0) instanceof PokémonV) {
				prizeCardsDrawn = 2;
			}
			
			opponent.knockOut(opponent.getActiveSpot(), 0);
			
			for (int i = 0; i < prizeCardsDrawn; i++) {
				player.drawPrizeCard();
			}
			
		}
		
		for (int i = 0; i < opponent.getBench().size(); i++) {
			
			if (opponent.checkKnockedOut(opponent.getBench().get(i)) == true) {
				
				int prizeCardsDrawn = 1;
				
				if (opponent.getBench().get(i) instanceof PokémonV) {
					prizeCardsDrawn = 2;
				}
				
				opponent.knockOut(opponent.getBench(), i);
				
				for (int j = 0; j < prizeCardsDrawn; j++) {
					player.drawPrizeCard();
				}
			}
		}
		
		
		player.getActiveSpot().get(0).addTurnInPlay();
		for (Card singleCard : player.getBench()) {
			singleCard.addTurnInPlay();
		}
		
		
		if (player.getPrizeCards().size() == 0) {
			System.out.println("\n" + player.getPlayerName() + " took all of their Prize Cards");
			player.winGame();
		}
		
		if (opponent.getPrizeCards().size() == 0) {
			System.out.println("\n" + opponent.getPlayerName() + " took all of their Prize Cards");
			opponent.winGame();
		}
		
		
		Player temp = player;
		player = opponent;
		opponent = temp;
		
		energyAttached = false;
		supporterUsed = false;
		retreated = false;
		startTurn();
		
	}
	
}
