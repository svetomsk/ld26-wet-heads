package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;
import entity.mob.snake.SnakeHead;
import entity.mob.snake.SnakePart;
import entity.mob.snake.SnakeTail;

public class Weapon extends Entity
{
	protected SnakeHead owner;
	private Image img;
	
	public Weapon init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, SnakeHead owner)
	{
		this.owner = owner;
		return (Weapon) super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	
	
	public void use()
	{
		
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getÑX()-Game.x);
    	int drawy = (int) (getÑY()-Game.y);

		double angle = owner.getAngle();
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
        
//        double angle = getAngle(control.getX()-drawx, control.getY()-drawy)+Math.PI/2;
        
//        Image eye = control.getX()-drawx >= 0 ? Pictures.eye_right : Pictures.eye_left;
//        if(control.getX()-drawx < 0 ) angle -= Math.PI;
//        
//        g.rotate(angle, drawx, drawy);
//        g.drawImage(eye, drawx-width/2, drawy-height/2, null);
//        g.rotate(-angle, drawx, drawy);
        
//        super.draw(g);
//        drawHealth(g);
		drawBounds(g);
    }
}
