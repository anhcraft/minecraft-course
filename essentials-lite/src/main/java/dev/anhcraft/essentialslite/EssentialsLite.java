package dev.anhcraft.essentialslite;

import dev.anhcraft.essentialslite.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsLite extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("tp").setExecutor(new TpCommand());
    }

    @Override
    public void onDisable() {

    }
}
