package m1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PASS1 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		parseFile();
	}
	private static void parseFile() throws IOException {
		br=new BufferedReader(new FileReader("/home/jitendra/practise/m1/macro-ip.txt"));
	String line;
	int mntp=0,mdtp=0,mnt_i=0,mdt_i=0;
	int lc=0,ala_i=0;
	ArrayList<MNT> mnt=new ArrayList<>();
	ArrayList<ALA>[] ala=new ArrayList[5];
	ArrayList<MDT> mdt=new ArrayList<>();
	boolean isMacro=false,first=false;;
	while((line=br.readLine())!=null)
	{
		ala[ala_i]=new ArrayList<>();

		if(line.contains("MACRO"))
		{
			String name=br.readLine();
			String params[]=name.split("\\s+");
			mnt.add(new MNT(mnt_i,mdtp,params[0]));
			mntp++;
			mnt_i++;
			if(params.length>1)
			{
				String[] param=params[1].split(",");
				String formal,actual;
				for(int i=0;i<param.length;i++)
				{
					actual="";
					formal="";
					if(param[i].contains("="))
					{
						actual =param[i];
						int index=actual.indexOf("=");
						formal=actual.substring(0, index-1);
						actual=actual.substring(index+1);
						ala[ala_i].add(new ALA(formal,actual,i));
					}
					else
					{
						formal=param[i];
						ala[ala_i].add(new ALA(formal,actual,i));
					}
				}
			}
			
			
			String macro_line;
			while((macro_line=br.readLine())!=null)
			{
				if(macro_line.contains("MEND"))
				{
					mdt.add(new MDT(mdtp,macro_line));
					mdtp++;
					ala_i++;
					break;
				}
				else {
				mdt.add(new MDT(mdtp,macro_line));
				mdtp++;
				}
			}
			
		}
		
	}
	for(int i=0;i<mnt.size();i++)
	{
		MNT a=mnt.get(i);
		System.out.println(a.getIndex()+"\t"+a.getName()+"\t"+a.getAddress_mdt());;
	}
	
	for(int i=0;i<mdt.size();i++)
	{
		MDT a=mdt.get(i);
		System.out.println(a.getIndex()+"\t"+a.getStatement()+"\t");;
	}
	
	}

}


















/*
 * MODELS
 * 
 * 
 */
class MNT{
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getAddress_mdt() {
		return address_mdt;
	}
	public void setAddress_mdt(int address_mdt) {
		this.address_mdt = address_mdt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	int index,address_mdt;
	public MNT(int index, int address_mdt, String name) {
		super();
		this.index = index;
		this.address_mdt = address_mdt;
		this.name = name;
	}
	String name;
	
}

class MDT{
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public MDT(int index, String statement) {
		super();
		this.index = index;
		this.statement = statement;
	}
	int index;
	String statement;
	
}

class ALA{
	public ALA(String formal, String actual) {
		super();
		this.formal = formal;
		this.actual = actual;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	String formal,actual;
	public ALA(String formal, String actual, int index) {
		super();
		this.formal = formal;
		this.actual = actual;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	int index;
}