# Sprint3: Inner, outer class. Enum
## Task 2
Suppose we have the next class:

![image](https://user-images.githubusercontent.com/61456363/166102368-b4a96791-7e94-41cd-9377-e347d8fce42a.png)

Create a **public inner class** named `Iterator` inside the `NameList` class. 
This class should follow the design specified in the diagram below:

![image](https://user-images.githubusercontent.com/61456363/166102387-e8a1b469-fc56-4e9e-9670-82ff1141aa1b.png)

## **Instructions**

1. Implement the `Iterator` class inside the `NameList` class.
2. Ensure the `Iterator` class can traverse the `names` array correctly using the methods `hasNext()` and `next()`.
3. Follow the method definitions and behavior as outlined in the diagram:
    - `hasNext()` should check whether there are more elements to iterate over.
    - `next()` should return the current element and advance the counter.
---
## **Important Note**

> Do not use `System.out.print` or `System.out.println` methods in your implementation, as they might cause test failures.

