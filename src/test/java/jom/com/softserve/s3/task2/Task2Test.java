package jom.com.softserve.s3.task2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    private static final String NAMED_LIST = "jom.com.softserve.s3.task2.NameList";
    private static final String ITERATOR = "jom.com.softserve.s3.task2.NameList$Iterator";

    @DisplayName("Verify that NameList contains a non-static nested Iterator class")
    @Test
    void testNameListContainsNonStaticNestedIterator() {

        Class<?> outerClazz = assertDoesNotThrow(() -> Class.forName(NAMED_LIST),
                "NameList class not found.");
        Class<?> innerClazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found.");
        assertAll("Iterator class validation",
                () -> assertNotNull(innerClazz.getEnclosingClass(),
                        "Iterator should be a nested class, but it has no enclosing class."),
                () -> assertEquals(outerClazz, innerClazz.getEnclosingClass(),
                        "Iterator is not declared inside NameList."),
                () -> assertFalse(Modifier.isStatic(innerClazz.getModifiers()),
                        "Iterator should not be a static class.")
        );
    }

    @DisplayName("Check that Iterator class has a 'counter' field")
    @Test
    void testIteratorClassHasCounterField() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");

        Field[] declaredFields = clazz.getDeclaredFields();
        boolean hasCounterField = Arrays.stream(declaredFields)
                .anyMatch(field -> "counter".equals(field.getName()));
        assertTrue(hasCounterField, "Iterator class should have a 'counter' field");
    }


    @DisplayName("Check that 'counter' field is private")
    @Test
    void testCounterFieldIsPrivate() {

        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");

        Field[] declaredFields = clazz.getDeclaredFields();
        Field counterField = Arrays.stream(declaredFields)
                .filter(field -> "counter".equals(field.getName()))
                .findFirst()
                .orElse(null);
        assertNotNull(counterField, "Iterator class should have a 'counter' field");
        assertTrue(Modifier.isPrivate(counterField.getModifiers()),
                "'counter' field should be private");
    }


    @DisplayName("Check that type of 'counter' field is int")
    @Test
    void testCounterFieldIsInt() {

        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");
        Field[] declaredFields = clazz.getDeclaredFields();
        Field counterField = Arrays.stream(declaredFields)
                .filter(field -> "counter".equals(field.getName()))
                .findFirst()
                .orElse(null);
        assertNotNull(counterField, "Iterator class should have a 'counter' field");
        assertEquals(int.class, counterField.getType(),
                "'counter' field should be of type int");
    }

//    @DisplayName("Check that 'hasNext' method returns a boolean value")
//    @Test
//    void testHasNextMethodReturnsBoolean() {
//
//        Class<?> clazz = NameList.Iterator.class;
//
//        Method hasNextMethod = Arrays.stream(clazz.getDeclaredMethods())
//                .filter(method -> "hasNext".equals(method.getName()))
//                .findFirst()
//                .orElse(null);
//
//        assertNotNull(hasNextMethod, "'hasNext' method should exist in the Iterator class");
//
//        assertEquals(boolean.class, hasNextMethod.getReturnType(),
//                "'hasNext' method should return a boolean value");
//    }

    @DisplayName("Check that Iterator class has 'hasNext' method")
    @Test
    void testIteratorHasHasNextMethod() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");
        boolean hasHasNextMethod = Arrays.stream(clazz.getDeclaredMethods())
                .anyMatch(method -> "hasNext".equals(method.getName()));
        assertTrue(hasHasNextMethod, "Iterator class should have a 'hasNext' method");
    }


    @DisplayName("Check that 'hasNext' method is public")
    @Test
    void testHasNextMethodIsPublic() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");
        Method hasNextMethod = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> "hasNext".equals(method.getName()))
                .findFirst()
                .orElse(null);
        assertNotNull(hasNextMethod, "Iterator class should have a 'hasNext' method");
        assertTrue(Modifier.isPublic(hasNextMethod.getModifiers()),
                "'hasNext' method should be public");
    }

//    @DisplayName("Check that 'next' method returns an object of String type")
//    @Test
//    void testNextMethodReturnsString() {
//        Class<?> clazz = NameList.Iterator.class;
//        Method nextMethod = Arrays.stream(clazz.getDeclaredMethods())
//                .filter(method -> "next".equals(method.getName()))
//                .findFirst()
//                .orElse(null);
//        assertNotNull(nextMethod, "'next' method should exist in the Iterator class");
//        assertEquals(String.class, nextMethod.getReturnType(),
//                "'next' method should return an object of String type");
//    }


    @DisplayName("Check that Iterator class contains a constructor")
    @Test
    void testIteratorClassHasConstructor() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertNotNull(constructors, "Iterator class should have at least one constructor");
        assertTrue(constructors.length > 0, "Iterator class should have at least one constructor");
    }


    @DisplayName("Check that Iterator class has a private constructor")
    @Test
    void testIteratorClassHasPrivateConstructor() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(ITERATOR),
                "Iterator class not found");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        boolean hasPrivateConstructor = Arrays.stream(constructors)
                .anyMatch(constructor -> Modifier.isPrivate(constructor.getModifiers()));
        assertTrue(hasPrivateConstructor, "Iterator class should have a private constructor");
    }


//    @DisplayName("Check that Iterator works properly")
//    @Test
//    void testIteratorFunctionality() {
//        String[] expected = {"Mike", "Emily", "Nick", "Patric", "Sara"};
//        String[] actual = new String[expected.length];
//        int counter = 0;
//        for (NameList.Iterator iterator = new NameList().getIterator(); iterator.hasNext(); ) {
//            actual[counter++] = iterator.next();
//        }
//        int finalCounter = counter;
//        assertAll("Iterator functionality",
//                () -> assertArrayEquals(expected, actual, "Iterator should correctly traverse the names array"),
//                () -> assertEquals(expected.length, finalCounter,
//                        "Iterator should traverse the entire array")
//        );
//    }
}
