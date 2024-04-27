package dev.anhcraft.executableblock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements CommandExecutor {
  private ExecutableBlock plugin;

  public CommandHandler(ExecutableBlock plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED+"không phải người chơi");
      return false;
    }
    Player player = (Player) sender;
    if (args.length > 0) {
      if (args[0].equals("add")) {
        if (args.length == 1) {
          sender.sendMessage(ChatColor.RED+"<command> cần phải ghi ít nhất 1 từ");
          return false;
        }
        List<String> params = new ArrayList<>(Arrays.asList(args));
        params.remove(0);
        String cmd = String.join(" ", params);
        plugin.getEventListener().waitForClickingBlock(player, block -> {
          plugin.addCommand(block, cmd);
          sender.sendMessage(ChatColor.GREEN+"Đã thêm câu lệnh: "+cmd);
        });
        sender.sendMessage(ChatColor.GOLD+"hãy chạm vào block mà bạn muốn thêm câu lệnh");
        return true;
      }
      if (args[0].equals("remove")) {
        plugin.getEventListener().waitForClickingBlock(player, block -> {
          plugin.removeCommands(block);
          sender.sendMessage(ChatColor.GREEN+"Đã xoá câu lệnh");
        });
        sender.sendMessage(ChatColor.GOLD+"hãy chạm vào block mà bạn muốn xoá câu lệnh");
        return true;
      }
    }
    sender.sendMessage(ChatColor.RED+"/eb add <command>: thêm câu lệnh, rồi chọn block");
    sender.sendMessage(ChatColor.RED+"/eb remove: xoá toàn bộ câu lệnh của block");
    return false;
  }
}
