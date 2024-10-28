
public class Larvesta extends Pokémon {

	public Larvesta() {
		
		setCardName("Larvesta");
		setMaxHP(80);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Flame Charge");
		setAttack1Text("(F) Flame Charge - 10 \nSearch your deck for a (F) Energy card and attach it to this Pokémon. Then, shuffle your deck.");
		setAttack1EnergyCost(1);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(3);
		
	}
	
	public int attack1Damage() {
		return 10;
	}
	
	public int attack1Effect() {
		return 2;
	}
 }
