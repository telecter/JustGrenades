package xyz.telecter.justgrenades.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import xyz.telecter.justgrenades.items.ModItems;

import java.util.List;

public class SmokeGrenadeEntity extends GrenadeEntity {

    public SmokeGrenadeEntity(EntityType<? extends GrenadeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SMOKE_GRENADE;
    }


    private double getParticleRange() {
        return (-0.5 + (int)(Math.random() * ((0.5 + 0.5) + 1)));
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        World world = this.getEntityWorld();
        Box box = new Box(this.getBlockPos()).expand(5);
        List<PlayerEntity> entities = world.getEntitiesByClass(PlayerEntity.class, box, LivingEntity::isAlive);

        entities.forEach(player -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 5*20, 0, false, false, false)));

        if (world instanceof ServerWorld serverWorld) {
            double radius = 5;
            double step = 0.5;

            double cx = this.getX();
            double cy = this.getY();
            double cz = this.getZ();
            for (double x = -radius; x <= radius; x += step) {
                for (double y = 0; y <= radius; y += step) {
                    for (double z = -radius; z <= radius; z += step) {
                        if (x*x + y*y + z*z <= radius * radius) {
                            serverWorld.spawnParticles(
                                    ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                                    cx + x + getParticleRange(), cy + y + getParticleRange(), cz + z + getParticleRange(),
                                    1,
                                    0, 0, 0,
                                    0
                            );
                        }
                    }
                }
            }
            this.kill(serverWorld);
        }

        }
}
