package xyz.telecter.justgrenades.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import xyz.telecter.justgrenades.items.ModItems;

public class GrenadeEntity extends ThrownItemEntity {

    public GrenadeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE;
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            serverWorld.createExplosion(this, this.getX(), this.getY()+1, this.getZ(), 3f, false,
                    World.ExplosionSourceType.TNT);
            this.kill(serverWorld);
        }
    }
}
