package entity.mob.snake;

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
		for(int q=0;q<8;q++)
		{
			SnakePart part = body.get(body.size()-1);
			body.add((SnakePart) new SnakePart().init(part.getX()+part.getWidth(), part.getY(), 0, 0, 0, 0, world, part));
		}
		SnakePart part = body.get(body.size()-1);
		body.add((SnakePart) new SnakeTail().init(part.getX()+part.getWidth(), part.getY(), 0, 0, 0, 0, world, part));
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
    	lvy-=0.7;
    }
    @Override
	public void onDown()
	{
		super.onDown();
    	lvy+=0.7;
	}
    
    @Override
    protected void initPictures() 
    {
    	img = Pictures.roll;
    	super.initPictures();
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
