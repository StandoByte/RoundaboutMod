package net.hydra.jojomod.registry;

import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.block.ModBlocks;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ForgeParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Roundabout.MOD_ID);

    public static final RegistryObject<SimpleParticleType> HIT_IMPACT = PARTICLES.register("hit_impact",
            () -> new SimpleParticleType(true)
    );
    public static final RegistryObject<SimpleParticleType> BLOOD = PARTICLES.register("blood",
            () -> new SimpleParticleType(true)
    );
    public static final RegistryObject<SimpleParticleType> BLUE_BLOOD = PARTICLES.register("blue_blood",
            () -> new SimpleParticleType(true)
    );
    public static final RegistryObject<SimpleParticleType> ENDER_BLOOD = PARTICLES.register("ender_blood",
            () -> new SimpleParticleType(true)
    );
}
