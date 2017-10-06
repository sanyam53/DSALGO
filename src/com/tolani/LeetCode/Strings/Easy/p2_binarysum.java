package com.tolani.LeetCode.Strings.Easy;

public class p2_binarysum {

        static int carry = 0;        // global variable carry.

        public String addBinary(String a, String b) {

            int s1 = a.length() - 1;
            int s2 = b.length() - 1;

            int sum =0,minLen =0;
            StringBuilder sb = new StringBuilder();
            boolean boo = true;        // if length of both is equal thn b is true else it is false

            if(s1 != s2)
            {
                minLen = Math.min(s1,s2) + 1;
                boo = false;
            }
            else minLen = s1 + 1;

            for(int i=0 ; i < minLen ; i++)     // addition is done frm backwards
            {
                sum = add(carry,Character.getNumericValue(a.charAt(s1--)),Character.getNumericValue(b.charAt(s2--)));

                sb.append(sum);
            }

            if(boo == true){ if(carry == 1){sb.append(1);} return sb.reverse().toString();}

            int diff = Math.abs(s1-s2);

            if(s1 > s2)
            {
                for(int j = 0 ; j < diff ; j++)
                {
                    sum = add(carry,Character.getNumericValue(a.charAt(s1--)),0);
                    sb.append(sum);
                }
            }
            else if (s1 < s2)
            {
                for(int j = 0 ; j < diff ; j++)
                {
                    sum = add(carry,Character.getNumericValue(b.charAt(s2--)),0);
                    sb.append(sum);
                }
            }

            if(carry == 1){sb.append(1);}
            return sb.reverse().toString();
        }

        public int add(int c,int a , int b)    // this function returns the sum of two binary digits and updates the global var carry
        {
            int sum =0;

            if(c == 1 && a == 1 && b == 1)
            {
                sum = 1;
                carry = 1;
            }

            else if ( c== 0 && a == 0 && b == 0)
            {
                sum =0 ; carry = 0;
            }
            else if ( c==0 && a == 1 && b ==1  || c==1 && a==0 && b==1 || c==1 && a==1 && b==0)
            {
                sum =0 ; carry = 1;
            }

            else{ sum=1; carry =0;}

            return sum;
        }


        public static void main(String[] args)
        {
            p2_binarysum x = new p2_binarysum();

            System.out.println(x.addBinary("11","1"));
        }

    }


