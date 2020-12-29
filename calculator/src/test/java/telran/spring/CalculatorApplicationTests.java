package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static telran.spring.api.ApiConstants.CALCULATOR_ADD;
import static telran.spring.api.ApiConstants.CALCULATOR_DIV;
import static telran.spring.api.ApiConstants.CALCULATOR_MUL;
import static telran.spring.api.ApiConstants.CALCULATOR_SUB;
import static telran.spring.api.ApiConstants.FIRST_PARAM;
import static telran.spring.api.ApiConstants.SECOND_PARAM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telran.spring.controllers.CalculatorController;
import telran.spring.service.interfaces.Calculator;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorApplicationTests {
	public static final String REQUEST_PARAMS = "%s?%s=%s&%s=%s";// add?avl1=1&val2=2
	public static final String REQUEST_PARAMS_MISSING = "%s?%s=%s";

	public void assertHttpStatusAndValue(String request, String expected, HttpStatus httpStatus) throws Exception {
		MockHttpServletResponse responce = mock.perform(MockMvcRequestBuilders.get(request)).andReturn().getResponse();
		assertEquals(httpStatus, HttpStatus.valueOf(responce.getStatus()));
		assertEquals(expected, responce.getContentAsString());
	}

	@Autowired
	CalculatorController controller;
	@Autowired
	Calculator calculator;
	@Autowired
	MockMvc mock;

	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(calculator);
	}

	@Tag("TwoRightParameters")
	@DisplayName("Two right parameters")
	@Nested
	class TwoRightParameters {
		@DisplayName("20+20=40")
		@Test
		void sum() throws Exception {
			String expected = "40";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS, CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM, 20),
					expected, HttpStatus.OK);
			assertHttpStatusAndValue(
					String.format(REQUEST_PARAMS, CALCULATOR_ADD, FIRST_PARAM, "0xFF", SECOND_PARAM, "0xFF"), "510",
					HttpStatus.OK);
		}

		@DisplayName("20-20=0")
		@Test
		void sub() throws Exception {
			String expected = "0";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS, CALCULATOR_SUB, FIRST_PARAM, 20, SECOND_PARAM, 20),
					expected, HttpStatus.OK);

		}

		@DisplayName("20/10=2")
		@Test
		void div() throws Exception {
			String expected = "2";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS, CALCULATOR_DIV, FIRST_PARAM, 20, SECOND_PARAM, 10),
					expected, HttpStatus.OK);

		}

		@DisplayName("20*20=400")
		@Test
		void mul() throws Exception {
			String expected = "400";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS, CALCULATOR_MUL, FIRST_PARAM, 20, SECOND_PARAM, 20),
					expected, HttpStatus.OK);
		}
	}

	@Tag("MissedFirstParameter")
	@DisplayName("Missed first parameter")
	@Nested
	class MissedFirstParameter {
		@DisplayName("+20=")
		@Test
		void sum() throws Exception {
			String expected = "";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_ADD, SECOND_PARAM, 20), expected,
					HttpStatus.BAD_REQUEST);
		}

		@DisplayName("-20=")
		@Test
		void sub() throws Exception {
			String expected = "";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_ADD, SECOND_PARAM, 20), expected,
					HttpStatus.BAD_REQUEST);
		}

		@DisplayName("/10=")
		@Test
		void div() throws Exception {
			String expected = "";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_ADD, SECOND_PARAM, 20), expected,
					HttpStatus.BAD_REQUEST);
		}

		@DisplayName("*20=")
		@Test
		void mul() throws Exception {
			String expected = "";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_ADD, SECOND_PARAM, 20), expected,
					HttpStatus.BAD_REQUEST);
		}
	}

	@Tag("MissedSecondParameter")
	@DisplayName("Missed second parameter")
	@Nested
	class MissedSecondParameter {
		@DisplayName("20+=20")
		@Test
		void sum() throws Exception {
			String expected = "20";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_ADD, FIRST_PARAM, 20), expected,
					HttpStatus.OK);
		}

		@DisplayName("20-=20")
		@Test
		void sub() throws Exception {
			String expected = "20";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_SUB, FIRST_PARAM, 20), expected,
					HttpStatus.OK);
		}

		@DisplayName("20/=20")
		@Test
		void div() throws Exception {
			String expected = "20";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_DIV, FIRST_PARAM, 20), expected,
					HttpStatus.OK);
		}

		@DisplayName("20*=20")
		@Test
		void mul() throws Exception {
			String expected = "20";
			assertHttpStatusAndValue(String.format(REQUEST_PARAMS_MISSING, CALCULATOR_MUL, FIRST_PARAM, 20), expected,
					HttpStatus.OK);
		}
	}
}
