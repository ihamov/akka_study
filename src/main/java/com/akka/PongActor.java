package com.akka;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Status;

public class PongActor extends AbstractLoggingActor{
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Ping", s ->
                        sender().tell("Pong", ActorRef.noSender())
                ).matchAny(x ->
                        sender().tell(new Status.Failure(new Exception("unknown message")), self())
                ).build();

    }
}
