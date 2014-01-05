package com.nishu.voxel.world;

import java.util.ArrayList;

import com.nishu.utils.Shader;
import com.nishu.utils.ShaderProgram;
import com.nishu.voxel.utilites.Constants;
import com.nishu.voxel.utilites.Spritesheet;
import com.nishu.voxel.world.chunks.Chunk;
import com.nishu.voxel.world.entities.mobs.MobManager;

public class WorldManager {

	private MobManager mobManager;
	
	@SuppressWarnings("unused")
	private ArrayList<Chunk> loadedChunks;
	private ArrayList<Chunk> activeChunks;

	private ShaderProgram shader;

	public WorldManager() {
		initGL();
		init();
		createWorld();
	}

	private void initGL() {
		Shader temp = new Shader("/shaders/chunk.vert", "/shaders/chunk.frag");
		shader = new ShaderProgram(temp.getvShader(), temp.getfShader());
	}

	private void init() {
		mobManager = new MobManager();
		loadedChunks = new ArrayList<Chunk>();
		activeChunks = new ArrayList<Chunk>();
	}

	private void createWorld() {
		for (int x = 0; x < Constants.viewDistance; x++) {
			for (int y = 0; y < Constants.viewDistance; y++) {
				for (int z = 0; z < Constants.viewDistance; z++) {
					activeChunks.add(new Chunk(shader, 1, x * Chunk.CHUNKSIZE, y * Chunk.CHUNKSIZE, z * Chunk.CHUNKSIZE));
				}
			}
		}
	}

	public void update() {
		mobManager.update();
	}

	public void render() {
		Spritesheet.tiles.bind();
		getMobManager().getPlayer().getCamera().applyTranslations();
		for (int i = 0; i < activeChunks.size(); i++) {
			activeChunks.get(i).render();
		}
		mobManager.render();
	}
	
	public MobManager getMobManager() {
		return mobManager;
	}
}
