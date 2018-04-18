import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pass2 {
	ArrayList<TableRow> SYMTAB;
	ArrayList<TableRow> LITTAB;

	public Pass2() {

		SYMTAB =new ArrayList<TableRow>();
		LITTAB = new ArrayList<TableRow>();

	}

	void readTables() //Reading littab and sybtab from file
	{
		BufferedReader br;
		String line;
		try {
			br=new BufferedReader(new FileReader("SYMTAB.txt"));
			while((line=br.readLine())!=null)
			{
				String parts[]=line.split("\\s+");
				SYMTAB.add(new TableRow(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[0])));
			}
			br.close();
			br=new BufferedReader(new FileReader("LITTAB.txt"));
			while((line=br.readLine())!=null)
			{
				String parts[]=line.split("\\s+");
				LITTAB.add(new TableRow(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[0])));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void generateCode(String filename)
	{
		readTables();
		BufferedReader br;
		BufferedWriter bw;
		String line;
		String codeLine="";
		System.out.println("=======PASS 2 CODE+======");
		try {
			br=new BufferedReader(new FileReader(filename));
			bw=new BufferedWriter(new FileWriter("PASS2.txt"));
			while((line=br.readLine())!=null)
			{
				String parts[]=line.split("\\s+");
				if(parts[1].contains("AD")||parts[1].contains("DL,02"))
				{
					System.out.println();
					bw.write("\n");
					continue;
				}
				else if(parts.length==3) //DC or print statement
				{
					if(parts[1].contains("DL"))
					{
						parts[1]=parts[1].replaceAll("[^0-9]","");
						if(Integer.parseInt(parts[1])==1)
						{
							int constant=Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
							codeLine="00\t0\t"+String.format("%03d", constant);
							System.out.println(codeLine);
							bw.write(codeLine+"\n");
						}
					}
					else if(parts[1].contains("IS"))
					{
						parts[1]=parts[1].replaceAll("[^0-9]","");
						if(Integer.parseInt(parts[1])==10)
						{
							if(parts[2].contains("S")) //Its a symbol
							{
								int symIndex=Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
								codeLine=String.format("%02d",Integer.parseInt(parts[1]))+"\t0\t"+String.format("%03d", SYMTAB.get(symIndex-1).getAddress());
								System.out.println(codeLine);
								bw.write(codeLine+"\n");
							}
							//Donno else part is required //check from BOOK 
							else if(parts[2].contains("C")) //Its a constant
							{
								int symIndex=Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
								codeLine=String.format("%02d",Integer.parseInt(parts[1]))+"\t0\t"+String.format("%03d", LITTAB.get(symIndex-1).getAddress());
								System.out.println(codeLine);
								bw.write(codeLine+"\n");
							}
						}
					}
				}
				else if(parts.length==2 && parts[1].contains("IS"))
				{
					int opcode=Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
					codeLine=String.format("%02d", opcode)+"\t"+0+"\t"+String.format("%03d", 0);
					System.out.println(codeLine);
					bw.write(codeLine+"\n");
				}
				else if(parts[1].contains("IS") && parts.length==4) //when INSTR is an imperative statement
				{
					int opcode=Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
					//	System.out.println("opcode IS:"+opcode);
					//parts[2] contains register Number
					int regcode=Integer.parseInt(parts[2]);

					if(parts[3].contains("S")) //Its a symbol
					{
						int symIndex=Integer.parseInt(parts[3].replaceAll("[^0-9]",""));
						codeLine=String.format("%02d",opcode)+"\t"+regcode+"\t"+String.format("%03d", SYMTAB.get(symIndex-1).getAddress());
						System.out.println(codeLine);
						bw.write(codeLine+"\n");
					}
					else if(parts[3].contains("L")) //Its a LITERAL
					{
						int litIndex=Integer.parseInt(parts[3].replaceAll("[^0-9]",""));
						codeLine=String.format("%02d",opcode)+"\t"+regcode+"\t"+String.format("%03d", LITTAB.get(litIndex-1).getAddress());
						System.out.println(codeLine);
						bw.write(codeLine+"\n");
					}
				}
				;
			}
			bw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
