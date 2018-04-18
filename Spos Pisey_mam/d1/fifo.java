import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class fifo {
    int n, page[], f, frames[], faults, count;
    double rate;
    BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
   
    fifo() throws IOException{
        System.out.println("Enter number of pages");
        n=Integer.parseInt(input.readLine());
        page=new int[n];
        System.out.println("Enter number of page frames");
        f=Integer.parseInt(input.readLine());
        frames=new int[f];
        count=1;
        read();
        call_fifo();
    }
    public void reset(){
        int j;
        for(j=0;j<f;j++)
            frames[j]=-1;
        faults=0;
        count=1;
    }
    public void read() throws IOException
    {
    int i;
    System.out.println("Enter the pages");
    for(i=0;i<n;i++)
    {
    System.out.println("Enter page number "+(i+1));
    page[i]=Integer.parseInt(input.readLine());
    }
    for(i=0;i<f;i++)
    frames[i]=-1;
    }
    public void call_fifo(){
        int i,j,k=0;
        reset();
        boolean found=false;
        for(i=0;i<n;i++)
        {
        for(j=0;j<f;j++)
        {
        if(page[i]==frames[j])
        found=true;
        }
        if(found==false)
        {
        frames[k]=page[i];
        if(k==f-1)
        k=0;
        else
        k++;
        faults++;

        }
        display();
        found=false;
        }
        System.out.println("Number of page faults = "+faults);
        }
    void display()
    {
    int i;
    System.out.print("Page frame "+count+" :");
    for(i=0;i<f;i++){
        if(frames[i]==-1)
            System.out.print("-1");
        else
            System.out.print(" "+frames[i]);
    }
    System.out.print("\n");
    count++;
    }
}
