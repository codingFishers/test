#include <stdio.h>
#include <malloc.h>

//List Const
#define INITIAL_SIZE  10
#define INCREASE_SIZE  10

//Status
typedef int Status;

#define OK 0
#define OVERFLOW 11
#define ERROR 99

typedef int ElemType;

typedef struct{
   ElemType *elem;
   int length;
   int listsize;
} SqList;

//create List
Status InitialSqList(SqList *L){
   L->elem = (ElemType *)malloc(INITIAL_SIZE * sizeof(ElemType));
   if(!L->elem) return OVERFLOW;

   L->length = 0;
   L->listsize = INITIAL_SIZE;
   return OK;
}

//insert element before i
Status InsertSqList(SqList *L, int i,  ElemType e){
   if(i<1 || i>L->length+1) return ERROR;
   
   if(L->length >= L->listsize){
      ElemType *base = (ElemType *)realloc(L->elem, (L->listsize+INCREASE_SIZE)*sizeof(ElemType));
      if(!base) return OVERFLOW;
      L->elem = base;
      L->listsize = L->listsize+INCREASE_SIZE;
   }
 
   ElemType *q = &L->elem[i-1];
      
   for(ElemType *p = &L->elem[L->length-1];p>=q;p--){
      *(p+1) = *p;
   }
   
   *q = e;
   L->length++;

   return OK;
}

//delete element before i
Status DeleteSqList(SqList *L, int i, ElemType *e){
   if(i < 1 || i > L->length) return ERROR;
   
   ElemType *p = &L->elem[i-1];
   ElemType *q = &L->elem[L->length-1];
   
   *e = *p;
  
   for(;p<q;p++){
      *(p) = *(p+1);
   }
   L->length --;
}

//print list
Status PrintSqList(SqList *L){
   if(L->length == 0 ){
      printf("Empty SqList, Capacity: %d\r\n", L->listsize);
      return OK;
   }
   printf("Length: %d, Capacity: %d\n", L->length, L->listsize);
   ElemType *q = &L->elem[L->length-1];
   for(ElemType *p = &L->elem[0];p<=q;p++){
      printf("element: %d\r\n", (*p));
   }
   return OK;
}

//find element e
int LocateElem(SqList L, ElemType e, int(*compare)(ElemType e1, ElemType e2)){
   int i=1;
   ElemType *p = L.elem;
   while(i<=L.length && !compare(*p++, e)) i++;
   
   if(i <= L.length){
      return i;
   }
   else{
      return 0;
   }
}

//compare element
int _comp(ElemType e1, ElemType e2){
   if(e1 == e2) return 1;
   return 0;
}

Status MergeSqList(SqList la, SqList lb, SqList *lc){
   lc->listsize = lc->length = la.length+lb.length;
   lc->elem = (ElemType *)malloc(lc->listsize * sizeof(ElemType));
   if(!lc->elem) return OVERFLOW;

   ElemType *pa = la.elem;
   ElemType *pa_last = pa+la.length-1;

   ElemType *pb = lb.elem;
   ElemType *pb_last = pb+lb.length-1;

   ElemType *pc = lc->elem;

   while(pa<=pa_last && pb<=pb_last){
      ElemType ea = *pa;
      ElemType eb = *pb;
      
      if(ea <= eb){
         *pc = ea;
         pa++;
      }else{
         *pc = eb;
         pb++;
      }
      pc++;
   } 

   while(pa<=pa_last){
      *pc++ = *pa++;
   }

   while(pb<=pb_last){
      *pc++ = *pb++;
   }

   return OK;
}

int main(){
   SqList L;
   InitialSqList(&L);
   for(int i=1;i<=21;i++){
      InsertSqList(&L, i, i);

   }
   PrintSqList(&L);
   ElemType e;
   DeleteSqList(&L, 2, &e);
   printf("delete: %d\r\n", e);
   PrintSqList(&L);
   DeleteSqList(&L, 7, &e);
   printf("delete: %d\r\n", e);
   PrintSqList(&L);

   int pos =  LocateElem(L, 1, *_comp);
   printf("position of 1 is : %d\r\n", pos);

   SqList La;
   InitialSqList(&La);
   InsertSqList(&La, 1, 1);
   InsertSqList(&La, 2, 3);
   InsertSqList(&La, 3, 4);
   InsertSqList(&La, 4, 7);
   InsertSqList(&La, 5, 9);


   SqList Lb;
   InitialSqList(&Lb);
   InsertSqList(&Lb, 1, 2);
   InsertSqList(&Lb, 2, 5);
   InsertSqList(&Lb, 3, 6);
   InsertSqList(&Lb, 4, 8);
   InsertSqList(&Lb, 5, 10);

   PrintSqList(&La);
   PrintSqList(&Lb);

   SqList Lc;
   MergeSqList(La, Lb, &Lc);

   PrintSqList(&Lc);
}

