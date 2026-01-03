package com.lambda.dlq;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import java.util.*;

public class DLQProcessingLambda {

    public void handle(SNSEvent snsEvent, Context context){

        final LambdaLogger logger = context.getLogger();
        for(Object records:snsEvent.getRecords()){
            logger.log(records.toString());
        }
    }
}
