package xyz.telecter.justgrenades.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.telecter.justgrenades.entity.GrenadeEntity;
import xyz.telecter.justgrenades.entity.ModEntityType;

public class GrenadeItem extends Item {
    public GrenadeItem(Settings settings) {
        super(settings);

    }
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        GrenadeEntity grenade = new GrenadeEntity(ModEntityType.GRENADE, world);
        grenade.setPosition(player.getEyePos());
        grenade.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.0F, 0F);
        world.spawnEntity(grenade);

        player.getStackInHand(hand).decrementUnlessCreative(1, player);

        return ActionResult.SUCCESS;
    }
}
