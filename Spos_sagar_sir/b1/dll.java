import java.util.*;
import java.io.*;

public class dll{

static{
System.loadLibrary("cal_java");
}

public native int add(int a,int b);

public static void main(String args[]){
Scanner sc=new Scanner(System.in);
System.out.println("Enter value of A");
int a=sc.nextInt();
System.out.println("Enter value of B");
int b=sc.nextInt();

new dll().add(a,b);
}

}
