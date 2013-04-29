package items;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;

import entity.mob.snake.SnakeHead;
import entity.mob.snake.weapon.Flamer;
import entity.mob.snake.weapon.RocketLauncher;

public class Flame_item extends Item{
	
	private Image img; 
	
	@Override
	public void onDeath()
	{
		world.apple_quantity--;
		super.onDeath();
	}
	
	@Override
	protected void initPictures()
	{
		img = Pictures.flame_item;
	}
	
	@Override
	protected boolean interactOnSnakeHead(SnakeHead snakeHead)
	{
		snakeHead.setNewWeapon(new Flamer());
		delete();
		return true;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		int drawx = (int) (getX()-Game.x);
    	int drawy = (int) (getY()-Game.y);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
	}
}
