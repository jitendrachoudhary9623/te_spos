package pass2;

public class Literal {
	int index;

	String Literal;
	public Literal(int index, String literal, int location) {
		super();
		this.index = index;
		Literal = literal;
		this.location = location;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getLiteral() {
		return Literal;
	}
	public void setLiteral(String literal) {
		Literal = literal;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	int location;
}
