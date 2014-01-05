package com.nishu.voxel.world.tiles;

import com.nishu.utils.Color4f;
import com.nishu.voxel.utilites.Spritesheet;

public class TileCrackedStone extends Tile{

	@Override
	public byte getId() {
		return 2;
	}

	@Override
	public Color4f getColor() {
		return Color4f.GRAY;
	}

	@Override
	public float[] getTexCoords() {
		return new float[] { 2 * Spritesheet.tiles.uniformSize(), 0f };
	}

}
