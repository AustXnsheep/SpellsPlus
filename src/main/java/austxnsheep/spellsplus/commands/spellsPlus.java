package austxnsheep.spellsplus.commands;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.customItems.customItemsCore;
import austxnsheep.spellsplus.data.dataManager;
import austxnsheep.spellsplus.data.manaManager;
import austxnsheep.spellsplus.execution.spellExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;

import static org.bukkit.Bukkit.getServer;

public class spellsPlus implements CommandExecutor {
    Plugin cls = new Main();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player effectedPlayer = getServer().getPlayer(args[1]);
        customItemsCore cicore = new customItemsCore();
        dataManager datamanager = new dataManager();
        File playerDataFile = datamanager.getPlayerDataFile(player.getUniqueId());
        if (player.isOp()) {
            if (args[0] == null) {
                sender.sendMessage(Core.getCommands());
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "help": {
                    sender.sendMessage(Core.getCommands());
                    break;
                }
                case "devinfo": {
                    player.sendMessage("Spells Plus V2.1 By austxnsheep");
                    break;
                }
                case "reload": {
                    sender.sendMessage("Reloading...");
                    try {
                        cls.reloadConfig();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    sender.sendMessage("Reloaded...");
                    break;
                }
                case "setmaxmana": {
                    sender.sendMessage("Player's Current mana level: " + manaManager.getCurrentMana(player));
                    manaManager.setMaxMana(player, Integer.parseInt(args[2]));
                    sender.sendMessage("Player's New mana level: " + manaManager.getCurrentMana(player));
                    break;
                }
                case "getmaxmana": {
                    assert effectedPlayer != null;
                    player.sendMessage(args[1] + "'s Maximum mana level: " + datamanager.readFile(playerDataFile));
                    break;
                }
                case "executespell": {
                    ItemStack item = player.getItemInHand();
                    //       NBTItem nbti = new NBTItem(item);
                    //       String spell = nbti.getString("spell");
                    //       Integer manacost = nbti.getInteger("manacost");
                    spellExecutor.ExecuteSpell(player, "Test1");
                    break;
                }
                case "addspell": {
                    String arg1 = args[1];
                    Integer arg2 = Integer.valueOf(args[2]);
                    Core.addSpell((Player) sender, arg1, arg2);

                    break;
                }
                case "giveitem": {
                    player.getInventory().addItem(cicore.getItem(1));
                    break;
                }
            }
        } else {
          player.sendMessage(Core.returnError("Not an operator."));
          return true;
        }
        return true;
    }
}