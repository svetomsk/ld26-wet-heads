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
		angle = getAngle(frontPart.frontPart.get—X() - frontPart.get—X(), frontPart.frontPart.get—Y() - frontPart.get—Y()) - Math.PI/2; 
				
//    	double dx = frontPart.getX() - getWidth()*Math.cos(angle);    	
//    	double dy = frontPart.getY() - getWidth()*Math.sin(angle);
//    	
//    	angle = getAngle(dx, dy) - Math.PI/2;
    	
    	double r2 = (frontPart.get—X()-get—X())*(frontPart.get—X()-get—X()) + (frontPart.get—Y()-get—Y())*(frontPart.get—Y()-get—Y());
    	
    	if(segment_gap*segment_gap < r2)
    	{
    		setX((long) (frontPart.get—X() + segment_gap*Math.cos(angle)));
    		setY((long) (frontPart.get—Y() + segment_gap*Math.sin(angle)));
    	}
	}
    @Override
    protected void initPictures() 
    {
    	img = Pictures.tailsnake;
    }
}
