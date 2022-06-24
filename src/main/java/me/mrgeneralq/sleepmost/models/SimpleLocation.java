package me.mrgeneralq.sleepmost.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class SimpleLocation {
	private UUID worldId;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

	public SimpleLocation(Location location) {
		worldId = location.getWorld().getUID();
		x = location.getX();
		y = location.getY();
		z = location.getZ();
		pitch = location.getPitch();
		yaw = location.getYaw();
	}

	public Location asLocation() {
		return new Location(Bukkit.getWorld(worldId), x, y, z, yaw, pitch);
	}
}
