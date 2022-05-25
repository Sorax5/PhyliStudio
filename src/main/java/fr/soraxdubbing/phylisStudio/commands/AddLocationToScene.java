package fr.soraxdubbing.phylisStudio.commands;

import fr.soraxdubbing.phylisStudio.PhylisStudio;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddLocationToScene implements CommandExecutor {

    private PhylisStudio plugin;

    public AddLocationToScene(PhylisStudio plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command");
            return false;
        }
        Player player = (Player) sender;
        if(args.length < 1){
            player.sendMessage("You must specify a scene name or id");
            return false;
        }

        if(args[0].matches("-?\\d+(\\.\\d+)?")){
            int id = Integer.parseInt(args[0]);
            plugin.getScene(id).addLocation(player.getLocation());
        }
        else{
            String name = args[0];
            plugin.getScene(name).addLocation(player.getLocation());
        }
        return true;
    }
}
