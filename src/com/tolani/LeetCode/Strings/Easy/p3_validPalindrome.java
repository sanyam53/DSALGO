/* Problem description :
  Given a string, determine if it is a palindrome, considering only alphanumeric characters and    ignoring cases.

        For example,
        "A man, a plan, a canal: Panama" is a palindrome.
        "race a car" is not a palindrome.

        Note:
        Have you consider that the string might be empty? This is a good question to ask during an interview.

        For the purpose of this problem, we define empty string as valid palindrome.

    notes : take care in loop conditions in two pointers approch bcz smwhr u need to write
while( left <= s.length()  - 1   && right <=0 with other conditions )
while moving to avoid infinite loop

*/




        package com.tolani.LeetCode.Strings.Easy;

public class p3_validPalindrome {

    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() -1;        // if it is empty string then r = -1 whch is lesser thn left and true returned

        int a=-1,b=-1;

        while(left <= right)
        {
            a = getCharValue(s.charAt(left++));
            if(a == -1)
            {
                while(left <= s.length()-1 && a == -1){a = getCharValue(s.charAt(left++));}
            }


            b= getCharValue(s.charAt(right--));
            if(b == -1)
            {
                while(right >=0 && b == -1){b = getCharValue(s.charAt(right--));}
            }

            if(a != b){ return false;}
        }

        return true;
    }

// in below functin we ve used a method Character.getNumericValue(char c) : which is CASE INSENSETIVE means it returns
//int value 10 for 'a' and 'A' and 35 for 'z' and 'Z' , it ll return numeric value for digit 0 to 9 and return -1 otherwise

    public int getCharValue(char c)
    {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');

        int cval = Character.getNumericValue(c);

        if(cval >=a && cval<= z) return cval;      // tc : if u return here 'cval-a' then same values for 'a' and 0 digit
        else if(cval>=0 && cval<=9) return cval;
        else return -1;
    }

    public static void main(String[] args)
    {
        p3_validPalindrome obj =  new p3_validPalindrome();
        System.out.println(obj.isPalindrome("race,,,ecar"));
        //other interesting test case u shuld try
        obj.isPalindrome("");
        obj.isPalindrome("abcd0");
        obj.isPalindrome(",,,,,,,..///;;");
    }

}

