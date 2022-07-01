package test.plugin.databse;

import test.plugin.join.JoinEffect;
import test.plugin.util.SysLog;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.UUID;

public class DBJoinEffect {

    private final static DatabaseService EXECUTOR = DatabaseService.SERVICE;

    private final static String INSERT_OR_UPDATE = "INSERT INTO table_effect (uuid, effectId) VALUES (?, ?) ON CONFLICT(\"uuid\") DO UPDATE SET effectId = ?";

    private static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS table_effect (uuid VARCHAR(32), effectId INTEGER)";


    {
        try {
            EXECUTOR.update(CREATE_TABLE_IF_NOT_EXISTS, statement -> {});
        } catch (SQLException e) {
            SysLog.err(getClass(), "Impossible de créer le table :");
            e.printStackTrace();
        }
    }

    public static int updateEffect(UUID uuid, JoinEffect effect) throws SQLException {
        return EXECUTOR.update(INSERT_OR_UPDATE, statement -> {
            statement.setInt(1, effect.ordinal());
        } );
    }
}
