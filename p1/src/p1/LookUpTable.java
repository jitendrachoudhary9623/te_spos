package p1;

import java.util.HashMap;

public final class LookUpTable {
HashMap<String,Integer> AD;
HashMap<String,Integer> IS;
HashMap<String,Integer> DL;
HashMap<String,Integer> CC;
HashMap<String,Integer> RG;
LookUpTable(){
	AD=new HashMap<>();
	IS=new HashMap<>();
	DL=new HashMap<>();
	CC=new HashMap<>();
	RG=new HashMap<>();
	
	IS.put("STOP",0);  // S A S M M M C B D R P
	IS.put("ADD",1);
	IS.put("SUB",2);
	IS.put("MOV",3);
	IS.put("MOVM",4);
	IS.put("MOVR",5);
	IS.put("CMP",6);
	IS.put("BC",7);
	IS.put("DIV",8);
	IS.put("READ",9);
	IS.put("PRINT",10);
	
	AD.put("START",01);
	AD.put("END",02);
	AD.put("ORIGIN",03);
	AD.put("EQU",04);
	AD.put("LTORG",05);
	
	DL.put("DS", 01);
	DL.put("DC",02);
	
	
	CC.put("EQ",1);
	CC.put("LT",2);
	CC.put("GT",3);
	CC.put("LE",4);
	CC.put("GE",5);
	CC.put("NE",6);
	CC.put("ANY",7);
	
	RG.put("AREG", 01);
	RG.put("BREG",02);
	RG.put("CREG",03);
	RG.put("DREG",04);
	
}

public String returnType(String s) {
	if(AD.containsKey(s))
	{
		return "AD";
	}
	if(IS.containsKey(s))
	{
		return "IS";
	}
	if(CC.containsKey(s))
	{
		return "CC";
	}
	if(DL.containsKey(s))
	{
		return "DL";
	}	
	if(RG.containsKey(s))
	{
		return "RG";
	}
	else return "-1";
	
}
public Integer returnCode(String s) {
	if(AD.containsKey(s))
	{
		return AD.get(s);
	}
	if(IS.containsKey(s))
	{
		return IS.get(s);
	}
	if(CC.containsKey(s))
	{
		return CC.get(s);
	}
	if(DL.containsKey(s))
	{
		return DL.get(s);
	}	
	if(RG.containsKey(s))
	{
		return RG.get(s);
	}
	return -1;
	
}




}
//final class
//create five hash maps