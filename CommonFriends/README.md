Program: Common Friends

This program finds the common friends between 2 friends.

Input:
Person: List of Friends

A:B C D
B:A C D E
C:A B D E
D:A B C E
E:B C D

Output:
Person1,Person2 List of Common Friends

A,B  C D
A,C  B D
A,D  B C
B,C  A D E
B,D  A C E
B,E  C D
C,D  A B E
D,E  B C


Approach:
1. Mapper will create pair of friends(Person,his friend) as Key and Person's friends list as Value
2. This pair of friends from Mapper will be Sorted before sending to reducer
3. Reducer will get the same pair from 2 persons if they are friends and will intersect the friend list of both the persons to find the common friends.
   
   Example:
   Mapper receives
   Line 1  -> (Key, A:B C D)
   Line 2  -> (Key, B:A C D E)
   
   Mapper emits 
   For Line 1  -> Key: (A,B) Value: B C D
                  Key: (A,C) Value: B C D
                  Key: (A,D) Value: B C D
   For Line 2  -> Key: (A,B) Value: A C D E      //(B,A) is sorted to (A,B)
                  Key: (B,C) Value: A C D E
                  Key: (B,D) Value: A C D E
                  Key: (B,E) Value: A C D E
   
   Reducer Receives: 
                  Key: (A,B) Value: [B C D, A C D E]
                  
   Reducer emits:
                  Key: (A,B) Value: C D
   
   
   
