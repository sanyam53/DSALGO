package com.tolani.DynamicProgramming;

public class p2_0_1_Knapsack {

    public static void main(String[] args)
    {
        int items = 4;

        int[] weights = {2,1,3,2};
        int[] profits = {12,10,21,15};

        int capacityOfKnapsack = 8;

        System.out.println(bottomUp(items,weights,profits,capacityOfKnapsack));
    }

    public static int bottomUp(int n , int[] wt, int[] pft, int W)
    {
        int[][] v;

        v = new int[n+1][W+1];       // v(i,j) : v(4,8) : v(n,W): v(no of items,capacity of knapsack)

        // intialization

        for(int i=0; i <= n ; i++) v[i][0] =0;

        for(int j=0; j <= W; j++) v[0][j] = 0;

        // actual recursive logic : v(i,j) = MAX{ v(i-1,j) , profit(i) + v( i-1 , j-pft(i) ) }
                                                                //only whn j >= wt(i)

        for(int j=1 ; j <=W ; j++)
        {
            for(int i=1 ; i <= n ;i++)
            {
                v[i][j] = v[i-1][j];

                if(j >= wt[i-1] && v[i][j] < pft[i-1] + v[i-1][j-wt[i-1]])
                {
                    v[i][j] = pft[i-1] + v[i-1][j-wt[i-1]];
                }
            }
        }
          // /* printing the table if u wnt to see the table entries

        for(int i=0; i <=n ; i++)
        {
            System.out.print(i + "  :");     // printing ith index

            for(int j=0; j <= W; j++)
            {
                System.out.print("  " + v[i][j] + "  ");       // entries in the table
            }
            System.out.println();
        }  // */


        return v[n][W];
    }
}
