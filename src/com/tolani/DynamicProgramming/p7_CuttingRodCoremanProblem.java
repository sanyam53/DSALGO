package com.tolani.DynamicProgramming;

import java.util.Arrays;

import static com.tolani.DynamicProgramming.p4_LongestCommonSubseqn.printTable;

public class p7_CuttingRodCoremanProblem {

    public static void main(String[] args) {
        int[] prices = {2, 5, 9, 6};

        int len = 5;     // length

        // recursive approach : >     System.out.println( cutRodRecursive(prices,len) );

        System.out.println(bottomUp(prices, len));


        // test case 2 :

        System.out.println();
        int[] prices2 = {1,5,8,9,10,17,17,20};
        int len2 = 8;
        bottomUp(prices2,len2);
    }

    public static int cutRodRecursive(int[] p, int len) {
        if (len == 0) return 0;        // condition to stop the recursion

        int n = len - 1;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i <= n; i++) {
            max = Math.max(max, p[i] + cutRodRecursive(p, n - i));
        }

        return max;
    }

    public static int bottomUp(int[] prices, int n) {
        int max = Integer.MIN_VALUE;

        int plen = prices.length;    // length of pieces tht are allowed

        int[][] v = new int[plen+1][n + 1];

        for (int i = 0; i <= plen; i++) {        // rows are the pieces tht are allwd , colns are length we ve to make
            v[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            v[0][j] = 0;
        }

        // v(i,j) = max price we cn get by using first 'i' items to make total length 'j'
        // to fill i,jth entry we ve two optins : either we pick ith item or we dnt pick ith item
        // v(i,j) = Pi + v(i,j-i)  : so if we pick ith item then price of tht added , now remaining length shuld be considerd using frst i lengths
        // v(i,j) = v(i-1,j) : using first i-1 lengths u hv to make total length 'j' to mk max profit

        for (int i = 1; i <= plen; i++)      // this is length of pieces tht are allowed to make total length 'j'
        {
            for (int j = 1; j <= n; j++) {
                v[i][j] = v[i - 1][j];

                if ((i <= j) && (prices[i - 1] + v[i][j - i]) > v[i][j]) {
                    v[i][j] = prices[i - 1] + v[i][j - i];
                }
            }
        }
            printTable(v, plen, n);

            return v[plen][n];
    }
}
