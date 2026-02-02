package net.pl3x.backondeath.component;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentRegistryProxy;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LastDeathComponent implements Component<EntityStore> {
    private static ComponentType<EntityStore, LastDeathComponent> TYPE;

    @NotNull
    public static ComponentType<EntityStore, LastDeathComponent> getComponentType() {
        return TYPE;
    }

    public static void register(@NotNull ComponentRegistryProxy<EntityStore> registry) {
        TYPE = registry.registerComponent(LastDeathComponent.class, "LastDeathComponent", LastDeathComponent.CODEC);
    }

    public static final BuilderCodec<LastDeathComponent> CODEC = BuilderCodec
        .builder(LastDeathComponent.class, LastDeathComponent::new)
        .append(new KeyedCodec<>("World", BuilderCodec.STRING), (c, v) -> c.world = v, c -> c.world).add()
        .append(new KeyedCodec<>("Position", BuilderCodec.DOUBLE_ARRAY), (c, v) -> c.position = v, c -> c.position).add()
        .append(new KeyedCodec<>("Rotation", BuilderCodec.FLOAT_ARRAY), (c, v) -> c.rotation = v, c -> c.rotation).add()
        .build();

    private String world;
    private double[] position;
    private float[] rotation;

    @Nullable
    public String getWorld() {
        return this.world;
    }

    public void setWorld(@Nullable World world) {
        this.world = world == null ? null : world.getName();
    }

    @NotNull
    public Vector3d getPosition() {
        return new Vector3d(position[0], position[1], position[2]);
    }

    public void setPosition(@NotNull Vector3d position) {
        this.position = new double[]{position.x, position.y, position.z};
    }

    @NotNull
    public Vector3f getRotation() {
        return new Vector3f(rotation[0], rotation[1], rotation[2]);
    }

    public void setRotation(@NotNull Vector3f rotation) {
        this.rotation = new float[]{rotation.x, rotation.y, rotation.z};
    }

    @Override
    @NotNull
    public LastDeathComponent clone() {
        LastDeathComponent component = new LastDeathComponent();
        component.world = this.world;
        component.position = this.position;
        component.rotation = this.rotation;
        return component;
    }
}
