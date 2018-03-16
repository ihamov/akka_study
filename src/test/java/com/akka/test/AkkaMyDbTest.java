package com.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.akka.AkkaMyDb;
import com.akka.message.SetRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AkkaMyDbTest {

    ActorSystem system = ActorSystem.create();
    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkaMyDb> actorRef = TestActorRef.create(system, Props.create(AkkaMyDb.class));
        actorRef.tell(new SetRequest("key", "value"), ActorRef.noSender());
        AkkaMyDb akkaMyDb = actorRef.underlyingActor();
        assertEquals(akkaMyDb.getMap().get("key"), "value");
    }


}
