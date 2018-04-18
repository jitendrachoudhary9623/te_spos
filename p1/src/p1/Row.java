package p1;

public class Row {
int location;
boolean isLabel;

public Row(int location, boolean isLabel) {
	super();
	this.location = location;
	this.isLabel = isLabel;
}
public Row() {
	
}
public int getLocation() {
	return location;
}
public void setLocation(int location) {
	this.location = location;
}
public boolean isLabel() {
	return isLabel;
}
public void setLabel(boolean isLabel) {
	this.isLabel = isLabel;
}
}
