package com.logixmods.core.blocks;

import com.logixmods.api.blocks.base.RadioactiveBlock;
import com.logixmods.api.tileentities.RadioactiveTileEntity;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockUranium extends RadioactiveBlock<RadioactiveTileEntity>
{
    public BlockUranium()
    {
        super("block_uranium", Material.IRON, 5, 10, MapColor.IRON, SoundType.METAL, CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public Class<RadioactiveTileEntity> getTileEntityClass()
    {
        return RadioactiveTileEntity.class;
    }

    @Override
    public RadioactiveTileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state)
    {
        return new RadioactiveTileEntity();
    }
}