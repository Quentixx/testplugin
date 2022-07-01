package test.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import test.plugin.databse.DatabaseService;
import test.plugin.util.SysLog;

import java.sql.SQLException;

public class Main extends JavaPlugin  {


    @Override
    public void onEnable() {
        try {
            DatabaseService.SERVICE.connect();
        } catch (SQLException e) {
            SysLog.err(getClass(), "Impossible de connecter la base de données :");
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
