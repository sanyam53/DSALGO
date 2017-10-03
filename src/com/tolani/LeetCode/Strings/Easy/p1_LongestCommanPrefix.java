package com.tolani.LeetCode.Strings.Easy;

public class p1_LongestCommanPrefix {

    public static void main(String[] args)
    {
        String s = longestCommonPrefixApp1(new String[]{"geeksforgeeks","geeks","geek","gee"});      // this is how we pass array as argmnt
        System.out.println(s);
    }

    // app 1 : comparing char by char tks O(m*n) time whr n is no of string and 'm' is len of cmn prefix

    public static String longestCommonPrefixApp1(String[] strs) {

        if(strs.length == 0) return "";

        int minLen = strs[0].length();

        for(int k =1 ; k < strs.length ; k++)
        {
            if(strs[k].length() < minLen) minLen = strs[k].length();
        }


        for(int i=0 ; i < minLen ; i++)          // if we dnt run it till minLen then it will be outof bounds fr smaller arrays
        {
            for(int j = 1 ; j < strs.length ; j++)      // #of strings we ve in an array of strings
            {
                if(strs[0].charAt(i) != strs[j].charAt(i))
                {
                    return strs[0].substring(0,i);
                }
            }
        }

        return strs[0].substring(0,minLen);        // if cms out means it is parsed till minLen
    }
}
