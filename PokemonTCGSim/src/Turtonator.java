
public class Turtonator extends Pokémon {

	public Turtonator() {
		
		setCardName("Turtonator");
		setMaxHP(130);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Tackle");
		setAttack1Text("(C)(C) Tackle - 30");
		setAttack1EnergyCost(2);
		setAttack2Name("Fire Spin");
		setAttack2Text("(F)(F)(F)(C) Fire Spin - 150 \nDiscard 2 Energy from this Pokémon.");
		setAttack2EnergyCost(4);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(3);
		
	}
	
	public int attack1Damage() {
		return 30;
	}
	
	public int attack2Damage() {
		discardCard(getAttachedEnergies(), 0);
		discardCard(getAttachedEnergies(), 0);
		return 150;
	}
	
	public int attack2Effect() {
		return 4;
	}
	
}
