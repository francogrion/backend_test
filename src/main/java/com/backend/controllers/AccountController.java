package com.backend.controllers;

import com.backend.handlers.AccountHandler;
import org.eclipse.jetty.http.MimeTypes;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.backend.utils.JsonUtil.dataToJson;
import static com.backend.utils.JsonUtil.jsonToBankAccount;

public class AccountController {

    private static AccountHandler accountHandler = AccountHandler.getInstance();
    public static Route getAccount = (Request request, Response response) -> {
        try {
            response.body(dataToJson(accountHandler.getBankAccount(request.params("account_number"))));
            response.status(200);

        } catch (Exception e) {
            response.body("{\"status\": \"" + e.getMessage() + "\"}");
            response.status(500);
        }
        response.type(MimeTypes.Type.APPLICATION_JSON.asString());
        return response.body();
    };
    public static Route createAccount = (Request request, Response response) -> {
        try {
            String accountNumber = accountHandler.createAccount(jsonToBankAccount(request.body()));
            response.body("{\"status\": \"Created\", \"accountNumber\": \"" + accountNumber + "\"}");
            response.status(200);
        } catch (Exception e) {
            response.body("{\"status\": \"" + e.getMessage() + "\"}");
            response.status(500);
        }
        response.type(MimeTypes.Type.APPLICATION_JSON.asString());
        return response.body();
    };

    private AccountController() {
    }
}
