package com.backend.utils;

import com.backend.domain.Amount;
import com.backend.domain.BankAccount;
import com.backend.domain.Customer;
import com.backend.domain.Transfer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JsonUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final String JSON_BANK_ACCOUNT = "{\r\n" +
            "  \"bankAccountNumber\" : \"1234\",\r\n" +
            "  \"bankCity\" : \"Cordoba\",\r\n" +
            "  \"bankName\" : \"Provincia\",\r\n" +
            "  \"customer\" : {\r\n" +
            "    \"infix\" : \"Sr\",\r\n" +
            "    \"firstName\" : \"Lionel\",\r\n" +
            "    \"lastName\" : \"Messi\",\r\n" +
            "    \"gender\" : \"MALE\",\r\n" +
            "    \"passportNo\" : \"998877\"\r\n" +
            "  },\r\n" +
            "  \"balance\" : {\r\n" +
            "    \"value\" : 123.0,\r\n" +
            "    \"currency\" : \"USD\"\r\n" +
            "  }\r\n" +
            "}";
    private static final String JSON_TRANSFER = "{ \r\n" +
            "\"originAccountNumber\": \"2\",\r\n" +
            "\"destinationAccountNumber\": \"1\",\r\n" +
            "\"amount\":{\r\n" +
            "    \"value\":100.00,\r\n" +
            "                \"currency\":\"USD\"\r\n" +
            "    }\r\n" +
            "}";

    @Test
    public void shouldReturnStringNullWhenParseDataToJsonReceivesNull() throws Exception {

        String json = JsonUtil.dataToJson(null);

        assertThat(json, is("null"));
    }

    @Test
    public void shouldParseDataToJson() throws Exception {
        BankAccount bankAccount = buildBankAccount();

        String json = JsonUtil.dataToJson(bankAccount);

        assertNotNull(json);
        assertThat(json, is(JSON_BANK_ACCOUNT));
    }

    private static BankAccount buildBankAccount() {
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setValue(123.00);

        Customer customer = new Customer();
        customer.setPassportNo("998877");
        customer.setInfix("Sr");
        customer.setFirstName("Lionel");
        customer.setLastName("Messi");
        customer.setGender(Customer.Gender.MALE);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccountNumber("1234");
        bankAccount.setBankCity("Cordoba");
        bankAccount.setBankName("Provincia");
        bankAccount.setBalance(amount);
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @Test
    public void shouldReturnNullWhenParseJsonToBankAccountReceivesNull() throws Exception {

        BankAccount bankAccount = JsonUtil.jsonToBankAccount(null);

        assertNull(bankAccount);
    }

    @Test
    public void shouldThrowExceptionWhenParseJsonToBankAccountReceivesEmptyString() throws Exception {
        expectedException.expect(RuntimeException.class);

        JsonUtil.jsonToBankAccount("");
    }

    @Test
    public void shouldParseJsonToBankAccount() throws Exception {

        BankAccount bankAccount = JsonUtil.jsonToBankAccount(JSON_BANK_ACCOUNT);

        assertNotNull(bankAccount);
        assertThat(bankAccount.getBankAccountNumber(), is("1234"));
        assertThat(bankAccount.getBankCity(), is("Cordoba"));
        assertThat(bankAccount.getBankName(), is("Provincia"));
        assertNotNull(bankAccount.getCustomer());
        assertThat(bankAccount.getCustomer().getFirstName(), is("Lionel"));
        assertThat(bankAccount.getCustomer().getLastName(), is("Messi"));
        assertThat(bankAccount.getCustomer().getGender(), is(Customer.Gender.MALE));
        assertThat(bankAccount.getCustomer().getInfix(), is("Sr"));
        assertThat(bankAccount.getCustomer().getPassportNo(), is("998877"));
        assertNotNull(bankAccount.getBalance());
        assertThat(bankAccount.getBalance().getCurrency(), is("USD"));
        assertThat(bankAccount.getBalance().getValue(), is(123.00));
    }

    @Test
    public void shouldReturnNullWhenParseJsonToTransferReceivesNull() throws Exception {

        Transfer transfer = JsonUtil.jsonToTransfer(null);

        assertNull(transfer);
    }

    @Test
    public void shouldThrowExceptionWhenParseJsonToTransferReceivesEmptyString() throws Exception {
        expectedException.expect(RuntimeException.class);

        JsonUtil.jsonToTransfer("");
    }

    @Test
    public void shouldParseJsonToTransfer() throws Exception {

        Transfer transfer = JsonUtil.jsonToTransfer(JSON_TRANSFER);

        assertNotNull(transfer);
        assertThat(transfer.getDestinationAccountNumber(), is("1"));
        assertThat(transfer.getOriginAccountNumber(), is("2"));
        assertNotNull(transfer.getAmount());
        assertThat(transfer.getAmount().getValue(), is(100.00));
        assertThat(transfer.getAmount().getCurrency(), is("USD"));

    }

}