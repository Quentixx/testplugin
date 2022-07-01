package test.plugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import test.plugin.databse.DBJoinEffect;
import test.plugin.databse.DatabaseService;
import test.plugin.join.JoinEffect;
import test.plugin.util.SysLog;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerEventListener implements Listener {

    private static final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        JoinEffect effect = JoinEffect.random();

        try {
            protocol.sendServerPacket(player, effect.getConsumer().accept(e, player, protocol));
        } catch (InvocationTargetException ex) {
            SysLog.err(getClass(), "Impossible de charger le pacquet : JoinEffect#" + effect.ordinal());
            ex.printStackTrace();
        }

        try {
            DBJoinEffect.updateEffect(uuid, effect);
        } catch (SQLException ex) {
            SysLog.err(getClass(), "Impossible de stocker l'effet dans la base de données :");
            ex.printStackTrace();
        }
    }

}
