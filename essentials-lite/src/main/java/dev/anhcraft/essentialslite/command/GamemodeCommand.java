package dev.anhcraft.essentialslite.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!sender.hasPermission("essentials.gamemode")) {
      sender.sendMessage(ChatColor.RED+"Không có quyền hạn!");
      return false;
    }
    Player target;
    if (args.length == 1) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED+"Cần phải ở trong game!");
        return false;
      }
      target = (Player) sender;
    } else if (args.length == 2) {
      target = Bukkit.getPlayer(args[1]);
      if (target == null) {
        sender.sendMessage(ChatColor.RED+args[1]+" không online");
        return false;
      }
    } else {
      sender.sendMessage(ChatColor.RED + "Sai lệnh: /gm <mode> [target]!");
      return false;
    }
    if (!args[0].equals("0") && !args[0].equals("1")) {
      sender.sendMessage(ChatColor.RED + "Sai giá trị: 0 = sinh tồn, 1 = sáng tạo");
      return false;
    }
    target.setGameMode(args[0].equals("0") ? GameMode.SURVIVAL : GameMode.CREATIVE);
    sender.sendMessage(ChatColor.GREEN+"Đã thay đổi sang chế độ "+(args[0].equals("0") ? "Sinh tồn" : "Sáng tạo")+" cho "+ChatColor.GOLD+target.getName());
    return true;
  }
}
