package fr.soraxdubbing.phylisStudio;

import fr.soraxdubbing.phylisStudio.commands.AddLocationToScene;
import fr.soraxdubbing.phylisStudio.commands.CreateScene;
import fr.soraxdubbing.phylisStudio.commands.GetScene;
import fr.soraxdubbing.phylisStudio.commands.RenderScene;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PhylisStudio extends JavaPlugin {

    private List<Scene> scenes;

    public List<Scene> getScenes() {
        return scenes;
    }

    public Scene getScene(int index) {
        return scenes.get(index);
    }

    public Scene getScene(String name) {
        for (Scene scene : scenes) {
            if (scene.getName().equals(name)) {
                return scene;
            }
        }
        return null;
    }

    public Scene getScene(UUID uudi) {
        for (Scene scene : scenes) {
            if (scene.getUuid().equals(uudi)) {
                return scene;
            }
        }
        return null;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        scenes = new ArrayList<>();
        // Register commands
        getCommand("CreateScene").setExecutor(new CreateScene(this));
        getCommand("GetScene").setExecutor(new GetScene(this));
        getCommand("RenderScene").setExecutor(new RenderScene(this));
        getCommand("AddLocation").setExecutor(new AddLocationToScene(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
