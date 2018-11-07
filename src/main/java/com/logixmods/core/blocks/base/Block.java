package com.logixmods.core.blocks.base;

import com.logixmods.core.Logix;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

public class Block extends net.minecraft.block.Block
{
    public Block(String name, Material material, float hardness, float resistance, MapColor mapColour, SoundType sound, CreativeTabs tab)
    {
        super(material, mapColour);
        setRegistryName(Logix.Core.ID + ":" + name);
        setUnlocalizedName(name);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(sound);
        setCreativeTab(tab);
    }

    public void registerBlock(final IForgeRegistry<net.minecraft.block.Block> registry)
    {
        registry.register(this);
        Logix.Debug.InfoIndented(this, "Registered block with registry name: \"" + getRegistryName() + "\", and unlocalized name: \"" + getUnlocalizedName() + "\"");
    }

    public void registerItemBlock(final IForgeRegistry<Item> registry)
    {
        registry.register(createItemBlock());
    }

    @SideOnly(Side.CLIENT)
    public void registerRenderer()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public Item createItemBlock()
    {
        return new ItemBlock(this).setRegistryName(Objects.requireNonNull(getRegistryName()));
    }
}