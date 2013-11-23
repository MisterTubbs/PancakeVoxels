package com.nishu.voxel.world.tiles;

public class TileVoid extends Tile{

	public TileVoid() {
		super((byte) 0);
	}

	@Override
	public byte getId() {
		return 0;
	}

}
