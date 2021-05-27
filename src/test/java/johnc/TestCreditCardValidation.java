package johnc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestCreditCardValidation extends ApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testVisa1() throws Exception {
		mockMvc.perform(get("/creditcard/validate/4111111111111111")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("valid"));
	}
	
	@Test
	public void testVisa2() throws Exception {
		mockMvc.perform(get("/creditcard/validate/4111111111111")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("invalid"));
	}
	
	@Test
	public void testVisa3() throws Exception {
		mockMvc.perform(get("/creditcard/validate/4012888888881881")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("valid"));
	}
	
	@Test
	public void testAmex() throws Exception {
		mockMvc.perform(get("/creditcard/validate/378282246310005")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("valid"));
	}
	
	@Test
	public void testDiscover() throws Exception {
		mockMvc.perform(get("/creditcard/validate/6011111111111117")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("valid"));
	}
	
	@Test
	public void testMastercard1() throws Exception {
		mockMvc.perform(get("/creditcard/validate/5105105105105100")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("valid"));
	}
	
	@Test
	public void testMastercard2() throws Exception {
		mockMvc.perform(get("/creditcard/validate/5105 1051 0510 5106")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("invalid"));
	}
	
	@Test
	public void testUnknown() throws Exception {
		mockMvc.perform(get("/creditcard/validate/9111111111111111")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.result").value("invalid"));
	}
}
