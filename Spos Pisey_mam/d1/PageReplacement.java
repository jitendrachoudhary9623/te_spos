import java.io.IOException;
import java.util.*;
public class PageReplacement {
	public static void main(String []args) throws IOException{
	    int a;
	    int i=1;
	    Scanner input=new Scanner(System.in);
	    while(i==1){
		System.out.println("***Page Replacement Menu***");
		System.out.println("1.FIFO");
		System.out.println("2.LRU");
		System.out.println("3.OPTIMAL");
		System.out.println("4.EXIT");
		System.out.println("ENTER YOUR CHOICE:");
		a=input.nextInt();
		    switch(a){
			    case 1:{
				new fifo();
				break;
			    }
			    case 2:{
				new lru();
				break;
			    }
			    case 3:{
				new optimal();
				break;
			    }
			    case 4:{
				System.out.println("PAGE REPLACEMENT CLOSED");
				i=0;
				break;
			    }
			    default:{
				System.out.flush();       
				System.err.println("Enter the correct input");
				break;
			    }
		    }
	    }
	}
}
 

 

 

