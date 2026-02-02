package net.pl3x.backondeath.system;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.pl3x.backondeath.component.LastDeathComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDeathSystem extends DeathSystems.OnDeathSystem {
    @Override
    public void onComponentAdded(
        @NotNull Ref<EntityStore> ref,
        @NotNull DeathComponent deathComponent,
        @NotNull Store<EntityStore> store,
        @NotNull CommandBuffer<EntityStore> commandBuffer
    ) {
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player == null) {
            return;
        }

        TransformComponent transform = store.getComponent(ref, TransformComponent.getComponentType());
        if (transform == null) {
            return;
        }

        LastDeathComponent lastDeath = store.getComponent(ref, LastDeathComponent.getComponentType());
        if (lastDeath == null) {
            lastDeath = new LastDeathComponent();
            commandBuffer.addComponent(ref, LastDeathComponent.getComponentType(), lastDeath);
        }

        lastDeath.setWorld(player.getWorld());
        lastDeath.setPosition(transform.getPosition());
        lastDeath.setRotation(transform.getRotation());
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.and(Player.getComponentType());
    }
}
