package com.tolani.LeetCode.Arrays.Easy;

import java.util.ArrayList;
import java.util.List;

public class p4_pascalTriangle {

    public static void main(String[] args)
    {
        makePascalTriangle(6);
    }

    public static List<List<Integer>> makePascalTriangle(int numOfRows)
    {
        List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();

        int size =1;

        List<Integer> l1 = new ArrayList<Integer>(size);
        l1.add(1);
        listOfLists.add(l1);    // 0th row

        for(int i=1 ; i < numOfRows ; i++)             // we ll running below code to generate each row frm d previous row
        {
            List<Integer> l = new ArrayList<Integer>(++size);    // creating the next row : which is a list with size + 1 than d previous list

            List<Integer> temp = listOfLists.get(i-1);    // getting the previous list frm the listOfLists

            l.add(temp.get(0));       // adding first element as it is

            // code for in bw first and last element
            for(int j=1 ; j < temp.size() ; j++)
            {
                l.add(temp.get(j-1) + temp.get(j));      // in bw element is additioin of two elements of previous list in order
            }

            l.add(temp.get(temp.size()-1));      // adding last element as it is

            listOfLists.add(l);       // now adding the new generated row or list in the listOfLists
        }

        System.out.println(listOfLists);

        return listOfLists;
    }
}
