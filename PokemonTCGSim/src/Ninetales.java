
public class Ninetales extends Pokémon {

	public Ninetales() {
		
		setCardName("Ninetales");
		setMaxHP(110);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(1);
		setPreEvolution("Vulpix");
		setAttack1Name("Supernatural Flames");
		setAttack1Text("(F)(C) Supernatural Flames - 70");
		setAttack1EnergyCost(2);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 70;
	}
	
}
