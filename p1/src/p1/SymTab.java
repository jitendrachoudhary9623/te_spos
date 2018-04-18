package p1;

public class SymTab {
String Label;
int index;
int location;
public SymTab(String label, int index, int location) {
	super();
	Label = label;
	this.index = index;
	this.location = location;
}
public String getLabel() {
	return Label;
}
public void setLabel(String label) {
	Label = label;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public int getLocation() {
	return location;
}
public void setLocation(int location) {
	this.location = location;
}
}
