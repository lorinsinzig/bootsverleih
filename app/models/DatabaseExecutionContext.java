package models;

import org.apache.pekko.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;
import javax.inject.Inject;

public class DatabaseExecutionContext extends CustomExecutionContext {

    @Inject
    public DatabaseExecutionContext(ActorSystem actorSystem) {
        // Uses the "database.dispatcher" configuration from application.conf
        super(actorSystem, "database.dispatcher");
    }
}