package com.akka;

import akka.actor.AbstractLoggingActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.akka.message.SetRequest;

import java.util.HashMap;
import java.util.Map;

public class AkkaMyDb extends AbstractLoggingActor {
    private final LoggingAdapter log =
            Logging.getLogger(context().system(), this);

    private final Map<String, Object> map = new HashMap<>();


    @Override
    public void preStart(){
        log.info("preStart method run...");
    }



    @Override
    public Receive createReceive() {
        return new ReceiveBuilder().match(SetRequest.class, message->{
            log.info("Received Set request: {}", message);
            map.put(message.getKey(), message.getValue());
        }).matchAny(o -> {
            log.info("received unknown message: {}", o);
        }).build();
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
