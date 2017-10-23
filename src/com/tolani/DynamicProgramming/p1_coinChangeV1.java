package com.tolani.DynamicProgramming;

import java.util.Arrays;

public class p1_coinChangeV1 {

    public static void main(String[] args)
    {
        int[] coins = {1,7,10};     // keep this in sorted order and assume 1st denom is 1 Re. for now
        int amount = 15;

        System.out.println("min no of coins needed to pay amount are : " + bottomUp(amount,coins));

    }


    public static int bottomUp(int amount,int[] coins)
    {
        int v[][];

        int a = amount;            // a = 15 rs.
        int c = coins.length;      // c = 3 coins

        v = new int[a+1][c+1];     //  amount we wnt to pay * total no of coins

        // amnt is 15 so arr[15] is 0 to 14 but we need 15 , same for #coins we need 0 to 3

        // now we will compute the things tht are easy to compute : basic entries in the table

        for(int j=1; j <= c ; j++)
        {
            v[0][j] = 0;           // we wnt to pay 0 Rs. using first 'j' no of coins : v(0,j)=0;
        }

        // thr is no need of filling v[i,0] as 0 bcz it wont be used anywhr in the program
        // thr is no need of filling 0th row and coln of table also bcz it also wont be used program

        for(int i=1 ; i <=a ; i++)
        {
            v[i][1] = i;     // we wnt to pay 'i' Rs. using d1 = 1 Re. coin , so we need exactly i #of coins
        }

        /*
        so now we will consider v(i,j) th entry : two options either we pick up jth coint or dnt

        if we pick the jth coin then : 1 + v(i-dj,j) : we added 1 bcz we counted the jth coin tht
        we picked up and now we wnt to pay amount (i-dj) using first 'j' coins

        BUT this cn be done only if ( i >= dj ) : money we wnt to pay is > or = the denom. we have
        if we dnt pick up jth coin then : v(i,j-1) : we pay i rs using first j-1 coins

        so v(i,j) = Min{ v(i,j-1) , 1 + v(i-dj,j) }   , whr dj is the value of jth coin
        */

        for(int j=2 ; j <= c ; j++)     // columns first : coln by coln
        {
            for(int i=1 ; i <= a ; i++)      // rows
            {
                v[i][j] = v[i][j-1];    // 1st u put this entry in each cell of table then u cmpre : u put by default 1st optin & update later

                // now u compute the 2nd option : which cn be computed only if (i >= dj) , so first chck tht conditin nd then only compute
                // else outOfBounds come and logically u cant pay 50 rs with a 100 rs note

                if(i >= coins[j-1] && v[i][j] > 1 + v[i-coins[j-1]][j])
                {
                    v[i][j] = 1 + v[i - coins[j - 1]][j];        // so we update the value if #coins are lesser in the 2nd option we computed
                }

                // we ve to put 'j-1' in coins array bcz it has 2nd coin value at j-1th index whereas in the table v, we ve ignored 1st column whr we ve 0 coins thts why this bit offset handling is thr
            }
        }

        /*  printing the table if u wnt to see the table entries

        for(int i=0; i <=a ; i++)
        {
            //System.out.print(i + "  :");     // printing ith index

            for(int j=0; j <=c; j++)
            {
                System.out.print("  " + v[i][j] + "  ");       // entries in the table
            }
            System.out.println();
        }*/

        printCombination(v,amount,coins);

        return v[a][c];    // v[a=15] [c=3]  : v[15][3]  : no need to put a+1,c+1 here :outofboundsindex will come !
    }

    public static void printCombination(int[][] v,int amount, int[] coins)
    {
        int i=amount;               // we intialize i to be a = 15 , and j to be c =3 , means we start with the last entry and we backtrack
        int j = coins.length ;

        // now we will chck tht frm where we came : means which previous entry we ve used to compute v(i,j) means which option we ve used :
        // we had 2 optins : either we pick the jth coin or we dnt pick the jth coin , so if we picked the jth coin then we will print it
        // and decrmnt amount i to be (i - dj) , else we decrmnt j to be j-1 if didnt pick the jth coin

        System.out.print("coins we ve used are : ");

        while(i >= 1)        // we shuld go till 1st row
        {
            while(j >= 1)     // we shuld go till 1st column
            {
                if(v[i][j] == v[i][j-1])      // means we didnt pick the jth coin , so we
                {
                    j = j-1;          // so we update j to be j-1
                }

                else          // means we had picked the jth coin
                {
                    System.out.print(coins[j-1] + " ");

                    i = i - coins[j-1];      // so we update i to (i-dj)
                }

            }
        }

        System.out.println();
    }

}
