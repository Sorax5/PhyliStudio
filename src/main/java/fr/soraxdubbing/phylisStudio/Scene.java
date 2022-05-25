package fr.soraxdubbing.phylisStudio;

import fr.soraxdubbing.phylisStudio.task.RenderTask;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Scene {

    private List<Double> Calculatedtime;

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    private String name;

    /**
     * renvois le nom de la scene
     * @return
     */
    public String getName() {
        return name;
    }

    private float time;

    /**
     * renvois le temps d'execution de la scene
     * @return
     */
    public float getTime() {
        return time;
    }

    /**
     * mets le temps d'éxecution de la scene
     * @param time
     */
    public void setTime(float time) {
        this.time = time;
    }

    private List<Location> locations;

    /**
     * renvois la liste des locations
     * @return
     */
    public List<Location> getLocation() {
        return locations;
    }

    /**
     * rajoute une location à la scene
     * @param location
     */
    public void addLocation(Location location) {
        locations.add(location);
    }

    /**
     * supprime une location de la scene selon l'index
     * @param index
     */
    public void removeLocation(int index) {
        locations.remove(index);
    }

    /**
     * renvois la longueur de la liste des locations
     * @return
     */
    public int getLocationSize() {
        return locations.size();
    }

    private List<Location> launch;

    /**
     * Constructeur par copie de la scene
     * @param scene
     */
    public Scene(Scene scene) {
        this.locations = scene.getLocation();
        this.uuid = UUID.randomUUID();
    }

    /**
     * Constructeur de la scene
     * @param name
     * @param time
     */
    public Scene(String name, float time) {
        this.name = name;
        this.time = time;
        this.uuid = UUID.randomUUID();
        this.locations = new ArrayList<>();

    }

    public void render(Player player, PhylisStudio plugin) {
        generate();

        System.out.println(" taille : " + this.launch.size());

        GameMode gameMode = player.getGameMode();

        player.setGameMode(GameMode.SPECTATOR);

        RenderTask renderTask = new RenderTask(launch,player,gameMode);
        renderTask.runTaskTimer(plugin, 0, (long) (1/getTime()));
    }

    public void generate() {

        this.launch = new ArrayList<>();

        Location location = this.getLocation().get(0).clone();

        for (int i = 0; i < this.getLocation().size(); i++) {

            Location l1 = this.locations.get(i).clone();
            Location l2 = this.locations.get((i + 1) % locations.size()).clone();

            Vector v1 = this.locations.get(i).toVector();
            Vector v2 = this.locations.get((i + 1) % locations.size()).toVector();
            Vector v3 = v2.subtract(v1).normalize();
            v3.multiply(new Vector(1/getTime(),1/getTime(),1/getTime()));

            // calcul du nombre de fois qu'on doit ajouter le vecteur
            double nbr = Math.ceil(l1.distance(l2))*getTime();

            double yaw = (this.getLocation().get((i+1) % this.getLocationSize()).getYaw() - this.getLocation().get(i).getYaw())/nbr;
            double pitch = (this.getLocation().get((i+1) % this.getLocationSize()).getPitch() - this.getLocation().get(i).getPitch())/nbr;
            Location last = l1.clone();
            for(int j = 1; j <= nbr; j++) {
                last.add(v3);
                last.setYaw((float) (last.getYaw() + yaw));

                last.setPitch((float) (last.getPitch() + pitch));


                System.out.println(last.getYaw());
                launch.add(last.clone());
            }
        }
        launch.add(location.clone());
    }
}
