// ref. : http://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/

// thr r many things u ve to consider here : wt shuld be the intial size of the hash table ? load factor concept etc.
// what if key field is some object : then u ve to use hashcode function to get a unique id and then do modulo

package com.tolani.HashTable;

public class MyHashTable {           // lets say this is the whole hash table structure

    HashNode[] bucketArray;    // this basically stores the head of the linked list connected to each bucket

    int numOfBuckets;   // capacity of the bucketArray
    int currentSize;      // current size of the bucket : use for calculation the load factor


    static class HashNode
    {
        int key;
        int value;
        HashNode next;     // pointer to the next node

        HashNode(int key,int value)
        {
            this.key = key; this.value = value; this.next = null;
        }

    }


    public MyHashTable()         // hash table constructor
    {
        numOfBuckets = 10;       // intially if size is not given
        bucketArray = new HashNode[numOfBuckets];    // so array is allctd the space based on the size

        for(int i=0 ; i < numOfBuckets ; i++)
        {
            bucketArray[i] = null;
        }
    }

    public MyHashTable(int size)     // const.2 :  creating hash table with the given size
    {
        numOfBuckets = size;

        bucketArray = new HashNode[numOfBuckets];

        for(int i=0; i <numOfBuckets ; i++)
        {
            bucketArray[i] = null;
        }
    }

/*
    add function : which adds the value to the hash table : it takes O(1) time : means constant time

    when u pass key & value to the add function , first of all it will generate a hashcode value
    by using a key and % it by total capacity of the hashtable , which will return an index of array
    tht in wht bucket tht key value pair shuld go

    now : after reaching the bucket , if bucket is already empty then we will add tht pair and mk it head
    else we will check if duplicate key exist in the bucket's linked list : if no then add it at head
    in constant time and if yes then give error saying duplicate key

*/

    public void add(int key,int value)
    {
        int bucketIndex = key % numOfBuckets ;

        HashNode hn = new HashNode(key,value);

        if(bucketArray[bucketIndex] == null)
        {
            bucketArray[bucketIndex] = hn;
        }

        else
            {
                         // check here if tht key already exist in the list then give error bcz key cnt be duplicate in a hash table
                if (!(checkForDuplicateKey(key, bucketArray[bucketIndex])))  // we r passing key and head of the list
                {
                    hn.next = bucketArray[bucketIndex];       // u joined the linked list as new node as head
                    bucketArray[bucketIndex] = hn;         // changed the head of the linked list : thts it
                }
                else {
                 System.out.println('\n' + "duplicate key cant exist please enter unique key");
                }
        }
    }

  /*checkForDuplicateKey functin will check tht duplicate key shuld not exist in the hash table ok
    now when we generate a hash code for the given autoboxed Integer object for a key then it will generate similar
*/

    public boolean checkForDuplicateKey(int key,HashNode head)
    {

        while(head != null)
        {
            if(head.key == key)
            {
                return true;
            }

            head = head.next;

        }

        return false;
    }

    public void displayHashTable()
    {
        for(int i=0 ; i < numOfBuckets ; i++)
        {
            System.out.print("bucket : " + i + " : ");

            HashNode hn = bucketArray[i];

            StringBuilder sb = new StringBuilder("");

            while(hn != null)
            {
                sb.append(hn.key + ":" + hn.value + " -> ");
                hn = hn.next;
            }

            System.out.print(sb);
            System.out.println();
        }
    }

    public void remove(int key)
    {
        int bucketIndex = key % numOfBuckets;          // u r tkng out an index of bucket in which tht key val pair can be (if present)
        HashNode head =bucketArray[bucketIndex];

        if(head== null)
        {
            System.out.println("linked list is empty : cant remove a key");
        }

        else if(head.key == key )
        {
            bucketArray[bucketIndex] = null;
        }
        else
        {
            boolean b = false;
            while( head.next != null )
            {
                if(head.next.key == key){
                    head.next = head.next.next;    // thts it u just remove tht node frm the link
                    b = true;
                    break;
                }
                head = head.next;
            }

            if(!b){System.out.println("there is no such key in the list");}

        }

    }

    // search function : it returns -1 , if list is null OR key doesnt exist
    // and returns value if key exists in the linked list

    public int search(int key)
    {
        int bucketindex = key % numOfBuckets;

        HashNode head = bucketArray[bucketindex];

        if(head == null) {System.out.println("list is empty man ! no key !"); return -1;}

        else
        {
            while(head != null)
            {
                if(head.key == key)
                    return head.value;

                head = head.next;
            }
        }

        System.out.println("key doesn't exist in the hash table");
        return -1;
    }

}
