
public class Victini extends Pokémon {

	public Victini() {
		
		setCardName("Victini");
		setMaxHP(70);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Quick Draw");
		setAttack1Text("(C) Quick Draw \nDraw a card.");
		setAttack1EnergyCost(1);
		setAttack2Name("Combustion");
		setAttack2Text("(F)(C) Combustion - 30");
		setAttack2EnergyCost(2);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 0;
	}
	
	public int attack1Effect() {
		return 1;
	}
	
	public int attack2Damage() {
		return 30;
	}
	
}
