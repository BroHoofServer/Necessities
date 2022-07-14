package moe.lumii.spigot.necessities.commands;

import moe.lumii.spigot.necessities.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CmdGameMode implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command,
                             final @NotNull String label, final @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            if (args.length != 2) {
                System.out.println("Usage: /gamemode <0-3> <name>");
                return false;
            }
            final Player target = Bukkit.getPlayer(args[1]);
            try {
                setGameMode(GameMode.getByValue(Integer.parseInt(args[0])), target, target);
            } catch (final Throwable ignored) {
            }
            return false;
        }

        final Player player = (Player) sender;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("survival")) {
                this.setGameMode(GameMode.SURVIVAL, player, player);
                return false;
            }
            if (args[0].equalsIgnoreCase("creative")) {
                this.setGameMode(GameMode.CREATIVE, player, player);
                return false;
            }
            if (args[0].equalsIgnoreCase("adventure")) {
                this.setGameMode(GameMode.ADVENTURE, player, player);
                return false;
            }
            if (args[0].equalsIgnoreCase("spectator")) {
                this.setGameMode(GameMode.SPECTATOR, player, player);
                return false;
            }
            try {
                this.setGameMode(GameMode.getByValue(Integer.parseInt(args[0])), player, player);
                return false;
            } catch (final NumberFormatException ignored) {
                PlayerUtils.sendMessage(
                        player, true,
                        String.format(
                                "%sUsage: /gamemode <0-3> <name>",
                                ChatColor.RED
                        )
                );
                return false;
            }
        }
        if (args.length == 2) {
            final Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                PlayerUtils.sendMessage(
                        player, true,
                        String.format(
                                "%sUnknown player '%s', please insert a valid username!",
                                ChatColor.RED,
                                args[1]
                        )
                );
                return false;
            }
            if (args[0].equalsIgnoreCase("survival")) {
                this.setGameMode(GameMode.SURVIVAL, player, target);
                return false;
            }
            if (args[0].equalsIgnoreCase("creative")) {
                this.setGameMode(GameMode.CREATIVE, player, target);
                return false;
            }
            if (args[0].equalsIgnoreCase("adventure")) {
                this.setGameMode(GameMode.ADVENTURE, player, target);
                return false;
            }
            if (args[0].equalsIgnoreCase("spectator")) {
                this.setGameMode(GameMode.SPECTATOR, player, target);
                return false;
            }
            try {
                this.setGameMode(GameMode.getByValue(Integer.parseInt(args[0])), player, target);
                return false;
            } catch (final NumberFormatException ignored) {
                PlayerUtils.sendMessage(
                        player, true,
                        String.format(
                                "%sUsage: /gamemode <0-3> <name>",
                                ChatColor.RED
                        )
                );
                return false;
            }
        }
        return false;
    }

    @Override
    public @NotNull List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command command,
                                               final @NotNull String label, final @NotNull String[] args) {
        if (args.length == 1)
            return Arrays.asList("0", "survival", "1", "creative", "2", "adventure", "3", "spectator");
        else if (args.length == 2) {
            final List<String> names = new ArrayList<>();
            Bukkit.getServer().getOnlinePlayers().forEach(player -> names.add(player.getName()));
            return names;
        } else return Collections.emptyList();
    }

    private void setGameMode(final GameMode mode, final Player sender, final Player target) {
        target.setGameMode(mode);
        final boolean survival = mode == GameMode.SURVIVAL;
        final boolean creative = mode == GameMode.CREATIVE;
        final boolean adventure = mode == GameMode.ADVENTURE;
        final boolean spectator = mode == GameMode.SPECTATOR;
        final String gameMode =
                (survival ? String.format("%sSurvival", ChatColor.RED) :
                        creative ? String.format("%sCreative", ChatColor.BLUE) :
                                adventure ? String.format("%sAdventure", ChatColor.YELLOW) :
                                        spectator ? String.format("%sSpectator", ChatColor.GREEN)
                                                : "") + ChatColor.RESET;
        sender.playSound(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.F, 1.F);
        if (sender != target) target.playSound(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.F, 1.F);
        sender.sendTitle(String.format("%c %s %c", '\u00bb', gameMode, '\u00ab'),
                String.format("The mode '%s' has" + " been set %ssuccessfully%s!", gameMode,
                        ChatColor.GREEN, ChatColor.RESET), 20, 40, 40);
        if (sender != target) target.sendTitle(String.format("%c %s %c", '\u00bb', gameMode, '\u00ab'),
                String.format("The mode '%s' has" + " been set %ssuccessfully%s!", gameMode,
                        ChatColor.GREEN, ChatColor.RESET), 20, 40, 40);
        //player.sendTitle(survival ? "" : creative ? "" : adventure ? "" : "", "", fa);
    }
}
