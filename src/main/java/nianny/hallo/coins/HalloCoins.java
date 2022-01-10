package nianny.hallo.coins;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HalloCoins implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("hallocoins");
	
	// Create item objects
	// NOTE: Group is set to null to prevent illegal forward referrences.
	public static final Item BASIC_COIN = new Item(new Item.Settings().group(null)); 
	public static final Item SILVER_COIN = new Item(new Item.Settings().group(null));
	public static final Item GOLD_COIN = new Item(new Item.Settings().group(null));
	public static final Item DIAMOND_COIN = new Item(new Item.Settings().group(null));
	public static final Item SILVER_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC));
	
	// Custom item grop
	public static final ItemGroup HALLOCOIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier("hallocoins", "general")) // translation key for group name (full translation key: itemGroup.hallocoins.general)
		.icon(() -> new ItemStack(GOLD_COIN))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(BASIC_COIN));
			stacks.add(new ItemStack(SILVER_COIN));
			stacks.add(new ItemStack(GOLD_COIN));
			stacks.add(new ItemStack(DIAMOND_COIN));
		})
		.build();
	

	
	

	
	@Override
	public void onInitialize() {
		LOGGER.info("HalloCoin is initialising!");
		
		
		// Register HalloCoins
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "basic_coin"), BASIC_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "silver_coin"), SILVER_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "gold_coin"), GOLD_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "diamond_coin"), DIAMOND_COIN);
		
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "silver_ingot"), SILVER_INGOT);


	}
}
