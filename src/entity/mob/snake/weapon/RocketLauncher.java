package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.snake.weapon.shell.Rocket;

public class RocketLauncher extends Weapon
{
	private Image img;
	
	private static double rocketSpeed = 8;
	
	public void use()
	{
		new Rocket().init(owner.getX(), owner.getY(),
				Math.cos(owner.getAngle())*rocketSpeed, Math.sin(owner.getAngle())*rocketSpeed,
				0, 0, owner.getWorld(), owner);
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (owner.getX()-Game.x);
    	int drawy = (int) (owner.getY()-Game.y);

		double angle = owner.getAngle();
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
    }
}
