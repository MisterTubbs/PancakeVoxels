package com.nishu.voxel.world;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.nishu.utils.Camera;
import com.nishu.utils.Camera3D;
import com.nishu.utils.Color4f;
import com.nishu.utils.Font;
import com.nishu.utils.GameLoop;
import com.nishu.utils.Screen;
import com.nishu.utils.Text;
import com.nishu.voxel.Main;
import com.nishu.voxel.world.chunks.Chunk;

public class World extends Screen {

	private Camera camera;
	private Font font;
	
	private Chunk c;

	private boolean renderText = true;
	
	public World() {
		initGL();
		init();
	}

	@Override
	public void init() {
		camera = new Camera3D.CameraBuilder().setAspectRatio(Main.WIDTH / Main.HEIGHT).setRotation(0, 0, 0).setPosition(0, 0, 0).setFieldOfView(67).build();
		font = new Font();
		font.loadFont("Default", "wasco.png");
		
		c = new Chunk(0, 0, 0);
	}

	@Override
	public void initGL() {
	}

	@Override
	public void update() {
		input();
	}

	private void input() {
		camera.updateKeys(32, 1);
		camera.updateMouse(1, 90, -90);
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					dispose();
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_F3) && !renderText) {
					renderText = true;
				} else if (Keyboard.isKeyDown(Keyboard.KEY_F3) && renderText) {
					renderText = false;
				}
			}
		}
	}

	@Override
	public void render() {
		render3D();
		
		camera.applyTranslations();
		c.render();
		glLoadIdentity();
		
		if (renderText) {
			render2D();
			renderText();
		}
	}

	private void renderText() {
		Text.renderString(font, "X:" + (int) camera.getX() + " Y:" + (int) camera.getY() + " Z:" + (int) camera.getZ(), 0f, 0.95f, 0.5f, Color4f.WHITE);
		Text.renderString(font, "Rotx:" + (int) camera.getPitch() + " RotY:" + (int) camera.getYaw() + " RotZ:" + (int) camera.getRoll(), 0f, 0.915f, 0.5f, Color4f.WHITE);
		Text.renderString(font, "FPS: " + GameLoop.getFPS(), 0f, 0.88f, 0.5f, Color4f.WHITE);
	}

	public void render2D() {
		glClearDepth(1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glOrtho(0, 1, 0, 1, -1, 1);
		glViewport(0, 0, Main.WIDTH, Main.HEIGHT);
		glMatrixMode(GL_MODELVIEW);
	}

	public void render3D() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(67.0f, Main.WIDTH / Main.HEIGHT, 0.001f, 1000f);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_DEPTH_TEST);
	}

	@Override
	public void dispose() {
		c.dispose();
		Display.destroy();
		System.exit(0);
	}
}
