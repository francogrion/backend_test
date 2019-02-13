package com.backend.utils;

import com.backend.domain.BankAccount;
import com.backend.domain.Transfer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtil {

    private JsonUtil() {
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOEXception while mapping object (" + data + ") to JSON");
        }
    }

    public static BankAccount jsonToBankAccount(String json) {
        ObjectMapper mapper = new ObjectMapper();
        if (json != null) {
            try {
                return mapper.readValue(json, BankAccount.class);
            } catch (IOException e) {
                throw new RuntimeException("IOEXception while mapping json (" + json + ") to BankAccount");
            }
        } else {
            return null;
        }
    }

    public static Transfer jsonToTransfer(String json) {
        ObjectMapper mapper = new ObjectMapper();
        if (json != null) {
            try {
                return mapper.readValue(json, Transfer.class);
            } catch (IOException e) {
                throw new RuntimeException("IOEXception while mapping json (" + json + ") to Transfer");
            }
        } else {
            return null;
        }
    }
}
