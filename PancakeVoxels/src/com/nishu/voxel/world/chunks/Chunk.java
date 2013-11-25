package com.nishu.voxel.world.chunks;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.nishu.utils.Vector3f;
import com.nishu.voxel.geometry.Shape;
import com.nishu.voxel.world.tiles.Tile;

public class Chunk {

	public static final int CHUNKSIZE = 16;
	
	private Vector3f pos;
	private FloatBuffer vCoords, cCoords;
	private byte[][][] tiles;

	private int vID, cID, sizeX, sizeY, sizeZ;
	private boolean isActive;
	
	public Chunk(float x, float y, float z){
		this(new Vector3f(x, y, z));
	}
	
	public Chunk(Vector3f pos){
		this.pos = pos;
		
		initGL();
		init();
	}
	
	public void initGL() {
		sizeX = (int) pos.getX() + CHUNKSIZE;
		sizeY = (int) (pos.getY() + CHUNKSIZE);
		sizeZ = (int) (pos.getZ() + CHUNKSIZE);

		tiles = new byte[sizeX][sizeY][sizeZ];
		
		vCoords = BufferUtils.createFloatBuffer(CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * (3 * 4 * 6));
		cCoords = BufferUtils.createFloatBuffer(CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * (4 * 6));
		
		createChunk();
		
		vCoords.flip();
		cCoords.flip();
		
		vID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		glBufferData(GL_ARRAY_BUFFER, vCoords, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		cID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, cID);
		glBufferData(GL_ARRAY_BUFFER, cCoords, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void init() {
	}
	
	private void createChunk() {
		for(int x = (int) pos.getX(); x < sizeX; x++) {
			for(int y = (int) pos.getY(); y < sizeY; y++) {
				for(int z = (int) pos.getZ(); z < sizeZ; z++) {
					tiles[x][y][z] = Tile.Grass.getId();
					vCoords.put(Shape.createCubeVertices(x, y, z, 1));
				}
			}
		}
	}
	
	public void update(){
	}

	public void render(){
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		glVertexPointer(3, GL_FLOAT, 0, 0);

		glEnableClientState(GL_VERTEX_ARRAY);
		
		glDrawArrays(GL_QUADS, 0, CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * (3 * 4 * 6));
		
		glDisableClientState(GL_VERTEX_ARRAY);
	}

	public void rebuild(){
	}
	
	private void checkTileInView(){
	}

	public void dispose(){
		glDeleteBuffers(vID);
		glDeleteBuffers(cID);
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
}
