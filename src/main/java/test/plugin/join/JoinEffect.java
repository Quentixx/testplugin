package test.plugin.join;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public enum JoinEffect {

    EFFECT_1((event, player, protocol) -> {
        Location loc = player.getLocation();
        player.sendMessage("Voici l'effet numéro uno ! (effet explosion)");
        PacketContainer packet = protocol.createPacket(PacketType.Play.Server.EXPLOSION);
        packet.getDoubles().
                write(0, loc.getX()).
                write(1, loc.getY()).
                write(2, loc.getZ());
        return packet;
    }),
    EFFECT_2((event, player, protocol) -> {
        Location loc = player.getLocation();
        player.sendMessage("Voici l'effet numéro 2 ! (son)");
        PacketContainer packet = protocol.createPacket(PacketType.Play.Server.NAMED_SOUND_EFFECT);
        packet.getModifier().writeDefaults();
        packet.getStrings().write(0, "mob.enderdragon.death");
        packet.getDoubles().
                write(0, loc.getX()).
                write(1, loc.getY()).
                write(2, loc.getZ());
        packet.getFloat().write(0,1F);
        return packet;
    }),
    EFFECT_3((event, player, protocol) -> {
        Location loc = player.getLocation();
        player.sendMessage("Voici l'effet numéro 3 ! (Vitesse améliorée)");
        PacketContainer packet = protocol.createPacket(PacketType.Play.Server.UPDATE_ATTRIBUTES);
        packet.getStrings().write(0, "generic.movement_speed");
        packet.getDoubles(). write(2, 10D);
        return packet;
    }),
    EFFECT_4((event, player, protocol) -> {
        Location loc = player.getLocation();
        player.sendMessage("Voici l'effet numéro 4 ! (ouverture du livre) ");
        PacketContainer packet = protocol.createPacket(PacketType.Play.Server.OPEN_BOOK);
        packet.getBooleans().
                write(3, true);
        packet.getIntegers().write(0, 4).write(1, 1);
        packet.getStringArrays().write(0, new String[]{"Ceci ne sert à rien."});
        return packet;
    });


    private static final Random RANDOM = new Random();

    private JoinEffectConsumer consumer;

    JoinEffect(JoinEffectConsumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Get a random JoinEffect.
     *
     * @return random effect
     */
    public static JoinEffect random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    public JoinEffectConsumer getConsumer() {
        return consumer;
    }
}
