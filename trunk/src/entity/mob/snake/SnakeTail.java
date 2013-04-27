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
		frontPart.setBackPart(this);
		return super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	@Override
	public void tick()
	{
		angle = getAngle(frontPart.frontPart.getX() - frontPart.getX(), frontPart.frontPart.getY() - frontPart.getY()) - Math.PI/2; 
				
//    	double dx = frontPart.getX() - getWidth()*Math.cos(angle);    	
//    	double dy = frontPart.getY() - getWidth()*Math.sin(angle);
//    	
//    	angle = getAngle(dx, dy) - Math.PI/2;
    	
    	double r2 = (frontPart.getX()-getX())*(frontPart.getX()-getX()) + (frontPart.getY()-getY())*(frontPart.getY()-getY());
    	
    	if(getWidth()*getWidth() < r2)
    	{
    		setX((long) (frontPart.getX() + getWidth()*Math.cos(angle)));
    		setY((long) (frontPart.getY() + getWidth()*Math.sin(angle)));
    	}
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
