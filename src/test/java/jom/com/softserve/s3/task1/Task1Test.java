package jom.com.softserve.s3.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    private static final String PIZZA = "jom.com.softserve.s3.task1.Pizza";
    private static final String PIZZA_BUILDER = "jom.com.softserve.s3.task1.Pizza$PizzaBuilder";

    @DisplayName("Verify that the Pizza class contains a public static nested class named PizzaBuilder")
    @Test
    void hasPizzaClassStaticNestedPizzaBuilderClassTest() {
        assertDoesNotThrow(() -> {
            Class<?> outerClazz = Class.forName(PIZZA);
            Class<?> innerClazz = Class.forName(PIZZA_BUILDER);

            assertNotNull(innerClazz.getEnclosingClass(), "PizzaBuilder should have an enclosing class.");
            assertEquals(outerClazz, innerClazz.getEnclosingClass(), "PizzaBuilder should be nested inside Pizza.");

            assertTrue(Modifier.isStatic(innerClazz.getModifiers()), "PizzaBuilder should be static.");
        });
    }

    @DisplayName("Verify that the PizzaBuilder class is declared as public")
    @Test
    void isPizzaBuilderPublicTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);
            assertTrue(Modifier.isPublic(clazz.getModifiers()), "PizzaBuilder class should be public.");
        }, "Class PizzaBuilder should exist.");
    }


    @DisplayName("Verify that the PizzaBuilder class declares at least one constructor")
    @Test
    void hasPizzaBuilderDeclaredConstructorTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            assertNotNull(declaredConstructors, "PizzaBuilder class should have at least one declared constructor.");
            assertTrue(declaredConstructors.length > 0, "PizzaBuilder class should have at least one constructor.");
        }, "Class PizzaBuilder should exist.");
    }


    @DisplayName("Verify that the constructor in the PizzaBuilder class is declared as private")
    @Test
    void isConstructorPrivateTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(declaredConstructor.getModifiers()),
                    "The constructor in PizzaBuilder class should be private.");
        }, "Class PizzaBuilder should exist and have a constructor.");
    }


    @DisplayName("Verify that the PizzaBuilder class declares a 'build' method with no parameters")
    @Test
    void hasPizzaBuilderClassMethodBuildTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);
            Method[] declaredMethods = clazz.getDeclaredMethods();

            // Check that 'build' method exists
            boolean isBuildMethodPresent = Arrays.stream(declaredMethods)
                    .anyMatch(method -> "build".equals(method.getName()));
            assertTrue(isBuildMethodPresent, "The PizzaBuilder class should contain a 'build' method.");

            // Check that 'build' method has no parameters
            Arrays.stream(declaredMethods)
                    .filter(method -> "build".equals(method.getName()))
                    .forEach(method -> assertEquals(0, method.getParameterCount(),
                            "The 'build' method should not take any parameters."));
        }, "Class PizzaBuilder should exist.");
    }


    @DisplayName("Verify that the 'build' method in PizzaBuilder returns an object of type Pizza")
    @Test
    void returnTypeClassMethodBuildTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);

            Method buildMethod = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> "build".equals(method.getName()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("The 'build' method was not found in PizzaBuilder class."));

            assertEquals("Pizza", buildMethod.getReturnType().getSimpleName(),
                    "The 'build' method should return an object of type Pizza.");
        }, "Class PizzaBuilder should exist.");
    }

    @DisplayName("Verify that the 'build' method in PizzaBuilder is declared as public")
    @Test
    void isMethodBuildPublicTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);

            Method buildMethod = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> "build".equals(method.getName()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("The 'build' method was not found in PizzaBuilder class."));

            assertTrue(Modifier.isPublic(buildMethod.getModifiers()),
                    "The 'build' method in PizzaBuilder should be declared as public.");
        }, "Class PizzaBuilder should exist.");
    }

    @DisplayName("Verify that the 'addSeafood' method in PizzaBuilder returns an object of type PizzaBuilder")
    @Test
    void returnTypeClassMethodAddSeafoodTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);

            Method addSeafoodMethod = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> "addSeafood".equals(method.getName()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("The 'addSeafood' method was not found in PizzaBuilder class."));

            assertEquals("PizzaBuilder", addSeafoodMethod.getReturnType().getSimpleName(),
                    "The 'addSeafood' method should return an object of type PizzaBuilder.");
        }, "Class PizzaBuilder should exist and contain the 'addSeafood' method.");
    }

    @DisplayName("Verify that the 'addSeafood' method in PizzaBuilder takes one parameter of type String")
    @Test
    void checkAddSeafoodParametersTest() {
        assertDoesNotThrow(() -> {
            Class<?> clazz = Class.forName(PIZZA_BUILDER);

            Method addSeafoodMethod = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> "addSeafood".equals(method.getName()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("The 'addSeafood' method was not found in PizzaBuilder class."));

            assertEquals(1, addSeafoodMethod.getParameterCount(),
                    "The 'addSeafood' method should take exactly one parameter.");

            Class<?>[] parameters = addSeafoodMethod.getParameterTypes();
            assertEquals("String", parameters[0].getSimpleName(),
                    "The parameter of 'addSeafood' method should be of type String.");
        }, "Class PizzaBuilder should exist and contain the 'addSeafood' method.");
    }


    @DisplayName("Check that 'cook' methods does not return null")
    @Test
    void isCookMethodReturnsNotNullTest() {
        assertNotNull(Oven.cook());

    }

    @DisplayName("Check that 'cook' method returns object of Pizza type")
    @Test
    void isCookMethodReturnsPizzaTest() {
        assertNotNull(Oven.cook());

    }

    @DisplayName("Verify that the created pizza contains at least three valid ingredients")
    @Test
    void hasPizzaAtLeastThreeIngredientsTest() {
        assertTrue(hasPizzaAtLeastThreeIngredients());
    }

    private static boolean hasPizzaAtLeastThreeIngredients() {
        Pizza pizza = Oven.cook();
        return Arrays
                .stream(new String[]{Oven.cook().getCheese(), pizza.getMeat(), pizza.getSeafood(),
                        pizza.getVegetable(), pizza.getMushroom()})
                .filter(ingredient -> ingredient != null && !ingredient.isEmpty()).count() >= 3;
    }

    @Test
    @DisplayName("Verify that the cook() method does not return null")
    void testCookMethodDoesNotReturnNull() {
        Pizza pizza = Oven.cook();
        assertNotNull(pizza, "The cook() method should not return null.");
    }

    @Test
    @DisplayName("Verify that the cook() method returns an instance of the Pizza class")
    void testCookMethodReturnsPizzaInstance() {
        Pizza pizza = Oven.cook();
        assertNotNull(pizza, "The cook() method should not return null.");
        assertTrue(pizza instanceof Pizza, "The cook() method should return an instance of the Pizza class.");
    }

    @Test
    @DisplayName("Verify that the created pizza has at least three valid ingredients")
    void testPizzaHasAtLeastThreeIngredients() {
        Pizza pizza = Oven.cook();

        long ingredientCount = Arrays.stream(new String[]{
                pizza.getCheese(), pizza.getMeat(), pizza.getSeafood(), pizza.getVegetable(), pizza.getMushroom()
        }).filter(ingredient -> ingredient != null && !ingredient.isEmpty()).count();

        assertTrue(ingredientCount >= 3, "The pizza should have at least 3 valid ingredients.");
    }

}
