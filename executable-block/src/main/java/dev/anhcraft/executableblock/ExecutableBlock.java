package dev.anhcraft.executableblock;

import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExecutableBlock extends JavaPlugin {
    private Map<Block, List<String>> commandOfBlocks = new HashMap<>();
    private EventListener eventListener;
    private static ExecutableBlock instance;

    public void addCommand(Block block, String cmd) {
        commandOfBlocks.computeIfAbsent(block, (v) -> new ArrayList<>()).add(cmd);
    }

    public void removeCommands(Block block) {
        commandOfBlocks.remove(block);
    }

    public List<String> getCommands(Block block) {
        return commandOfBlocks.get(block);
    }

    public EventListener getEventListener() { // getter
        return eventListener;
    }

    public static ExecutableBlock getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getCommand("eb").setExecutor(new CommandHandler(this));

        eventListener = new EventListener(this);
        getServer().getPluginManager().registerEvents(eventListener, this);
    }

    @Override
    public void onDisable() {

    }
}
