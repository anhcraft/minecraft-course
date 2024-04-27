package dev.anhcraft.essentialslite.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (args.length > 0 && (args[0].equals("walk") || args[0].equals("fly"))) {
      if (!sender.hasPermission("essentials.speed."+args[0])) {
        sender.sendMessage(ChatColor.RED+"Không có quyền hạn!");
        return false;
      }
      if (args.length == 1) {
        sender.sendMessage(ChatColor.RED+"Cần nhập <value>");
        return false;
      }
      float val;
      try {
        val = Float.parseFloat(args[1]);
      } catch (NumberFormatException e) {
        sender.sendMessage(ChatColor.RED+"<value> phải là số thực");
        return false;
      }
      if (val < -1 || val > 1) {
        sender.sendMessage(ChatColor.RED+"<value> phải từ -1.0 cho đến 1.0");
        return false;
      }
      if (!(sender instanceof Player) && args.length == 2) {
        sender.sendMessage(ChatColor.RED+"Cần nhập [target]");
        return false;
      }
      Player target = args.length == 2 ? null : Bukkit.getPlayer(args[2]);
      if (sender instanceof Player && target == null) {
        if (args.length > 2) {
          sender.sendMessage(ChatColor.RED+"[target] không tồn tại hoặc không online");
          return false;
        }
        target = (Player) sender;
      }
      if (target == null) {
        sender.sendMessage(ChatColor.RED+"[target] không tồn tại hoặc không online");
        return false;
      }
      if (args[0].equals("walk"))
        target.setWalkSpeed(val);
      else
        target.setFlySpeed(val);
      if (sender != target)
        sender.sendMessage(ChatColor.GREEN+"Thành công! Tốc độ của "+target.getName()+" là "+val);
      target.sendMessage(ChatColor.GREEN+"Tốc độ của bạn là "+val);
      return true;
    }
    sender.sendMessage(ChatColor.GOLD+"/speed walk <value> [target]: thay đổi tốc độ đi");
    sender.sendMessage(ChatColor.GOLD+"/speed fly <value> [target]: thay đổi tốc độ bay");
    return false;
  }
}
