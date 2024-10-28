import java.util.ArrayList;

/**
 * Card --- program to create Card objects for use in Player and CardGame classes
 * @author Nick Domenico
 */
public class Card extends CardGame {

	// instance variable, the only one shared across all types of Cards
	private String name;
	
	
	// getters and setters
	public String getCardName() {
		return name;
	}
	
	public void setCardName(String cardName) {
		name = cardName;
	}
	
	public String toString() {
		return name;
	}
	
	public String getAllCardText() {
		return "";
	}
	
	
	/**
	 * Note: all of the following methods are only written to make the 
	 * methods in Player and CardGame compatible with Card objects, even 
	 * though they will only be called as the methods from the subclasses
	 */
	
	public int getMaxHP() {
		return 0;
	}
	
	public int getCurrentDamage() {
		return 0;
	}
	
	public void addDamage(int damage) {
		
	}
	
	public void healDamage(int healAmmount) {
		
	}

	
	public int getTurnsInPlay() {
		return 0;
	}
	
	public void addTurnInPlay() {
		
	}
	
	public String getType() {
		return "";
	}
	
	public int getEvolutionStage() {
		return -1;
	}
	
	public ArrayList<String> getPreEvolutions() {
		ArrayList<String> preEvolutions = new ArrayList<>();
		return preEvolutions;
	}
	
	
	public ArrayList<Card> getEvolvedFrom() {
		ArrayList<Card> evolvedFrom = new ArrayList<>();
		return evolvedFrom;
	}
	
	public void setEvolvedFrom(Card preEvolution) {
		
	}
	
	public ArrayList<Card> getAttachedEnergies() {
		ArrayList<Card> attachedEnergies = new ArrayList<>();
		return attachedEnergies;
	}
	
	public void addAttachedEnergy(Card energy) {
		
	}
	
	public void setAttachedEnergies(ArrayList<Card> energies) {
		
	}
	
	public int getRetreatCost() {
		return 0;
	}
	
	public String getAttack1Name() {
		return "";
	}
	
	public int attack1Damage() {
		return 0;
	}
	
	public int attack1Effect() {
		return 0;
	}
	
	public String getAttack1Text() {
		return "";
	}
	
	public int getAttack1EnergyCost() {
		return 0;
	}
	
	public String getAttack2Name() {
		return "";
	}
	
	public int attack2Damage() {
		return 0;
	}
	
	public int attack2Effect() {
		return 0;
	}
	
	public String getAttack2Text() {
		return "";
	}
	
	public int getAttack2EnergyCost() {
		return 0;
	}
	
	public boolean getCantAttackStatus() {
		return false;
	}
	
	public void setCantAttackStatus(boolean status) {
		
	}
	
	public int itemEffect() {
		return 0;
	}
	
	public int supporterEffect() {
		return 0;
	}

}