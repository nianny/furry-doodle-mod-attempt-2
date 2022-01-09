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
	public static Item BASIC_COIN; // we're literally trading one error for another
	
	// Custom item group (seems appropriate)
	// translation key: "itemGroup.hallocoins.general"
	// P.S. this SO post is as old as Minecraft
	// https://stackoverflow.com/questions/1746758/illegal-forward-reference-in-java
	public static ItemGroup HALLOCOIN_GROUP = FabricItemGroupBuilder.create(new Identifier("hallocoins", "general"))
		.icon(() -> new ItemStack(BASIC_COIN))
		.appendItems((stacks, itemGroup) -> {
			for ( Item item : Registry.ITEM ) { 
				// For each item in registry,
				// if the item's group is 
				if ( item.getGroup() == itemGroup ) stacks.add( new ItemGroup(item) );
			}
			// I apologise for the style
		}) 
		.build();
	
	

	
	@Override
	public void onInitialize() {
		LOGGER.info("HalloCoin is initialising! Have fun!");
		
		// Register HalloCoin
		// See
		// https://github.com/FabricMC/fabric/blob/1.18/fabric-item-groups-v0/src/testmod/java/net/fabricmc/fabric/test/item/group/ItemGroupTest.java
		// for why i did this
		BASIC_COIN = Registry.register(Registry.ITEM, new Identifier("hallocoins", "basic_coin"), new Item(new Item.Settings().group(HALLOCOIN_GROUP)));

	}
}
