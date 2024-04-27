package dev.anhcraft.essentialslite.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class TpCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!sender.hasPermission("essentials.tp")) {
      sender.sendMessage(ChatColor.RED + "Không có quyền hạn!");
      return false;
    }
    if (args.length < 4) {
      sender.sendMessage(ChatColor.RED + "/tp <player> <x> <y> <z>");
      return false;
    }
    Player player = Bukkit.getPlayer(args[0]);
    if (player == null) {
      sender.sendMessage(ChatColor.RED+"<player> không tồn tại hoặc chưa online");
      return false;
    }
    double x, y, z;
    try {
      x = Double.parseDouble(args[1]);
    } catch (NumberFormatException e) {
      sender.sendMessage(ChatColor.RED + "<x> phải là số thực");
      return false;
    }
    try {
      y = Double.parseDouble(args[2]);
    } catch (NumberFormatException e) {
      sender.sendMessage(ChatColor.RED + "<y> phải là số thực");
      return false;
    }
    try {
      z = Double.parseDouble(args[3]);
    } catch (NumberFormatException e) {
      sender.sendMessage(ChatColor.RED + "<z> phải là số thực");
      return false;
    }
    World world = args.length == 4 ? player.getWorld() : Bukkit.getWorld(args[4]);
    if (world == null) {
      sender.sendMessage(ChatColor.RED+"<world> không tồn tại");
      return false;
    }
    Location loc = new Location(world, x, y, z);
    player.teleportAsync(loc).whenComplete(new BiConsumer<Boolean, Throwable>() {
      @Override
      public void accept(Boolean aBoolean, Throwable throwable) {
        sender.sendMessage(ChatColor.GREEN + "Đã dịch chuyển thành công");
      }
    });
    return false;
  }
}
