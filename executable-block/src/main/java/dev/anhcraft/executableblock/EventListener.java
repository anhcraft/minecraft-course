package dev.anhcraft.executableblock;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventListener implements Listener {
  private ExecutableBlock plugin;
  private Map<Player, List<Consumer<Block>>> waiting = new HashMap<>();

  public EventListener(ExecutableBlock plugin) {
    this.plugin = plugin;
  }

  public void waitForClickingBlock(Player player, Consumer<Block> callback) {
    waiting.computeIfAbsent(player, (v) -> new ArrayList<>()).add(callback);
  }

  @EventHandler
  private void onInteract(PlayerInteractEvent event) {
    if (event.hasBlock() && event.getHand() == EquipmentSlot.HAND) {
      List<Consumer<Block>> callbacks = waiting.get(event.getPlayer());
      if (callbacks == null || callbacks.isEmpty()) {
        List<String> cmd = plugin.getCommands(event.getClickedBlock());
        if (cmd != null) {
          for (String s : cmd) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
          }
        }
        return;
      }
      callbacks.remove(0).accept(event.getClickedBlock());
    }
  }

  @EventHandler
  private void onQuit(PlayerQuitEvent event) {
    waiting.remove(event.getPlayer());
  }
}
