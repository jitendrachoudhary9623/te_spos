import java.util.*;
public class optimal {
    int n, page[], f, frames[], faults, count;
    double rate;
    Scanner input= new Scanner(System.in);
    optimal(){
        System.out.println("Enter number of pages");
        n=input.nextInt();
        page=new int[n];
        System.out.println("Enter number of page frames");
        f=input.nextInt();
        frames=new int[f];
        count=1;
        int i;
        System.out.println("Enter the pages");
        for(i=0;i<n;i++)
        {
        System.out.println("Enter page number "+(i+1));
        page[i]=input.nextInt();
        }
        for(i=0;i<f;i++)
        frames[i]=-1;
        call_opt();
    }
    public void display()
    {
    int i;
    System.out.print("Page frame "+count+" :");
    for(i=0;i<f;i++)
    {
    if(frames[i]==-1) 
    System.out.print(" -");
    else
    System.out.print(" "+frames[i]);
    }
    System.out.print("\n");
    count++;
    }
    public void reset()
    {
        int j;
        for(j=0;j<f;j++)
        frames[j]=0;
        faults=0;
        count=1;
        }
    public void call_opt(){
    int i,j=0,k,duration[],max,flag[];
    reset();
    duration=new int[f];
    flag=new int[f];
    boolean found=false;

    for(i=0;i<n;i++)
    {
    for(j=0;j<f;j++)
    {
    flag[j]=0;
    duration[j]=n;
    }

    for(k=i+1;k<n;k++)
    {
    for(j=0;j<f;j++)
    if(page[k]==frames[j]&&flag[j]==0)
    {
    duration[j]=k;
    flag[j]=1;
    }
    }

    for(j=0;j<f;j++)
    if(page[i]==frames[j])
    found=true;
    if(found==false)
    {
    max=0;
    for(j=0;j<f;j++)
    {
    if(duration[j]>duration[max])
    max=j;
    if(frames[j]<0)
    {
    max=j;
    break;
    }
    }
    frames[max]=page[i];
    faults++;
    }

    display();
    found=false;

    }
    System.out.println("Number of page faults = "+faults);
    }
}
