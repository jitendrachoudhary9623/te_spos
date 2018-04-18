package lru;

import java.util.Scanner;

public class Least {

	public static int min(int counter[], int number_of_frames) {
		int position = 0;
		int minimum = counter[0];
		for (int i = 0; i < number_of_frames; i++) {
			if (minimum > counter[i]) {
				position = i;
			}
		}

		return position;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int number_of_frames;
		int size_of_ref;

		System.out.println("Enter number of frames");
		number_of_frames = sc.nextInt();

		System.out.println("Enter number of pages");
		size_of_ref = sc.nextInt();

		int pages[] = new int[size_of_ref];
		System.out.println("Enter page reference String");
		for (int i = 0; i < size_of_ref; i++) {
			pages[i] = sc.nextInt();
		}

		int frames[] = new int[number_of_frames];
		int counter[] = new int[number_of_frames];

		for (int i = 0; i < number_of_frames; i++) {
			frames[i] = 0;
			counter[i] = 0;
		}
		
		int recent=0,pageFault=0;
		//main logic
		for(int i=0;i<size_of_ref;i++)
		{
			int flag=0;
			for(int j=0;j<number_of_frames;j++) //look for equal
			{
				if(pages[i]==frames[j])
				{
					counter[j]=recent++;
					flag=1;
					break;
				}
			}
			if(flag==0)  //if no pages are there
			{
				for(int j=0;j<number_of_frames;j++)
				{
					if(frames[j]==0)
					{
						counter[j]=recent++;
						frames[j]=pages[i];
						flag=1;
						pageFault++;
						break;
					}
				}
				
			}
			if(flag==0) { //replace
				int position=min(counter,number_of_frames);
				frames[position]=pages[i];
				counter[position]=recent++;
			}
			
			for(int j=0;j<number_of_frames;j++)
			{
				System.out.print(frames[j]+"\t");
			}
			System.out.println();
		}
		
			
	}
}
