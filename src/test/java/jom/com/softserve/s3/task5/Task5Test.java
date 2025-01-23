package jom.com.softserve.s3.task5;

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
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {
	private static final String CLIENT_TYPE = "jom.com.softserve.s3.task5.ClientType";

	@DisplayName("Check that ClientType is an enum")
	@Test
	void isClientTypeEnumTest() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");
		assertTrue(clazz.isEnum(), "ClientType should be an enum");
	}

	@DisplayName("Check that ClientType contains appropriate values")
	@ParameterizedTest
	@ValueSource(strings = { "NEW", "SILVER", "GOLD", "PLATINUM", "months" })
	void testClientTypeContainsFields(String name) {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");

		assertTrue(clazz.isEnum(), "ClientType should be an enum");

		boolean fieldExists = Arrays.stream(clazz.getDeclaredFields())
				.anyMatch(field -> field.getName().equals(name));

		assertTrue(fieldExists, "ClientType should contain: " + name);
	}


	@DisplayName("Check that 'months' field is private")
	@Test
	void isMonthsFieldPrivateTest() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");
		Field field = assertDoesNotThrow(() -> clazz.getDeclaredField("months"),
				"The 'months' field does not exist in ClientType");
		assertTrue(Modifier.isPrivate(field.getModifiers()),
				"'months' field should be private");
	}


	@DisplayName("Check that 'months' field is of type int")
	@Test
	void isMonthsFieldIntTest() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");
		Field field = assertDoesNotThrow(() -> clazz.getDeclaredField("months"),
				"The 'months' field does not exist in ClientType");
		assertEquals(int.class, field.getType(), "'months' field should be of type int");
	}

	@DisplayName("Check that 'discount' method is public")
	@Test
	void isDiscountMethodPublic() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");
		Method discountMethod = assertDoesNotThrow(() -> clazz.getDeclaredMethod("discount"),
				"The 'discount' method does not exist in ClientType");
		assertTrue(Modifier.isPublic(discountMethod.getModifiers()),
				"'discount' method should be public");
	}

	@DisplayName("Check that ClientType enum has a constructor")
	@Test
	void hasClientTypeConstructorTest() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(CLIENT_TYPE),
				"ClientType class not found");
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		assertNotNull(constructors, "ClientType enum should have at least one constructor");
		assertTrue(constructors.length > 0, "ClientType enum should have at least one constructor");
	}


//	private static Stream<Arguments> providePriceWithDiscountForClientType() {
//		return Stream.of(
//				Arguments.of(1294.48, ClientType.NEW),       // No discount (1.0)
//				Arguments.of(1255.6456, ClientType.SILVER),  // Example: discount() = 0.97
//				Arguments.of(1177.9768, ClientType.GOLD),    // Example: discount() = 0.91
//				Arguments.of(1022.6392, ClientType.PLATINUM) // Example: discount() = 0.79
//		);
//	}
//
//	@DisplayName("Check that discount is calculated properly for special types of clients")
//	@ParameterizedTest
//	@MethodSource("providePriceWithDiscountForClientType")
//	void testDiscountCalculation(double expectedPrice, ClientType clientType) {
//		assertEquals(expectedPrice, clientType.discount() * 1294.48, 0.01,
//				"Discount calculation for " + clientType + " is incorrect");
//	}

}
