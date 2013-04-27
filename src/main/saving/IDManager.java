package main.saving;

import java.util.HashMap;

import block.Dirt;
import block.Lava;
import block.Rock;
import block.Rubber;
import block.decor.Background;
import block.decor.Ghost_Rock;
import block.decor.Grass;
import block.decor.Wood;
import entity.Chest;
import entity.mob.Angel;
import entity.mob.Character;

public class IDManager
{
	private static HashMap<Integer, Class> classes = new HashMap()
	{
		{
			put(1, Character.class);
			put(2, Angel.class);
			
			put(65, Chest.class);

		}
	};
	
	private static HashMap<Class, Integer> ids = new HashMap()
	{
		{
			put(Character.class, 1);
			put(Angel.class, 2);
			
			put(Chest.class, 65);
		}
	};
	
	public static int getID(Class cl)
	{
		return ids.get(cl);
	}
	public static Class getClass(int id)
	{
		return classes.get(id);
	}
	
	private static HashMap<Byte, Class> blocks = new HashMap()
	{
		{
			put((byte) 1, Dirt.class);
			put((byte) 2, Rock.class);
			put((byte) 3, Lava.class);
			put((byte) 4, Rubber.class);
			put((byte) 5, Ghost_Rock.class);
			put((byte) 6, Grass.class);
			put((byte) 7, Wood.class);
			put((byte) 8, Background.class);
		}
	};
	
	private static HashMap<Class, Byte> blockIDs = new HashMap()
	{
		{
			put(Dirt.class, (byte) 1);
			put(Rock.class, (byte) 2);
			put(Lava.class, (byte) 3);
			put(Rubber.class, (byte) 4);
			put(Ghost_Rock.class, (byte) 5);
			put(Grass.class, (byte) 6);
			put(Wood.class, (byte) 7);
			put(Background.class, (byte) 8);
		}
	};
	public static byte getBlockID(Class cl)
	{
		return blockIDs.get(cl);
	}
	public static Class getBlockClass(byte id)
	{
		return blocks.get(id);
	}
}
