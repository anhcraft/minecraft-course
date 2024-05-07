package dev.anhcraft.economy;

import dev.jorel.commandapi.*;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Economy extends JavaPlugin {
    private static final Set<String> WALLETS = Set.of("Bank", "MoMo", "Zalo");
    private Map<String, Map<String, Double>> balance = new HashMap<>();
    private Map<String, List<String>> transactions = new HashMap<>();

    @Override
    public void onEnable() {
        var getBalance = new CommandExecutor() {
            @Override
            public void run(CommandSender sender, CommandArguments args) throws WrapperCommandSyntaxException {
                String player = (String) args.get("player");
                if (player == null) {
                    if (sender instanceof Player) player = sender.getName();
                    else {
                        sender.sendMessage("Cần nhập <player>");
                        return;
                    }
                }
                if (sender instanceof Player && !sender.getName().equals(player) &&
                  !sender.hasPermission("money.get."+player)) {
                    sender.sendMessage("Cần quyền hạn money.get."+player);
                    return;
                }
                sender.sendMessage("Số tiền đang có:");
                if (balance.containsKey(player)) {
                    for (Map.Entry<String, Double> ent : balance.get(player).entrySet()) {
                        if (!sender.hasPermission("money.wallet."+ent.getKey())) continue;
                        sender.sendMessage(String.format("> %s: %.2f", ent.getKey(), ent.getValue()));
                    }
                }
                sender.sendMessage("Danh sách giao dịch:");
                if (transactions.containsKey(player)) {
                    for (String string : transactions.get(player)) {
                        sender.sendMessage("> " + string);
                    }
                }
            }
        };

        var addBalance = new Function<Boolean, CommandExecutor>() {
            @Override
            public CommandExecutor apply(Boolean aBoolean) {
                return (sender, args) -> {
                    String player = (String) args.get("player");
                    String wallet = (String) args.getOrDefault("wallet", "Bank");
                    if (!sender.hasPermission("money.wallet."+wallet)) {
                        sender.sendMessage("Không có quyền money.wallet."+wallet);
                        return;
                    }
                    double amount = (Double) args.getOrDefault("amount", 0d);
                    amount *= aBoolean ? 1 : -1;
                    double min = (Double) args.getOrDefault("min", 0d);
                    String note = (String) args.getOrDefault("note", "<Không có>");
                    boolean debt = (Boolean) args.getOrDefault("debt", false);
                    if (balance.containsKey(player))
                        amount += balance.get(player).getOrDefault(wallet, 0d);
                    if (!debt && amount < min) {
                        sender.sendMessage(String.format("Số tiền thấp nhất có thể sở hữu là %.2f (số tiền mới: %.2f)", min, amount));
                        return;
                    }
                    amount = Math.max(amount, min);
                    balance.computeIfAbsent(player, (v) -> new HashMap<>()).put(wallet, amount);
                    sender.sendMessage(String.format("Tiền của %s trong ví %s là %.2f", player, wallet, amount));
                    transactions.computeIfAbsent(player, (v) -> new ArrayList<>()).add(String.format("[%s] %s: %.2f", wallet, note, amount));
                };
            }
        };

        var playerSuggestions = ArgumentSuggestions.stringsAsync(new Function<SuggestionInfo<CommandSender>, CompletableFuture<String[]>>() {
            @Override
            public CompletableFuture<String[]> apply(SuggestionInfo<CommandSender> suggestionInfo) {
                return CompletableFuture.supplyAsync(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new));
            }
        });

        new CommandAPICommand("balance")
          .withArguments(
            new StringArgument("player").replaceSuggestions(playerSuggestions)
          )
          .executes(getBalance).register();

        new CommandAPICommand("money")
          .withAliases("eco", "bank")
          .withSubcommands(
            new CommandAPICommand("set")
              .withPermission("money.set")
              .withArguments(
                new StringArgument("player").replaceSuggestions(playerSuggestions),
                new DoubleArgument("amount"),
                new MultiLiteralArgument("wallet", WALLETS.toArray(String[]::new)).setOptional(true),
                new DoubleArgument("min").setOptional(true).combineWith(
                  new BooleanArgument("debt")
                )
              )
              .executes(new CommandExecutor() {
                  @Override
                  public void run(CommandSender sender, CommandArguments args) throws WrapperCommandSyntaxException {
                      String player = (String) args.get("player");
                      String wallet = (String) args.getOrDefault("wallet", "Bank");
                      if (!sender.hasPermission("money.wallet."+wallet)) {
                          sender.sendMessage("Không có quyền money.wallet."+wallet);
                          return;
                      }
                      double amount = (Double) args.getOrDefault("amount", 0d);
                      double min = (Double) args.getOrDefault("min", 0d);
                      boolean debt = (Boolean) args.getOrDefault("debt", false);
                      if (!debt && amount < min) {
                          sender.sendMessage(String.format("Số tiền thấp nhất có thể sở hữu là %.2f (đang nhập: %.2f)", min, amount));
                          return;
                      }
                      amount = Math.max(amount, min);
                      balance.computeIfAbsent(player, (v) -> new HashMap<>()).put(wallet, amount);
                      sender.sendMessage(String.format("Tiền của %s trong ví %s là %.2f", player, wallet, amount));
                  }
              }),

            new CommandAPICommand("add").withPermission("money.add")
              .withAliases("deposit")
              .withArguments(
                new StringArgument("player").replaceSuggestions(playerSuggestions),
                new DoubleArgument("amount").replaceSafeSuggestions(SafeSuggestions.suggest(
                  10_000d,
                  20_000d,
                  50_000d,
                  100_000d
                )),
                new MultiLiteralArgument("wallet", WALLETS.toArray(String[]::new)).setOptional(true),
                new DoubleArgument("min").setOptional(true).combineWith(
                  new BooleanArgument("debt"),
                  new GreedyStringArgument("note")
                )
              )
              .executes(addBalance.apply(true)),

            new CommandAPICommand("withdraw").withPermission("money.withdraw")
              .withArguments(
                new StringArgument("player").replaceSuggestions(playerSuggestions),
                new DoubleArgument("amount").replaceSafeSuggestions(SafeSuggestions.suggest(
                  10_000d,
                  20_000d,
                  50_000d,
                  100_000d
                )),
                new MultiLiteralArgument("wallet", WALLETS.toArray(String[]::new)).setOptional(true),
                new DoubleArgument("min").setOptional(true).combineWith(
                  new BooleanArgument("debt"),
                  new GreedyStringArgument("note")
                )
              )
              .executes(addBalance.apply(false)),

            new CommandAPICommand("get").withPermission("money.get")
              .withArguments(
                new StringArgument("player").replaceSuggestions(playerSuggestions)
              )
              .executes(getBalance)
          )
          .register();
    }

    @Override
    public void onDisable() {

    }
}
