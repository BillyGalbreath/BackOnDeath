package net.pl3x.backondeath.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.pl3x.backondeath.component.LastDeathComponent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BackCommand extends AbstractPlayerCommand {
    public BackCommand() {
        super("back", "Teleport to your last death location");
    }

    @Override
    public boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void execute(
        @NotNull CommandContext ctx,
        @NotNull Store<EntityStore> store,
        @NotNull Ref<EntityStore> sourceRef,
        @NotNull PlayerRef playerRef,
        @NotNull World world
    ) {
        LastDeathComponent lastDeath = store.getComponent(sourceRef, LastDeathComponent.getComponentType());
        if (lastDeath == null) {
            ctx.sendMessage(Message.raw("No death location to go back to").color(Color.RED));
            return;
        }

        World targetWorld = Universe.get().getWorld(lastDeath.getWorld());
        if (targetWorld == null) {
            ctx.sendMessage(Message.raw("Cannot find world to teleport to").color(Color.RED));
            return;
        }

        Teleport teleport = new Teleport(targetWorld, lastDeath.getPosition(), lastDeath.getRotation());
        store.addComponent(sourceRef, Teleport.getComponentType(), teleport);

        ctx.sendMessage(Message.raw("Teleporting to your last death location").color(Color.GREEN));
    }
}
