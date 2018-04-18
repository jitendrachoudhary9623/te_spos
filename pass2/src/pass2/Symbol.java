package pass2;

public class Symbol {
public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
int index;

String Symbol;
int location;
public Symbol(int index, String symbol, int location) {
	super();
	this.index = index;
	Symbol = symbol;
	this.location = location;
}
}
