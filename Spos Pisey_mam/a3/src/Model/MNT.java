package Model;

public class MNT {

	String Name;
	int Address;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAddress() {
		return Address;
	}
	public void setAddress(int address) {
		Address = address;
	}
	public MNT(String name, int address) {
		super();
		Name = name;
		Address = address;
	}
	public MNT() {
	}
	
}
