
public class PikachuV extends PokémonV {

	public PikachuV() {
		
		setCardName("Pikachu V");
		setMaxHP(190);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Charge");
		setAttack1Text("(L) Charge \nSearch your deck for up to 2 (L) Energy cards and attach them to this Pokemon. Then, shuffle your deck.");
		setAttack1EnergyCost(1);
		setAttack2Name("Thunderbolt");
		setAttack2Text("(L)(L)(C) Thunderbolt - 200 \nDiscard all Energy from this Pokemon.");
		setAttack2EnergyCost(3);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);

	}
	
	public int attack1Damage() {
		return 0;
	}
	
	public int attack1Effect() {
		return 3;
	}
	
	public int attack2Damage() {
		return 200;
	}
	
	public int attack2Effect() {
		return 5;
	}
}
