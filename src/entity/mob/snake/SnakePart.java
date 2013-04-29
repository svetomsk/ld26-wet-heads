package entity.mob.snake;

import java.awt.Graphics2D;
import java.awt.Image;

import particle.Blood;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;
import entity.mob.controllers.Controller;
import entity.mob.controllers.Group;

public class SnakePart extends Mob
{
	protected Image img;
	
	protected long prevX;
	protected long prevY;
	protected double prevAngle;
	
	protected SnakePart frontPart;
	protected SnakePart backPart;
	protected double angle;
	
	protected int segment_gap = 20;
	
	public Entity init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, SnakePart nextPart)
	{
		this.frontPart = nextPart;
		frontPart.setBackPart(this);
		return super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	
	protected void setBackPart(SnakePart backPart)
	{
		this.backPart = backPart; 
	}
	protected void setFrontPart(SnakePart frontPart)
	{
		this.frontPart = frontPart; 
	}

	@Override
	protected void finalInit(World world)
	{
		super.finalInit(world);
		control = new Controller(this);
		
		group.removeMob(this);
		Group.character.addMob(this);
		group = Group.character;
	}
	
	@Override
	public void damage(int damage, int knockback, double dir)
	{
		if(damage == 0) return;
		new Blood(get—X(), get—Y(), world);
		frontPart.damage(damage, 0, 0);
	}
    @Override
    public void tick()
    {
    	prevX = x;
    	prevY = y;
    	prevAngle = angle;

    	if(frontPart != null)
    	{
	    	x = frontPart.getPrevX();
	    	y = frontPart.getPrevY();
	    	angle = frontPart.getPrevAngle();
    	}
    	
    	super.tick();
    			
//    	if(frontPart == null) return;
//    	if(backPart == null) return;
//    	
//    	double dx = frontPart.get—X() - backPart.get—X();
//    	double dy = frontPart.get—Y() - backPart.get—Y();
//    	
//    	angle = getAngle(dx, dy) - Math.PI/2;
//    	
//    	double r2 = (frontPart.get—X()-get—X())*(frontPart.get—X()-get—X()) + (frontPart.get—Y()-get—Y())*(frontPart.get—Y()-get—Y());
//    	
//    	if(segment_gap*segment_gap < r2)
//    	{
//    		setX((long) (frontPart.get—X() + segment_gap*Math.cos(angle)));
//    		setY((long) (frontPart.get—Y() + segment_gap*Math.sin(angle)));
//    	}
    }
    
    @Override
    protected void initPictures() 
    {
    	img = Pictures.bodysnake;
    	super.initPictures();
    }
    @Override
	protected void animationTick(){}
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (get—X()-Game.x);
    	int drawy = (int) (get—Y()-Game.y);

		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
		g.rotate(-angle-Math.PI/2, drawx, drawy);
        
//		drawBounds(g);
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
		return 48;
	}
	@Override
	public int getHeight()
	{
		return 48;
	}
	
	public long getPrevX() 
	{
		return prevX;
	}
	public long getPrevY() 
	{
		return prevY;
	}
	public double getPrevAngle() 
	{
		return prevAngle;
	}
}
