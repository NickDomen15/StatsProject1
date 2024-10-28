
public class Sizzlipede extends Pokémon {

	public Sizzlipede() {
		
		setCardName("Sizzlipede");
		setMaxHP(80);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Gnaw");
		setAttack1Text("(F) Gnaw - 10");
		setAttack1EnergyCost(1);
		setAttack2Name("Ember");
		setAttack2Text("(F)(C) Ember - 20");
		setAttack2EnergyCost(2);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(2);
		
	}
	
	public int attack1Damage() {
		return 10;
	}
	
	public int attack2Damage() {
		return 20;
	}
	
}
