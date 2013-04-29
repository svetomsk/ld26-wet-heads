package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.snake.weapon.shell.Snow;

public class Snower extends Weapon
{
	private Image img;

	private static int cooldown = 60;
	private int currentCooldown = 60;
	
	private static double rocketSpeed = 16;
	
	@Override
	public void use(double angle)
	{
		if(currentCooldown < 0)
		{
			new Snow().init(owner.getCX(), owner.getCY(),
				Math.cos(angle)*rocketSpeed, Math.sin(angle)*rocketSpeed,
				0, 0, owner.getWorld(), owner);
			currentCooldown = cooldown;
		}
	}
	
	@Override
	public void tick()
	{
		super.tick();
		currentCooldown--;
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (owner.getCX()-Game.x);
    	int drawy = (int) (owner.getCY()-Game.y);

		double angle = owner.getAngle();
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
    }
}
