package com.tolani.Stack_Hack;

public class FindMin_InSlidingWindow {

    static int[] stackQueue = new int[100];

    static int TOS= -1;        // top of the stack for the insertion
    static int FRONT= 0;      // front for the deletion


    static int[] arr = {3,6,8,7,5,2,9,10};

    static int[] arr2 = {6,8,3,5,10,2,1,4};  // see this test case to check in the last window ok.

    public static void main(String[] args)
    {
        FindMin(arr,0,arr.length-1,3);
    }

    public static void FindMin(int arr[],int low , int high,int winsize)
    {
        int i=0;
        int winpointer=0;     // winpointer is the starting pointer to the window whereas 'i' is ending pointer of the window

        while(i < winsize)
        {
            if(TOS == -1)          // to push the first element onto the stack
            {
                push(i); i++;
            }

            else if(arr[i] > arr[stackQueue[TOS]])
            {
                push(i); // pushing the index of the element
                i++;
            }

            else if(arr[i] < arr[stackQueue[TOS]])
            {
                pop();
               // interesting things is : do nothing with 'i' here so i wont incrmnt and loop will run again
            }
        }

        System.out.println(arr[stackQueue[FRONT]]);

        winpointer++;  // winpointer is 1 now

        while(i <=high)        // as this is the end pointer to the window so it will go till the last index , so last window also get checked
        //while(winpointer <= (high-winsize+1))    // in this approach last elements in the window left out to check so last win wont be chckd for min
        {
            if(TOS == -1)        // this is imp bcz whn stack get empties then ur program will crash !
            {
                push(i);
                FRONT = 0;    // so stack is empty now so make front again pointing to the frst element
            }

            if(arr[i] > arr[stackQueue[TOS]])
            {
                push(i);
               // i++;
            }

            else if(arr[i] < arr[stackQueue[TOS]])
            {
                pop();
                continue;
            }

            i++;

            if(stackQueue[FRONT] < ((i-1) - winsize +1)) {FRONT++;}   // (i-1) here bcz 'i' get incrmntd aftr pushing

            // before printing this we ve to delete the elemnt which isnt in the range
            System.out.println(arr[stackQueue[FRONT]]);
            //winpointer++;

        }


    }

    public static void push(int i)
    {
        stackQueue[++TOS] = i;
    }

    public static int pop()
    {
        int temp = stackQueue[TOS--];
        return temp;
    }
}
