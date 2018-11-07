package com.logixmods.api.radiation;

import com.logixmods.core.Logix;
import com.logixmods.core.LogixCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RadiationType
{
    private static List<RadiationType> typesToRegister;
    private static List<RadiationType> registeredTypes = new ArrayList<>();
    private static boolean             initialized = false;

    /** Returns an unmodifiable version of the RadiationType registry (the list of all types that have been registered) */
    public static List<RadiationType> getRegisteredTypes()
    {
        return Collections.unmodifiableList(registeredTypes);
    }

    /** Initializes the registry and registers default types, then external types (if any are specified).
     <br/><font color="orange"><strong>API Users: Do not use this!</strong></font>
     */
    public static void initialize()
    {
        if (!initialized)
        {
            Logix.Debug.Info(LogixCore.getInstance(), "Initializing RadiationType registry");
            registeredTypes = new ArrayList<>();
            ALPHA.register();
            BETA.register();
            GAMMA.register();
            XRAY.register();
            NEUTRON.register();
            if (typesToRegister != null && typesToRegister.size() > 0)
            {
                for (RadiationType type : typesToRegister)
                {
                    type.register();
                }
                typesToRegister.clear();
            }
            initialized = true;
        }
    }

    public static final RadiationType ALPHA = new RadiationType("alpha", "Alpha", 'α');
    public static final RadiationType BETA = new RadiationType("beta", "Beta", 'β');
    public static final RadiationType GAMMA = new RadiationType("gamma", "Gamma", 'γ');
    public static final RadiationType XRAY = new RadiationType("xray", "X-Ray", '☢');
    public static final RadiationType NEUTRON = new RadiationType("neutron", "Neutron", '☢');

    private final boolean isOfficial;
    private final String modID;
    private final String codeName;
    private final String displayName;
    private final char symbol;

    private RadiationType(String codeName, String displayName, char symbol)
    {
        this.modID = Logix.Core.ID;
        this.codeName = codeName;
        this.displayName = displayName;
        this.symbol = symbol;
        this.isOfficial = true;
    }

    /**
     Specify a RadiationType to be registered later
     @param modID Declare your mod ID here, so that it is easy to identify your types from others
     @param codeName A unique name consisting of only letters (can contain underscores), which must be 3+ characters long, used for identification
     @param displayName The name of this type, as you would like it to be seen on-screen (if it is ever displayed)
     @param symbol The ASCII symbol of this type, as you would like it to be seen on-screen (if it is ever displayed)
     */
    public RadiationType(String modID, String codeName, String displayName, char symbol)
    {
        this.modID = modID;
        this.codeName = codeName;
        this.displayName = displayName;
        this.symbol = symbol;
        this.isOfficial = false;
        if (typesToRegister == null)
        {
            typesToRegister = new ArrayList<>();
            Logix.Debug.Warning(this, "Init list");
        }
        typesToRegister.add(this);
    }

    /** Performs validity checks, then if the RadiationType is valid, registers it with Logix Core. */
    private void register()
    {
        if (!isOfficial)
        {
            // Ensure Forge is currently in the initialization phase
            if ( Logix.getPhase() != Logix.Phase.Initialization )
            {
                Logix.Debug.Error(this, "Cannot register RadiationType \"" + codeName + " (" + displayName + ", " + symbol +
                        ") because Forge is not currently in the FMLInitialization phase. Please register your types during FMLInitialization. " +
                        "Skipping registration for this RadiationType. " + Logix.getReportMessage(modID));
                return;
            }

            // Ensure the type isn't already registered
            if ( registeredTypes.contains(this) )
            {
                Logix.Debug.Error(this, "Cannot register RadiationType \"" + codeName + " (" + displayName + ", " + symbol +
                        ") because it appears to already be registered. Skipping registration for this RadiationType. " + Logix.getReportMessage(modID));
                return;
            }

            // Ensure the mod ID is specified and valid
            if ( modID.length() < 1 )
            {
                Logix.Debug.Error(this, "Cannot register RadiationType \"" + codeName + " (" + displayName + ", " + symbol +
                        ") because the mod ID was not specified. Skipping registration for this RadiationType. " + Logix.getReportMessage(modID));
                return;
            }
            else
            {
                if ( !isOfficial && (Logix.isLogixID(modID) || Logix.isImmitatingLogixID(modID)) )
                {
                    Logix.Debug.Error(this, "Cannot register RadiationType \"" + codeName + " (" + displayName + ", " + symbol +
                            ") because the mod ID was specified as a Logix mod ID, however the RadiationType was being added by an " +
                            "external source, possibly a mod pretending to be a Logix mod. Skipping registration for this RadiationType. " + Logix.getReportMessage(modID));
                    return;
                }
            }

            // Ensure the code-name is unique
            if ( registeredTypes.size() > 0 )
            {
                for ( RadiationType type : registeredTypes )
                {
                    if ( type.codeName.equalsIgnoreCase(this.codeName) )
                    {
                        Logix.Debug.Error(this, "Cannot register RadiationType \"" + codeName + " (" + displayName + ", " + symbol +
                                ") because its code name appears to already be taken. Skipping registration for this RadiationType. " + Logix.getReportMessage(modID));
                        return;
                    }
                }
            }
        } // endof: if !isOfficial

        registeredTypes.add(this);
        if (!isOfficial)
            Logix.Debug.InfoIndented(this, "Registered unofficial RadiationType: code-name = \"" + codeName + "\", display name = \""
                    + displayName + "\", symbol = \"" + symbol + "\". Added by the mod: " + modID);
        else
            Logix.Debug.InfoIndented(this, "Registered official RadiationType: code-name = \"" + codeName + "\", display name = \""
                    + displayName + "\", symbol = \"" + symbol + "\"");
    }

    /** Represents the ID of the mod which created this type. This is specified by the mod developer, so beware, it could be inaccurate. */
    public String getModID()
    {
        return modID;
    }

    /** Represents the unique name of this type, used for identification and serialization purposes. */
    public String getCodeName()
    {
        return codeName;
    }

    /** Represents the display name of this type, which is how the developer wants it to be shown on-screen. */
    public String getDisplayName()
    {
        return displayName;
    }

    /** Represents the ASCII symbol of this type, which is how the developer wants it to be shown on-screen. */
    public char getSymbol()
    {
        return symbol;
    }

    /** Represents whether or not this type is added by Logix, or an external mod developer. */
    public boolean isOfficial()
    {
        // TODO: Find a way to make this true for all mods in the Logix set, without allowing other developers to cheat the system
        return isOfficial;
    }
}