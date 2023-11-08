package com.eteration.simplebanking;

import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doReturn;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class IntegrationTests {

    @MockBean
    private AccountService accountService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetAccount() {
        BankAccount bankAccount = new BankAccount("John Doe", "123456");
        doReturn(bankAccount).when(accountService).findAccount("123456");

        BankAccount response = webTestClient.get().uri("/account/v1/123456").exchange().expectStatus().isOk().expectBody(BankAccount.class).returnResult().getResponseBody();

        assert response != null;
        assert response.getAccountNumber().equals("123456");
        assert response.getOwner().equals("John Doe");
    }


    @Test
    public void testCreditTransaction() {
        BankAccount bankAccount = new BankAccount("John Doe", "123456");
        doReturn(bankAccount).when(accountService).findAccount("123456");
        // Perform a credit transaction
        webTestClient
                .post()
                .uri("/account/v1/credit/123456")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue("{\"amount\": 1000.0}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("OK")
                .jsonPath("$.approvalCode").isNotEmpty();
    }

    @Test
    public void testDebitTransaction() {
        BankAccount bankAccount = new BankAccount("John Doe", "123456");
        bankAccount.setBalance(50);
        doReturn(bankAccount).when(accountService).findAccount("123456");
        // Perform a debit transaction
        webTestClient
                .post()
                .uri("/account/v1/debit/123456")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue("{\"amount\": 50.0}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("OK")
                .jsonPath("$.approvalCode").isNotEmpty();
    }

}
