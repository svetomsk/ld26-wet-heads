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
	private double angle = Math.PI;
	
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
		for(int q=0;q<4;q++)
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
		lvx = v*Math.cos(angle);
		lvy = v*Math.sin(angle);
		super.tick();
	}
	@Override
	protected void slowly()
	{
		v *= getSpeed()/(getSpeed()+1);
	}
	
	@Override
	public void damage(int damage, int knockback, double dir)
	{
//		if(cooldownAfterDamage>0) return;
//		super.damage(damage, knockback, dir);
//		cooldownAfterDamage = 12;
	}
	
	private double criticalAngle = Math.PI/4; 
	
	@Override
	public void onLeft()
	{
//		if(getAngle(getX() - backPart.getX(), getY() - backPart.getY())-criticalAngle)
		angle -= Math.PI/50;
	}
	@Override
	public void onRight()
	{
		angle += Math.PI/50;
	}
	
    @Override
    public void onUp() 
    {
    	v++;
    }
    @Override
	public void onDown()
	{
//    	if(v>0)
//    	v--;
	}
    
    @Override
    protected void initPictures() 
    {
    	img = Pictures.headsnake;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getX()-Game.x);
    	int drawy = (int) (getY()-Game.y);

//		double angle = getAngle(lvx, lvy);
		  
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
    }
    @Override
	public double getSpeed()
	{
		return 10;
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

    private int satiety = 0;
	public void feed()
	{
		satiety++;
		addBody();
	}
	
	private void addBody()
	{
		SnakePart nbp = new SnakePart();
		SnakeTail tail = (SnakeTail) body.get(body.size()-1);
		SnakePart bp = body.get(body.size()-2); 
		
		body.remove(body.size()-1);
		
		nbp.init(tail.getX(), tail.getY(), 0, 0, 0, 0, world, bp);
		nbp.setFrontPart(bp);
		nbp.setBackPart(tail);
		bp.setBackPart(nbp);
		body.add(nbp);
		
		tail.setFrontPart(nbp);
		body.add(tail);
	}

	public int getSatiety()
	{
		return satiety;
	}
}
