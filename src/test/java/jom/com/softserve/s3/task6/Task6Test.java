package jom.com.softserve.s3.task6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    private static final String ADDRESS_BOOK = "jom.com.softserve.s3.task6.AddressBook";
    private static final String NAME_ADDRESS_PAIR = "jom.com.softserve.s3.task6.AddressBook$NameAddressPair";
    private static final String PERSON = "jom.com.softserve.s3.task6.AddressBook$NameAddressPair$Person";
    private static final String ADDRESS_BOOK_ITERATOR = "jom.com.softserve.s3.task6.AddressBook$AddressBookIterator";
    private static final String SORT_ORDER = "jom.com.softserve.s3.task6.SortOrder";

    @DisplayName("Check that a class is present")
    @ParameterizedTest
    @MethodSource("provideClass")
    void testClassIsPresent(String type, String className) {

        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(type),
                "The " + className + " class could not be found");
        assertEquals(className, clazz.getSimpleName(),
                "The class name does not match the expected name");
    }

    @DisplayName("Check that a class has a specific field")
    @ParameterizedTest
    @MethodSource("provideFieldType")
    void testClassHasDeclaredField(String type, String fieldName) {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(type),
                "The class " + type + " could not be found");
        boolean fieldExists = Arrays.stream(clazz.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(fieldName));
        assertTrue(fieldExists, "The class " + type + " should have a field named: " + fieldName);
    }

    @DisplayName("Check that a class has fields with the proper type")
    @ParameterizedTest
    @MethodSource("provideFieldType")
    void checkFieldType(String classType, String fieldName, Class<?> expectedFieldType) {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(classType),
                "The class " + classType + " could not be found");
        Field field = assertDoesNotThrow(() -> clazz.getDeclaredField(fieldName),
                "The field " + fieldName + " does not exist in class " + classType);
        assertEquals(expectedFieldType, field.getType(),
                "The field " + fieldName + " in class " + classType + " should be of type " + expectedFieldType.getName());
    }

    @DisplayName("Check that NameAddressPair class has 'person' field of Person type")
    @Test
    void checkFieldType() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(NAME_ADDRESS_PAIR),
                "The class NameAddressPair could not be found");
        Class<?> personClass = assertDoesNotThrow(() -> Class.forName(PERSON),
                "The class Person could not be found");
        Field field = assertDoesNotThrow(() -> clazz.getDeclaredField("person"),
                "The field 'person' does not exist in class NameAddressPair");
        assertEquals(personClass, field.getType(),
                "The field 'person' in class NameAddressPair should be of type Person");
    }

    @DisplayName("Check that a class has properly defined methods")
    @ParameterizedTest
    @MethodSource("provideTypeDeclaredMethod")
    void hasTypeDeclaredMethod(String classType, String methodName, Class<?>[] paramTypes) {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(classType),
                "The class " + classType + " could not be found");
        Method method = assertDoesNotThrow(() -> clazz.getDeclaredMethod(methodName, paramTypes),
                "The method " + methodName + " with specified parameter types does not exist in class " + classType);
        assertNotNull(method, "The method " + methodName + " should be defined in class " + classType);
    }

    @DisplayName("Check that a method returns the appropriate type")
    @ParameterizedTest
    @MethodSource("provideMethodReturnType")
    void hasMethodReturnType(String classType, String methodName, Class returnType) {
        try {
            Class<?> clazz = Class.forName(classType);
            Method[] methods = clazz.getDeclaredMethods();
            boolean isMethod = false;
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    assertEquals(method.getReturnType(), returnType);
                    isMethod = true;
                }
            }
            assertTrue(isMethod);
        } catch (ClassNotFoundException e) {
            fail("There is no such class");
        }
    }

    @DisplayName("Check that class contains overridden methods")
    @ParameterizedTest
    @MethodSource("provideOverrideTypeMethod")
    void overridesTypeMethod(String parent, String child, String methodName) {
        try {
            Class<?> parentClass = Class.forName(parent);
            Class<?> childClass = Class.forName(child);
            Method[] childMethods = childClass.getDeclaredMethods();
            Method[] parentMethods = parentClass.getDeclaredMethods();
            boolean isMethod = false;
            for (final Method parentMethod : parentMethods) {
                for (final Method childMethod : childMethods) {
                    if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
                            && !Modifier.isPrivate(parentMethod.getModifiers())) {
                        isMethod = true;
                    }
                }
            }
            assertTrue(isMethod);
        } catch (ClassNotFoundException e) {
            fail("There is no such class");
        }
    }


    @DisplayName("Check that a class implements an interface")
    @ParameterizedTest
    @MethodSource("provideImplementTypeInterface")
    void implementsTypeInterface(final String parentName, final String childName) {
        try {
            final Class<?> parentClazz = Class.forName(parentName);
            final Class<?> childClazz = Class.forName(childName);
            assertTrue(parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface());
        } catch (ClassNotFoundException e) {
            fail("There is no such class");
        }
    }

    private static Stream<Arguments> provideImplementTypeInterface() {
        return Stream.of(Arguments.of("java.lang.Iterable", ADDRESS_BOOK),
                Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR));
    }

    @DisplayName("Check that NameAddressPair class is private")
    @Test
    void isNameAddressPairClassPrivate() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(NAME_ADDRESS_PAIR),
                "The class " + NAME_ADDRESS_PAIR + " could not be found");
        assertTrue(Modifier.isPrivate(clazz.getModifiers()),
                "The class " + NAME_ADDRESS_PAIR + " should be private");
    }

    @DisplayName("Check that 'person' field is final")
    @Test
    void isPersonFieldFinal() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(NAME_ADDRESS_PAIR),
                "The class " + NAME_ADDRESS_PAIR + " could not be found");
        Field field = assertDoesNotThrow(() -> clazz.getDeclaredField("person"),
                "The field 'person' does not exist in class " + NAME_ADDRESS_PAIR);
        assertTrue(Modifier.isFinal(field.getModifiers()),
                "The field 'person' in class " + NAME_ADDRESS_PAIR + " should be final");
    }

    @DisplayName("Check that NameAddressPair class has a private constructor")
    @Test
    void isConstructorPrivate() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(NAME_ADDRESS_PAIR),
                "The class " + NAME_ADDRESS_PAIR + " could not be found");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertTrue(constructors.length > 0,
                "The class " + NAME_ADDRESS_PAIR + " should have at least one constructor");
        for (Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "The constructor " + constructor + " in class " + NAME_ADDRESS_PAIR + " should be private");
        }
    }

    @DisplayName("Check that SortOrder is an enum")
    @Test
    void isSortOrderEnum() {
        Class<?> clazz = assertDoesNotThrow(() -> Class.forName(SORT_ORDER),
                "The class " + SORT_ORDER + " could not be found");
        assertTrue(clazz.isEnum(), "The class " + SORT_ORDER + " should be an enum");
    }

    @DisplayName("Check that AddressBookIterator class is a non-static nested class")
    @Test
    void isAddressBookIteratorNonStaticNestedType() {
        Class<?> parentClass = assertDoesNotThrow(() -> Class.forName(ADDRESS_BOOK),
                "The class " + ADDRESS_BOOK + " could not be found");
        Class<?> childClass = assertDoesNotThrow(() -> Class.forName(ADDRESS_BOOK_ITERATOR),
                "The class " + ADDRESS_BOOK_ITERATOR + " could not be found");
        assertEquals(parentClass, childClass.getEnclosingClass(),
                "The class " + ADDRESS_BOOK_ITERATOR + " should be a nested class of " + ADDRESS_BOOK);
        assertFalse(Modifier.isStatic(childClass.getModifiers()),
                "The class " + ADDRESS_BOOK_ITERATOR + " should be non-static");
    }

