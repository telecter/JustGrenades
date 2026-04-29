package xyz.telecter.justgrenades.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import xyz.telecter.justgrenades.JustGrenades;

public class ModEntityType {
        public static void initialize() {
        }

        public static final EntityType<GrenadeEntity> GRENADE = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JustGrenades.MOD_ID, "grenade"),
                        EntityType.Builder.of(GrenadeEntity::new, MobCategory.MISC)
                                        .sized(0.25f, 0.25f)
                                        .clientTrackingRange(4)
                                        .build(ResourceKey.create(Registries.ENTITY_TYPE,
                                                        Identifier.fromNamespaceAndPath(JustGrenades.MOD_ID, "grenade"))));
    public static final EntityType<SmokeGrenadeEntity> SMOKE_GRENADE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(JustGrenades.MOD_ID, "smoke_grenade"),
            EntityType.Builder.of(SmokeGrenadeEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(JustGrenades.MOD_ID, "smoke_grenade"))));

}
