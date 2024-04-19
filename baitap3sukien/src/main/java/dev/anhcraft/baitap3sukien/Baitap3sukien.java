package dev.anhcraft.baitap3sukien;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Baitap3sukien extends JavaPlugin implements Listener {
    private Map<UUID, Long> quitTime = new HashMap<>();
    private Map<UUID, Long> cooldown = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
/*
    @EventHandler
    private void onLogin(PlayerLoginEvent event) {
        Long time = quitTime.remove(event.getPlayer().getUniqueId());
        if (time == null || System.currentTimeMillis() - time > 3000) return;
        event.getPlayer().kick();
    }*/

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        long currentCooldown = cooldown.getOrDefault(id, 3000L);
        Long time = quitTime.remove(id);
        if (time == null || System.currentTimeMillis() - time > currentCooldown) return;
        event.getPlayer().kickPlayer("Phát hiện join bot, cần chờ " + (currentCooldown / 1000) + " giây");
        cooldown.put(id, currentCooldown + 2000);
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        quitTime.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }
}
