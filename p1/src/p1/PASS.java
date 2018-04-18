package p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PASS {
	/*public static void main(String[] args) throws IOException {
		PASS p = new PASS();
		p.parseFile();
	}*/
	static Queue<String> queue = new LinkedList<>();
	public void parseFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/home/jitendra/practise/p1/src/input.txt"));
		int lc = 0,lp=0;
		String line;
		HashMap<String, Row> symbolTab = new HashMap<>();
		ArrayList<LitTab> literalTab = new ArrayList<>();
		
		while ((line = br.readLine()) != null) {
		//	System.out.println(line + "  " + lc);
			String parts[] = line.split("\\s+");
			if (parts[1].equals("START")) {
				lc = Integer.parseInt(parts[2]);
			}
			if (!parts[0].isEmpty()) {
				if (symbolTab.containsKey(parts[0])) {
					symbolTab.put(parts[0], new Row(lc, true));
					lc++;

				} else if (parts[1].equals("DS")) {

					lc += Integer.parseInt(parts[2]);

				} else {
					symbolTab.put(parts[0], new Row(lc, true));
					lc++;

				}
				
				if(parts[2].contains(","))
				{
					String symbols[] = parts[2].split(",");
					//System.out.println(symbols[0]);
					if (symbols.length > 1) {
						char a = symbols[1].charAt(0);
						if (a == '=') {
							// literalTab.add(new LitTab(symbol[1],lc));
							queue.add(symbols[1]);
							//lc++;
						} 
				}
				}

				if (parts[1].equals("EQU")) {
					if (symbolTab.containsKey(parts[2])) {
						Row r = symbolTab.get(parts[2]);
						int size = r.getLocation();
						// System.out.println("Size\t"+size);
						lc = size;
						symbolTab.put(parts[0], new Row(lc, true));
						lc++;
					}
				}

			}

			if (parts[1].equals("ORIGIN")) {
				String seprate[] = parts[2].split("\\+");
				int size;
				if (symbolTab.containsKey(seprate[0])) {
					Row r = symbolTab.get(seprate[0]);
					size = r.getLocation();
					// System.out.println("Size\t"+size);
					lc = size + Integer.parseInt(seprate[1]) - 1;
				}
				lc++;
			}

			if (parts[0].isEmpty()) {
				String symbol[] = parts[2].split(",");
				// System.out.println(symbol[0]);
				if (symbol.length > 1) {
					char a = symbol[1].charAt(0);
					if (a == '=') {
						//System.out.println(symbol[1]);
						// literalTab.add(new LitTab(symbol[1],lc));
						queue.add(symbol[1]);
						System.out.println(symbol[1]);


					//	System.out.println("Peak "+queue.peek());
						lc++;
					} else {
						if (Character.isDigit(a)) {
							lc++;
						} else if (!Character.isDigit(a)) {
							symbolTab.put(symbol[1], new Row(lc, false));
							lc++;
						} else
							lc++;
					}

					if (parts[1].equals("BC")) {
						lc = lc + 2;
					}

				} else if (parts[1].equals("STOP")) {
					lc++;
				} else if (parts[1].equals("LTORG")) {
				//	System.out.println(lc);
					Iterator itr=queue.iterator();  
					
					lp=lc;
					while(!queue.isEmpty()) {
					//	System.out.println("Finale "+queue.peek());
					literalTab.add(new LitTab(queue.peek(),lp));
					lp++;
					queue.remove();
					}
					
				
				}

			}
		}
		BufferedWriter symbol=new BufferedWriter(new FileWriter("/home/jitendra/practise/p1/src/Symbol.txt"));
		int j=0;
		PrintWriter writer = new PrintWriter("/home/jitendra/practise/p1/src/Symbol.txt", "UTF-8");
		
		
		for (Map.Entry m : symbolTab.entrySet()) {
			Row r = (Row) m.getValue();
			//System.out.println(" " + m.getKey() + " " + r.getLocation() + " ");
			writer.println(j+"\t\t" + m.getKey() + "\t\t" + r.getLocation() );
			j++;

		}writer.close();
		
		writer = new PrintWriter("/home/jitendra/practise/p1/src/Literal.txt", "UTF-8");
		
		for(int i=0;i<literalTab.size();i++)
		{
			writer.println(i+"\t\t"+literalTab.get(i).getLiteral()+"\t\t"+literalTab.get(i).getLocation());;
		}writer.close();
	}
	}


	
