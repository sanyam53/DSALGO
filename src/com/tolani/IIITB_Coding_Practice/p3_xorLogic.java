
/*
    Given a set, find XOR of the XOR’s of all subsets.

    The question is to find XOR of the XOR’s of all subsets. i.e if the set is {1,2,3}. All
    subsets are : [{1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}]. Find the XOR of each of the
    subset and then find the XOR of every subset result.
*
*   Approach 1 : find all the subsets and do the xor of all the elements in it and do the xor of
*               all the results : finding subsets using bit manipulation tks O(n*2^n)
*                                 and doing xor does some more work which is very unefficient.
*
*    Approach 2 : simple logic of xor is tht : xor of same elements give result 0
*                 now in the powerset u ll get cancelled all the elements and gets result 0 always
*                 except one case whr u have only one element in set S , so power set will hv only
*                 tht element
*
*                 S = { 1,2,3,... any no of elements }
*                 xor of all the subsets , and xor of tht gvs result 0
*
*                 but if S = { 1 }  : if S contains only one element then result is itself
*
* */

/*
*   INPUT : first line contains num of test cases
*               next line contains num of elements in a set S
*               next line is space seperated integers which is a set given (S)
*
*               repeat if T > 1
*
*   OUTPUT : XOR OF XOR OF SUBSETS
* */

package com.tolani.IIITB_Coding_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3_xorLogic {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i=0 ; i < T ; i++) {
            int N = Integer.parseInt(br.readLine());

            int arr[] = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int ans = calXOR(arr);

            System.out.println(ans);
        }
    }

    public static int calXOR(int[] arr)
    {
        if(arr.length > 1) return 0;
        else return arr[0];
    }

}
