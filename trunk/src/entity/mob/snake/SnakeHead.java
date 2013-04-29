package entity.mob.snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

import particle.Blood;

import main.Game;
import main.Pictures;
import main.World;
import GUI.GUI;
import entity.mob.controllers.Group;
import entity.mob.snake.weapon.RocketLauncher;
import entity.mob.snake.weapon.Weapon;

public class SnakeHead extends SnakePart
{
	
	private Weapon wep;
	
	public Weapon getWep()
	{
		return wep;
	}
	
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
		
		body.add((SnakePart) new SnakePart().init(get�X()+getWidth(), get�Y(), 0, 0, 0, 0, world, this));
		for(int q=0;q<9;q++)
		{
			SnakePart part = body.get(body.size()-1);
			body.add((SnakePart) new SnakePart().init(part.get�X()+part.getWidth(), part.get�Y()-part.getHeight()/2, 0, 0, 0, 0, world, part));
		}
		SnakePart part = body.get(body.size()-1);
		body.add((SnakePart) new SnakeTail().init(part.get�X()+part.getWidth(), part.get�Y(), 0, 0, 0, 0, world, part));
		
		v = 9;
	}
	
	private boolean speedUp = false;
	
	@Override
	public void tick()
	{
		v++;
		slowly();
		
		speedUp = false;
		super.tick();
		
		lvx = v*Math.cos(angle);
		lvy = v*Math.sin(angle);
	}
	@Override
	protected void slowly()
	{
		v *= getSpeed()/(getSpeed()+1);
	}
	@Override
	public void onUp() 
	{
		super.onUp();
		v++;
		speedUp = true;
	}
	
	private double criticalAngle = Math.PI/4; 
	
	@Override
	public void onLeft()
	{
//		if(getAngle(getX() - backPart.getX(), getY() - backPart.getY())-criticalAngle)
		int k;
		if(speedUp)
		{
			k = 70;
		}
		else k = 40;
		angle -= Math.PI/k;
	}
	@Override
	public void onRight()
	{
		int k;
		if(speedUp)
		{
			k = 70;
		}
		else k = 40;
		angle += Math.PI/k;
	}

    @Override
    public void damage(int damage, int knockback, double dir)
	{	
		if(damage == 0) return;
		hp -= Math.max(damage - getStrength(), 0);
		for(int q=0;q<1;q++)
		{
			new Blood(get�X(), get�Y(), world);
		}
	}	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.headsnake;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (get�X()-Game.x);
    	int drawy = (int) (get�Y()-Game.y);
		  
		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-3*img.getHeight(null)/4, null);
		g.rotate(-angle-Math.PI/2, drawx, drawy);
        
//		drawBounds(g);
		drawHealth(g);
    }
    @Override
	public double getSpeed()
	{
		return 16;
	}
//    @Override
//	public double getJumpPower()
//	{
//		return 1;
//	}
    @Override
	public int getMaxHP()
	{
		return 8000;
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
    public double getAngle()
	{
		return angle;
	}

    private int satiety = 0;
	public void feed()
	{
		satiety++;
		hp += 1000;
		if(hp > getMaxHP())
		{
			hp = getMaxHP();
		}
		addBody();
	}
	
	private void addBody()
	{
		SnakePart nbp = new SnakePart();
		SnakeTail tail = (SnakeTail) body.get(body.size()-1);
		SnakePart bp = body.get(body.size()-2); 
		
		body.remove(body.size()-1);
		
		nbp.init(tail.get�X(), tail.get�Y(), 0, 0, 0, 0, world, bp);
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

	public void setNewWeapon(Weapon wep)
	{
		this.wep = wep.init(get�X(), get�Y(), 0, 0, 0, 0, world, this);
	}
}
