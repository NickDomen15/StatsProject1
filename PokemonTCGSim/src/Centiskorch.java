
public class Centiskorch extends Pokémon {

	public Centiskorch() {
		
		setCardName("Centiskorch");
		setMaxHP(130);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(1);
		setPreEvolution("Sizzlipede");
		setAttack1Name("Steady Firebreathing");
		setAttack1Text("(F) Steady Firebreathing - 30");
		setAttack1EnergyCost(1);
		setAttack2Name("Heat Blast");
		setAttack2Text("(F)(F)(C) Heat Blast - 100");
		setAttack2EnergyCost(3);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(3);
		
	}
	
	public int attack1Damage() {
		return 30;
	}
	
	public int attack2Damage() {
		return 100;
	}
	
}
