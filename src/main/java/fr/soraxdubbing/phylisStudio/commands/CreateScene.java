package fr.soraxdubbing.phylisStudio.commands;

import fr.soraxdubbing.phylisStudio.PhylisStudio;
import fr.soraxdubbing.phylisStudio.Scene;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateScene implements CommandExecutor {

    private PhylisStudio plugin;

    public CreateScene(PhylisStudio plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if(args.length <= 1 || args.length > 2) {
            player.sendMessage("Usage: /createscene <scene name> <executeTime>");
            return true;
        }

        plugin.getScenes().add(new Scene(args[0], Float.parseFloat(args[1])));

        return true;
    }
}
