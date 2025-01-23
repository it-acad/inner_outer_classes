# Sprint3: Inner, outer class. Enum
## Task 5 Implementing a ClientType Enum with Discounts
Create an enum named ClientType to represent different client statuses (NEW, SILVER, GOLD, PLATINUM).

Each constant should have an associated private field **int months** — the number of months associated with the client status.

Set for each item of **enum** a value according to next table:  

| Client status | Count of months |
|---------------|-----------------|
| NEW           | 1               |
| SILVER        | 12              |
| GOLD          | 30              |
| PLATINUM      | 60              |

Add a public method named **discount()** in the ClientType enum.</br>
The method should:</br>
    - Return a discount coefficient (a value of type double between 1.0 and 0.0) based on the client’s status.</br>
    - **Default Value:** For **NEW**, the discount() method should return **1.0**.

For the other constants (SILVER, GOLD, PLATINUM), override the discount() method to calculate the discount using the following formulas:
 
| Client status | Formula                                  |
|---------------|------------------------------------------|
| NEW           | Default value: 1.0                       |
| SILVER        | (100 - `<count of months>` * 0.25) / 100 |
| GOLD          | (100 - `<count of months>` * 0.3) / 100  |
| PLATINUM      | (100 - `<count of months>` * 0.35) / 100 |

> Avoid using System.out.print or System.out.println in your code to ensure that all tests pass correctly.  

