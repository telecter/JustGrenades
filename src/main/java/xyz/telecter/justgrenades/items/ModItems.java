package xyz.telecter.justgrenades.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.telecter.justgrenades.JustGrenades;
import xyz.telecter.justgrenades.entity.ModEntityType;

import java.util.function.Function;

public class ModItems {
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(GRENADE));
    }

    public static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JustGrenades.MOD_ID, name));
        Item item = factory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static final Item GRENADE = register("grenade", (settings) -> new GrenadeItem(ModEntityType.GRENADE, settings), new Item.Settings()
            .useCooldown(2)
            .maxCount(16)
        );
    public static final Item SMOKE_GRENADE = register("smoke_grenade", (settings) -> new GrenadeItem(ModEntityType.SMOKE_GRENADE, settings), new Item.Settings()
            .useCooldown(2)
            .maxCount(16)
    );
}
