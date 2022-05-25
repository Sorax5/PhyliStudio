package fr.soraxdubbing.phylisStudio.commands;

import fr.soraxdubbing.phylisStudio.PhylisStudio;
import fr.soraxdubbing.phylisStudio.Scene;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetScene implements CommandExecutor {

    private PhylisStudio plugin;

    public GetScene(PhylisStudio plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (int i = 0; i < plugin.getScenes().size(); i++) {

            Scene scene = plugin.getScenes().get(i);

            String message = "Scene n°"+i+ " : " +scene.getName() +
                    " - " + scene.getTime() +
                    " s - " + scene.getUuid();

            sender.sendMessage(message);
        }

        return true;
    }
}
