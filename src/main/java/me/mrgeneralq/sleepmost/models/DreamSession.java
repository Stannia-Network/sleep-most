package me.mrgeneralq.sleepmost.models;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DreamSession {

    private final Dream dream;
    private final Player player;
    private int duration;
    private Location fromLocation;

    public DreamSession(Dream dream, Player player) {
        this.dream = dream;
        this.player = player;
    }

    public Dream getDream() {
        return dream;
    }

    public Player getPlayer() {
        return player;
    }

    public int getDuration() {
        return duration;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }
}
