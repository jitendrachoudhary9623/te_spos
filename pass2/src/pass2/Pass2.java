package pass2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Pass2 {

	public static void main(String[] args) throws IOException {
		Pass2 p = new Pass2();
		p.parseFile();
		
	}
	static BufferedReader br;
	static HashMap<Integer,Symbol> symbolTable=new HashMap<>();
	static HashMap<Integer,Literal> literalTable=new HashMap<>();

	public void parseFile() throws IOException {
		createSymbolTable();
		createLiteralTable();
		
		//Rules
		/*
		 Ignore ADs, and (dl,01) 
		 for (dl,02) take the constant values
		 * */
		 
		String line;
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/IC.txt"));
		int begin=0;
		int lc=0;
		while((line=br.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			if(begin==0)
			{
				
				lc=getNum(parts[1]);
				System.out.println(lc);
				
				begin++;
			}
			else if(parts.length==1)
			{
			if(parts[0].contains("IS")) {
				System.out.println(lc+" )\t\t"+getNum(parts[0])+"\t\t"+"00\t\t00");
				lc++;
			}
			else if(parts[0].contains("AD"))
			{
				
				continue;
			}
			begin++;
			}
			else if(parts.length==2)
			{
			if(parts[0].contains("(DL,02)"))
			{
				int num=getNum(parts[1]);
				printFormat(lc, 00, 00, num);
			
				lc++;
			}
			else if(parts[0].contains("IS"))
			{
				
				int is_num=getNum(parts[0]);
				char type=parts[1].charAt(1);
				System.out.println(type);
				int type_num=getNum(parts[1]);
				int value=00;
				switch(type)
				{
				case 'S':
					value=symbolTable.get(type_num).getLocation();
					break;
				case 'L':
					value=literalTable.get(type_num).getLocation();
					break;
				}
				
				printFormat(lc, is_num, 00, value);
				lc++;
			}
				
				
				
		
			begin++;

			}
			else if(parts.length==3)
			{
				 if(parts[0].contains("IS"))
					{
						
						int is_num=getNum(parts[0]);
						int rg=getNum(parts[1]);
						
						char type=parts[2].charAt(1);
					
						int type_num=getNum(parts[2]);
						int value=00;
						switch(type)
						{
						case 'S':
							value=symbolTable.get(type_num).getLocation();
							break;
						case 'L':
							value=literalTable.get(type_num).getLocation();
							break;
						}
						
						printFormat(lc, is_num, rg, value);
						lc++;
					}
			begin++;
			}
			
			
		}
	}
	
	public static void printFormat(int lc,int mc1,int rg,int val)
	{
		System.out.println(lc+" ) \t\t"+mc1+"\t\t"+rg+"\t\t"+val);
	}
	public static int getNum(String s) {
		String a=s;
		a=a.replace("(","");
		a=a.replace(")","");
		a=a.replace(",", "");
		if(a.contains("IS"))
		{
			a=a.replace("IS", "");
		}
		else if(a.contains("DL"))
		{
			a=a.replace("DL", "");
		}
		else if(a.contains("CC"))
		{
			a=a.replace("CC", "");
		}
		else if(a.contains("AD"))
		{
			a=a.replace("AD", "");
		}
		else if(a.contains("C"))
		{
			a=a.replace("C", "");
		}
		else if(a.contains("RG"))
		{
			a=a.replace("RG", "");

		}
		else if(a.contains("L"))
		{
			a=a.replace("L", "");
		}
		else if(a.contains("S"))
		{
			a=a.replace("S", "");
		}
		
		
		return Integer.parseInt(a);
	}
	
	
	public static void createSymbolTable() throws IOException {
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/Symbol.txt"));
	String line;
	Symbol sym;
	while((line=br.readLine())!=null)
	{
		String parts[]=line.split("\\s+");
		int index=Integer.parseInt(parts[0]);
		String symbol=parts[1];
		int location=Integer.parseInt(parts[2]);
		sym=new Symbol(index,symbol,location);
		symbolTable.put(index,sym);
	}
/*	for(int i=0;i<symbolTable.size();i++)
	{
		System.out.println(symbolTable.get(i+1).getSymbol());
	}*/
	}
	
	
	public void createLiteralTable() throws IOException {
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass2/src/pass2/Literal.txt"));
	String line;
	Literal lit;
	while((line=br.readLine())!=null)
	{
		String parts[]=line.split("\\s+");
		int index=Integer.parseInt(parts[0]);
		String literal=parts[1];
		int location=Integer.parseInt(parts[2]);
		lit = new Literal(index,literal,location);
		literalTable.put(index, lit);
	}
	
	}
}
