package dev.anhcraft.baitap2sukien;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Baitap2sukien extends JavaPlugin implements Listener {
    private Map<UUID, Set<UUID>> owners = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;
        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL) {
            if (owners.containsKey(event.getRightClicked().getUniqueId())) {
                owners.get(event.getRightClicked().getUniqueId())
                  .remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage("Đã xoá ấn ký");
            }
            return;
        }
        owners.computeIfAbsent(event.getRightClicked().getUniqueId(), (v) -> new HashSet<>())
          .add(event.getPlayer().getUniqueId());
        event.getPlayer().sendMessage("Đã lưu lại ấn ký");
    }

    @EventHandler
    private void onEntityRemoved(EntityRemoveFromWorldEvent event) {
        Set<UUID> ownerSet = owners.get(event.getEntity().getUniqueId());
        if (ownerSet == null)
            return;
        Location loc = event.getEntity().getLocation();
        String msg = "Thực thể vừa biến mất tại " + loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " +loc.getBlockZ();
        for (UUID uuid : ownerSet) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;
            player.sendMessage(msg);
        }
    }
}
