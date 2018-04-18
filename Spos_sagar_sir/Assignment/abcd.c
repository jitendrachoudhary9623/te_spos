#include<stdio.h>

int main()
{

char input[10];
printf("Enter String\n");
int i;

//input
for(i=0;i<5;i++)
{
scanf("%c",&input[i]);
}

i=0;
while(i<=5)
{
char a=input[i];
if(a>='a'&&a<='z')
{
printf("Alphabet %c \n",a);
}
else
{
switch(a)
{
case '+':
case '-':
case '*':
case '/':
case '=':
printf("Operator %c \n",a);
break;
default:
printf("invalid");
}
}

i++;
}
return 0;
}
