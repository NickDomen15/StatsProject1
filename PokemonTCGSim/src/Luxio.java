
public class Luxio extends Pokémon {

	public Luxio() {
		
		setCardName("Luxio");
		setMaxHP(90);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(1);
		setPreEvolution("Shinx");
		setAttack1Name("Electric Claws");
		setAttack1Text("(L)(C) Electric Claws - 50");
		setAttack1EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 50;
	}
	
}
