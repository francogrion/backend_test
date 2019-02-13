package com.backend.controllers;

import com.backend.handlers.TransferHandler;
import org.eclipse.jetty.http.MimeTypes;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.backend.utils.JsonUtil.jsonToTransfer;

public class TransferController {

    private static TransferHandler transferHandler = TransferHandler.getInstance();
    public static Route createAccount = (Request request, Response response) -> {
        try {
            transferHandler.transfer(jsonToTransfer(request.body()));
            response.body("{\"status\": \"Transfer done!\"}");
            response.status(200);

        } catch (Exception e) {
            response.body("{\"status\": \"" + e.getMessage() + "\"}");
            response.status(500);
        }
        response.type(MimeTypes.Type.APPLICATION_JSON.asString());
        return response.body();
    };

    private TransferController() {
    }
}
