package entity.mob.snake;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;
import entity.mob.controllers.Controller;

public class SnakePart extends Mob
{
	protected Image img;
	
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

    	if(frontPart == null) return;
    	if(backPart == null) return;
    	
    	double dx = frontPart.getX() - backPart.getX();
    	double dy = frontPart.getY() - backPart.getY();
    	
    	angle = getAngle(dx, dy) - Math.PI/2;
    	
    	double r2 = (frontPart.getX()-getX())*(frontPart.getX()-getX()) + (frontPart.getY()-getY())*(frontPart.getY()-getY());
    	
    	if(segment_gap*segment_gap < r2)
    	{
    		setX((long) (frontPart.getX() + segment_gap*Math.cos(angle)));
    		setY((long) (frontPart.getY() + segment_gap*Math.sin(angle)));
    	}
//    	if(backPart == null) return;
    	
//    	double dx = frontPart.getX() + getWidth()*Math.cos(angle);    	
//    	double dy = frontPart.getY() + getWidth()*Math.sin(angle);
//    	
//    	System.out.println(getWidth() * Math.cos(angle));
//    	
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
    	img = Pictures.bodysnake;
    	super.initPictures();
    }
    @Override
	protected void animationTick(){}
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (x-Game.x+getWidth()/2);
    	int drawy = (int) (y-Game.y+getHeight()/2);

//		double angle = getAngle(lvx, lvy);
		  
		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
		g.rotate(-angle-Math.PI/2, drawx, drawy);
        
//        double angle = getAngle(control.getX()-drawx, control.getY()-drawy)+Math.PI/2;
        
//        Image eye = control.getX()-drawx >= 0 ? Pictures.eye_right : Pictures.eye_left;
//        if(control.getX()-drawx < 0 ) angle -= Math.PI;
//        
//        g.rotate(angle, drawx, drawy);
//        g.drawImage(eye, drawx-width/2, drawy-height/2, null);
//        g.rotate(-angle, drawx, drawy);
        
//        super.draw(g);
//        drawHealth(g);
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
		return 64;
	}
	@Override
	public int getHeight()
	{
		return 64;
	}
}
