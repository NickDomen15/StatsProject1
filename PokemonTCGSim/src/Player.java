import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Player --- program to create Player objects that have ArrayLists 
 * of Card objects (deck, hand, prizeCards, discardPile, activeSpot, 
 * and bench) to use in the CardGame class
 * @author Nick Domenico
 */
public class Player {

	Random rand = new Random();
	Scanner in = new Scanner(System.in);
	
	private String playerName;
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> prizeCards;
	private ArrayList<Card> discardPile = new ArrayList<>();
	private ArrayList<Card> activeSpot;
	private ArrayList<Card> bench;
	private int mulligansDrawn = 0;
	
	
	/**
	 * Default constructor, initializes Player with no name inputted
	 */
	public Player() {
		
		playerName = "no name inputted";
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		prizeCards = new ArrayList<>();
		discardPile = new ArrayList<>();
		activeSpot = new ArrayList<>();
		bench = new ArrayList<>();
		
	}
	
 	
	/**
	 * Constructor, initalizes Player with playerName inputted
	 * @param playerName
	 */
	public Player(String playerName) {
		
		this.playerName = playerName;
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		prizeCards = new ArrayList<>();
		discardPile = new ArrayList<>();
		activeSpot = new ArrayList<>();
		bench = new ArrayList<>();
		
	}
	
