import java.util.*;
class lru {
int frm[],indfrm;
int p[],indp,i,j,fs[],indfs,n;
int index,k,l,flag1=0,flag2=0,pf=0,frsize;
Scanner  input=new Scanner(System.in);
lru(){
call_lru();
}
public void disp(){
    int i;
    System.out.println();
    for(i=0;i<3;i++)
    System.out.println("\t"+frm[i]);
}
public void call_lru(){
    System.out.println("Enter the frame size");
    indfrm=input.nextInt();
    frsize=indfrm;
    System.out.println("Enter the number of pages");
    n=input.nextInt();
    p=new int[n];
    frm=new int[indfrm];
    fs=new int[indfrm];
    System.out.println("Enter the page number");
    for(i=0;i<n;i++){
        p[i]=input.nextInt();
    }
    for(i=0;i<indfrm;i++){
        frm[i]=-1;
    }
    for(j=0;j<n;j++){
        flag1=0;
        flag2=0;
        for(i=0;i<indfrm;i++){
            if(frm[i]==p[j]){
                flag1=1;
                flag2=1;
                break;
            }
        }
        if(flag1==0){
            for(i=0;i<indfrm;i++){
                if(frm[i]==-1)
                {
                frm[i]=p[j];
                flag2=1;
                break;
                }
            }
        }
        if(flag2==0){
            for(i=0;i<indfrm;i++){
                fs[i]=0;
            }
            for(k=j-1,l=1;l<=frsize-1;l++,k--){
                for(i=0;i<indfrm;i++){
                    if(frm[i]==p[k]){
                        fs[i]=1;
                    }
                }
            }
            for(i=0;i<indfrm;i++){
                if(fs[i]==0)
                    index=i;
            }
            frm[index]=p[j];
            pf++;
        }
        disp();
    }
    System.out.println();
    System.out.println("number of page faults: "+pf);
}
}
