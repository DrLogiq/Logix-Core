package com.logixmods.api.blocks.base;

import com.logixmods.core.blocks.base.Block;
import com.logixmods.api.tileentities.RadioactiveTileEntity;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class RadioactiveBlock<TE extends RadioactiveTileEntity> extends Block
{
    public RadioactiveBlock(String name, Material material, float hardness, float resistance, MapColor mapColour, SoundType sound, CreativeTabs tab)
    {
        super(name, material, hardness, resistance, mapColour, sound, tab);
    }

    /** Use this to specify the TileEntity class for this block, for example, {@code return MyRadioactiveTileEntity.class;} */
    public abstract Class<TE> getTileEntityClass();

    /** Called whenever the game needs to create a TileEntity for this block. You should use this to return a new instance of the TileEntity class for this block,
     for example, {@code return new MyRadioactiveTileEntity();}*/
    @Override
    public abstract TE createTileEntity(@Nonnull World world, @Nonnull IBlockState state);

    public TE getTileEntity(IBlockAccess world, BlockPos pos)
    {
        return (TE)world.getTileEntity(pos);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
}