	/**
	 *  Getters, setters, and resetters 
	 * 	(resetters used only for Monte Carlos)
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void resetDeck() {
		deck.clear();
	}
	
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void resetHand() {
		hand.clear();
	}
	
	
	public ArrayList<Card> getPrizeCards() {
		return prizeCards;
	}
	
	public void resetPrizeCards() {
		prizeCards.clear();
	}
	
	
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}
	
	
	public ArrayList<Card> getActiveSpot() {
		return activeSpot;
	}

	
	public ArrayList<Card> getBench() {
		return bench;
	}
	
	
	public int getMulligansDrawn() {
		return mulligansDrawn;
	}
	
	public void addToMulligansDrawn() {
		mulligansDrawn++;
	}
	
	public void setMulligansDrawn(int mulligans) {
		mulligansDrawn = mulligans;
	}
	
	
	/**
	 * Fills the deck with Cards for use in Monte Carlo simulations
	 */
	public void fillDeckTest(int numberOfPokémon, int numberOfRareCandy) {
		
		for(int i = 0; i < (60 - numberOfPokémon - numberOfRareCandy); i++) {
			
			deck.add(new BasicEnergy("Fire"));
		}
		
		for (int i = 0; i < numberOfPokémon; i++) {
			deck.add(new Charmander());
		}
		
		for (int i = 0; i < numberOfRareCandy; i++) {
			deck.add(new RareCandy());
		}
	
	}
	
	
	/**
	 * Fills the deck with the Cards of the Cinderace V Deck from the 
	 * 2022 printing of the TCG Battle Academy
	 */
	public void fillDeckCinderace() {
		
		for (int i = 0; i < 18; i++) {
			deck.add(new BasicEnergy("Fire"));
		}
		for (int i = 0; i < 4; i++) {
			deck.add(new Sizzlipede());
			deck.add(new GreatBall());
			deck.add(new Hop());
		}
		for (int i = 0; i < 3; i++) {
			deck.add(new Centiskorch());
			deck.add(new Vulpix());
			deck.add(new Larvesta());
			deck.add(new Shauna());
		}
		for (int i = 0; i < 2; i++) {
			deck.add(new Ninetales());
			deck.add(new Volcarona());
			deck.add(new Victini());
			deck.add(new EnergyRetrieval());
			deck.add(new Switch());
			deck.add(new PokémonCatcher());
			deck.add(new BugCatcher());
		}
		deck.add(new CinderaceV());
		deck.add(new Turtonator());
		deck.add(new Potion());
		deck.add(new Sonia());
	}
	
	
	/**
	 * Fills the deck with the Cards of the Pikachu V Deck from the 
	 * 2022 printing of the TCG Battle Academy
	 */
	public void fillDeckPikachu() {
		
		for (int i = 0; i < 18; i++) {
			deck.add(new BasicEnergy("Lightning"));
		}
		for (int i = 0; i < 4; i++) {
			deck.add(new Blitzle());
			deck.add(new Shinx());
			deck.add(new GreatBall());
			deck.add(new Hop());
		}
		for (int i = 0; i < 3; i++) {
			deck.add(new Zebstrika());
			deck.add(new Luxio());
			deck.add(new Shauna());
		}
		for (int i = 0; i < 2; i++) {
			deck.add(new Luxray());
			deck.add(new Yamper());
			deck.add(new Potion());
			deck.add(new Switch());
			deck.add(new BugCatcher());
		}
		deck.add(new PikachuV());
		deck.add(new Boltund());
		deck.add(new Zeraora());
		deck.add(new Morpeko());
		deck.add(new EnergyRecycler());
		deck.add(new BossOrders());
		deck.add(new Sonia());
		
	}
	
	
	/**
	 * Draws an opening hand of 7 Cards from the deck
	 */
	public void drawHand() {
		
		for (int i = 0; i < 7; i++) {
			int cardToTakeIndex = rand.nextInt(deck.size());
			hand.add(deck.get(cardToTakeIndex));
			deck.remove(cardToTakeIndex);
		}

	}
	
	
	/**
	 * Checks opening hand for a Basic Pokémon
	 * Returns true if at least 1 is found, false if none are found
	 * @return boolean
	 */
	public boolean checkHand() {
		// loop thru hand, if one card is a Pokémon, return true, else return false
		for(Card singleCard : hand) {
			
			if (singleCard instanceof Pokémon && singleCard.getEvolutionStage() == 0) {
				return true;
			}
			
		}
		return false;
	}
	
	
	/**
	 * Draws 6 Cards from the deck for Prize Cards
	 */
	public void drawPrizes() {
		
		for (int i = 0; i < 6; i++) {
			int cardToTakeIndex = rand.nextInt(deck.size());
			prizeCards.add(deck.get(cardToTakeIndex));
			deck.remove(cardToTakeIndex);
		}
		
	}
	
	
	/**
	 * Checks Prize Cards for the amount of Rare Candies Prizes
	 * Used in Monte Carlo simulation
	 * @return int prizedRareCandies
	 */
	public int checkPrizes() {
		
		int prizedRareCandies = 0;
		
		for (Card singleCard : prizeCards) {
			
			if (singleCard instanceof RareCandy) {
				prizedRareCandies++;
			}
			
		}
		
		return prizedRareCandies;
	}
	
	
	/**
	 * Flips a coin using rand.nextInt(2) + 1 and 
	 * prints the result: 1 for heads, 2 for tails
	 * @return int
	 */
	public int flipCoin() {
		int result = rand.nextInt(2) + 1;
		if (result == 1) {
			System.out.println("\nCoin flipped heads");
		}
		if (result == 2) {
			System.out.println("\nCoin flipped tails");
		}
		return result;
	}
	
	
	/**
	 * Prints all of the text on the Card cardSelected
	 * @param cardSelected
	 */
	public void getCardText(Card cardSelected) {
		System.out.println(cardSelected.getAllCardText());
	}
	
	
	/**
	 * Draws numberOfCardsToDraw from the deck
	 * @param numberOfCardsToDraw
	 */
	public void drawCards(int numberOfCardsToDraw) {
		
		for (int i = 0; i < numberOfCardsToDraw; i++) {
			int cardToTakeIndex = rand.nextInt(deck.size());
			hand.add(deck.get(cardToTakeIndex));
			System.out.println("\nYou drew a " + deck.get(cardToTakeIndex));
			deck.remove(cardToTakeIndex);
		}
		
	}
	
	
	/**
	 * Prompts Player to select a Basic Pokémon for their Active when starting the game
	 * If they have additional Basics in their hand, allows seting more on the Bench
	 */
	public void selectStartingPokémon() {
		System.out.println("\n" + playerName + ", select a Basic Pokémon for your Active");
		
		int cardSelectedIndex = selectCard(hand);
		while (checkBasicPokémon(hand, cardSelectedIndex) == false) {
			cardSelectedIndex = selectCard(hand);
		}
		
		System.out.println("\nPut " + hand.get(cardSelectedIndex) + " in the Active Spot");
		
		activeSpot.add(hand.get(cardSelectedIndex));
		hand.remove(cardSelectedIndex);
		
		boolean additionalBasics = checkHand();
		if (additionalBasics == true) {
			selectAdditionalBasics();
		}
		
	}
	
	
	/**
	 * Prompts Player if they want to put additional Basic Pokémon on their Bench
	 * when starting the game
	 */
	public void selectAdditionalBasics() {
		
		int numberOfBasicsInHand = 0;
		for (Card singleCard : hand) {
			if (singleCard instanceof Pokémon && singleCard.getEvolutionStage() == 0) {
				numberOfBasicsInHand++;
			}
		}
		
		for (int i = 0; i < numberOfBasicsInHand; i++) {
			System.out.println("\nWould you like to put additional Basic Pokémon on your bench? (Input 1 for yes, 0 for no)");
			int playerChoice = in.nextInt();
			if (playerChoice == 1) {
				int cardSelectedIndex = selectCard(hand);
				while (checkBasicPokémon(hand, cardSelectedIndex) == false) {
					cardSelectedIndex = selectCard(hand);
				}
				
				System.out.println("\nPut " + hand.get(cardSelectedIndex) + " on the Bench");
				
				bench.add(hand.get(cardSelectedIndex));
				hand.remove(cardSelectedIndex);
					
			}
			if (playerChoice == 0) {
				break;
			}
		}
	}
	
	
	/**
	 * Prompts Player to select a Card from an area
	 * Returns the index of the card that was selected
	 * @param area
	 * @return int
	 */
	public int selectCard(ArrayList<Card> area) {
		System.out.println("\n" + area);
		System.out.println("Select a card (Input 1-" + area.size() + " to select)");
		int cardSelected = in.nextInt();
		return cardSelected - 1;
	}

	
	/**
	 * Checks to see if a selected Card is a Basic Pokémon
	 * Returns true if yes, false if no
	 * @param placeToSearch
	 * @param cardSelected
	 * @return boolean
	 */
	public boolean checkBasicPokémon(ArrayList<Card> placeToSearch, int cardSelected) {
		if (!(placeToSearch.get(cardSelected) instanceof Pokémon)) {
			System.out.println("\nThat is not a Pokémon.");
			return false;
		}
		else if (placeToSearch.get(cardSelected) instanceof Pokémon && placeToSearch.get(cardSelected).getEvolutionStage() != 0) {
			System.out.println("\nThat is not a Basic Pokémon.");
			return false;
		}
		return true;
	}
	

