package p1;

public class LitTab {
String literal;
int location;
int index;
public LitTab(String literal, int location) {
	super();
	this.literal = literal;
	this.location = location;
}
public LitTab(String literal, int location,int index) {
	super();
	this.literal = literal;
	this.location = location;
	this.index=index;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public LitTab() {
	
}
public String getLiteral() {
	return literal;
}
public void setLiteral(String literal) {
	this.literal = literal;
}
public int getLocation() {
	return location;
}
public void setLocation(int location) {
	this.location = location;
}

}
