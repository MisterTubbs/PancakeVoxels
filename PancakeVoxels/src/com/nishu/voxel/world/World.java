package com.nishu.voxel.world;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.nishu.utils.Camera;
import com.nishu.utils.Camera3D;
import com.nishu.utils.Font;
import com.nishu.utils.Screen;
import com.nishu.utils.Text;
import com.nishu.voxel.Main;

public class World extends Screen {

	private Camera camera;
	private Font f;

	public World() {
		initGL();
		init();
	}

	@Override
	public void init() {
		camera = new Camera3D.CameraBuilder()
				.setAspectRatio(Main.WIDTH / Main.HEIGHT).setRotation(0, 0, 0)
				.setPosition(0, 0, 0).setFieldOfView(67).build();
		f = new Font();
		f.loadFont("Default", "garamond.png");
	}

	@Override
	public void initGL() {
	}

	public void render2D() {
		glClearColor(0f, 0f, 0.75f, 0f);
		glClearDepth(1);
		glEnable(GL_TEXTURE_2D);
		glViewport(0, 0, Main.WIDTH, Main.HEIGHT);
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 1, 0, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		Text.renderString(f, "x: " + (int) camera.getX() + " , Y: " + (int) camera.getY() + " , Z: " + (int) camera.getZ() + " , Rotx: " + (int) camera.getPitch() + " , RotY: " + (int) camera.getYaw() + " , RotZ: " + (int) camera.getRoll(), 0f, (int) 0f, 0.5f);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(67f, Main.WIDTH / Main.HEIGHT, 0.001f, 1000);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
	}

	@Override
	public void update() {
		camera.updateKeys(32, 1);
		camera.updateMouse(1, 90, -90);
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			dispose();
		}
	}

	@Override
	public void render() {
		render2D();
	}

	@Override
	public void dispose() {
		Display.destroy();
		System.exit(0);
	}
}
