package com.nishu.voxel.world.chunks;

import com.nishu.utils.Vector3f;

public class Chunk {
	
	private Vector3f pos;

	private byte[][][] tiles;
	private int vID, cID;
	private boolean isActive;
	
	public Chunk(float x, float y, float z){
		this(new Vector3f(x, y, z));
	}
	
	public Chunk(Vector3f pos){
		this.pos = pos;
	}
	
	public void update(){
	}

	public void render(){
	}

	public void rebuild(){
	}
	
	private void checkTileInView(){
	}

	public void dispose(){
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
}
