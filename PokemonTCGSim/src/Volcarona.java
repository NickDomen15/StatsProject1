
public class Volcarona extends Pokémon {

	public Volcarona() {
		
		setCardName("Volcarona");
		setMaxHP(130);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(1);
		setPreEvolution("Larvesta");
		setAttack1Name("Combustion");
		setAttack1Text("(F)(C) Combustion - 50");
		setAttack1EnergyCost(2);
		setAttack2Name("Fire Spin");
		setAttack2Text("(F)(F)(C) Fire Spin - 170 \nDiscard 2 Energy from this Pokémon.");
		setAttack2EnergyCost(3);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(2);
		
	}
	
	public int attack1Damage() {
		return 50;
	}
	
	public int attack2Damage() {
		return 170;
	}
	
	public int attack2Effect() {
		return 4;
	}
}
