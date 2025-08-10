package xyz.telecter.justgrenades;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import xyz.telecter.justgrenades.entity.ModEntityType;

public class JustGrenadesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(
				ModEntityType.GRENADE, FlyingItemEntityRenderer::new);
	}
}