	/**
	 * Discards a Card at cardIndex from an area
	 * @param area
	 * @param cardIndex
	 */
	public void discardCard(ArrayList<Card> area, int cardIndex) {
		discardPile.add(area.get(cardIndex));
		System.out.println("\nDiscarded " + area.get(cardIndex));
		area.remove(cardIndex);
	}
	
	
	/**
	 * Checks to see if a Pokémon is Knocked Out
	 * Returns true if they are Knocked Out, false if they are not
	 * @param card
	 * @return true
	 */
	public boolean checkKnockedOut(Card card) {
		
		if (card.getCurrentDamage() >= card.getMaxHP()) {
			return true;
		}
		
		return false;
		
	}
	
	
	/**
	 * Knocks out a Pokémon by discarding it, all of the Pokémon
	 * that it evolved from, and all of the Energies that were attached to it
	 * @param area
	 * @param cardIndex
	 */
	public void knockOut(ArrayList<Card> area, int cardIndex) {
		
		Card PokémonKnockedOut = area.get(cardIndex);
		
		System.out.println("\n" + PokémonKnockedOut + " was knocked out");
		
		ArrayList<Card> knockedOutAttachedEnergies = new ArrayList<Card>();
		for (Card singleCard : area.get(cardIndex).getAttachedEnergies()) {
			knockedOutAttachedEnergies.add(singleCard);
		}
		
		ArrayList<Card> knockedOutEvolvedFrom = new ArrayList<Card>();
		for (Card singleCard : area.get(cardIndex).getEvolvedFrom()) {
			knockedOutEvolvedFrom.add(singleCard);
		}
		
		area.get(cardIndex).getAttachedEnergies().clear();
		area.get(cardIndex).getEvolvedFrom().clear();
		
		discardCard(area, cardIndex);
		
		for (Card singleCard : knockedOutEvolvedFrom) {
			discardPile.add(singleCard);
			System.out.println("\nDiscarded " + singleCard);
		}
		
		for (Card singleCard : knockedOutAttachedEnergies) {
			discardPile.add(singleCard);
			System.out.println("\nDiscarded " + singleCard);
		}
		
	}
	
	
	/**
	 * Draws a Card from prizeCards and puts it into hand
	 */
	public void drawPrizeCard() {
		
		if (prizeCards.size() != 0) {
			
			Card cardDrawn = prizeCards.get(0);
			prizeCards.remove(0);
			
			hand.add(cardDrawn);
			System.out.println("\n" + playerName + " drew a " + cardDrawn + " from their Prize Cards");
		}
		
	}
	
	
	/**
	 * Prompts Player to select one of their Benched Pokémon to 
	 * become their new Active after their Active Pokémon
	 * has been Knocked Out
	 */
	public void promoteNewActive() {
		
		System.out.println("\n" + playerName + ", select one of your Benched Pokémon to be your new Active");
		
		int benchedPokémonIndex = selectCard(bench);
		
		activeSpot.add(bench.get(benchedPokémonIndex));
		bench.remove(benchedPokémonIndex);
		
		System.out.println("\n" + activeSpot.get(0).getCardName() + " is now your Active Pokémon");
		
	}
	
	
	/**
	 * Prints the winning player, then terminates the program
	 */
	public void winGame() {
		
		System.out.println("\n" + playerName + " won the game");
		System.exit(0);
		
	}

	
	/**
	 * Runs the Monte Carlo simulation for checking the opening hand for a Basic Pokémon
	 * Outputs the results to a csv file
	 * @param numberOfAttempts
	 * @param fileName
	 */
	public void runOpeningHand(double numberOfAttempts, String fileName) {
		
		try {
			
			File file = new File(fileName);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fWriter); 
			
			writer.write("Number of Pokémon in deck, Percentage of hands with Pokémon \n");
			
			for (int i = 1; i < 61; i++) {
				
				int numberOfPokémon = i;
				writer.write(numberOfPokémon + ", ");
				resetDeck();
				resetHand();
				fillDeckTest(numberOfPokémon, 0);
				double numberOfSuccesses = 0;
				
				for (int j = 0; j < numberOfAttempts; j++) {
					
					drawHand();
					if (checkHand() == true) {
						numberOfSuccesses++;
					}
					resetDeck();
					resetHand();
					fillDeckTest(numberOfPokémon, 0);
						
				}
				
				double percentage = numberOfSuccesses/numberOfAttempts;
				writer.write(percentage + "\n");
			}
	
			writer.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	/**
	 * Runs the Monte Carlo simulation for checking Prize Cards 
	 * for all of the Rare Candies in deck (bricked)
	 * Outputs the results to a csv file
	 * @param numberOfPokémon
	 * @param numberOfAttempts
	 * @param fileName
	 */
	public void runPrizeTest(int numberOfPokémon, double numberOfAttempts, String fileName) {
		
		try {
			
			File file = new File(fileName);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fWriter);
			
			
			writer.write("Number of Rare Candies in Deck, Percentage of Bricked Openings, Number of basic Pokémon in deck: " + numberOfPokémon + ",Number of attempts: " + numberOfAttempts + "\n");
			
			for (int i = 1; i < 5; i++) {
				
				int numberOfRareCandies = i;
				writer.write(numberOfRareCandies + ", ");
				resetDeck();
				resetHand();
				resetPrizeCards();
				fillDeckTest(numberOfPokémon, numberOfRareCandies);
				double numberOfSuccesses = 0;
				
				for (int j = 0; j < numberOfAttempts; j++) {
					
					drawHand();
					drawPrizes();
					
					if (checkHand() == true && checkPrizes() == numberOfRareCandies) {
						numberOfSuccesses++;
					}
					
					resetDeck();
					resetHand();
					resetPrizeCards();
					fillDeckTest(numberOfPokémon, numberOfRareCandies);
						
				}
				
				
				double percentage = numberOfSuccesses/numberOfAttempts;
				writer.write(percentage + "\n");
			}
			
			writer.close();
			
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}
