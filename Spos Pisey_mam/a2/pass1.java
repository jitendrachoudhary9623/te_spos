import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class PassOneAsm 
{	
	static int symIndex=0,litIndex=0;
	int lc=0;
	int littab_ptr=0;
	int pooltab_ptr=0;
	static BufferedReader br;
	ArrayList<TableRow> LITTAB;
	ArrayList<Integer> POOLTAB;
	LinkedHashMap<String, TableRow> SYMTAB;
	public PassOneAsm()
	{
		SYMTAB=new LinkedHashMap<String,TableRow>();
		LITTAB=new ArrayList<TableRow>();
		POOLTAB=new ArrayList<Integer>();
		POOLTAB.add(1);
		//POOLTAB=new HashMap<String,TableRow>();
		lc=0;
	}
	/********************************************************
	 *  MAIN METHOD											*
	 ********************************************************/

	public static void main(String[] args)
	{
		PassOneAsm pass1=new PassOneAsm();
		pass1.parseFile();
                //Uncomment below Lines after creating Pass 2 //Assembler
		//Pass2 pass2=new Pass2();
		//pass2.generateCode("IC.txt");

	}

	/*
	 * This Method Parses input file and Generates IC+ SYMTAB +LITTAB+POOLTAB
	 */
	public  void parseFile()
	{
		String line;
		String codeLine;
		BufferedWriter bw;
		InstTable lookuptable=new InstTable();
		try {
			br=new BufferedReader(new FileReader("input.asm"));
			bw=new BufferedWriter(new FileWriter("IC.txt"));
			if(br==null)
			{
				System.out.println("No INPUT FILE IS PRESENT");
			}

			while((line=br.readLine())!=null)
			{
				String parts[]=line.split("\\s+");
				//IF There is LABEL
				if(!parts[0].isEmpty())
				{
					if(SYMTAB.containsKey(parts[0]))
					{
						SYMTAB.put(parts[0], new TableRow(parts[0],(lc),SYMTAB.get(parts[0]).getIndex())) ;
					}
					else
					{
						SYMTAB.put(parts[0],new TableRow(parts[0], (lc),++symIndex));
					}
				}
				if(parts[1].equals("LTORG"))
				{
					int ptr=POOLTAB.get(pooltab_ptr)-1;
					for(int i=ptr; i<littab_ptr;i++)
					{
						lc++;
						//	System.out.println(lc+"here"+i);
						LITTAB.set(i, new TableRow(LITTAB.get(i).getSymbol(), lc));

						codeLine="(DL,01)\t(C,"+LITTAB.get(i).getSymbol()+")";
						System.out.println(lc+": "+codeLine);
						bw.write(lc+"\t"+codeLine+"\n");
					}

					pooltab_ptr++;
					POOLTAB.add(littab_ptr);
				}
				//Process start or origin AD
				if(parts[1].equalsIgnoreCase("START")||parts[1].equalsIgnoreCase("ORIGIN"))
				{
					int loc=0;
					if(parts[2].contains("+")&&parts[1].equals("ORIGIN"))
					{
						String op[]=parts[2].split("\\+");
						loc=SYMTAB.get(op[0]).getAddress()+Integer.parseInt(op[1].trim());
						codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
						codeLine=codeLine+"\t(S,"+SYMTAB.get(op[0]).getIndex()+")+"+Integer.parseInt(op[1].trim());
						lc=loc;

					}
					else //START INSTRUCTION
					{
						loc=Integer.parseInt(parts[2].trim());
						codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
						codeLine=codeLine+"\t(C,"+loc+")";
						lc=loc;

					}
					System.out.println(lc+": "+codeLine);
					bw.write(lc+"\t"+codeLine+"\n");
					//lc=loc;
					//System.out.println("LC = "+lc);
				}

				//For EQU STATEMENT
				if(parts[1].equalsIgnoreCase("EQU"))
				{
					int loc=0;
					if(parts[2].contains("+"))
					{
						String op[]=parts[2].split("\\+");
						loc=SYMTAB.get(op[0]).getAddress()+Integer.parseInt(op[1].trim());
						codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
						codeLine=codeLine+"\t(S,"+SYMTAB.get(op[0]).getIndex()+")+"+Integer.parseInt(op[1].trim());
					}
					else if(parts[2].contains("-"))  //IF THERE IS - SIGN IN OPERAND FIELD
					{
						String op[]=parts[2].split("\\-");
						loc=SYMTAB.get(op[0]).getAddress()-Integer.parseInt(op[1].trim());
						codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
						codeLine=codeLine+"\t(S,"+SYMTAB.get(op[0]).getIndex()+")+"+Integer.parseInt(op[1].trim());
					}
					else //DIRECT LOCATION IS GIVEN
					{
						loc=Integer.parseInt(parts[2].trim());
						codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
						codeLine=codeLine+"\t(C,"+loc+")";

					}
					System.out.println(lc+": "+codeLine);
					bw.write(lc+"\t"+codeLine+"\n");
					if(SYMTAB.containsKey(parts[0]))
					{
						SYMTAB.put(parts[0], new TableRow(parts[0],loc,SYMTAB.get(parts[0]).getIndex())) ;
					}
					else
						SYMTAB.put(parts[0], new TableRow(parts[0],loc,++symIndex));
				}

				//For declarative statements
				if(lookuptable.return_type(parts[1]).equals("DL"))
				{  int old_lc=lc;
				int memsize=Integer.parseInt(parts[2].replace("'", ""));
				if(parts[1].equalsIgnoreCase("DS"))
				{
					codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
					codeLine=codeLine+"\t(C,"+memsize+")";
					System.out.println(old_lc+": "+codeLine);
					bw.write(old_lc+"\t"+codeLine+"\n");

					lc=lc+memsize;
				}
				else
				{
					lc++;; //in case of DC

					//Here LC INCREMENT PROBLEM NEEDS TO BE SOLVED

					codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
					codeLine=codeLine+"\t(C,"+memsize+")";
					System.out.println(lc+": "+codeLine);
					bw.write(lc+"\t"+codeLine+"\n");
				}
				}
				//If an Imperative Instruction is detected
				if(lookuptable.return_type(parts[1]).equals("IS"))
				{
					codeLine="("+lookuptable.return_type(parts[1])+",0"+lookuptable.return_code(parts[1])+")";
					int j=2;
					String code2="";
					while(j<parts.length)
					{
						if(lookuptable.return_type(parts[j].replace(",", "")).equals("RG")) //if operand is register
						{
							code2+="\t"+lookuptable.return_code(parts[j].replace(",", "").trim())+" ";
						}
						else
						{
							if(parts[j].contains("=")) //literal
							{
								String lit=parts[j].replace("=", "").replace("'", "");
								LITTAB.add(new TableRow(lit, -1,++litIndex));
								littab_ptr++;
								//code to be added for pooltab processing
								code2+="\t(L,"+(litIndex)+")";
							}
							else if(SYMTAB.containsKey(parts[j]))
							{
								int ind=SYMTAB.get(parts[j]).getIndex();
								code2+= "\t(S,0"+ind+")"; 


							}
							else
							{
								SYMTAB.put(parts[j], new TableRow(parts[j],-1,++symIndex));
								int ind=SYMTAB.get(parts[j]).getIndex();
								code2+= "\t(S,0"+ind+")";
							}
						}
						j++;
					}
					lc++;
					codeLine+=code2;
					System.out.println(lc+": "+codeLine);
					bw.write(lc+"\t"+codeLine+"\n");
				}
				if(parts[1].equalsIgnoreCase("END"))
				{
					int ptr=POOLTAB.get(pooltab_ptr);
					for(int i=ptr; i<littab_ptr;i++)
					{
						lc++;

						LITTAB.set(i, new TableRow(LITTAB.get(i).getSymbol(), lc));
						codeLine="(DL,01)\t(C,"+LITTAB.get(i).getSymbol()+")";
						System.out.println(lc+": "+codeLine);
						bw.write(lc+"\t"+codeLine+"\n");
					}
					pooltab_ptr++;
					POOLTAB.add(littab_ptr);
					codeLine="(AD,02)";
					System.out.println(lc+": "+codeLine);
					bw.write(lc+"\t"+codeLine+"\n");
				}


			}
			bw.close();


			printSYMTAB();
			//Printing Literal table
			PrintLITTAB();
			printPOOLTAB();

		} catch (IOException e) {


			e.printStackTrace();
		}
	}

	void printSYMTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("SYMTAB.txt"));
		//Printing Symbol Table
		java.util.Iterator<String> iterator = SYMTAB.keySet().iterator();
		System.out.println("SYMBOL TABLE");
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			TableRow value = SYMTAB.get(key);

			System.out.println(value.getIndex()+"\t" + value.getSymbol()+"\t"+value.getAddress());
			bw.write(value.getIndex()+"\t" + value.getSymbol()+"\t"+value.getAddress()+"\n");
		}
		bw.close();
	}
	void PrintLITTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("LITTAB.txt"));
		System.out.println("\nLiteral Table\n");
		//Processing LITTAB
		for(int i=0;i<LITTAB.size();i++)
		{
			TableRow row=LITTAB.get(i);
			System.out.println(i+"\t"+row.getSymbol()+"\t"+row.getAddress());
			bw.write((i+1)+"\t"+row.getSymbol()+"\t"+row.getAddress()+"\n");
		}
		bw.close();
	}
	void printPOOLTAB() throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter("POOLTAB.txt"));
		System.out.println("\nPOOLTAB");
		System.out.println("Index\t#first");
		for (int i = 0; i < POOLTAB.size(); i++) {
			System.out.println(i+"\t"+POOLTAB.get(i));
			bw.write((i+1)+"\t"+POOLTAB.get(i)+"\n");
		}
		bw.close();
	}

}
