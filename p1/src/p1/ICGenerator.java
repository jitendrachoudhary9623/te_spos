package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ICGenerator {
	BufferedReader sym,lit,input;
	LookUpTable lt;
	HashMap<String,SymTab> SymbolTable =new HashMap<>();
	HashMap<String,LitTab> LiteralTable =new HashMap<>();
	ICGenerator() throws FileNotFoundException{
		sym = new BufferedReader(new FileReader("/home/jitendra/practise/p1/src/Symbol.txt"));
		lit = new BufferedReader(new FileReader("/home/jitendra/practise/p1/src/Literal.txt"));
		input = new BufferedReader(new FileReader("/home/jitendra/practise/p1/src/input.txt"));
		lt=new LookUpTable();
	
	}
	
	public static void main(String[] args) throws IOException {
		PASS p=new PASS();
		p.parseFile();
		
		ICGenerator gen=new ICGenerator();
		gen.start();
	}
	public void start() throws IOException {
		createSymbolTable();
		createLiteralTable();
	}

	private void createLiteralTable() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		String line;
		while((line=lit.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			LiteralTable.put(parts[1], new LitTab(parts[1],Integer.parseInt(parts[0]),Integer.parseInt(parts[2])));

		}
		
	}

	private void createSymbolTable() throws IOException {
		// TODO Auto-generated method stub
		
		String line;
		while((line=sym.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			SymbolTable.put(parts[1], new SymTab(parts[1],Integer.parseInt(parts[0]),Integer.parseInt(parts[2])));
		}
	
	}
	
	
}
