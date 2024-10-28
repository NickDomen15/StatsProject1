import java.util.ArrayList;

/**
 * Pokémon --- program to create Pokémon objects for use in Player
 * and CardGame classes
 * Initialization of instance variables is done in the classes for each
 * specific Pokémon Card
 * @author Nick Domenico
 */
public class Pokémon extends Card {

	// instance variables
	private int maxHP;
	private int currentDamage;
	private int turnsInPlay = 0;
	private String type;
	private String weakness;
	private String resistance;
	private int retreatCost;
	private String attack1Name;
	private String attack1Text;
	private int attack1EnergyCost;
	private String attack2Name;
	private String attack2Text;
	private int attack2EnergyCost;
	private int evolutionStage;
	private ArrayList<String> preEvolutions = new ArrayList<>();
	private ArrayList<Card> evolvedFrom = new ArrayList<>();
	private ArrayList<Card> attachedEnergies = new ArrayList<>();
	private boolean cantAttackStatus = false;
	
	
	// getters and setters
	@Override public int getMaxHP() {
		return maxHP;
	}
	
	public void setMaxHP(int userInputHP) {
		maxHP = userInputHP;
	}
	
	
	@Override public int getCurrentDamage() {
		return currentDamage;
	}
	
	@Override public void addDamage(int damage) {
		
		currentDamage = currentDamage + damage;
	}
	
	@Override public void healDamage(int healAmmount) {
		int initialDamage = currentDamage;
		currentDamage = currentDamage - healAmmount;
		if (currentDamage < 0) {
			currentDamage = 0;
		}
		System.out.println(getCardName() + " healed for " + (currentDamage - initialDamage) + " damage");
	}
	
	
	@Override public int getTurnsInPlay() {
		return turnsInPlay;
	}
	
	@Override public void addTurnInPlay() {
		turnsInPlay++;
	}

	
	@Override public String getType() {
		return type;
	}
	
	public void setType(String PokémonType) {
		type = PokémonType;
	}
	
	
	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	
	public String getResistance() {
		return resistance;
	}
	
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	
	
	@Override public int getRetreatCost() {
		return retreatCost;
	}
	
	public void setRetreatCost(int retreatCost) {
		this.retreatCost = retreatCost;
	}

	
	@Override public String getAttack1Name() {
		return attack1Name;
	}
	
	public void setAttack1Name(String name) {
		attack1Name = name;
	}
	
	
	@Override public int attack1Damage() {
		return 0;
	}
	
	@Override public String getAttack1Text() {
		return attack1Text;
	}
	
	public void setAttack1Text(String text) {
		attack1Text = text;
	}
	
	
	@Override public int getAttack1EnergyCost() {
		return attack1EnergyCost;
	}
	
	public void setAttack1EnergyCost(int numberOfEnergies) {
		attack1EnergyCost = numberOfEnergies;
	}
	
	
	@Override public String getAttack2Name() {
		return attack2Name;
	}
	
	public void setAttack2Name(String name) {
		attack2Name = name;
	}
	
	@Override public int attack2Damage() {
		return 0;
	}
	
	@Override public String getAttack2Text() {
		return attack2Text;
	}
	
	public void setAttack2Text(String text) {
		attack2Text = text;
	}
	
	
	@Override public int getAttack2EnergyCost() {
		return attack2EnergyCost;
	}
	
	public void setAttack2EnergyCost(int numberOfEnergies) {
		attack2EnergyCost = numberOfEnergies;
	}
	
	
	@Override public int getEvolutionStage() {
		return evolutionStage;
	}
	
	
	public void setEvolutionStage(int stage) {
		evolutionStage = stage;
	}
	
	
	@Override public ArrayList<String> getPreEvolutions() {
		return preEvolutions;
	}
	
	public void setPreEvolution(String preEvolutionName) {
		preEvolutions.add(preEvolutionName);
	}
	
	
	@Override public ArrayList<Card> getEvolvedFrom() {
		return evolvedFrom;
	}
	
	@Override public void setEvolvedFrom(Card preEvolution) {
		evolvedFrom.add(preEvolution);
	}

	
	@Override public ArrayList<Card> getAttachedEnergies() {
		return attachedEnergies;
	}
	
	@Override public void addAttachedEnergy(Card energy) {
		attachedEnergies.add(energy);
	}
	
	@Override public void setAttachedEnergies(ArrayList<Card> energies) {
		for (Card singleCard : energies) {
			attachedEnergies.add(singleCard);
		}
	}
	
	
	@Override public boolean getCantAttackStatus() {
		return cantAttackStatus;
	}
	
	@Override public void setCantAttackStatus(boolean status) {
		cantAttackStatus = status;
	}
	
	
	@Override public String getAllCardText() {
		
		if (evolutionStage == 0) {
			if (attack2Text == null) {
				String cardText = getCardName() + "\nPokémon, Type: " + type + "\nStage: " + evolutionStage + ", Turns in play: " + turnsInPlay + "\nHP: " + maxHP + ", Current Damage: " + currentDamage + "\nAttached Energies: " + attachedEnergies + "\n" + attack1Text + "\nWeakness: " + weakness + ", Resistance: " + resistance + ", Retreat Cost: " + retreatCost;
				return cardText;
			}
			else {
				String cardText = getCardName() + "\nPokémon, Type: " + type + "\nStage: " + evolutionStage + ", Turns in play: " + turnsInPlay + "\nHP: " + maxHP + ", Current Damage: " + currentDamage + "\nAttached Energies: " + attachedEnergies + "\n" + attack1Text + "\n" + attack2Text + "\nWeakness: " + weakness + ", Resistance: " + resistance + ", Retreat Cost: " + retreatCost;
				return cardText;
			}
		}
		else {
			if (attack2Text == null) {
				String cardText = getCardName() + "\nPokémon, Type: " + type + "\nStage: " + evolutionStage + ", Evolved from: " + preEvolutions + ", Turns in play: " + turnsInPlay + "\nHP: " + maxHP + ", Current Damage: " + currentDamage + "\nAttached Energies: " + attachedEnergies + "\n" + attack1Text + "\nWeakness: " + weakness + ", Resistance: " + resistance + ", Retreat Cost: " + retreatCost;
				return cardText;
			}
			else {
				String cardText = getCardName() + "\nPokémon, Type: " + type + "\nStage: " + evolutionStage + ", Evolved from: " + preEvolutions + ", Turns in play: " + turnsInPlay + "\nHP: " + maxHP + ", Current Damage: " + currentDamage + "\nAttached Energies: " + attachedEnergies + "\n" + attack1Text + "\n" + attack2Text + "\nWeakness: " + weakness + ", Resistance: " + resistance + ", Retreat Cost: " + retreatCost;
				return cardText;
			}
		}
		
	}

}
