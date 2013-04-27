package entity.mob.snake;

import main.Pictures;
import main.World;
import entity.Entity;

public class SnakeTail extends SnakePart
{
//	public int cooldownAfterDamage;

	public Entity init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, SnakePart nextPart)
	{
		this.frontPart = nextPart;
		return super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	@Override
	public void damage(int damage, int knockback, double dir)
	{
		super.damage(damage, 0, dir);
	}
    @Override
    protected void initPictures() 
    {
    	img = Pictures.roll;
    	super.initPictures();
    }
}
