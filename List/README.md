# 📃 Java List Implementation

This Java program implements a generic linked list (`List<E>`) and demonstrates its use through various operations. The List class provides fundamental operations such as adding elements, removing elements, and accessing the head and tail of the list. It supports generic elements, allowing it to handle various data types. The program also includes utility classes Person and Max to demonstrate the list uses.
Graded 100 :D

## Features
- Generic Implementation: The List<E> class is generic, suitable for various data types.
- Iterability: Implements the Iterable<E> interface, allowing for modern loop iteration and stream operations.
- Dynamic Size Adjustment: Automatically adjusts size as elements are added or removed.

## Components
### ListNode
Class representing a node in the linked list, holding the data and a reference to the next node.
### List
Class representing a generic linked list, using the list node of the ListNode class.
### Person
A simple class that models a person with attributes like name, birth year, and phone number.
### EmptyListException
A custom exception thrown when operations are attempted on an empty list.
### Max
Class that finds the max element in a generic list of comparable objects. It utilizes the Comparable interface, which allows the objects to be compared based on a predefined ordering.
### Main
The main class, Main, demonstrates:
-Creating and manipulating a list of strings input by the user.
-Reversing the list.
-Managing a list of Person objects, adding entries, and finding the oldest person using a utility class Max.

## Section Descriptions in Main
### Section C
Demonstrates how to populate the list with strings provided by the user, print the list, reverse it, and print the reversed list.

### Section E
Shows how to create a list of Person objects, handle potential exceptions, display the list, and determine the oldest person using a comparative utility.

## Requirements
- Java Development Kit (JDK) 8 or higher
