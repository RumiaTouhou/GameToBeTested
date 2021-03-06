package com.mygdx.game.Entitys;

import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.PlayerController;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.PirateGame;


/**
 * Player's ship entity.
 */
public class Player extends Ship {
    boolean reward_powerUp;
    /**
     * Adds ship with PlayerController component and sets its speed.
     *
     * @param speed of movement across map
     */
    private Player(float speed) {
        super();

        PlayerController pc = new PlayerController(this, speed);
        addComponent(pc);
        reward_powerUp = false;
        setName("Player");
    }

    /**
     * Adds ship with PlayerController component, loading its speed from GameManager settings.
     */
    public Player() {
        this(GameManager.getSettings().get("starting").getFloat("playerSpeed"));
    }

    public void updateHealth() {
        String mode = DifficultyManager.getDifficulty();
        JsonValue starting = GameManager.getSettings().get("starting");
        int health;
        if (mode == "e") {
            health = starting.getInt("health-easy");
        } else if (mode == "h") {
            health = starting.getInt("health-hard");
        } else {
            health = starting.getInt("health");
        }
        setHealth(health);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }

    public int getAmmo() {
        return getComponent(Pirate.class).getAmmo();
    }
    public void setAmmo(Integer ammo){
        getComponent(Pirate.class).setAmmo(ammo);
    }

    public void setReward_powerUp(boolean reward_powerUp){
        this.reward_powerUp = reward_powerUp;
    }

    public boolean getReward_powerUp(){
        return reward_powerUp;
    }
}
