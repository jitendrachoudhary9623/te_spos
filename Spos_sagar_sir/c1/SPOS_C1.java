import java.io.*;
import java.util.*;
public class SPOS_C1
{
	static int n;
	static process[] p=new process[10];
	/*public static void accept(int ch);
	public static void fcfs();
	public static void disp_gantt();
	public static void sjf_sort();
	public static void priority_sort();
	public static void round_robin(int tq);*/
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)
	{
		int ch,tq;
		do
		{
			System.out.println("\n\t—- Scheduling Algorithms —-");
			System.out.println("\n\t1. FCFS\n\t2. SJF\n\t3. Priority Based\n\t4. Round Robin\n\t5. Exit");
			System.out.println("\n\t Enter your choice: ");
			ch = sc.nextInt();
			switch(ch)
			{
				case 1: accept(ch);
					fcfs();
					break;

				case 2: accept(ch);
					sjf_sort();
					fcfs();
					break;

				case 3: accept(ch);
					priority_sort();
					fcfs();
					break;

				case 4: System.out.println("\n\tEnter the Time Quantum: ");
					tq= sc.nextInt();
					accept(ch);
					round_robin(tq);
					break;

				case 5: break;
			}
			System.out.println("\n\n\tPress any key to continue………");
	
		}while(ch!=5);
	}

	public static void accept(int ch)
	{
		int i;
		System.out.println("\n\tHow many processes: ");
		n= sc.nextInt();;
		System.out.println("\tEnter the values\n");
		if(ch==1 || ch==2)
		{
			System.out.println("\n\tArrival Time and Burst Time\n");
			for(i=0;i<n;i++)
			{
				p[i]=new process();
				System.out.println("\tEnter for Process : "+i);
				p[i].at= sc.nextInt();
				p[i].bt= sc.nextInt();
				p[i].pid=i;
			}
		}
		if(ch==3)
		{
			System.out.println("\n\tArrival Time , Burst Time and Priority\n");
			for(i=0;i<n;i++)
			{
				p[i]=new process();
				System.out.println("\tEnter for Process : "+i);
				p[i].at= sc.nextInt();
				p[i].bt= sc.nextInt();
				p[i].prior= sc.nextInt();;
				p[i].pid=i;
			}
		}
		if(ch==4)
		{
			System.out.println("\n\tArrival Time and Burst Time\n");
			for(i=0;i<n;i++)
			{
				p[i]=new process();
				System.out.println("\tEnter for Process :"+i);
				p[i].at= sc.nextInt();
				p[i].bt= sc.nextInt();
				p[i].pid=i;
			}
		}
	}

	public static void fcfs()
	{
		int i;
		float avgtat=0,avgwt=0;
		for(i=0;i<n;i++)
		{
			if(i==0)
			{
				p[i].st=i;
				p[i].wt=p[i].st-p[i].at;
				p[i].tat=p[i].wt+p[i].bt;
				p[i].ft=p[i].st+p[i].bt;
			}
			else
			{
				p[i].st=p[i-1].ft;
				p[i].wt=p[i].st-p[i].at;
				p[i].tat=p[i].wt+p[i].bt;
				p[i].ft=p[i].st+p[i].bt;
			}
			avgtat=avgtat+p[i].tat;
			avgwt=avgwt+p[i].wt;
		}
		avgtat=avgtat/n;
		avgwt=avgwt/n;
		disp_gantt();
		System.out.println("\n\t** Average Turn Around Time:%f **"+avgtat);
		System.out.println("\n\t** Average waiting time:%f **"+avgwt);
	}

	public static void disp_gantt()
	{
		int i;
		System.out.println("\n\tGANTT CHART\n ");
		for(i=0;i<n;i++)
			System.out.println("\t P"+p[i].pid);
		System.out.println("\n");
		for(i=0;i<n;i++)
			System.out.println("\t "+p[i].st);
		System.out.println("\t "+p[i-1].ft);
		System.out.println("\n");
	}

	public static void sjf_sort()
	{
		int i,j;
		process temp;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n-i-1;j++)
			{
				if(p[j].bt>p[j+1].bt)
				{
					temp=p[j];
					p[j]=p[j+1];
					p[j+1]=temp;
				}
			}
		}
	}

	public static void priority_sort()
	{
		int i,j;
		process temp;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n-i-1;j++)
			{
				if(p[j].prior>p[j+1].prior)
				{
					temp=p[j];
					p[j]=p[j+1];
					p[j+1]=temp;

				}
			}
		}
	}

	public static void round_robin(int tq)
	{
		int allterminate=0,cnt=0,i;
		int[] gt=new int[30];
		int[] ps=new int[30];
		int temp=0,pcnt=0;
		float avgtat=0;
		System.out.println("\n\tGANTT CHART\n\n");
		while(allterminate<n)
		{
			for(i=0;i<n;i++)
			{
				if(p[i].flag==0) //process incomplete
				{
					p[i].st=temp;
					cnt++;
					if(p[i].bt>=tq)//burst time > time slice
					{
						p[i].bt=p[i].bt-tq;
						temp=temp+tq;
						gt[cnt]=temp;
						ps[pcnt]=p[i].pid;
						pcnt++;
					}
					else
					{ //burst time < time slice
						temp=temp+p[i].bt;
						gt[cnt]=temp;
						p[i].bt=0;
						ps[pcnt]=p[i].pid;
						pcnt++;
					}

					if(p[i].bt==0)
					{
						allterminate++;
						p[i].flag=1;
						p[i].ft=temp;
					}

					if(p[i].flag==1)
					{
						p[i].tat=p[i].ft;
						avgtat=avgtat+p[i].tat;
					}
				}//if

			}//for

		}//while
		for(i=0;i<pcnt;i++)
			System.out.println("\t P "+ps[i]);
		System.out.println("\n\t0 ");
		for(i=1;i<=cnt;i++)
			System.out.println("\t "+gt[i]);

		avgtat = avgtat/n;
		System.out.println("\n\t** Average Turn Around Time:"+avgtat+" **");
	}
}
