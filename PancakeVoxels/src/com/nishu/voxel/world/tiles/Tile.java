package com.nishu.voxel.world.tiles;

import com.nishu.utils.Color4f;

public abstract class Tile {

	public static Tile Air = new TileAir();
	public static Tile Void = new TileVoid();
	public static Tile Grass = new TileGrass();

	public abstract byte getId();
	public abstract Color4f getColor();
	public abstract float[] getTexCoords();
	
	public static Tile getTile(byte id) {
		switch(id) {
		case -1:
			return Tile.Air;
		case 0:
			return Tile.Void;
		case 1:
			return Tile.Grass;
		}
		return Tile.Void;
	}
}
