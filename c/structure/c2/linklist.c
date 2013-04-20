#include <stdio.h>
#include <malloc.h>

#define OK 0
#define OVERFLOW 1
#define ERROR 99

typedef int Status;
typedef int ElemType;

typedef struct LNode{
   ElemType data;
   struct LNode *next;
}LNode, *LinkList;

Status GetElem(LinkList l, int i, ElemType *e){
   LinkList p = l->next;
   int j = 1;
   while(p && j<i){
      j++;
      p++;
   } 
   if(!p || j>i) return ERROR;
   
   *e = p->data;
   return OK;
}

void PrintList(LinkList l){
   LinkList p = l->next;
   while(p){
      printf("data: %d\r\n", p->data);
      p = p->next;
   }
}

int main(){
   LNode head;
   LinkList l = &head;
   LinkList p = &head;
   int i;
   for(i=0;i<10;i++){
      LNode *node = (LNode*)malloc(sizeof(LNode));
      node->data = i;
      node->next = NULL;
      p->next = node;
      printf("p: %d \t\t", p);
      printf("p->next: %d \t\t", p->next);
      printf("node.next: %d \t\t", node->next);
      p = node;
      printf("new p: %d\r\n", p);

   }
PrintList(&head);
   return OK;
}
