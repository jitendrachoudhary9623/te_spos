package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.MNT;
import Model.MPT;

public class Main {

	static File file;
	static FileWriter writer;
	static List<MNT> mntTable=new ArrayList<MNT>();
	static List<MPT> mptTable=new ArrayList<MPT>();


	public static void main(String args[]) throws IOException {
		// Output File
		file = new File("Hello.txt");
		System.out.println(file.getAbsolutePath());
		file.createNewFile();
		writer = new FileWriter(file);

		// input file
		BufferedReader br = new BufferedReader(new FileReader("/home/admin1/SPOS/MACRO1/src/Files/input.txt"));
		String state;
		int lt=0;
		boolean next=false;
		// reading input file till eof
		while ((state = br.readLine()) != null) {
			if(state.equals("MACRO"))
			{
				String s;
				boolean flag=true;
				next=true;

				while(flag)
				{
					s=br.readLine();
					if(next==true)
					{
						next=false;
						String a[]=s.split(" ");
						mntTable.add(new MNT(a[0],lt));
					}
					if(s.equals("MEND"))
					{
						flag=false;
						break;
					}
					lt++;
					if(s.equals("MACRO"))
					{
						lt--;
						next=true;

					}
					else{
					mptTable.add(new MPT(s));
					
					
					}
					
				}
			}
			
		}
		writer.write("\n\nMPT TABLE\n\n");
		for(int i=0;i<mptTable.size();i++)
		{
			System.out.println(mptTable.get(i).getStatement());
			writer.write(""+mptTable.get(i).getStatement()+"\n");
		}
		writer.write("\n\nMNT TABLE\n\n");

		for(int i=0;i<mntTable.size();i++)
		{
			System.out.println(mptTable.get(i).getStatement());
			writer.write(""+mntTable.get(i).getName()+"  ||  "+mntTable.get(i).getAddress()+"\n");
		}
		writer.close();
	}
}
