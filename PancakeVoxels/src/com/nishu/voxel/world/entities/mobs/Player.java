package com.nishu.voxel.world.entities.mobs;

import com.nishu.voxel.world.entities.Camera;

public class Player extends Mob {

	public Player(Camera camera, int id) {
		super(camera, camera.getX(), camera.getY(), camera.getZ(), camera.getPitch(), camera.getYaw(), camera.getRoll(), id, 0);
	}

	public void update() {
		//changed
		move();
	}

	public void render() {
	}

	public void dispose() {
	}

}
