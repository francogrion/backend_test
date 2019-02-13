package com.backend.main;

import com.backend.controllers.AccountController;
import com.backend.controllers.TransferController;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class RestServer {

    public static void main(String[] args) {

        port(8080);

        // Set up routes
        get("/account_service/:account_number", AccountController.getAccount);
        post("/account_service", AccountController.createAccount);
        post("/transfer", TransferController.createAccount);
        get("*", (request, response) -> "Service Not Found");
    }
}