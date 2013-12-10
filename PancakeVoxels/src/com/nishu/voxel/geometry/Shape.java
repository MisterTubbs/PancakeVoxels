package com.nishu.voxel.geometry;

import static org.lwjgl.opengl.GL11.*;

import com.nishu.utils.Color4f;
import com.nishu.voxel.utilites.Spritesheet;

public class Shape {
	
	public static void createCube(float x, float y, float z, Color4f color, float[] texCoords, float size) {
		//bottom face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x, y, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x + size, y, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y, z);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y, z);

		// top face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x, y + size, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x + size, y + size, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y + size, z + size);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y + size, z + size);

		// front face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x, y, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x + size, y, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y + size, z);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y + size, z);

		// back face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x, y + size, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x + size, y + size, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y, z + size);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y, z + size);

		// left face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x + size, y, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x + size, y, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y + size, z + size);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x + size, y + size, z);

		// right face
		glColor4f(color.r, color.g, color.b, color.a);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex3f(x, y, z + size);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1]);
		glVertex3f(x, y, z);
		glTexCoord2f(texCoords[0] + Spritesheet.tiles.uniformSize(), texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y + size, z);
		glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.tiles.uniformSize());
		glVertex3f(x, y + size, z + size);
	}
}
