
public class Energy extends Card {

	private int value;
	private String type;
	
	public int getValue() {
		
		return value;
	}
	
	public void setValue(int energyValue) {
		value = energyValue;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String energyType) {
		type = energyType;
	}
	
	public void attach() {
		
	}
	
	public String getAllCardText() {
		String cardText = "Energy, " + getCardName();
		return cardText;
	}
	
}