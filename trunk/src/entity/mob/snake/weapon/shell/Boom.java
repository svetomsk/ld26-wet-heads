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
	
	private int size = 0;
	
	@Override
	public void tick()
	{
		size++;
		
		if(size > 128)
		{
			delete();
		}
		
		super.tick();
	}
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		mob.damage(30, 0, 0);
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
    	int drawx = (int) (getX()-Game.x);
    	int drawy = (int) (getY()-Game.y);

    	
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
        
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
