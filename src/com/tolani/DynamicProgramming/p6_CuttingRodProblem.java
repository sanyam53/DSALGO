package com.tolani.DynamicProgramming;

import static com.tolani.DynamicProgramming.p4_LongestCommonSubseqn.printTable;

public class p6_CuttingRodProblem {

    public static void main(String[] args) {

        int[] cuts = {0,25, 50, 75,100};

        int[] cuts2 = {0,80,90,95,100};

        bottomUp(cuts);

        System.out.printf("\n \n \n");        // System.out.printf : is thr for formatting the output.

        bottomUp(cuts2);
    }

    public static int bottomUp(int[] cuts)
    {
        int n = cuts.length ;  // means 'n' is the last index tht exist in array indxing

        int lenOfRod = cuts[n-1] - cuts[0] ;

        int[][] t = new int[n][n];

        for(int i=0; i < n-1 ; i++) {     // we go till 'n-1' bcz : i+1 is in thr see
            t[i][i+1] = 0;            // if thr r two consecutive pts then cost of cutting is 0
        }

        for(int i=0 ; i < n-2 ; i++)     // we go till n-3 bcz i+2 is in thr so tkng cr of outofbnds
        {
            t[i][i+2] = cuts[i+2] - cuts[i];     // so if thr is only one way to cut : at the mid pt then , cost is the len of rod bw i & j
        }

        for(int l=3 ; l < n ; l++)
        {
            for(int i=0 ; i < n-l ; i++)
            {
                int j = i+l;

                t[i][j] = cuts[j] - cuts[i];       // 1st cut would be a[j] - a[i] cost

                int value = Integer.MAX_VALUE;

                for(int k = i+1 ; k < j ; k++)         // now we will chck for all cuts bw i & j : and find minimum of them : i < k < j
                {
                    if( (t[i][k] + t[k][j]) < value)
                    {
                        value = t[i][k] + t[k][j];
                    }
                }

                t[i][j] = t[i][j] + value;
            }
        }

        printTable(t,n-1,n-1);

        return t[0][n-1];
    }

}
