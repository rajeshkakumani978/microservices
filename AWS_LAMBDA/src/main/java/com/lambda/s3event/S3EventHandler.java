package com.lambda.s3event;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.StringUtils;


public class S3EventHandler implements RequestHandler<S3Event, Boolean> {


    @Override
    public Boolean handleRequest(S3Event input, Context context) {
         throw new RuntimeException("S3 Execution failed");

//        final AmazonS3 s3Client = AmazonS3Client.builder()
//                .withClientConfiguration(new ClientConfiguration())
//                .build();
//
//        final LambdaLogger logger = context.getLogger();
//
//        if(input.getRecords() == null){
//            logger.log("No records found");
//        }
//
//        for(S3EventNotification.S3EventNotificationRecord record: input.getRecords()) {
//            String bucketName = record.getS3().getBucket().getName();
//            String objectKey = record.getS3().getObject().getKey();
//
//            S3Object s3Object = s3Client.getObject(bucketName, objectKey);
//            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
//
//            logger.log(s3ObjectInputStream.getHttpRequest().toString());
//        }
//        logger.log("CSv File Parsing");
        //return null;
    }
}
