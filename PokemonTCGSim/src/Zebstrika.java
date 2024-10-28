
public class Zebstrika extends Pokémon {

	public Zebstrika() {
		
		setCardName("Zebstrika");
		setMaxHP(110);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(1);
		setPreEvolution("Blitzle");
		setAttack1Name("Low Kick");
		setAttack1Text("(L) Low Kick - 30");
		setAttack1EnergyCost(1);
		setAttack2Name("Zap Kick");
		setAttack2Text("(L)(C) Zap Kick - 70");
		setAttack2EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 30;
	}
	
	public int attack2Damage() {
		return 70;
	}
	
}
