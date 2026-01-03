package com.lambda.errorHandling;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.util.StringUtils;
import com.google.gson.Gson;

public class LambdaApiGateway implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        //log the entire event
        final LambdaLogger logger = context.getLogger();
        logger.log("Api Event: " + requestEvent.toString());

        //get user details from POST call and save that to db
        //fetch the request body from the event

        String body = requestEvent.getBody();

        final User user = gson.fromJson(body, User.class);
        //client check - if username and id are not null
        if(StringUtils.isNullOrEmpty(user.getUserName()) || user.getId() == null){
            new RuntimeException("User Not found");
        }

        return null;
    }
}
