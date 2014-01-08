package com.nishu.voxel.utilites;

import java.util.Random;

public class Constants {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static Random rand = new Random();
	
	public static int viewDistance = 11;
	public static int CHUNKSIZE = 16;
	
	public static int chunksLoaded = 0;
	public static int chunksFrustum = 0;
	
	public static float textSize = 0.35f;
}
