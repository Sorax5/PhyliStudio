package fr.soraxdubbing.phylisStudio.task;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RenderTask extends BukkitRunnable {

    private int i = 0;
    private List<Location> list;
    private Player player;
    private GameMode gameMode;

    public RenderTask(List<Location> list, Player player, GameMode gameMode) {
        this.list = list;
        this.player = player;
        this.gameMode = gameMode;
    }

    @Override
    public void run() {
        player.teleport(list.get(i));

        i++;

        if (i >= list.size()) {
            player.setGameMode(gameMode);
            cancel();
        }

    }
}
