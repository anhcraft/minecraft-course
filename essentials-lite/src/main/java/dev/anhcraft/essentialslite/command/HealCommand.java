package dev.anhcraft.essentialslite.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!sender.hasPermission("essentials.heal")) {
      sender.sendMessage(ChatColor.RED+"Không có quyền hạn!");
      return false;
    }
    Player target;
    if (args.length == 0) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED+"Cần phải ở trong game!");
        return false;
      }
      target = (Player) sender;
    } else {
      target = Bukkit.getPlayer(args[0]);
      if (target == null) {
        sender.sendMessage(ChatColor.RED+args[0]+" không online");
        return false;
      }
    }
    target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    sender.sendMessage(ChatColor.GREEN+"Đã hồi máu cho "+target.getName());
    return true;
  }
}
