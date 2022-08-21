package com.management.bank.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.management.bank.BaseTest;
import com.management.bank.exception.RequestExceptionHandler;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.response.AccountResponse;
import com.management.bank.services.AccountService;

@TestPropertySource("classpath:aaplication.properties")
@SpringBootTest
public class AccountControllerTest extends BaseTest {

	private final String ACCOUNT_CONTROLLER_URL = "/bank-management/api/v1/account";

	private MockMvc mockMvc;

	@InjectMocks
	private RequestExceptionHandler requestExceptionHandler;

	@InjectMocks
	private AccountController accountController;

	@Mock
	private AccountService accountService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.accountController)
				.setControllerAdvice(this.requestExceptionHandler).build();
	}

	// Similarly we can write test cases for rest of the methods and edge cases.
	@Test
	public void testCreateAccount() throws Exception {
		final String uri = this.ACCOUNT_CONTROLLER_URL.concat("/create");
		final AccountRequest accountRequest = AccountRequest.builder().customerId(2).accountType("SAVING").build();

		Mockito.when(this.accountService.createAccount(any(AccountRequest.class)))
				.thenReturn(AccountResponse.builder().message("Account created successfully!").build());

		final ObjectMapper mapper = new ObjectMapper();
		final String accountRequestStr = mapper.writeValueAsString(accountRequest);

		final MvcResult mvcResult = this.mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(accountRequestStr))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		mapper.registerModule(new JavaTimeModule());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		final AccountResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<AccountResponse>() {
				});
		assertNotNull(response);
		verify(this.accountService, times(1)).createAccount(any(AccountRequest.class));
	}

}
