import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class FCFS {

	public static void main(String[] args) {
		int num=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of process");
		num=sc.nextInt();
		
		int at[]=new int[num];
		int bt[]=new int[num];
		int wt[]=new int[num];
		int tat[]=new int[num];
		for(int i=0;i<num;i++)
		{
			System.out.println("Enter arrival time ");
			at[i]=sc.nextInt();
			System.out.println("Enter burst time");
			bt[i]=sc.nextInt();
		}
		wt[0]=0;
		for(int i=1;i<num;i++) {
			wt[i]=wt[i-1]+bt[i-1];
			wt[i]=wt[i]-at[i-1];
		}
		int wait=0;
		for(int i=0;i<num;i++)
		{
			tat[i]=wt[i]+bt[i];
			wait=wait+at[i];
		}
		System.out.println("Arrival\tBurstTime\tWaitingTime\tTurnAroundTime");
		for(int i=0;i<num;i++)
		{
			System.out.println(at[i]+"\t"+bt[i]+"\t"+wt[i]+"\t"+tat[i]);
		}
		System.out.println("actual waiting time "+(wait/num));
	}
}
