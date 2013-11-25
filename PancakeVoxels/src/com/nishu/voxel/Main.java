package com.nishu.voxel;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.opengl.Display;

import com.nishu.utils.GameLoop;
import com.nishu.utils.Screen;
import com.nishu.utils.Window;
import com.nishu.voxel.world.World;

public class Main extends Screen{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private GameLoop gameLoop;
	private World world;
	
	public Main(){
		gameLoop = new GameLoop();
		gameLoop.setScreen(this);
		gameLoop.start(30);
	}

	@Override
	public void init() {
		initCamera();
		
		world = new World();
	}

	@Override
	public void initGL() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		gluPerspective(67.0f, WIDTH / HEIGHT, 0.001f, 1000f);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_DEPTH_TEST);
	}
	
	private void initCamera(){
	}

	@Override
	public void update() {
		world.update();
	}
	
	@Override
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0, 0, 0.75f, 1);
		
		world.render();
	}
	
	@Override
	public void dispose() {
		world.dispose();
	}
	
	public static void main(String[] args){
		Window.createWindow(WIDTH, HEIGHT, "Voxels", false);
		new Main();
	}
}
