package jom.com.softserve.s3.task4;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Tas4Test {

	private static final String LINE_TYPE = "jom.com.softserve.s3.task4.LineType";

	@DisplayName("Check that LineType is an enum")
	@Test
	void isLineTypeEnumTest() {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(LINE_TYPE),
				"LineType class not found");
		assertTrue(clazz.isEnum(), "LineType should be an enum");
	}

	@DisplayName("Check that LineType contains appropriate values")
	@ParameterizedTest
	@ValueSource(strings = { "SOLID", "DASHED", "DOTTED", "DOUBLE" })
	void testLineTypeContainsAppropriateValues(String name) {
		Class<?> clazz = assertDoesNotThrow(() -> Class.forName(LINE_TYPE),
				"LineType class not found");
		assertTrue(clazz.isEnum(), "LineType should be an enum");
		boolean valueExists = Arrays.stream(clazz.getEnumConstants())
				.map(Object::toString)
				.anyMatch(name::equals);
		assertTrue(valueExists, "LineType enum should contain value: " + name);
	}


//	@DisplayName("Check that type of line matches its message")
//	@ParameterizedTest
//	@MethodSource("provideStringsForIsEnum")
//	void drawLineTest(LineType type, String expected) {
//		assertEquals(expected, Line.drawLine(type));
//	}
//
//	private static Stream<Arguments> provideStringsForIsEnum() {
//		return Stream.of(Arguments.of(LineType.DASHED, "The line is dashed type"),
//				Arguments.of(LineType.DOTTED, "The line is dotted type"),
//				Arguments.of(LineType.DOUBLE, "The line is double type"),
//				Arguments.of(LineType.SOLID, "The line is solid type"));
//	}
}
