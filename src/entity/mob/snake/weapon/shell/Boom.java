package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.Entity;
import entity.mob.Mob;

public class Boom extends Entity
{
	private Image img;
	
	private int size = 16;
	
	@Override
	public void tick()
	{
		size+=16;
		x-=8;
		y-=8;
		
		if(size > 512)
		{
			delete();
		}
		
		super.tick();
	}
	
	@Override
	protected boolean collideIslands(boolean verticalWalls)
	{
		return false;
	}
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		mob.damage(10, 0, 0);
		return true;
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);

		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
		drawBounds(g);
    }
    
    @Override
    public int getWidth()
    {
    	return size;
    }
    @Override
    public int getHeight()
    {
    	return size;
    }
}
