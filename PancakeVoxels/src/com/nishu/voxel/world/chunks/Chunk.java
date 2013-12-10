package com.nishu.voxel.world.chunks;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;

import java.util.Random;

import com.nishu.utils.ShaderProgram;
import com.nishu.utils.Vector3f;
import com.nishu.voxel.geometry.Shape;
import com.nishu.voxel.world.tiles.Tile;

public class Chunk {

	public static final int CHUNKSIZE = 16;

	private Vector3f pos;
	private byte[][][] tiles;
	private ShaderProgram shader;

	private int vcID, sizeX, sizeY, sizeZ;
	private boolean isActive;

	private Random rand;

	public Chunk(ShaderProgram shader, float x, float y, float z) {
		this(shader, new Vector3f(x, y, z));
	}

	public Chunk(ShaderProgram shader, Vector3f pos) {
		this.pos = pos;
		this.shader = shader;

		initGL();
		init();
	}

	public void initGL() {
		rand = new Random();

		sizeX = (int) pos.getX() + CHUNKSIZE;
		sizeY = (int) pos.getY() + CHUNKSIZE;
		sizeZ = (int) pos.getZ() + CHUNKSIZE;

		vcID = glGenLists(1);

		tiles = new byte[sizeX][sizeY][sizeZ];

		createChunk();
		rebuild();
	}

	public void init() {
	}

	private void createChunk() {
		for (int x = (int) pos.getX(); x < sizeX; x++) {
			for (int y = (int) pos.getY(); y < sizeY; y++) {
				for (int z = (int) pos.getZ(); z < sizeZ; z++) {
					tiles[x][y][z] = Tile.Air.getId();
					if (rand.nextInt(10) == 0)
						tiles[x][y][z] = Tile.Grass.getId();
				}
			}
		}
	}

	public void update() {
	}

	public void render() {
		glCallList(vcID);
	}

	public void rebuild() {
		glNewList(vcID, GL_COMPILE);
		glBegin(GL_QUADS);
		for (int x = (int) pos.getX(); x < sizeX; x++) {
			for (int y = (int) pos.getY(); y < sizeY; y++) {
				for (int z = (int) pos.getZ(); z < sizeZ; z++) {
					if (tiles[x][y][z] != -1) {
						Shape.createCube(x, y, z, Tile.getTile(tiles[x][y][z]).getColor(), Tile.getTile(tiles[x][y][z]).getTexCoords(), 1);
					}
				}
			}
		}
		glEnd();
		glEndList();
	}

	@SuppressWarnings("unused")
	private void checkTileInView() {
	}

	public void dispose() {
		shader.dispose();
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
