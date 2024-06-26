package com.mememan.mememanmod.manager;


import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class MMMNetworkManager {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(ManMemeMod.prefix("channel"), () ->
                    PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);
    private static int PACKET_ID = 0;

    static void registerPackets(FMLCommonSetupEvent event) {
        registerCTSPackets();
        registerSTCPackets();
    }

    private static void registerCTSPackets() {
    }

    private static void registerSTCPackets() {
    }

    /**
     * Sends a packet from client to server. C2S.
     * @param packet Packet to send to the server.
     */
    public static void sendPacketToServer(Object packet) {
        CHANNEL.sendToServer(packet);
    }

    /**
     * Sends a packet to all clients from the server. S2C.
     * @param packet Packet to send to client.
     */
    public static void sendPacketToClient(Object packet) {
        CHANNEL.sendTo(packet, Minecraft.getInstance().getConnection().getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    /**
     * Sends a tracking entity/player packet to all tracking clients (as well as the {@code trackedEntity} from the server. S2C.
     * @param packet Packet to send (S2C)
     * @param trackedEntity Tracked Entity
     */
    public static void sendEntityTrackingPacket(Object packet, Entity trackedEntity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> trackedEntity), packet);
    }
}