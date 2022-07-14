package moe.lumii.spigot.necessities;

import moe.lumii.spigot.necessities.commands.CmdGameMode;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Commands(
        @Command(name = "gamemode", aliases = "gm")
)
@Plugin(name = "Necessities", version = "1.0.0-release.1")
@ApiVersion(ApiVersion.Target.v1_13)
@LogPrefix("Necessities")
@LoadOrder(PluginLoadOrder.STARTUP)
@Author("lumii")
@Description("An advanced alternative to Essentials(X)")
@Website("https://lumii.moe/")
public final class Necessities extends JavaPlugin {
    public static Necessities getInstance() {
        return getPlugin(Necessities.class);
    }

    @Override
    public void onEnable() {
        this.getCommand("gamemode").setExecutor(new CmdGameMode());
    }

    @Override
    public void onDisable() {
    }
}
