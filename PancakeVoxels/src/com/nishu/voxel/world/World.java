package com.nishu.voxel.world;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_MODULATE;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_ENV;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_ENV_MODE;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexEnvi;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.nishu.utils.Color4f;
import com.nishu.utils.Font;
import com.nishu.utils.GameLoop;
import com.nishu.utils.Screen;
import com.nishu.utils.Text;
import com.nishu.voxel.Main;
import com.nishu.voxel.world.tiles.Tile;

public class World extends Screen {

	public static final int AIRCHUNK = 0, MIXEDCHUNK = 1;

	private Font font;
	private WorldManager worldManager;

	private boolean renderText = true;

	public World() {
		initGL();
		init();
	}

	@Override
	public void init() {
		Tile.createTileMap();
		font = new Font();
		font.loadFont("Default", "fonts/wasco.png");
		
		worldManager = new WorldManager();
	}

	@Override
	public void initGL() {
		glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		glEnable(GL_CULL_FACE);
	}

	@Override
	public void update() {
		input();
		worldManager.update();
	}

	private void input() {
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
					renderText = !renderText;
				} 
			}
		}
	}

	@Override
	public void render() {
		render3D();
		
		worldManager.render();
		glLoadIdentity();
		
		if (renderText) {
			render2D();
			renderText();
		}
	}

	private void renderText() {
		Text.renderString(font, "X:" + (int) worldManager.getMobManager().getPlayer().getX() + " Y:" + (int) worldManager.getMobManager().getPlayer().getY() + " Z:" + (int) worldManager.getMobManager().getPlayer().getZ(), 0f, 0.95f, 0.5f, Color4f.WHITE);
		Text.renderString(font, "Rotx:" + (int) worldManager.getMobManager().getPlayer().getPitch() + " RotY:" + (int) worldManager.getMobManager().getPlayer().getYaw() + " RotZ:" + (int) worldManager.getMobManager().getPlayer().getRoll(), 0f, 0.915f, 0.5f, Color4f.WHITE);
		Text.renderString(font, "FPS: " + GameLoop.getFPS(), 0f, 0.88f, 0.5f, Color4f.WHITE);
	}

	public void render2D() {
		glCullFace(GL_BACK);
		glClearDepth(1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glOrtho(0, 1, 0, 1, -1, 1);
		glViewport(0, 0, Main.WIDTH, Main.HEIGHT);
		glMatrixMode(GL_MODELVIEW);
	}

	public void render3D() {
		glCullFace(GL_FRONT);
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(67.0f, Main.WIDTH / Main.HEIGHT, 0.001f, 1000f);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_DEPTH_TEST);
	}

	@Override
	public void dispose() {
		Display.destroy();
		System.exit(0);
	}
}
