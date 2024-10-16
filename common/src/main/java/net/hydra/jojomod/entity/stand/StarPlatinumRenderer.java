package net.hydra.jojomod.entity.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.entity.client.ModEntityRendererClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class StarPlatinumRenderer<T extends StandEntity> extends StandRenderer<StarPlatinumEntity> {

    private static final ResourceLocation SKIN_1 = new ResourceLocation(Roundabout.MOD_ID,"textures/stand/star_platinum.png");
    public StarPlatinumRenderer(EntityRendererProvider.Context context) {
        super(context, new StarPlatinumModel<>(context.bakeLayer(ModEntityRendererClient.STAR_PLATINUM_LAYER)),0f);
        this.addLayer(new StarPlatinumEyeLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(StarPlatinumEntity entity) {
        return SKIN_1;
    }

    @Override
    public void render(StarPlatinumEntity mobEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        if (!(mobEntity.getUser() != null && Minecraft.getInstance().player != null && mobEntity.getScoping() &&
                mobEntity.getUser().is(Minecraft.getInstance().player) &&
                Minecraft.getInstance().options.getCameraType().isFirstPerson())) {
            if (mobEntity.isBaby()) {
                matrixStack.scale(0.5f, 0.5f, 0.5f);
            } else {
                matrixStack.scale(0.87f, 0.87f, 0.87f);
            }
            super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }

    @Nullable
    @Override
    protected RenderType getRenderType(StarPlatinumEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return super.getRenderType(entity, showBody, true, showOutline);
    }


}
