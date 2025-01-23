# Sprint3: Inner, outer class. Enum
## Task 1 Implementing a PizzaBuilder

You need to implement a Pizza class with an inner class PizzaBuilder, which will be used to create an instance of Pizza. 
The implementation must adhere to the specified structure:

![image](https://user-images.githubusercontent.com/61456363/166102284-359b753d-ab87-463a-808e-fcb0643a0c41.png)

## Instructions
1. **Define a `Pizza` Class**:
   - The `Pizza` class should include the following private fields:
      - `String cheese`
      - `String meat`
      - `String seafood`
      - `String vegetable`
      - `String mushroom`
     - In the `Pizza` class:
     - Create a **private constructor** `Pizza()` to restrict direct instantiation.
     - Implement a static method `base()` that returns a new instance of the `PizzaBuilder` class.
     - Add methods `getCheese`, `getMeat`, `getSeafood`, `getVegetable`, `getMushroom` to access the fields.

2. **Define a `PizzaBuilder` Inner Class**:
   - Inside the `Pizza` class, define a `public static` inner class named `PizzaBuilder`.
   - Implement the following methods in `PizzaBuilder`:
      - `addCheese(String cheese): PizzaBuilder` — to set the cheese.
      - `addMeat(String meat): PizzaBuilder` — to set the meat.
      - `addSeafood(String seafood): PizzaBuilder` — to set the seafood.
      - `addVegetable(String vegetable): PizzaBuilder` — to set the vegetable.
      - `addMushroom(String mushroom): PizzaBuilder` — to set the mushroom.
   - Implement a `build()` method that creates and returns an instance of the `Pizza` class.
   - Ensure that the `PizzaBuilder` class adheres to the provided class diagram.
   
![image](https://user-images.githubusercontent.com/61456363/166102293-967e2fe9-acba-479f-8370-e0cc5304fb60.png)


2. **Implement the `cook` Method**:
    - The `cook` method can use the inner methods like `addCheese`, `addMeat`, etc., to create a `Pizza` instance with **at least three of your favorite ingredients**.
    - Use the `build()` method to return the completed `Pizza` object.

3. **Testing Guidelines**:
    - Do not use `System.out.print` or `System.out.println` methods in your code to ensure all tests pass correctly.

4. **Output Requirements**:
   - Make sure that the structure of the `PizzaBuilder` class matches the provided class diagram, including all methods and the constructor `PizzaBuilder()`.

