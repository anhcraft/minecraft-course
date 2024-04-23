package dev.anhcraft.essentialslite.command;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TimeCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!sender.hasPermission("essentials.time")) {
      sender.sendMessage(ChatColor.RED+"Không có quyền hạn!");
      return false;
    }
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED+"Cần phải ở trong game!");
      return false;
    }
    Player player = (Player) sender;
    if (args.length > 0) {
      if (args[0].equals("get")) {
        sender.sendMessage(ChatColor.GOLD+"Thời gian hiện tại là: "+player.getWorld().getTime());
        return true;
      } else if (args[0].equals("set")) {
        if (args.length == 2 && args[1].matches("\\d+")) {
          int time = Integer.parseInt(args[1]);
          player.getWorld().setTime(time);
          sender.sendMessage(ChatColor.GOLD+"Thay đổi thời gian thành công");
          return true;
        } else {
          sender.sendMessage(ChatColor.RED+"Sai lệnh: /time set <time>!");
          return false;
        }
      }
    }
    sender.sendMessage(ChatColor.RED+"Sai lệnh: /time <set/get>!");
    return false;
  }
}
