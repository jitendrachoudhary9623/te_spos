package pass1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import pass1.LookUpTable;

public class Pass1 {
	static int symCount=0;
	static int lc=0,start=0;
	static BufferedReader br;
	static BufferedWriter bf;
	static LinkedHashMap<String,Row> symbolTable;
	LookUpTable lt=new LookUpTable();
	
	Pass1(){
		symbolTable=new LinkedHashMap<>();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pass1 pass=new Pass1();
		pass.parseFile();
	}
	void parseFile() throws IOException
	{
		br=new BufferedReader(new FileReader("/home/jitendra/practise/pass1/src/pass1/input.txt"));
		String l;
		boolean begin=true;
		while((l=br.readLine())!=null)
		{
			String parts[]=l.split("\\s+");
			if(parts[1].equals("START"))//start
			{
				lc=Integer.parseInt(parts[2]);
			}
			else if(!parts[0].isEmpty())//label
			{
				if(parts[1].equals("DS"))
				{
					int i=Integer.parseInt(parts[2]);
					symbolTable.put(parts[0], new Row(parts[0],lc,symCount++));
					lc=lc+i;

				}
				else if(parts[1].equals("EQU")) {
					if(symbolTable.containsKey(parts[2])) {
						Row r=symbolTable.get(parts[2]);
						int a=r.getLocation();
						lc=a;
						symbolTable.put(parts[0], new Row(parts[0],lc++,symCount++));

					}
				}
				else
				symbolTable.put(parts[0], new Row(parts[0],lc++,symCount++));
			}
			else if(parts[0].isEmpty())//no label
			{
				if(parts[1].equals("ORIGIN"))
				{
					char b=parts[2].charAt(parts[2].length()-1);
					int a=Integer.parseInt(b+"");
					lc=lc+a;
				}
				
				else
				lc++;
			}
			else {
				lc++;
			}
		}
		  
		for(Map.Entry m:symbolTable.entrySet()){  
			Row r=(Row) m.getValue();
		   System.out.println(r.getCount()+" "+m.getKey()+" "+r.getLocation()+" ");  
	}
	}
}
//read the file
//split with spaces using \\s+
//