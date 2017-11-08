package com.tolani.Standard_Problems;

public class Power {

    public static void main(String[] args)
    {
        System.out.println(powRecur(2,10));
        System.out.println(powIterative(2,10));
    }

    // recursive code to find a power in logn time

    public static int powRecur(int a , int n)
    {
        if(n == 0) return 1;

        else if(n == 1) return a;

        else if(n % 2 == 0)
        {
           int v = powRecur(a,n/2);      // we ve computed this value to save two recursive calls
           return v*v;               // this is " pow(a,n/2)*pow(a,n/2); "
        }
        else
        {
            int v = powRecur(a,n/2);
            return a*v*v;             // this is  " a * pow(a,n/2) * pow(a,n/2); ""
        }
    }

    // iterative code for the same , to find pow in logn time  : its kind of bit manipulation

    // logic is tht if n is 6 then bit pattern is "110" : so we ll multiply 2^4 * 2^2 = 2^6 : we got

    public static int powIterative(int a , int n)
    {
        int y = 1;

        while(n > 0)        // we will go through bit pattern of 'n' and square the 'a' if we hv 1
        {
            if(n % 2 != 0)        // if n is odd as example '3'
            {
                y = y*a;         // multiplying with 'a' then it will be squared
            }

            a = a*a;     // we r squaring every time the 'a' value a2 , a4, a8 ... : whn 1 in bit pattern

            n = n/2;   //or n >> 1  // u r dividing 'n' by 2 means it is right shift in bit pattern
                                // moving ahead in bit pattern of 'n'
        }

        return y;
    }
}
