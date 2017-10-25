package com.tolani.DynamicProgramming;

public class p3_subsetSumProblem {

    public static void main(String[] args)
    {

        int[] values = {1,2,5,9,20};

        int targetSum = 7;

        System.out.println(bottomUp(values,targetSum));
    }

    // we will make a table x(i,j) : our destn is : x(n,sum) : using first 'n' nums , is it pssble
    // to add upto == sum : no lesser or greater : it is strictly equal to , so we store ans in
    // yes /no means true/false

    // intialization would be : x(0,j) = false , x(n,0) = false
    // x(1,j) : using first item if we can make sum == j : two optins : if j == val(i) thn TRUE
                                                                // Else if j != val(i) thn FALSE
    // x(i,j) : using first 'i' items cn we mk sum == j
    // two optins here : either we pick ith item or we dnt pikc
    // 1st : if we pick ith item : then x(i,j) = x( i-1,j-val(i) )  ; this is only if j>= val(ith)
    // 2nd : if we dnt pick ith item : x(i,j) = x(i-1,j)

    // so eqn wuld be : x(i,j) = x(i-1,j) || x(i-1,j-val(i)) ; so we r doing OR operation here bcz
    // we wnt to see if it is pssble to mk sum 'j' with either optin using first 'i' items


    public static boolean bottomUp(int[] val,int sum)
    {
        int n = val.length;    // no of nums we have

        boolean[][] x;      // we will store boolean value T/F in a table to see it is pssble or nt ?

        x = new boolean[n+1][sum+1];     // size of table is O(#of items * target sum)

        for(int i=0 ; i <=n ; i++)      // tc here bro !
        {
            x[i][0]= true;   // u wnt to make sum 0 and u ve any no of itmes : 0 ,1,2,... : so simply u dnt pick tht and mk sum 0 : so true
        }

        for(int j=1; j <=sum ; j++)
        {
            x[0][j] = false;       // if u have 0 items and u ve to make sum frm 1 to targetsum then u cnt so it is false
        }

        // below for loop is for understanding only : u cn keep this and update the indices in the below most for loop to be i = 2 , j=1
       /* for(int j=1 ; j <= sum ; j++)    // 1st row  whn u ve first item only : u fill it true if tht item val == sum u wnt else u fill it false
        {
            if(j == val[0])
            {
                x[1][j] = true;
            }
            else x[1][j]= false;
        }*/

        for(int j=1 ; j <= sum ;j++)
        {
            for (int i = 1; i <= n; i++)       // starting frm the 2nd row
            {

                x[i][j] = x[i-1][j];       // if we dnt pick ith item

                if(j >= val[i-1])
                {
                    x[i][j] = x[i-1][j-val[i-1]] || x[i][j];      // x(i,j) = x(i-1,j-Vi) || x(i-1,j) which is already thr so we took x(i,j)
                }
            }
        }

           // /* printing the table if u wnt to see the table entries

        for(int i=0; i <=n ; i++)
        {
            System.out.print(i + "  :");     // printing ith index

            for(int j=0; j <= sum; j++)
            {
                System.out.print("  " + x[i][j] + "  ");       // entries in the table
            }
            System.out.println();
        }  // */


        return x[n][sum];
    }
}
