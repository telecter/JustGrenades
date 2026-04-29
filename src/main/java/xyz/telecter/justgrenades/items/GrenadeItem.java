package xyz.telecter.justgrenades.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import xyz.telecter.justgrenades.entity.GrenadeEntity;
import xyz.telecter.justgrenades.entity.ModEntityType;
import xyz.telecter.justgrenades.entity.SmokeGrenadeEntity;

public class GrenadeItem extends Item {
    private final EntityType<? extends GrenadeEntity> entityType;

    public GrenadeItem(EntityType<? extends GrenadeEntity> entityType, Properties settings) {
        super(settings);
        this.entityType = entityType;
    }
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ThrowableItemProjectile grenade;

        if (entityType.equals(ModEntityType.GRENADE)) {
            grenade = new GrenadeEntity(ModEntityType.GRENADE, world);
        } else {
            grenade = new SmokeGrenadeEntity(ModEntityType.SMOKE_GRENADE, world);
        }

        grenade.setPos(player.getEyePosition());

        grenade.setDeltaMovement(Vec3.directionFromRotation(player.getRotationVector()));
        world.addFreshEntity(grenade);

        player.getMainHandItem().consume(1, player);

        return InteractionResult.SUCCESS;
    }
}
