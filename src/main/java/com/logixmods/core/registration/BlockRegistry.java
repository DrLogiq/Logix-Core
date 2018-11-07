package com.logixmods.core.registration;

import com.logixmods.core.Logix;
import com.logixmods.core.blocks.BlockUranium;
import com.logixmods.core.blocks.base.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Logix.Core.ID)
@ObjectHolder(Logix.Core.ID)
public final class BlockRegistry
{
    @ObjectHolder("block_uranium")
    public static final Block URANIUM = new BlockUranium();

    private static final Block[] blocks =
    {
        URANIUM
    };

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<net.minecraft.block.Block> event)
    {
        Logix.Debug.Info(new BlockRegistry(), "Registering blocks");
        final IForgeRegistry<net.minecraft.block.Block> registry = event.getRegistry();
        for (Block block : blocks)
            block.registerBlock(registry);

        TileEntityRegistry.registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        for (Block block : blocks)
            block.registerItemBlock(registry);
    }

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event)
    {
        for (Block block : blocks)
            block.registerRenderer();
    }
}