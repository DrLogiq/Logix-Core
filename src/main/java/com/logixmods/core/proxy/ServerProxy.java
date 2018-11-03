package com.logixmods.core.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import static com.logixmods.core.Logix.Debug;

public final class ServerProxy implements IProxy
{
    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        Debug.Info(this, "Server Pre-Initialization");
    }

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        Debug.Info(this, "Server Initialization");
    }

    @Override
    public void postInitialize(FMLPostInitializationEvent event)
    {
        Debug.Info(this, "Server Post-Initialization");
    }
}