/*
*
*   Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
        ex : "(({}))"  "()[]{}"  : these are valid
*       invalid are : "))))"  "()))))"  "("
*
*   in approach 1 we r making 6 comparisions means O(6*n) comparisions made
*   we can optimize this ! think it out !
*
*   in optimized approach : we will push if open brackets arrives : thn we will push correspndng close brckt -> means now when close bracket comes means we r on tht char in the string and we will only pop
*   if it is equal to the current char : so we r drctly comparing it with current char in the string and not to the all closed brackets
*
*   so only 3 comparisns with open brackets and only one comparisns while closed brkct so it is : O(4*n)
*
* */


package com.tolani.LeetCode.Stacks.Easy;

import com.tolani.Stack_Hack.MyStack;

public class p1_validParenthesis {

    public static void main(String[] args)
    {
        isValidApproach1(" ");
    }



    public static boolean isValidApproach1(String s) {

        MyStack stck = new MyStack(1000000);

        for(int i=0 ; i < s.length() ; i++)
        {
            switch(s.charAt(i))
            {
                case '(' :
                    stck.push(1);
                    break;

                case '{':
                    stck.push(2);
                    break;

                case '[' :
                    stck.push(3);
                    break;

                case ')':
                    if(!stck.isStackEmpty() && stck.pick() == 1) {stck.pop();}
                    else return false;
                    break;

                case '}':
                    if(!stck.isStackEmpty() && stck.pick() == 2) {stck.pop();}
                    else return false;
                    break;

                case ']':
                    if(!stck.isStackEmpty() && stck.pick() == 3) {stck.pop();}
                    else { return false; }
                    break;
            }
        }

        if(stck.TOS == -1) return true;
        else return false;
    }
}