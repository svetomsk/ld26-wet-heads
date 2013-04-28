package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.Entity;
import entity.mob.Mob;

public class Rocket extends Shell
{
	private Image img;
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		if(mob.getGroup() != owner.getGroup())
		{
			new Boom();
			delete();
		}
		return super.interactOnMob(mob);
	}

	@Override
	protected void interactOn(byte id)
	{
		new Boom();
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getX()-Game.x);
    	int drawy = (int) (getY()-Game.y);

		double angle = getAngle(getLVX(), getLVY());
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
        
		drawBounds(g);
    }
}
