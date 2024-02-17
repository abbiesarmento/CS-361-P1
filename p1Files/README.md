# Project #1: (Deterministic Finite Automata)

* Author: Adam Adri, Abbie Sarmento 
* Class: CS361 Section #002
* Semester: Spring 2024

## Overview

In this project, we were to create a program that mimics an deterministic finite 
automata. This was done based off of given interfaces that mimic the deterministic
finite automata (dfa) itself and the states that go in the dfa. The dfa object
has a 5-tuple, meaning the alphabet, states, start state, final states, and the
transition table. Our state sets our in Linked Hash Sets and our transition 
table is designed using Hash Maps. We were also given a jUnit test suite but 
added more tests to it to ensure our code works as expected. 

## Reflection

This project proved more difficult than expected. We didn't expect to struggle with it 
as much as we did but I suppose that made it a great first project to break us in and
set expectations for the other projects in class. What worked well in this project
was the instructions and the starter code. We knew what methods needed to be added,
what they did, and what was expected of our code. This helped a lot in understanding 
where we're going wrong when it came to actually writing our code. Our struggle was
our unfamiliarity with using sets. We learned that you can't use .get() with sets and
that caused a lot of confusion in how to check the sets and how to make our transition
table. In addition, the set method .contains() wasn't working because our set was a set 
of objects and not something searchable such as strings. We ended up turning the sets
into arrays and iterating through them that way to check for existing states. If there
were very large sets of states, our code wouldn't be the most efficient but luckily we
worked with relatively small sets of states. 

I don't think there were any concepts that still weren't clear by the end of the project.
In order to debug and modify our methods, we printed our sets and our transition table to 
keep checking it against the jUnit tests and to make sure it looked and ran the way it 
should. I think if there's one thing we would change about our design process, it's to go 
through and write out our classes and methods earlier the time frame that we have to make 
our assignment. Starting earlier and getting the bones set up would have given us a better
expectation of the time frame needed to complete the project. That's also what we would 
go back in time to tell ourselves. Create the bones earlier and understand that we will be
using sets and to familiar ourselves with the different kinds of sets. 

## Compiling and Using

This program comes with a test suite to test the code. You can compile the code by going 
into the directory and typing: javac *.java . This will compile all the .java files in 
the directory. You can then find out test suite named DFATest.java and run the suite 
typing: ./DFATest.java . This will run the test suite and show the project works as 
intended. 

## Sources used

https://docs.oracle.com/javase/6/docs/api/java/util/LinkedHashSet.html
https://www.baeldung.com/java-map-duplicate-keys
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
https://www.geeksforgeeks.org/hashset-iterator-method-in-java/
https://stackoverflow.com/questions/175186/updating-an-object-within-a-set
https://www.w3schools.com/java/java_hashmap.asp

----------