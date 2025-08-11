package models;

import play.db.Database;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BoatRepository {
    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public BoatRepository(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }
}
