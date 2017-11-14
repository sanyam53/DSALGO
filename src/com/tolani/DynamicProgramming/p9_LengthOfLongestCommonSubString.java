package com.tolani.DynamicProgramming;

import static com.tolani.DynamicProgramming.p4_LongestCommonSubseqn.printTable;

public class p9_LengthOfLongestCommonSubString {

    public static void main(String[] args) {
        String X = "0101010";        // length is 7
        String Y = "0001010";         // len is 7

        int m = X.length();
        int n = Y.length();

        System.out.println("length of Longest Common Substring for given two strings is : "
                + LCSsubSpaceEff(X.toCharArray(), Y.toCharArray(), m, n));
    }

    public static int LCSsub(char X[], char Y[], int m, int n) {
        int table[][] = new int[m + 1][n + 1];          // 8 :8 : means 0 to 7 table
        int res = 0;  // To store length of the longest common substring

        // intialization

        for (int i = 0; i <= m; i++) table[i][0] = 0;
        for (int j = 0; j <= n; j++) table[0][j] = 0;

        // filling entries further
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    res = Math.max(res, table[i][j]);
                } else
                    table[i][j] = 0;
            }
        }

        printTable(table, m, n);

        return res;
    }

    public static int LCSsubSpaceEff(char X[] , char Y[] , int m , int n)
    {
         int arr1[] = new int[m+1];
         int arr2[] = new int[m+1];                           // 8 :8 : means 0 to 7 table
        int res = 0;  // To store length of the longest common substring

        // intialization

        for (int i = 0; i <= m; i++) arr2[i] =0;

        for(int i=1 ; i <=m ;i++)
        {
            if(i%2 == 0)
            {
                //fill scnd arr and use 1st arr as stored vals
                 if (X[i - 1] == Y[i - 1]) {
                    arr2[i] = 1 + arr1[i-1];
                    res = Math.max(res, arr2[i]);
                } else
                    arr2[i] = 0;

            }

            else
            {
                // fill frst arr and use 2nd arr as stored vals
             if (X[i - 1] == Y[i - 1]) {
                    arr1[i] = 1 + arr2[i-1];
                    res = Math.max(res, arr1[i]);
                } else
                    arr1[i] = 0;

            }
        }

        return res;
    }
}