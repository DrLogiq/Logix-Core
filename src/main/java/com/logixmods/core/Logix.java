package com.logixmods.core;

import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

/** The primary source for all Logix mod references and some useful utilities */
public final class Logix
{
    /** Want to write something to the console? This will be the best way to do that! */
    public static final class Debug
    {
        private static boolean initialized = false;
        private static boolean announced = false;
        private static Logger  out         = null;

        /** Make sure the Debug class isn't a complete waste of space */
        public static void Initialize(Logger logger)
        {
            if (initialized) return;
            out = logger;
            initialized = true;
        }

        /** Make your prescence known! */
        public static void Announce(Object sender)
        {
            if (announced) return;
            switch ( ThreadLocalRandom.current().nextInt(0, 1+5) )
            {
                default: case 0:
                    Log(sender, "Fear not, Logix is here!");
                    break;
                case 1:
                    Log(sender, "Initiating doomsday sequence...");
                    break;
                case 2:
                    Log(sender, "What are you looking at?");
                    break;
                case 3:
                    Log(sender, "BAGEL. Hungry now?");
                    break;
                case 4:
                    Log(sender, "01000010 01110010 01100101 01100001 01100100.");
                    break;
                case 5:
                    Log(sender, "Hm, strange, you were a lot nicer-looking on your profile!");
                    break;
            }
            announced = true;
        }

        /** Get your message across */
        public static void Log(Object sender, String message)
        {
            if (!initialized) { System.err.println("Logix Logger not initialized yet!"); return; }
            out.info(CalculatePrefix(sender) + " // INFO -> " + message);
        }

        /** Get your message across */
        public static void Info(Object sender, String message)
        {
            Log(sender, message);
        }

        /** Warn others of impending doom */
        public static void Warning(Object sender, String message)
        {
            if (!initialized) { System.err.println("Logix Logger not initialized yet!"); return; }
            out.warn(CalculatePrefix(sender) + " // WARNING -> " + message);
        }

        /** *Sigh* ... if you must! */
        public static void Error(Object sender, String message)
        {
            if (!initialized) { System.err.println("Logix Logger not initialized yet!"); return; }
            out.error(CalculatePrefix(sender) + " // ERROR -> " + message);
        }

        /** Make sure it's obvious who's sending the message */
        private static String CalculatePrefix(Object sender)
        {
            String pkg = sender.getClass().getPackage().toString();
            if (pkg.contains("logixmods.core"))
                return Core.NAME;
            else
                return "Unknown Mod";
        }
    }

    /** All things relating to the core mod */
    public static final class Core
    {
        public static final String ID = "logix_core";
        public static final String NAME = "Logix Core";
        public static final String VERSION = "0.1a";
        public static final String CLIENT_PROXY = "com.logixmods.core.proxy.ClientProxy";
        public static final String SERVER_PROXY = "com.logixmods.core.proxy.ServerProxy";
    }

    /** A list of the available modules, which are part of the mod set */
    public enum Module
    {
        /** The core mod - a staple requirement for the other mods in the set, and a requirement for any mods that wish to make use of its API */
        Core
    }
}