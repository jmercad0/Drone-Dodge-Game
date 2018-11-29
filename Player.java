
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
		x+=velX+1;
		y+=velY;
		
		x = Game.clamp((int)x,10,Game.WIDTH - 78);
		y = Game.clamp((int)y, 18, Game.HEIGHT - 88);
		
		collision();
	}
	public void collision() {
		for (int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				for(Rectangle r: tempObject.getAllBounds()) {
					if(getBounds().intersects(r)) {
						handler.removeObject(tempObject);
						HUD.lives--;
					}
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		
		g.fillRect((int)x, (int)y, 60, 45);
		g.fillRect((int)x-10, (int)y+5, 10, 52);
		g.fillRect((int)x+60, (int)y+5, 10, 52);
		g.drawLine((int)x+30, (int)y, (int)x+30, (int)y-15);
		g.fillRect((int)x-10, (int)y-15, 80, 3);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-10,(int)y-15,80,72);
	}
	@Override
	public Rectangle[] getAllBounds() {
		return null;
	}
}