package pass1;

public class Row {
	String instruction;
	int location;
	int count;
	
	public Row(String instruction, int location, int count) {
		super();
		this.instruction = instruction;
		this.location = location;
		this.count = count;
	}
	public Row()
	{
		
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
