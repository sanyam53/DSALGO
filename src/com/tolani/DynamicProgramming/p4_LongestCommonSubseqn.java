package com.tolani.DynamicProgramming;

public class p4_LongestCommonSubseqn {

    public static void main(String[] args)
    {
        char[] str1 = "abcabba".toCharArray();
        char[] str2 = "cbabaca".toCharArray();

       int maxLengthLCS = bottomUp(str1,str2);

       System.out.println(maxLengthLCS);
    }

    public static int bottomUp(char[] x , char[] y)
    {
        int n = x.length;
        int m = y.length;

        int[][] L = new int[n+1][m+1];

        for(int i=0 ; i <= n ; i++) {
            L[i][0] = 0;
        }

        for(int j =0 ; j <= m ; j++) {
            L[0][j] = 0;
        }

     // lets say len(x) is n & len(y) is 'm' : now we compare Xn with Ym : if both r equal tht means they will be in the subsequence definately
     // so we will find LCS Of remaining seqnuences and add (Xn or Ym) to it
     // if (Xn != Ym) then we can say tht both of them cant be in the L.C.subsequence : one of them may be in the LCS or may nt be : tht we hv
     // check

     // recursive eqn is : L(i,j) = 1 + L(i-1,j-1)                 ; if  Xi == Yj    :: means we now find LCS recursively fr remaining seqns
     //                           = Max{ L(i-1,j) , L(i,j-1) }    ; otherwise      :: means we ignore one char frm one seqn , and find LCS in rem.

        for(int i = 1 ; i <= n ; i++)
        {
            for(int j = 1; j <= m ; j++)
            {
                if(x[i-1] == y[j-1])        // if Xi == Yj  : but tc in arrays we passed these indices are : i-1,j-1 respctlvly
                {
                    L[i][j] = 1 + L[i-1][j-1];
                }
                else
                {
                    L[i][j] = Math.max(L[i-1][j] , L[i][j-1]);
                }
            }
        }

        printTable(L,n,m);

       printLCS(L,x,y);

        return L[n][m];
    }

    // thr can be many LCS pssble : u cant print all of them efficiently if they happn to be
    // O(2^n) : so u print anyone and ignore others

    public static void printLCS(int[][] L , char[] x , char[] y)
    {
        int n = x.length;
        int m = y.length;

        StringBuilder sb = new StringBuilder();

        while(n >= 1 && m >=1)            // if u tk loop within loop here then code will fail : outof bounds : think why ? : bcz diagnl entries
        {
                if( L[n][m] == (1 + L[n-1][m-1]) )        // if it is 1 + diagonal entry
                {
                    sb.append(x[n-1]);
                    n--;
                    m--;
                }
                else if (L[n][m] == L[n - 1][m])       // if it is equal to above entry
                {
                    n--;
                }
                else // l(n,m) == l(n,m-1)            // if it is equal to left side entry
                {
                    m--;
                }

        }

        System.out.println(sb.reverse());
    }

    public static void printTable(int[][] table ,int low , int high)
    {

        // /* printing the table if u wnt to see the table entries

        for(int i=0; i <=low ; i++)
        {
            System.out.print(i + "  :");     // printing ith index

            for(int j=0; j <=high; j++)
            {
                System.out.print("  " + table[i][j] + "  ");       // entries in the table
            }
            System.out.println();
        }  // */

    }
}
