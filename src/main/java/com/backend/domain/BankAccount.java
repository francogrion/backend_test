package com.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "bankAccountNumber",
        "bankCity",
        "bankName",
        "customer",
        "balance"
})
public class BankAccount {

    @JsonProperty("bankAccountNumber")
    private String bankAccountNumber;
    @JsonProperty("bankCity")
    private String bankCity;
    @JsonProperty("bankName")
    private String bankName;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("balance")
    private Amount balance;

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankAccount{");
        sb.append("bankAccountNumber='").append(bankAccountNumber).append('\'');
        sb.append(", bankCity='").append(bankCity).append('\'');
        sb.append(", bankName='").append(bankName).append('\'');
        sb.append(", customer=").append(customer);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }

    public void receiveTransfer(Amount amountToReceive) {
        this.balance.setValue(this.balance.getValue() + amountToReceive.getValue());
    }

    public boolean payTransfer(Amount amountToPay) {
        boolean enoughFunds = false;
        if (amountToPay.getValue() <= this.balance.getValue()) {
            this.balance.setValue(this.balance.getValue() - amountToPay.getValue());
            enoughFunds = true;
        }
        return enoughFunds;
    }
}