package xyz.telecter.justgrenades.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.telecter.justgrenades.JustGrenades;

public class ModEntityType {
        public static void initialize() {
        }

        public static final EntityType<GrenadeEntity> GRENADE = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of(JustGrenades.MOD_ID, "grenade"),
                        EntityType.Builder.create(GrenadeEntity::new, SpawnGroup.MISC)
                                        .dimensions(0.25f, 0.25f) // dimensions in Minecraft units of the projectile
                                        .maxTrackingRange(4)
                                        // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                                        .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                                                        Identifier.of(JustGrenades.MOD_ID, "grenade"))));

}
