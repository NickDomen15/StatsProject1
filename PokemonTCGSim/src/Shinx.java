
public class Shinx extends Pokémon {

	public Shinx() {
		
		setCardName("Shinx");
		setMaxHP(50);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Gnaw");
		setAttack1Text("(L) Gnaw - 10");
		setAttack1EnergyCost(1);
		setAttack2Name("Electric Claws");
		setAttack2Text("(L)(C) Electric Claws - 20");
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