//    @DisplayName("Check that an AddressBook object is not null")
//    @Test
//    void checkBookCreationTest() {
//        AddressBook addressBook = new AddressBook(150);
//        assertNotNull(addressBook, "The AddressBook object should not be null after creation");
//    }
//
//    @DisplayName("Check the number of added records to AddressBook")
//    @Test
//    void createFewBooksTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        boolean firstAdded = addressBook.create("John", "Brown", "Address #1");
//        boolean secondAdded = addressBook.create("Karen", "Davis", "Address #2");
//        boolean thirdAdded = addressBook.create("Steven", "Taylor", "Address #1");
//
//        assertTrue(firstAdded, "Failed to add the first record");
//        assertTrue(secondAdded, "Failed to add the second record");
//        assertTrue(thirdAdded, "Failed to add the third record");
//
//        int totalAdded = (firstAdded ? 1 : 0) + (secondAdded ? 1 : 0) + (thirdAdded ? 1 : 0);
//        assertEquals(3, totalAdded, "The total number of successfully added records should be 3");
//    }
//
//    @DisplayName("Ensure only one record is added when identical contacts are added to AddressBook")
//    @Test
//    void createFewTheSameBooksTest() {
//        AddressBook addressBook = new AddressBook(4);
//        boolean firstAdded = addressBook.create("John", "Brown", "Address #1");
//        boolean secondAdded = addressBook.create("John", "Brown", "Address #1");
//        assertTrue(firstAdded, "The first contact should be added successfully");
//        assertFalse(secondAdded, "The second identical contact should not be added");
//        int totalAdded = (firstAdded ? 1 : 0) + (secondAdded ? 1 : 0);
//        assertEquals(1, totalAdded, "The total number of successfully added contacts should be 1");
//    }
//
//    @DisplayName("Ensure AddressBook allows adding multiple contacts with identical addresses")
//    @Test
//    void createThreeBooksWithTwoAddressesTest() {
//        AddressBook addressBook = new AddressBook(2);
//        boolean firstAdded = addressBook.create("John", "Brown", "Address #1");
//        boolean secondAdded = addressBook.create("Karen", "Davis", "Address #2");
//        boolean thirdAdded = addressBook.create("Steven", "Taylor", "Address #1");
//
//        assertTrue(firstAdded, "The first contact should be added successfully");
//        assertTrue(secondAdded, "The second contact should be added successfully");
//        assertTrue(thirdAdded, "The third contact with a shared address should be added successfully");
//
//        int totalAdded = (firstAdded ? 1 : 0) + (secondAdded ? 1 : 0) + (thirdAdded ? 1 : 0);
//        assertEquals(3, totalAdded, "The total number of successfully added contacts should be 3");
//    }
//
//    @DisplayName("Ensure 'read' method returns null when trying to read a non-existing record")
//    @Test
//    void readNonExistingBookTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        String result = addressBook.read("Susan", "Brown");
//        assertNull(result, "The 'read' method should return null for a non-existing record");
//    }
//
//    @DisplayName("Ensure updating an existing record works correctly")
//    @Test
//    void updateAddressByExistingKeyTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        boolean updateStatus = addressBook.update("Steven", "Taylor", "Address #3");
//
//        assertTrue(updateStatus, "The update should return true for an existing record");
//        assertEquals("Address #3", addressBook.read("Steven", "Taylor"),
//                "The 'read' method should return the updated address for the record");
//    }
//
//    @DisplayName("Ensure that updating a non-existing record returns false")
//    @Test
//    void updateAddressByNotExistingKeyTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        boolean updateStatus = addressBook.update("Susan", "Brown", "Address #3");
//        assertFalse(updateStatus, "Updating a non-existing record should return false");
//    }
//
//    @DisplayName("Ensure deleting an existing record works correctly")
//    @Test
//    void deleteRecordByExistingKeyTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        boolean deleteStatus = addressBook.delete("Steven", "Taylor");
//        assertTrue(deleteStatus, "Deleting an existing record should return true");
//
//        String readResult = addressBook.read("Steven", "Taylor");
//        assertNull(readResult, "The deleted record should no longer be readable");
//    }
//
//
//    @DisplayName("Ensure that deleting a non-existing record returns false")
//    @Test
//    void deleteRecordByNotExistingKeyTest() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        boolean deleteStatus = addressBook.delete("Susan", "Brown");
//        assertFalse(deleteStatus, "Deleting a non-existing record should return false");
//    }
//
//    @DisplayName("Ensure that the size of an empty AddressBook is 0")
//    @Test
//    void checkSizeForEmptyAddressBook() {
//        AddressBook addressBook = new AddressBook(4);
//
//        int size = addressBook.size();
//        assertEquals(0, size, "The size of an empty AddressBook should be 0");
//    }
//
//
//    @DisplayName("Ensure that the size of AddressBook increases after adding records")
//    @Test
//    void checkChangingAddressBookSize() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        int size = addressBook.size();
//        assertEquals(3, size, "The size of the AddressBook should be 3 after adding 3 records");
//    }
//
//    @DisplayName("Ensure AddressBook size decreases correctly after removing records")
//    @Test
//    void checkChangingAddressBookSizeWhenRemovingRecord() {
//        AddressBook addressBook = new AddressBook(4);
//
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//
//        addressBook.delete("John", "Brown");
//        addressBook.delete("Karen", "Davis");
//
//        int size = addressBook.size();
//        assertEquals(1, size, "The size of AddressBook should be 1 after removing two records");
//    }
//
//
//    @DisplayName("Ensure iteration across records works correctly")
//    @Test
//    void checkIterationAcrossRecords() {
//        String[] expected = {
//                "First name: Karen, Last name: Davis, Address: Address #2",
//                "First name: Steven, Last name: Taylor, Address: Address #3",
//                "First name: Susan, Last name: Brown, Address: Address #4"
//        };
//
//        AddressBook addressBook = new AddressBook(4);
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("Steven", "Taylor", "Address #1");
//        addressBook.create("Susan", "Brown", "Address #4");
//        addressBook.update("Steven", "Taylor", "Address #3");
//        addressBook.delete("John", "Brown");
//
//        String[] actual = new String[3];
//        int counter = 0;
//        for (Object record : addressBook) {
//            actual[counter++] = record.toString();
//        }
//
//        assertArrayEquals(expected, actual, "The records obtained during iteration should match the expected records");
//    }
//
//
//    @DisplayName("Ensure sorting of records in ascending order")
//    @Test
//    void checkSortingRecordsAscending() {
//        String[] expected = {
//                "First name: John, Last name: Brown, Address: Address #1",
//                "First name: John, Last name: Taylor, Address: Address #1",
//                "First name: Karen, Last name: Davis, Address: Address #2",
//                "First name: Susan, Last name: Brown, Address: Address #4"
//        };
//
//        AddressBook addressBook = new AddressBook(4);
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Susan", "Brown", "Address #4");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("John", "Taylor", "Address #1");
//
//        addressBook.sortedBy(SortOrder.ASC);
//
//        String[] actual = new String[4];
//        int counter = 0;
//        for (Object record : addressBook) {
//            actual[counter++] = record.toString();
//        }
//
//        assertArrayEquals(expected, actual, "The records should be sorted in ascending order");
//    }
//
//    @DisplayName("Ensure sorting of records in descending order")
//    @Test
//    void checkSortingRecordsDescending() {
//        String[] expected = {
//                "First name: Susan, Last name: Brown, Address: Address #4",
//                "First name: Karen, Last name: Davis, Address: Address #2",
//                "First name: John, Last name: Taylor, Address: Address #1",
//                "First name: John, Last name: Brown, Address: Address #1"
//        };
//
//        AddressBook addressBook = new AddressBook(4);
//        addressBook.create("John", "Brown", "Address #1");
//        addressBook.create("Susan", "Brown", "Address #4");
//        addressBook.create("Karen", "Davis", "Address #2");
//        addressBook.create("John", "Taylor", "Address #1");
//
//        addressBook.sortedBy(SortOrder.DESC);
//
//        String[] actual = new String[4];
//        int counter = 0;
//        for (Object record : addressBook) {
//            actual[counter++] = record.toString();
//        }
//
//        assertArrayEquals(expected, actual, "The records should be sorted in descending order");
//    }

    private static Stream<Arguments> provideClass() {
        return Stream.of(Arguments.of(ADDRESS_BOOK, "AddressBook"),
                Arguments.of(NAME_ADDRESS_PAIR, "NameAddressPair"),
                Arguments.of(PERSON, "Person"),
                Arguments.of(ADDRESS_BOOK_ITERATOR, "AddressBookIterator"));
    }

    private static Stream<Arguments> provideFieldType() {
        return Stream.of(Arguments.of(ADDRESS_BOOK, "counter", int.class),
                Arguments.of(NAME_ADDRESS_PAIR, "address", String.class),
                Arguments.of(PERSON, "firstName", String.class));
    }

    private static Stream<Arguments> provideTypeDeclaredMethod() {
        return Stream.of(Arguments.of(ADDRESS_BOOK, "create", new Class[]{String.class, String.class, String.class}),
                Arguments.of(ADDRESS_BOOK, "delete", new Class[]{String.class, String.class}),
                Arguments.of(ADDRESS_BOOK, "size", new Class[]{}));
    }

    private static Stream<Arguments> provideMethodReturnType() {
        return Stream.of(Arguments.of(ADDRESS_BOOK, "create", boolean.class),
                Arguments.of(ADDRESS_BOOK, "delete", boolean.class),
                Arguments.of(ADDRESS_BOOK, "size", int.class),
                Arguments.of(ADDRESS_BOOK, "read", String.class));
    }

    private static Stream<Arguments> provideOverrideTypeMethod() {
        return Stream.of(Arguments.of("java.lang.Iterable", ADDRESS_BOOK, "iterator"),
                Arguments.of("java.lang.Object", PERSON, "equals"),
                Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR, "hasNext"),
                Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR, "next"));
    }

    private static boolean equalParamTypes(final Class<?>[] params1, final Class<?>[] params2) {
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; ++i) {
                if (params1[i] != params2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
