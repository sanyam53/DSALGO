/*
    Problem : find longest comman prefix in the given array of strings.

    see this frm here : http://www.geeksforgeeks.org/longest-common-prefix-set-1-word-by-word-matching/
                        http://www.geeksforgeeks.org/longest-common-prefix-set-2-character-by-character-matching/

                        https://leetcode.com/problems/longest-common-prefix/solution/

                        O(n*m) time by any algo.
 */

// we have learned sm methds here of String class in java

// 1. str.charAt(index) : returns a char at specified index
// 2. str.substring(int begin , int end) : returns substring of gvn indices


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
