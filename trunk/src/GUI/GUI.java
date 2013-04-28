package GUI;

import items.Item;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import main.Game;
import main.Input;
import main.Pictures;
import entity.mob.Angel;
import entity.mob.Mob;
import entity.mob.controllers.Controller;
import entity.mob.snake.SnakeHead;

public class GUI extends Controller
{
	protected Input input;
	public boolean stepState = true;
	private Item leftHand;
	
	private SnakeHead mob;
	
	public GUI(Mob mob, Input input) {
		super(mob);
		this.input = input;
		this.mob = (SnakeHead) mob;
//		leftHand = new SwordItem(mob);
	}	
	@Override
	public void tick()
	{
        //walk
        if(input.right.down) mob.onRight();
        if(input.left.down) mob.onLeft();
        if(input.up.down) mob.onUp();
        if(input.down.down) mob.onDown();
        
//        //weapon
//        if(input.b0Clicked)
//        {
//        	if(leftHand != null)
//        		leftHand.use(input.x+Game.x, input.y+Game.y);
//        }
        
        //throw item
        if(input.space.typed)
        {
        	mob.getWep().use();
        }
        if(input.q.typed)
        {
        	try
			{
				Game.finishLevel();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//        	if(leftHand!=null)
//        	{
//		    	leftHand.throwItem();
//		    	leftHand = null;
//        	}
        }
        
    	//----------------------------------------------------------
        
		if(input.wheel)
		{
			new Angel().init((input.x+Game.x), (input.y+Game.y), mob.getWorld());
//			new DamageMignonSeed((input.x+Game.x), (input.y+Game.y), mob.getWorld());
//			new JumpMignon((input.x+Game.x), (input.y+Game.y), mob.getWorld(), mob);
//			new DamageMignon((input.x+Game.x), (input.y+Game.y), mob.getWorld(), mob);
//			new Zombie((input.x+Game.x), (input.y+Game.y), mob.getWorld());
//			new SwordItem((input.x+Game.x), (input.y+Game.y), mob.getWorld());
//			new Wind((input.x+Game.x), (input.y+Game.y), mob.getWorld());
		}
	}
	
	public void tickGlobal()
	{	
//		if(input.restart.typed)
//		{
//			mob.getWorld().clear();
//			mob.getWorld().createLevel();
//		}
        if(input.quit.typed)
        {
            Game.addMenu();
        }
        if(input.quicksave.typed)
        {
        	Game.quickSave();
        }
        if(input.quickload.typed)
        {
        	Game.quickLoad();
        }
        
        if(mob.isDeleted())
        {
//        	Game.addMenu();
        	Game.showDeath();
        }
        if(input.pause.typed)
        {
           stepState = !stepState;
        }
	}
	@Override
	public boolean tryGet(Item item) {
//		if(leftHand==null)
//		{
//			leftHand = item;
//			return true;
//		}
		return false;
	}
	public void draw(Graphics2D g)
	{
		if(!stepState)
		{
			Image value = Pictures.pause;
			g.drawImage(value, (int)(Game.WIDTH/2-value.getWidth(null)/2), 128, null);
		}
	}
	
	public long getMobX()
	{
		return mob.getX();
	}
	public long getMobY()
	{
		return mob.getY();
	}
	
	public int getX()
	{
		return input.x;
	}
	
	public int getY()
	{
		return input.y;
	}
}
