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
import org.lwjgl.system.CallbackI;

public class HalloCoins implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("hallocoins");

	// DUMMY ITEMS
	private static final Item DUMMY_COIN = new Item(new Item.Settings());


	// Custom item group (seems appropriate)
	// translation key: "itemGroup.hallocoins.general"
	// P.S. this SO post is as old as Minecraft
	// https://stackoverflow.com/questions/1746758/illegal-forward-reference-in-java
	public static ItemGroup HALLOCOIN_GROUP = FabricItemGroupBuilder.build(
			new Identifier("hallocoins", "general"),
			() -> new ItemStack(DUMMY_COIN)
	);

	// Create item objects
	public static Item BASIC_COIN = new Item(new Item.Settings().group(HALLOCOIN_GROUP));

	
	@Override
	public void onInitialize() {
		LOGGER.info("HalloCoin is initialising! Have fun!");

		// Register HalloCoin
		BASIC_COIN = Registry.register(Registry.ITEM, new Identifier("hallocoins", "basic_coin"), BASIC_COIN);

	}
}
