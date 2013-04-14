#include <stdio.h>
int main()
{
  int a,b;
  a = 5;
  b = a+++a--;
  printf("a=%d, b=%d\r\n", a, b);
  return 0;
}
