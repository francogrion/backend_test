package com.backend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "originAccountNumber",
        "destinationAccountNumber",
        "balance"
})
public class Transfer {

    @JsonProperty("originAccountNumber")
    public String originAccountNumber;
    @JsonProperty("destinationAccountNumber")
    public String destinationAccountNumber;
    @JsonProperty("amount")
    public Amount amount;

    public String getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(String originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transfer{");
        sb.append("originAccountNumber='").append(originAccountNumber).append('\'');
        sb.append(", destinationAccountNumber='").append(destinationAccountNumber).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}