package entity.mob.snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Game;
import main.Pictures;
import main.World;
import GUI.GUI;
import entity.mob.controllers.Group;

public class SnakeHead extends SnakePart
{
//	public int cooldownAfterDamage;
	private GUI control;
	private double v = 0;
	
	public ArrayList<SnakePart> body = new ArrayList<SnakePart>();
	
	@Override
	public void finalInit(World world)
	{
		super.finalInit(world);
		super.control = new GUI(this, Game.getInput());
		control = (GUI) super.control;
		Game.setGUI((GUI)control);
		
		group.removeMob(this);
		Group.character.addMob(this);
		group = Group.character;
		
		body.add((SnakePart) new SnakePart().init(getX()+getWidth(), getY(), 0, 0, 0, 0, world, this));
		for(int q=0;q<64;q++)
		{
			SnakePart part = body.get(body.size()-1);
			body.add((SnakePart) new SnakePart().init(part.getX()+part.getWidth(), part.getY()-part.getHeight()/2, 0, 0, 0, 0, world, part));
		}
		SnakePart part = body.get(body.size()-1);
		body.add((SnakePart) new SnakeTail().init(part.getX()+part.getWidth(), part.getY(), 0, 0, 0, 0, world, part));
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		angle = getAngle(lvx, lvy)-Math.PI/2;
	}
	
	@Override
	public void damage(int damage, int knockback, double dir)
	{
//		if(cooldownAfterDamage>0) return;
//		super.damage(damage, knockback, dir);
//		cooldownAfterDamage = 12;
	}
	
    @Override
    public void onUp() 
    {
    	super.onUp();
    	v++;
    }
    @Override
	public void onDown()
	{
		super.onDown();
    	v--;
	}
    
    @Override
    protected void initPictures() 
    {
    	img = Pictures.roll;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (x-Game.x+width/2);
    	int drawy = (int) (y-Game.y+height/2);

//		double angle = getAngle(lvx, lvy);
		  
		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.drawImage(img[currentFrame], drawx-img[currentFrame].getWidth(null)/2, drawy-img[currentFrame].getHeight(null)/2, null);
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
    }
    @Override
	public double getSpeed()
	{
		return 9;
	}
//    @Override
//	public double getJumpPower()
//	{
//		return 1;
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
}
