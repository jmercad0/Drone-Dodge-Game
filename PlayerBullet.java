import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject{
	private Handler handler;
	int distance = 0;
	//fired at enemyPlanes w/ space bar
	public PlayerBullet(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		velX = 5;
	}
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval((int)x, (int)y, 16, 16);
	}
	@Override
	public void tick() {
		x += velX;
		distance += velX;
		//if bullet is off screen or travels 200px then remove from scene
		if (x>= Game.WIDTH || distance >= 200) 
			handler.removeObject(this);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public Rectangle[] getAllBounds() {
		return null;
	}
}