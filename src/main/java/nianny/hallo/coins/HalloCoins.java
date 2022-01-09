package nianny.hallo.coins;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HalloCoins implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("hallocoins");
	
	// Custom item group (seems appropriate)
	public static final ItemGroup HALLOCOIN_GROUP = FabricItemGroup.create(
		new Identifier("hallocoins", "general")) // translation key for group name (full translation key: itemGroup.hallocoins.general)
		.icon(() -> new ItemStack(BASIC_COIN)) // FIXME: may break
		.appendItens(stacks -> { stacks.add(BASIC_COIN); }) // FIXME: ^ see above comment
		.build();
	
	// Create item objects
	public static final Item BASIC_COIN = new Item(new Item.Settings().group(HALLOCOIN_GROUP);
	
	

	
	@Override
	public void onInitialize() {
		LOGGER.info("HalloCoin is initialising!");
		
		
		// Register HalloCoin
		Registry.register(Registry.ITEM, new Identifier("hallocoins", "basic_coin"), BASIC_COIN);


	}
}
