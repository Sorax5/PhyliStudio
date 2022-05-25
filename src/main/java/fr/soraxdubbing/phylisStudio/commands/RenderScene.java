package fr.soraxdubbing.phylisStudio.commands;

import fr.soraxdubbing.phylisStudio.PhylisStudio;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenderScene implements CommandExecutor {

    private PhylisStudio plugin;

    public RenderScene(PhylisStudio plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command");
            return true;
        }
        Player player = (Player) sender;
        if(args.length < 1){
            player.sendMessage("You must specify a scene name or id");
            return true;
        }

        if(args[0].matches("-?\\d+(\\.\\d+)?")){
            int id = Integer.parseInt(args[0]);
            plugin.getScene(id).render(player,plugin);
        }
        else{
            String name = args[0];
            if(name == null){
                player.sendMessage("You must specify a scene name or id");
                return true;
            }
            plugin.getScene(name).render(player,plugin);
        }
        return true;
    }
}
