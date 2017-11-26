package com.tolani.DynamicProgramming;

import static com.tolani.DynamicProgramming.p4_LongestCommonSubseqn.printTable;

public class p5_LongestPalindromeSubseqn {

    public static void main(String[] args)
    {
        char[] x = "ABCABBA".toCharArray();

        bottomUpLPM(x);
    }

    // for below code follow the example in the notebook

    public static int bottomUpLPM(char[] x)
    {
        int n = x.length;    // n = 7;

        int[][] L = new int[n][n];  // 0 to 6 : here u need (0,n-1)th entry : so u dnt hv to tk n+1 here in table size

        for(int i=0; i < n ; i++)   //  i -> 0 to 6      :: L(i,i) : for one char it is the LLPSubseqn : so we pick it so len is 1
        {
            L[i][i]=1;
        }

        // for two chars we match them and if matches then len is 2 , otherwise we pick any 1 char in palindrome so len is 1

        for(int i=0 ; i < n-1 ;i++)      // i -> 0 to 5 menas till scnd last char
        {
            if(x[i] == x[i+1])            // otherwise arrindexbounds here
            {
                L[i][i + 1] = 2;
            }
            else L[i][i+1] = 1;
        }

        // now we will fill entries diagonal by diagonal : mark indices of i and j to write indices : bcz loop will be diff in this
        // 'i' is the inner loop as it incrmnts linearly but everytime one entry reduces in 'i' : so tc of tht by 'l'
        // 'j' is having sm diff 'l' frm 'i' : so j = i+l ; and then it incrmnts linearly

        // here thr is symmetry to LCS problem as we need only below diagonal entry . left entry and below entry to fill the currnt i,jth entry
        // if char matches then it is 1 + below diagnl entry , otherwise max of ( lhs , below ) entries
        // why it is so ? : see the insights frm the notebook

        int j=0;

        for(int l = 2; l < n ; l++)     // 'l' is the diff bw i and j which is at most (0,6) so ^
                                        // so 'l' is ( frm 2 to 6 )
        {
            for(int i=0 ; i < n-l ; i++)    // as u move diagonal by diagonal : entries reduces see : thts why i < n-1-l
            {                                    // i -> 0 to (7-2)=5 but bcz of < it will go till 4 : so i is frm 0 to 4 which is fine
                j=i+l;

                if(x[i] == x[j])
                {
                    L[i][j] = 2 + L[i+1][j-1];
                }
                else
                {
                    L[i][j]= Math.max(L[i+1][j],L[i][j-1]);
                }
            }

        }

        printTable(L,n-1,n-1);

        printCombination(L,x);

        return L[0][n-1];
    }

    public static void printCombination(int[][] L,char[] x)
    {
        int i=0;
        int j=x.length-1;

        StringBuilder sb = new StringBuilder();

        while(i != j)          // as u reach main diagonal entry u stp thr and u tk tht 1 length character frm thr at last
        {
            if(L[i][j] == (2 + L[i+1][j-1]))    // so u take both characters here
            {
                sb.append(x[i]);         // so we r picking one char now at begin , for end we will append it later
                i++;j--;
            }

            else if(L[i][j] == L[i][j-1])          // u ignore jth char here
            {
                j--;
            }
            else                     // u ignr ith char here
            {
                i++;
            }
        }

        sb.append(x[i]);        // we r taking tht one char as we reached digonal entry : >

        if(sb.length() % 2 == 0)          // if palindrome is even then just reverse it and append
        {
            sb.append(sb.reverse());
        }
        else                             // if it is odd then dnt double the middle char , just reverse remaining chars and append
        {
            int n = sb.length() - 2;
            for(int k=n ; k>=0 ; k--)
            {
                sb.append(sb.charAt(k));
            }
        }

        System.out.println(sb.reverse());
    }

}
