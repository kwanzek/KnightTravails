KnightTravails
==============

A solution for finding the shortest path between two chess squares for a knight piece

This is for Ruby Quiz 27 here

http://rubyquiz.com/quiz27.html

This solution uses a breadth first search to find the shortest path between two squares.
This is because there are no edge costs to take into account as each path has the same cost.

The user is expected to input a chess board location such as "c5" (lower case letter and single number)
for both the start node and goal state. Then they may enter any number of "forbidden squares" that
the algorithm will ignore in determining the shortest path. 

There is no sanitation of the input and if the input is not correct the program simply returns an empty list.

There is a test class for simple JUnit testing of the algorithm.
