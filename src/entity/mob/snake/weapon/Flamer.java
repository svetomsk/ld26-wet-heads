package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.snake.weapon.shell.Flame;

public class Flamer extends Weapon
{
	private Image img;

	private static double flameSpeed = 16;
	
	@Override
	public void use(double angle)
	{
		angle += Math.PI*(Math.random()-0.5)/6;
		new Flame().init(owner.getCX(), owner.getCY(),
			Math.cos(angle)*flameSpeed, Math.sin(angle)*flameSpeed,
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
    	int drawx = (int) (owner.getCX()-Game.x);
    	int drawy = (int) (owner.getCY()-Game.y);

		double angle = owner.getAngle();
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
    }
}
