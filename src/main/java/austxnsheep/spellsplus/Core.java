package austxnsheep.spellsplus;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Core {
    //Example of how to use this function 'Enable()'
    public static void Enable() {
        Bukkit.broadcastMessage("SpellCore Enabled...");
    }
    //Example of how to use this function 'addSpell(<Player Name> <Name of spell> <Mana Cost>)'
    public static void addSpell(Player player, String spell, Integer manacost) {
        //Anitial Statements
        if(player.getItemInHand().getAmount()>1) {
            player.sendMessage(returnError("You cannot put spells on stacked items"));
            return;
        }
        //Vars
        ItemStack item = player.getItemInHand();
        String lore = String.valueOf(item.getLore());
        String newlore = ChatColor.DARK_PURPLE + "       " + spell + "    " + manacost;
        item.setLore(Collections.singletonList(lore + newlore));
        //NBT
        NBTItem nbti = new NBTItem(item);
        if(nbti.hasKey("spell")) {
            player.sendMessage(returnError("This item has a spell: " + nbti.getString("spell")));
            return;
        }
        else {
            nbti.setString("spell", spell);
            nbti.setInteger("manacost", manacost);
            player.sendMessage(returnSuccess("Added the spell " + spell + " to your item"));
            return;
        }
    }
    //Example of how to use this function 'addSpell(<Error in Sentence form>)'
    public static String returnError(String error) {
        return "Failed To Execute: " + error;
    }
    public static String returnSuccess(String cause) {
        return "Success:" + cause;
    }
    public static String getCommands()  {
        return "commands:\n" +
                "  Help:\n" +
                "    description: Sends the message you're reading\n" +
                "    usage: /spellsPlus Help\n" +
                "    permission: SpellsPlus.help\n" +
                "  reload:\n" +
                "    description: Reloads the configuration of the plugin\n" +
                "    usage: /spellsPlus reload\n" +
                "    permission: SpellsPlus.reload\n" +
                "  getMaxMana:\n" +
                "    description: Returns the amount of mana a player has\n" +
                "    usage: /spellsPlus getMana <Player>\n" +
                "    permission: SpellsPlus.getMana\n" +
                "  addSpell:\n" +
                "    description: A admin command to add spells to any item\n" +
                "    usage: /spellsPlus addSpell <Spell> <mana cost>\n" +
                "    permission: SpellsPlus.addSpell\n" +
                "  setMaxMana:\n" +
                "    description: Sets the mana of a specified player\n" +
                "    usage: /spellsPlus setMana <Player> <Amount of mana>\n" +
                "    permission: SpellsPlus.setMana\n" +
                "  executeSpell:\n" +
                "    description: Executes the specified spell\n" +
                "    usage: /spellsPlus executeSpell <Spell name>\n" +
                "    permission: SpellsPlus.executeSpell\n" + ChatColor.RED +
                "Capitalization Matters.";
    }
}

