package com.oos.payment.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentVerification {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Endpoint to verify transaction
//    private final String VERIFY_ENDPOINT = "https://ravesandboxapi.flutterwave.com/flwv3-pug/getpaidx/api/v2/verify";

    private final String VERIFY_ENDPOINT = "https://api.ravepay.co/flwv3-pug/getpaidx/api/v2/verify";
//please make sure to change this to production url when you go live


    public JSONObject verify(String txref, String secret, double amount) {

        // This packages the payload
        JSONObject data = new JSONObject();
        data.put("txref", txref);
        data.put("SECKEY", secret);

        // end of payload

        // This sends the request to server with payload
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(VERIFY_ENDPOINT)
                    .header("Content-Type", "application/json")
                    .body(data)
                    .asJson();
        } catch (UnirestException e) {
            logger.error(e.getMessage());
            return null;
        }

        // This get the response from payload
        JsonNode jsonNode = response.getBody();

        // This get the json object from payload
        JSONObject responseObject = jsonNode.getObject();

        // check of no object is returned
        if (responseObject == null) {
            logger.warn("No response from server");
            return null;
        }
        // throw new Exception("No response from server");

        // This get status from returned payload
        String status = responseObject.optString("status", null);

        // this ensures that status is not null
        if (status == null) {
            logger.warn("Transaction status unknown");
            return null;
        }

        // This confirms the transaction exist on rave
        if (!"success".equalsIgnoreCase(status)) {
            String message = responseObject.optString("message", null);
            logger.warn(message);
            return null;
        }

        data = responseObject.getJSONObject("data");

        // This get the amount stored on server
        double actualAmount = data.getDouble("amount");

        // This validates that the amount stored on client is same returned
        if (actualAmount != amount) {
            logger.warn("Amount does not match");
            return null;
        }
        // now you can give value for payment.
        return data;

    }
}
