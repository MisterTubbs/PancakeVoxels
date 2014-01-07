package com.nishu.voxel.world.tiles;

import com.nishu.utils.Color4f;
import com.nishu.voxel.utilites.Spritesheet;

public class TileGrass extends Tile {

	/* 
	 * Texture coords for multi-textured tiles
	 * bottom - first
	 * top - second
	 * front - third
	 * back - fourth
	 * left - fifth
	 * right - sixth
	 */
	
	@Override
	public byte getId() {
		return 1;
	}

	@Override
	public Color4f getColor() {
		return new Color4f(1f, 1f, 1f, 1);
	}

	@Override
	public float[] getTexCoords() {
		return new float[] { 4 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(), 
				Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
				3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
				3 * Spritesheet.tiles.uniformSize(), 0,
				3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
				3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize() };
	}
}
