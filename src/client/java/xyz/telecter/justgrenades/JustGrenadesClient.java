package xyz.telecter.justgrenades;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import xyz.telecter.justgrenades.entity.ModEntityType;

public class JustGrenadesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(
				ModEntityType.GRENADE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(
                ModEntityType.SMOKE_GRENADE, ThrownItemRenderer::new);
	}
}