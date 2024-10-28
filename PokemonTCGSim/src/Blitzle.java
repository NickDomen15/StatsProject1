
public class Blitzle extends Pokémon {

	public Blitzle() {
		
		setCardName("Blitzle");
		setMaxHP(60);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Zap Kick");
		setAttack1Text("(L) Zap Kick - 20");
		setAttack1EnergyCost(1);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 20;
	}
	
}
