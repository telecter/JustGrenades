package xyz.telecter.justgrenades.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import xyz.telecter.justgrenades.items.ModItems;

import java.util.List;

public class SmokeGrenadeEntity extends GrenadeEntity {

    private static final SoundEvent EXPLOSION_SOUND = SoundEvent.createVariableRangeEvent(Identifier.withDefaultNamespace("entity.generic.explode"));

    public SmokeGrenadeEntity(EntityType<? extends GrenadeEntity> entityType, Level world) {
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
    public void onInsideBlock(BlockState state) {
        Level world = this.level();
        AABB box = new AABB(this.getOnPos()).inflate(5);
        List<Player> entities = world.getEntitiesOfClass(Player.class, box, LivingEntity::isAlive);

        entities.forEach(player -> player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 5*20, 0, false, false, false)));

        if (world instanceof ServerLevel serverWorld) {
            serverWorld.playSound(this, this.getOnPos(), EXPLOSION_SOUND, SoundSource.BLOCKS, 1.0f, 1.0f);
            double radius = 5;
            double step = 0.5;

            double cx = this.getX();
            double cy = this.getY();
            double cz = this.getZ();
            for (double x = -radius; x <= radius; x += step) {
                for (double y = 0; y <= radius; y += step) {
                    for (double z = -radius; z <= radius; z += step) {
                        if (x*x + y*y + z*z <= radius * radius) {
                            serverWorld.sendParticles(
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
