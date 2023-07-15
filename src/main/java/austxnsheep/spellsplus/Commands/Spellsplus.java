package austxnsheep.spellsplus.Commands;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.customItems.CustomItemCore;
import austxnsheep.spellsplus.Data.DataManager;
import austxnsheep.spellsplus.Data.ManaManager;
import austxnsheep.spellsplus.Execution.SpellExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public class Spellsplus implements CustomItemCore, CommandExecutor, Core {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        Player effectedPlayer = getServer().getPlayer(args[1]);
        DataManager datamanager = new DataManager();
        ManaManager manamanager = new ManaManager();
        if (player.isOp()) {
            if (args[0] == null) {
                sender.sendMessage(getCommands());
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "help": {
                    sender.sendMessage(getCommands());
                    break;
                }
                case "github": {
                    sender.sendMessage("[SpellsPlus] " + "Check the config for this plugin.");
                    break;
                }
                case "devinfo": {
                    player.sendMessage("[SpellsPlus] " + "Spells Plus V2.1 By austxnsheep");
                    break;
                }
                case "reload": {
                    sender.sendMessage("[SpellsPlus] " + "Reloading...");
                    try {
                        datamanager.saveAllPlayerData();
                    } catch (Exception e) {
                        System.out.println("[SpellsPlus]" + e);
                    }
                    sender.sendMessage("[SpellsPlus] " + "Reloaded...");
                    break;
                }
                case "setmaxmana": {
                    sender.sendMessage("[SpellsPlus] " + "Player's Current mana level: " + manamanager.getCurrentMana(player));
                    manamanager.setCurrentMana(player, Integer.parseInt(args[2]));
                    sender.sendMessage("[SpellsPlus] " + "Player's New mana level: " + manamanager.getCurrentMana(player));
                    break;
                }
                case "getmaxmana": {
                    assert effectedPlayer != null;
                    player.sendMessage(args[1] + "'s Maximum mana level: " + datamanager.getPlayerData(player, "maxMana"));
                    break;
                }
                case "executespell": {
                    ItemStack item = player.getItemInHand();
                    //       NBTItem nbti = new NBTItem(item);
                    //       String spell = nbti.getString("spell");
                    //       Integer manacost = nbti.getInteger("manacost");
                    SpellExecutor.ExecuteSpell(player, "Test1");
                    break;
                }
                case "addspell": {
                    String arg1 = args[1];
                    Integer arg2 = Integer.valueOf(args[2]);
                    Core.addSpell((Player) sender, arg1, arg2);
                    break;
                }
                case "giveitem": {
                    player.getInventory().addItem(getItem(Integer.parseInt(args[1])));
                    break;
                }
                case "runnableinfo": {
                    ChatColor uncompletedcolor = Main.runnable.getUncompletedChatcolor();
                    ChatColor completedcolor = Main.runnable.getCompletedChatcolor();
                    int regenerationRate = Main.runnable.getRegenerationRate();
                    player.sendMessage("RegenerationRate: " + regenerationRate, "Uncompleted chat color: " + uncompletedcolor, "Completed chat color: " + completedcolor);
                    break;
                }
                case "warp": {
                    if (Bukkit.getWorld(args[1]) == null) {
                        player.sendMessage("Failed to teleport: World does not exist.");
                        return false;
                    }
                    if (args[1] == null) {
                        player.sendMessage("Failed to teleport: Please specify a world by name.");
                        return false;
                    }
                    player.sendMessage("Warping to dimension: " + args[1]);
                    World world = Bukkit.getWorld(args[1]);
                    Location destination = player.getLocation().clone();
                    destination.setWorld(world);
                    player.teleport(destination);
                    break;
                }
                default: {
                    sender.sendMessage(getCommands());
                }
            }
        } else {
          player.sendMessage(returnError("Not an operator."));
          return true;
        }
        return true;
    }
}