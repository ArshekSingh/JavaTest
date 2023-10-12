package codebase;

public class AWS {

    //Pub
    queueMessagingTemplate.send(collectionProperties.getCloudAwsEndPointUri(), MessageBuilder.withPayload(new ObjectMapper().writeValueAsString(smsQueueRequest)).build());

}
