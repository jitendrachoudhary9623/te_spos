package pass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class p2 {
	static HashMap<Integer,SymbolTable> symbol=new HashMap<>();
	static HashMap<Integer,LiteralTable> literal=new HashMap<>();
	static BufferedReader br;
	static BufferedWriter out;

	public static void main(String[] args) throws IOException {
		
		getSymbols();
		getLiterals();
		
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/IC.txt"));
		out=new BufferedWriter(new FileWriter("/home/jitendra/practise/pass2/src/pass2/Mc.txt"));

		String line;
		boolean begin=true;
		int lt=0;
		int dc=0;
		int rg=0;
		int add=0;
		while((line=br.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			if(begin==true)
			{
				lt=getNumber(parts[1]);
				
				

				begin=false;
			}
			else if(parts.length==1)
			{
				if(line.contains("AD"))
				{
				continue;
				}
				else
				dc=getNumber(parts[0]);
				
				print(lt++,dc,rg,add);

			}
			else if(parts.length==2)
			{

				if(line.contains("AD"))
				{
				continue;
				}
				else if(line.contains("DL,01"))
				{
					dc=-1;
					rg=-1;
					add=-1;
				continue;
				}
				else
				{
					if(parts[0].contains("IS"))
					{
						dc=getNumber(parts[0]);
						rg=0;
						char type=parts[1].charAt(1);
						switch(type)
						{
						case 'S':
							add=symbol.get(getNumber(parts[1])).getLocation();
							break;
						case 'L':
							add=literal.get(getNumber(parts[1])).getLocation();

							break;
						}
					}
					else if(parts[0].contains("DL,02"))
					{
						dc=0;
						rg=0;
						add=getNumber(parts[1]);
					}
				}
				print(lt++,dc,rg,add);

			}
			else if(parts.length==3)
			{
				if(parts[0].contains("IS"))
				{
					dc=getNumber(parts[0]);
					rg=getNumber(parts[1]);
					char type=parts[2].charAt(1);
					switch(type)
					{
					case 'S':
						add=symbol.get(getNumber(parts[2])).getLocation();
						break;
					case 'L':
						add=literal.get(getNumber(parts[2])).getLocation();

						break;
					}
				}
				print(lt++,dc,rg,add);

			}
		}
		for(int i=1;i<=symbol.size();i++)
		{
			int a=symbol.get(i).getLocation();
			if(lt<=a)
			{
				print(a,-1,-1,-1);
			}
		}
		out.close();
	}
	static void print(int lc,int dc,int rg,int add) throws IOException {
		String a;
		if(dc>9)
			a=lc+" )\t\t"+dc+"\t\t0"+rg+"\t\t"+add+"\t\t";
		else if(rg==-1)
			a=lc+" )";
		else
			a=lc+" )\t\t0"+dc+"\t\t0"+rg+"\t\t"+add+"\t\t";

		out.write(a+"\n");
System.out.println(a);		
	}
	static int getNumber(String s)
	{
		String a=s;
		a=a.replace("(", "");
		a=a.replace(")", "");
		a=a.replace(",", "");
		if(a.contains("IS"))
		{
			a=a.replace("IS", "");

		}
		else if(a.contains("DL"))
		{
			a=a.replace("DL", "");
		}
		else if(a.contains("C"))
		{
			a=a.replace("C", "");
		}
		else if(a.contains("L"))
		{
			a=a.replace("L", "");

		}
		else if(a.contains("S"))
		{
			a=a.replace("S", "");

		}
		else if(a.contains("AD"))
		{
			a=a.replace("AD", "");

		}
		else if(a.contains("RG"))
		{
			a=a.replace("RG", "");

		}
		return Integer.parseInt(a);
	}
	static void getLiterals() throws IOException {
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/Literal.txt"));
		LiteralTable lit;
		String line;
		while((line=br.readLine())!=null)
		{
		String parts[]=line.split("\\s+");
		int index=Integer.parseInt(parts[0]);
		int location=Integer.parseInt(parts[2]);
		String literals=parts[1];
		lit=new LiteralTable(index,location,literals);
		literal.put(index, lit);
		
		}
		
		for(int i=0;i<literal.size();i++)
		{
			System.out.println(literal.get(i).getLiteral());
		}
	}
	
	static void getSymbols() throws IOException {
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/Symbol.txt"));
		SymbolTable sym;
		String line;
		while((line=br.readLine())!=null)
		{
		String parts[]=line.split("\\s+");
		int index=Integer.parseInt(parts[0]);
		int location=Integer.parseInt(parts[2]);
		String symbols=parts[1];
		System.out.println(symbols);
		sym=new SymbolTable(index,location,symbols);
		symbol.put(index, sym);
		
		}
		
		for(int i=1;i<=symbol.size();i++)
		{
			System.out.println(symbol.get(i).getSymbol());
		}
	}
}

class SymbolTable{
int index;
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
public String getSymbol() {
	return Symbol;
}
public void setSymbol(String symbol) {
	Symbol = symbol;
}
int location;
String Symbol;
public SymbolTable(int index, int location, String symbol) {
	super();
	this.index = index;
	this.location = location;
	Symbol = symbol;
}
	
}

class LiteralTable{
int index;
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
public String getLiteral() {
	return literal;
}
public void setLiteral(String literal) {
	this.literal = literal;
}
int location;
String literal;
public LiteralTable(int index, int location, String literal) {
	super();
	this.index = index;
	this.location = location;
	this.literal = literal;
}
}
