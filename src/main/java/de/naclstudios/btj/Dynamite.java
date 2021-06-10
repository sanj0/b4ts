package de.naclstudios.btj;

import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;

/**
 * Detonates and leaves a {@link Detonation}
 */
public class Dynamite extends GameObject {

    public static int TIMER = 250;
    public static final float WIDTH = 25;
    public static final float HEIGHT = 25;
    public static final String TAG = "dynamite";

    private int lifeTime = 0;
    public Dynamite(final float xPos, final float yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, TAG);
    }

    public void detonate() {
        SceneManager.getCurrentScene().addGameObject(new Detonation(getTransform().getCentre(), 300, 5));
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(true);
    }

    @Override
    public void onCollision(final CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {
        if (lifeTime >= TIMER) {
            detonate();
            removeFromCurrentScene();
        } else {
            lifeTime++;
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.blend(ColorUtil.BLACK, ColorUtil.MAROON_RED_COLOR, (float) lifeTime / TIMER));
        saltyGraphics.drawRect(this);
    }
}
