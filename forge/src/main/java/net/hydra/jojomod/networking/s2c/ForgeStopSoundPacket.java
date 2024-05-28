package net.hydra.jojomod.networking.s2c;

import net.hydra.jojomod.event.powers.StandUser;
import net.hydra.jojomod.event.powers.StandUserClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeStopSoundPacket {
    private final int cancelPlayerID;

    public ForgeStopSoundPacket(int cancelPlayerID){
        this.cancelPlayerID = cancelPlayerID;
    }
    public ForgeStopSoundPacket(FriendlyByteBuf buf){
        this.cancelPlayerID = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(cancelPlayerID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                Entity User = player.level().getEntity(cancelPlayerID);
                ((StandUserClient)User).clientQueSoundCanceling();
            }
        });
        return true;
    }
}
