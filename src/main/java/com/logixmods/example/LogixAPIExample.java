package com.logixmods.example;

import com.logixmods.api.radiation.RadiationType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 The LogixAPIExample class serves to show other modders how to make use of the Logix API, so that they can extend the Logix mod set,
 adding their own radioactive blocks, smartphone apps, etc. Some code will be commented out, to prevent it from actually running,
 however it is all fully functional and tested code. Comments starting with "//!!" are functional code that is being disabled.
 Use this (and other classes within the "com.logixmods.example" package) as a reference, should you ever need help, before asking
 for help on forums.
 */
//!! @net.minecraftforge.fml.common.Mod(modid = "logix_api_example", name = "Logix API Example Mod", version = "0")
@SuppressWarnings("all")
public class LogixAPIExample
{
    // Declare a RadiationType as a field in your mod's main class and it will automatically be registered, provided you do NOT mark it as static.
    RadiationType myRadiationType = new RadiationType("logix_api_example", "example_radiationtype", "Example Radiation Type", 'e');

    //!! @net.minecraftforge.fml.common.Mod.EventHandler
    private void preInitialize(FMLPreInitializationEvent event)
    {

    }
}