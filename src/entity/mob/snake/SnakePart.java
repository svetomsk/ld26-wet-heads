package entity.mob.snake;

import java.awt.Graphics2D;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;
import entity.mob.controllers.Controller;

public class SnakePart extends Mob
{
//	public int cooldownAfterDamage;

	protected SnakePart frontPart;
	protected SnakePart backPart;
	protected double angle;
	
	public Entity init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, SnakePart nextPart)
	{
		this.frontPart = nextPart;
		frontPart.setBackPart(this);
		return super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	private void setBackPart(SnakePart backPart)
	{
		this.backPart = backPart; 
	}

	@Override
	protected void finalInit(World world)
	{
		super.finalInit(world);
		control = new Controller(this);
	}
	
	@Override
	public void damage(int damage, int knockback, double dir)
	{
		super.damage(damage, 0, dir);
	}
    @Override
    public void tick()
    {
    	super.tick();

//    	if(frontPart == null) return;
//    	if(backPart == null) return;
//    	
//    	double dx = frontPart.getX() - backPart.getX();
//    	double dy = frontPart.getY() - backPart.getY();
//    	
//    	angle = getAngle(dx, dy) - Math.PI/2;
//    	
//    	double r2 = (frontPart.getX()-getX())*(frontPart.getX()-getX()) + (frontPart.getY()-getY())*(frontPart.getY()-getY());
//    	
//    	if(getWidth()*getWidth() < r2)
//    	{
//    		setX((long) (frontPart.getX() + getWidth()*Math.cos(angle)));
//    		setY((long) (frontPart.getY() + getWidth()*Math.sin(angle)));
//    	}
    }
    
    @Override
    protected void initPictures() 
    {
    	img = Pictures.roll;
    	super.initPictures();
    }
    @Override
    public void draw(Graphics2D g)
    {
    	
    	int drawx = (int) (x-Game.x+width/2);
    	int drawy = (int) (y-Game.y+height/2);

		double angle = getAngle(lvx, lvy);
		  
		g.rotate(angle, drawx, drawy);
		g.drawImage(img[currentFrame], drawx-img[currentFrame].getWidth(null)/2, drawy-img[currentFrame].getHeight(null)/2, null);
		g.rotate(-angle, drawx, drawy);
        
//        double angle = getAngle(control.getX()-drawx, control.getY()-drawy)+Math.PI/2;
        
//        Image eye = control.getX()-drawx >= 0 ? Pictures.eye_right : Pictures.eye_left;
//        if(control.getX()-drawx < 0 ) angle -= Math.PI;
//        
//        g.rotate(angle, drawx, drawy);
//        g.drawImage(eye, drawx-width/2, drawy-height/2, null);
//        g.rotate(-angle, drawx, drawy);
        
//        super.draw(g);
        drawHealth(g);
    }
    
    @Override
	public double getSpeed()
	{
		return 15;
	}
//    @Override
//	public double getJumpPower()
//	{
//		return 13;
//	}
    @Override
	public int getMaxHP()
	{
		return 500;
	}
    @Override
	public int getDamage()
	{
		return 0;
	}
    @Override
	public int getKnokback()
	{
		return 0;
	}
    @Override
	public double getStrength()
	{
		return 0;
	}
    @Override
	public int getWidth()
	{
		return 20;
	}
	@Override
	public int getHeight()
	{
		return 20;
	}
}
