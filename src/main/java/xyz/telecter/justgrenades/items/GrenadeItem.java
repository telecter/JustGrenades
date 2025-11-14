package xyz.telecter.justgrenades.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.telecter.justgrenades.entity.GrenadeEntity;
import xyz.telecter.justgrenades.entity.ModEntityType;
import xyz.telecter.justgrenades.entity.SmokeGrenadeEntity;

public class GrenadeItem extends Item {
    private final EntityType<? extends GrenadeEntity> entityType;

    public GrenadeItem(EntityType<? extends GrenadeEntity> entityType, Settings settings) {
        super(settings);
        this.entityType = entityType;
    }
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ThrownItemEntity grenade;

        if (entityType.equals(ModEntityType.GRENADE)) {
            grenade = new GrenadeEntity(ModEntityType.GRENADE, world);
        } else {
            grenade = new SmokeGrenadeEntity(ModEntityType.SMOKE_GRENADE, world);
        }

        grenade.setPosition(player.getEyePos());
        grenade.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.0F, 0F);
        world.spawnEntity(grenade);

        player.getStackInHand(hand).decrementUnlessCreative(1, player);

        return ActionResult.SUCCESS;
    }
}
