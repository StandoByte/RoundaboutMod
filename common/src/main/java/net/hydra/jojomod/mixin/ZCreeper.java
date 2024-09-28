package net.hydra.jojomod.mixin;

import com.google.common.collect.Lists;
import net.hydra.jojomod.event.ModEffects;
import net.hydra.jojomod.event.powers.StandUser;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Iterator;

@Mixin(Creeper.class)
public class ZCreeper extends Monster {
    /**Minor code for stopping creepers in a barrage*/
    @Shadow
    private int oldSwell;
    @Shadow
    private int swell;

    protected ZCreeper(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "spawnLingeringCloud()V", at = @At("STORE"), ordinal = 0)
    protected Collection<MobEffectInstance> roundabout$SpawnLingeringCloud(Collection<MobEffectInstance> col) {
        if (!col.isEmpty()) {
            Collection<MobEffectInstance> col2 = Lists.newArrayList();
            for (MobEffectInstance $$2 : col) {
                if (!$$2.getEffect().equals(ModEffects.BLEED)){
                    col2.add($$2);
                }
            }
            return col2;
        }
        return col;
    }
    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    protected void RoundaboutTick(CallbackInfo ci) {
        if (((StandUser)this).isDazed() ||
                (!((StandUser)this).roundabout$getStandDisc().isEmpty() &&
                        ((StandUser)this).getStandPowers().disableMobAiAttack())) {
            if (((Creeper)(Object)this).isAlive()) {
                oldSwell = swell;
            }

            this.swell -= 1;
            if (this.swell < 0) {
                this.swell = 0;
            }
            super.tick();
            ci.cancel();
        }
    }
}
