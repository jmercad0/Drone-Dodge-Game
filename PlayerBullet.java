import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PlayerBullet extends GameObject{
	private Handler handler;
	Random r = new Random();
	int distance = 0;
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
		// TODO Auto-generated method stub
		x += velX;
		distance += velX;
		if (x>= Game.WIDTH || distance >= 200) handler.removeObject(this);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public Rectangle[] getAllBounds() {
		// TODO Auto-generated method stub
		Rectangle[] r = new Rectangle[1];
		r[0] = new Rectangle((int)x,(int)y,16,16);
		return r;
	}
}