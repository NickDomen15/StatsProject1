
public class Yamper extends Pokémon {

	public Yamper() {
		
		setCardName("Yamper");
		setMaxHP(70);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Bite");
		setAttack1Text("(C) Bite - 10");
		setAttack1EnergyCost(1);
		setAttack2Name("Zap Kick");
		setAttack2Text("(L)(C) Zap Kick - 20");
		setAttack2EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 10;
	}
	
	public int attack2Damage() {
		return 20;
	}
}
