%{
FILE *out;

int openP=0;
int closeP=0;
%}

%%

("import "[a-zA-Z]*"."[a-zA-Z]*"."[a-zA-Z]*""["*"]*";")  					{fprintf(out,"Import Statement found\n  %s \n\n",yytext);}
["{"] 								{openP++;fprintf(out,"Opening Bracket Found , bracket Number %d \n\n",openP);}
("class "[a-zA-Z]*"") 						{fprintf(out,"Class declerationfound\n %s \n\n",yytext);}
("public static void "[m][a][i][n]"(String args[])")  		{fprintf(out,"Main Function found %s \n\n ",yytext);}
("System.out.println"["("]""["""]""[a-zA-Z]*""[" "]*""[a-zA-Z]*""[")"]*";") 					{fprintf(out,"print satement found %s\n\n",yytext);}
["}"] 								{closeP++;fprintf(out,"Closing Bracket Found , bracket Number %d \n\n",closeP);}
%%

int main()
{
yyin=fopen("hello.java","r");
out=fopen("output-java.txt","w");
yylex();
return 0;
}

int yywrap()
{
return 1;
}
