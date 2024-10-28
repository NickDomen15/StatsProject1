
/**
 * Trainer --- program to create Trainer objects for use in Player and CardGame classes
 * @author Nick Domenico
 */
public class Trainer extends Card {

	// instance variables
	private String trainerType;
	private String effectText;
	
	
	// getters and setters
	public String getTrainerType() {
		return trainerType;
	}
	
	public void setTrainerType(String trainerType) {
		this.trainerType = trainerType;
	}
	
	public String getEffectText() {
		return effectText;
	}
	
	public void setEffectText(String effectText) {
		this.effectText = effectText;
	}
	
	@Override public String getAllCardText() {
		String cardText = getCardName() + "\nTrainer, " + trainerType + "\n" + effectText;
		return cardText;
	}
}
