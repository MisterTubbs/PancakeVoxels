package com.nishu.voxel.world.tiles;

import com.nishu.utils.Color4f;

public class TileVoid extends Tile {

	@Override
	public byte getId() {
		return 0;
	}

	@Override
	public Color4f getColor() {
		return new Color4f(0.5f, 0.5f, 0.5f, 1);
	}

	@Override
	public float[] getTexCoords() {
		return new float[] { 0f, 0f };
	}
}
