
public class Vulpix extends Pokémon {

	public Vulpix() {
		
		setCardName("Vulpix");
		setMaxHP(70);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Smash Kick");
		setAttack1Text("(C) Smash Kick - 10");
		setAttack1EnergyCost(1);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 10;
	}
	
}
