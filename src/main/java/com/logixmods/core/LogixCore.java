package com.logixmods.core;

import com.logixmods.core.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import static com.logixmods.core.Logix.Core.*;
import static com.logixmods.core.Logix.Debug;

/** The core mod's main class - it all begins here! */
@Mod(modid = ID, name = NAME, version = VERSION)
public final class LogixCore
{
    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    private static IProxy proxy;

    @Mod.EventHandler
    private void preInitialize(FMLPreInitializationEvent event)
    {
        Debug.Initialize(event.getModLog());
        Debug.Announce(this);

        proxy.preInitialize(event);
    }

    @Mod.EventHandler
    private void initialize(FMLInitializationEvent event)
    {
        proxy.initialize(event);
    }

    @Mod.EventHandler
    private void postInitialize(FMLPostInitializationEvent event)
    {
        proxy.postInitialize(event);
    }
}