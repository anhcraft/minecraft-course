package dev.anhcraft.baitap1sukien;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Baitap1sukien extends JavaPlugin implements Listener {
    private Map<UUID, Integer> counter = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setFoodLevel(1);
    }

    @EventHandler
    private void onPlayerConsume(PlayerItemConsumeEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        int count = counter.getOrDefault(id, 0);
        if (event.getItem().getType() == Material.APPLE && count < 3) {
            count++;
            counter.put(id, count);
            if (count == 3)
                event.getPlayer().sendMessage("Đã tiến vào trạng thái công kích");
        } else {
            counter.remove(id);
        }
    }

    @EventHandler
    private void onEntityDamage(EntityDamageByEntityEvent event) {
        UUID id = event.getDamager().getUniqueId();
        int count = counter.getOrDefault(id, 0);
        if (count == 3) {
            event.setDamage(event.getDamage() * 2);
            event.getDamager().sendMessage("Kĩ năng công kích: x2 sát thương");
        }
    }
}
