package com.nishu.voxel.world.tiles;

public abstract class Tile {

	public static Tile Void = new TileVoid();
	
	private byte id;
	private boolean isActive;
	
	public Tile(byte id){
		this.id = id;
	}

	public abstract byte getId();

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
