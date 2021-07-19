package com.dimitri.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for the application entry point.
 * <p>
 * Utilizes Spring Security Mock User support to perform tests.
 * @author dmitrilc
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CalculatorApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	/**
	 * Tests going to the home page as annonymous user.
	 * <p>
	 * Expects HTTP 401.
	 * @throws Exception if the response is not 401.
	 */
	@Test
	public void testGetHomeNoAuth() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isUnauthorized());
	}
	
	/**
	 * Tests getting the pre-built login Spring page.
	 * @throws Exception if the response is not 200.
	 */
	@Test
	public void testGetLogin() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
	
	/**
	 * Tests manual login using formLogin() method.
	 * @throws Exception if the response is not 302 or if the redirected url is not root.
	 */
	@Test
	public void testManualLogin() throws Exception {
		this.mockMvc.perform(formLogin().user("user").password("password"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
	
	/**
	 * Tests going to the root url as an authenticated user.
	 * @throws Exception if response is not 200.
	 */
	@Test
	@WithMockUser
	public void testGetHome() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	/**
	 * Tests posting to logout endpoint. Spring security Post requests require csrf.
	 * <p>
	 * The test behavior is different than a real web browser as they will receive 302.
	 * @throws Exception if response is not 204
	 */
	@Test
	@WithMockUser
	public void testLogout() throws Exception {
		this.mockMvc.perform(post("/logout").with(csrf()))
		.andExpect(status().isNoContent());
	}

}