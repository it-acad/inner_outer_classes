# Sprint3: Inner, outer class. Enum
## Task 3 Using Anonymous Classes to Implement Strategies

Suppose we have an **execute** method with the following signature:

![image](https://user-images.githubusercontent.com/61456363/166102434-ec0b1a6f-8a38-4afe-aad7-4aa5781f1d77.png)

Where **Strategy** is the following interface:

![image](https://user-images.githubusercontent.com/61456363/166102443-dcae76ac-ec9f-4c0b-9b75-fb725b30419d.png)

Your task is to:

1. Implement the execute Method:

   - The **execute** method takes **two integers** a and b and a **Strategy object** as arguments.
   - It calls the **doOperation** method from the provided Strategy object, calculates the result, and returns it.
   
2. Override the doOperation **Method Using Anonymous Classes**:

   - Use anonymous classes to override the doOperation method for four different strategies:
      - Addition: Add a to b (a + b).
      - Subtraction: Subtract b from a (a - b).
      - Multiplication: Multiply a by b (a * b).
      - Division: Divide a by b (a / b).
3. Call the execute Method:
   - **Call the execute method** **four times**, each time _passing one of the above strategies as the third argument_.

> **Do not use System.out.print or System.out.println in your solution to ensure all tests pass.**

