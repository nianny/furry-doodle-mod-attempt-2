package nianny.hallo.coins;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nianny.hallo.coins.util.CommandRegistration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HalloCoins implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static Map<UUID, Integer> mp = new HashMap<>(); //map each player to their balance
	public static Map<String, HalloCoinItem> convert= new HashMap<>(); //map string to corresponding item
	public static final Logger LOGGER = LogManager.getLogger("hallocoins");

	// Create item objects
	public static final Item BASIC_COIN = new HalloCoinItem(new Item.Settings().group(null), "basic_coin", 1); //Set to null for now. During ItemGroup registration it will be added into the group.
	public static final Item SILVER_COIN = new HalloCoinItem(new Item.Settings().group(null), "silver_coin", 9);
	public static final Item GOLD_COIN = new HalloCoinItem(new Item.Settings().group(null), "gold_coin", 81);
	public static final Item DIAMOND_COIN = new HalloCoinItem(new Item.Settings().group(null), "diamond_coin", 729);
	// Custom item group (seems appropriate)
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
		
		// Register HalloCoin
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "basic_coin"), BASIC_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "silver_coin"), SILVER_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "gold_coin"), GOLD_COIN);
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "diamond_coin"), DIAMOND_COIN);
		CommandRegistration.registerCommands();

	}
	//TODO: add GUI to display how much u have in balance

}
