/*
*   fibo(n) = {fibo(n-1) + fibo(n-2)} % 100
* */


package com.tolani.Standard_Problems;

public class FiboModulo_Periodic {

    public static void main(String[] args)
    {
        int ans;

        int length = findCycleLength(3);

        System.out.println(length);

        ans=FindFibo(10230,3);

        System.out.println(ans);
    }

    public static int FindFibo(int num,int mod)
    {
        int cycleElement = num % findCycleLength(mod);

        int a=0 ; int b=1; int c=0;

        if(cycleElement == 0) return 0;

        else if (cycleElement == 1) return 1;

        else
            {
              for(int i=2; i <= cycleElement ; i++)
                {
                  c = ( a + b) % mod;
                  a=b;
                  b=c;
                }
            }

        return c;

  }


    public static int findCycleLength(int modulo)
    {
        int a = 0 ; int b = 1;

        int c;

        int cycleLen = 0;

        for(int i= 2; i <= modulo*modulo ; i++)          // bcz thr r m*m unique pairs pssble
        {
            c = (a + b) % modulo;
            a = b;
            b = c;

            cycleLen++;

            if( a == 0 && b == 1)
            {
                return cycleLen;
            }
        }

        return -1;

    }
}
