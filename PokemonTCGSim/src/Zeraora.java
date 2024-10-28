
public class Zeraora extends Pokémon {

	public Zeraora() {
		
		setCardName("Zeraora");
		setMaxHP(120);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Wild Charge");
		setAttack1Text("(L)(C) Wild Charge - 70 \nThis Pokemon also does 20 damage to itself.");
		setAttack1EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 70;
	}
	
	public int attack1Effect() {
		return 7;
	}
	
}
