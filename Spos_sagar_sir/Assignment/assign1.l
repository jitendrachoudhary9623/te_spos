%{
FILE *fp;
int vLen=0;
int cLen=0;

%}

%%

[a,i,e,o,u] {cLen++; fprintf(fp,"%s is a vowel\n",yytext);}
[a-zA-Z] {vLen++;fprintf(fp,"%s is a consonants\n",yytext);}
%%

int main()
{
yyin=fopen("a1.txt","r");
fp=fopen("o1.txt","w");
yylex();
printf("%d\n",cLen);
printf("%d",vLen);
return 0;
}

int yywrap()
{


return 1;
}
