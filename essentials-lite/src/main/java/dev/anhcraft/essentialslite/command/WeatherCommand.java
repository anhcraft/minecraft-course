package dev.anhcraft.essentialslite.command;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WeatherCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!sender.hasPermission("essentials.weather")) {
      sender.sendMessage(ChatColor.RED+"Không có quyền hạn!");
      return false;
    }
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED+"Cần phải ở trong game!");
      return false;
    }
    if (args.length != 1 || (!args[0].equals("downfall") && !args[0].equals("clear"))) {
      sender.sendMessage(ChatColor.RED+"Sai lệnh: /weather <downfall/clear>!");
      return false;
    }
    WeatherType type = WeatherType.valueOf(args[0].toUpperCase());
    Player player = (Player) sender;
    player.getWorld().setStorm(type == WeatherType.DOWNFALL);
    player.getWorld().setThundering(type == WeatherType.DOWNFALL);
    sender.sendMessage(ChatColor.GREEN+"Đã thay đổi thời tiết");
    return true;
  }
}
