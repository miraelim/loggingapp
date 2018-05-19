#include <stdio.h>
#include <string.h>

int main(int argc, char **argv)
{
    FILE *fp1;
    char weatherdata[1024];
    fp1 =fopen("test.txt","r");

    if(fp1==NULL){
	printf("파일 읽기  실패\n");
	return 0;
    }

    while(fgets(weatherdata, 1024, fp1) != NULL)
    {
	printf(weatherdata);
    }
    return 0;
}

