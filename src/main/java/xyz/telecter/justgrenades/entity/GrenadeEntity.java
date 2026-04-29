package xyz.telecter.justgrenades.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import xyz.telecter.justgrenades.items.ModItems;

public class GrenadeEntity extends ThrowableItemProjectile {

    public GrenadeEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE;
    }

    @Override
    public void onInsideBlock(BlockState state) {
        if (this.level() instanceof ServerLevel serverWorld) {
            serverWorld.explode(this, this.getX(), this.getY()+1, this.getZ(), 3f, false,
                    Level.ExplosionInteraction.TNT);
            this.kill(serverWorld);
        }
    }
}
