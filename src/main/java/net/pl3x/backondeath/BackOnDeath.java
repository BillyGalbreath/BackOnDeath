package net.pl3x.backondeath;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import net.pl3x.backondeath.command.BackCommand;
import net.pl3x.backondeath.component.LastDeathComponent;
import net.pl3x.backondeath.system.PlayerDeathSystem;
import org.jetbrains.annotations.NotNull;

public class BackOnDeath extends JavaPlugin {
    public BackOnDeath(@NotNull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        LastDeathComponent.register(getEntityStoreRegistry());

        getEntityStoreRegistry().registerSystem(new PlayerDeathSystem());

        getCommandRegistry().registerCommand(new BackCommand());
    }
}
