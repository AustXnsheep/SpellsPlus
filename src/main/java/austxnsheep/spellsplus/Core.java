package austxnsheep.spellsplus;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public interface Core {
    //Example of how to use this function 'Enable()'
    default void Enable() {
        Bukkit.broadcastMessage("SpellCore Enabled...");
    }
    //Example of how to use this function 'addSpell(<Player Name> <Name of spell> <Mana Cost>)'
    public static void addSpell(Player player, String spell, Integer manacost) {
        //Anitial Statements
        if(player.getItemInHand().getAmount()>1) {
            player.sendMessage("You cannot put spells on stacked items");
            return;
        }
        //Vars
        ItemStack item = player.getItemInHand();
        String lore = String.valueOf(item.getLore());
        String newlore = ChatColor.DARK_PURPLE + "       " + spell + "    " + manacost;
        item.setLore(Collections.singletonList(lore + newlore));
        /* NBT
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
         */
    }
    default String getProgressBar(int current, int max, int totalBars, String barChar, String completedColor, String notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return StringUtils.repeat(completedColor + barChar, progressBars)
                + StringUtils.repeat(notCompletedColor + barChar, totalBars - progressBars);
    }
    default void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
    //Example of how to use this function 'returnError(<Error in Sentence form>)'
    default String returnError(String error) {
        return "Failed To Execute: " + error;
    }
    //Example of how to use this function 'returnSuccess(<Error in Sentence form>)'
    default String returnSuccess(String cause) {
        return "Successfully Executed: " + cause;
    }
    default String getCommands()  {
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
                "    permission: SpellsPlus.executeSpell\n" +
                "  devInfo:\n" +
                "    description: Information about this plugin\n" +
                "    usage: /spellsPlus devinfo\n" +
                "    permission: SpellsPlus.info\n" +
                "  warp:\n" +
                "    description: Warps you to a specified world\n" +
                "    usage: /spellsPlus warp <Name of World Ex. nether>\n" +
                "    permission: SpellsPlus.warp\n" +
                "  runnableinfo:\n" +
                "    description: Gives you information on the plugins current runnable (useful for if it breaks)\n" +
                "    usage: /spellsPlus runnableinfo\n" +
                "    permission: SpellsPlus.runnableinfo\n" +
                "  giveItem:\n" +
                "    description: Gives the item you specify depending on the ID(You can find the ID for all items in the plugin config)\n" +
                "    usage: /spellsPlus giveItem <Item ID>\n" +
                "    permission: SpellsPlus.giveitem\n" + ChatColor.RED +
                "";
    }
}

