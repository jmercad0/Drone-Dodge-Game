
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject{
	Random r = new Random();
	Handler handler;
	public Player(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
	}


	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		x = Game.clamp((int)x,0,Game.WIDTH - 38);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60);
		
		collision();
	}
	/*
	 * Still need to re-adjust to fit entire shape of drone
	 */
	public void collision() {
		for (int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		g.fillRect((int)x, (int)y, 50, 25);
		g.fillRect((int)x-10, (int)y+5, 10, 32);
		g.fillRect((int)x+50, (int)y+5, 10, 32);
		g.drawLine((int)x+25, (int)y, (int)x+25, (int)y-15);
		g.fillRect((int)x, (int)y-15, 50, 3);
	}
	/*
	 * Still needs to model for entire shape of drone
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,50,25);
	}
	
	


	
}