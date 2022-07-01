package test.plugin.join;

import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public interface JoinEffectConsumer {

    PacketContainer accept(PlayerJoinEvent event, Player player, ProtocolManager protocol);
}
