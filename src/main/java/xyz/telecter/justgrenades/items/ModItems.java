package xyz.telecter.justgrenades.items;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import xyz.telecter.justgrenades.JustGrenades;
import xyz.telecter.justgrenades.entity.ModEntityType;

import java.util.function.Function;

public class ModItems {
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register(itemGroup -> {
                    itemGroup.accept(GRENADE);
                    itemGroup.accept(SMOKE_GRENADE);
                });
    }

    public static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JustGrenades.MOD_ID, name));
        Item item = factory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static final Item GRENADE = register("grenade", (settings) -> new GrenadeItem(ModEntityType.GRENADE, settings), new Item.Properties()
            .useCooldown(2)
            .stacksTo(16)
        );
    public static final Item SMOKE_GRENADE = register("smoke_grenade", (settings) -> new GrenadeItem(ModEntityType.SMOKE_GRENADE, settings), new Item.Properties()
            .useCooldown(2)
            .stacksTo(16)
    );
}
