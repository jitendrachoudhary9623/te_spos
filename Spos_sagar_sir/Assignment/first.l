%{

#include<stdio.h>
FILE *fp;
int iLen=0;
int nLen=0;
int wLen=0;
int oLen=0;
int cLen=0;
int newLen=0;
int pLen;
%}


%%

[a-zA-Z]+ {iLen++; printf("%s\n",yytext); pLen=yyleng; printf("%d is Length",pLen);fprintf(fp,"%d is Length\n",pLen);}
[0-9]+ {nLen++;printf("%s\n",yytext);}
["+","-","/","*","="] {oLen++;printf("%s\n",yytext);}
[" "] {wLen++;printf("%s\n",yytext);}
["\n"] {newLen++;printf("%s\n",yytext);}
%%

int main()
{
yyin=fopen("sample.txt","r");
fp=fopen("output.txt","w");
yylex();


printf("%d is length of Word\n",iLen);
printf("%d is number of character\n",cLen);
printf("%d is length of Number\n",nLen);
printf("%d is length of White Space\n",wLen);
printf("%d is length of operator\n",oLen);
printf("%d is length of New Line\n",newLen);
return 0;
}

int yywrap()
{
return 1;
}
