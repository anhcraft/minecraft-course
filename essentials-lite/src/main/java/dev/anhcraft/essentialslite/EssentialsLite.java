package dev.anhcraft.essentialslite;

import dev.anhcraft.essentialslite.command.GamemodeCommand;
import dev.anhcraft.essentialslite.command.HealCommand;
import dev.anhcraft.essentialslite.command.TimeCommand;
import dev.anhcraft.essentialslite.command.WeatherCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsLite extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
    }

    @Override
    public void onDisable() {

    }
}
