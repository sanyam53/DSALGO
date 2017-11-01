package com.tolani.DynamicProgramming;

import java.util.Arrays;

import static com.tolani.DynamicProgramming.p4_LongestCommonSubseqn.printTable;

public class p7_CuttingRodCoremanProblem {

    public static void main(String[] args) {
        int[] prices = {2, 5, 9, 6};  // profits u cn make by sellling pieces of length 1 , 2 ,3 ,4 respectively

        int len = 5;     // length of rod u are given

        // recursive approach : >     System.out.println( cutRodRecursive(prices,len) );

        System.out.println(bottomUp(prices, len));
        System.out.println(bottomUpSpaceEff(prices,len));

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

    // here table will be : rows are the pieces of length tht are allowed to take  , colns are the total length we wnt to make using pieces
    // state v(i,j) = max profit we cn make using first i pieces ( with respctv lens) to make total length 'j'

    // intialization is v(i,0) = v(0,j) =0;

    // v(i,j) = if we pick ith piece of lenght 'i' then we will add the profit we cn mk using tht piece and now we hv to consider remaining len
                // means p[i] + v(i,j-i)    : means now using the same set of pieces (first i) we wnt to mk length 'j-i' to maximize profit
    // if we dnt pick ith piece then it is simple tht using i-1 pieces (of resp len) we ll mk profit : which is v(i-1,j)
    // so we will choose maximum of abv two but tk care tht 1st optin is only avlbl when "total length" >= "piece length"

    public static int bottomUp(int[] prices, int n) {

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

    public static int bottomUpSpaceEff(int[] p ,int len)
    {
        int[] v = new int[len+1];    // 0 to 5 : v[6]

        v[0]=0;

        for(int totalLen=1 ; totalLen <= len ; totalLen++)       // this is total length we wnt to mk
        {
             v[totalLen] = v[totalLen-1];       // if u exclude the jth piece then tk the previous entry

            for(int pieceLen=1 ;pieceLen <= p.length &&  pieceLen <= totalLen ; pieceLen++)     // this is pieces of len we allwd
            {
                if( (totalLen >= pieceLen) && ( p[pieceLen-1] + v[totalLen-pieceLen] ) > v[totalLen])      // if u wnt to pick jth item then
                {
                    v[totalLen] = p[pieceLen-1] + v[totalLen-pieceLen];
                }
            }

        }

        for(int i : v)
        {
            System.out.print(i + " ");
        }

        return v[len];
    }

}
