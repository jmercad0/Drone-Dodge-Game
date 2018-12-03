import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyPlane extends GameObject{
	private Handler handler;
	private Random r = new Random();
	public EnemyPlane(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		velX = Math.negateExact(r.nextInt(5)+3);
		velY = 0;
	}
	public void render(Graphics g) {
		//draw body
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 48, 16);
		//draw wings
		g.setColor(Color.BLUE);
		g.drawLine((int)x+20, (int)y, (int)x+38, (int)y-16);
		g.drawLine((int)x+48, (int)y, (int)x+66, (int)y-16);
		g.drawLine((int)x+66, (int)y-16, (int)x+38, (int)y-16);
		g.drawLine((int)x+15, (int)y+14, (int)x+33, (int)y+32);
		g.drawLine((int)x+43, (int)y+14, (int)x+61, (int)y+32);
		g.drawLine((int)x+61, (int)y+32, (int)x+33, (int)y+32);
	}
	//repeatedly called to detect hits
	public void collision() {
		for (int i = 0; i< handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.PlayerBullet) {
				for(Rectangle r: getAllBounds()) {
					//if bullet hits plane
					if(r.getBounds().intersects(tempObject.getBounds())) {
						handler.removeObject(this);
						handler.removeObject(tempObject);
					}
				}
			}
		}
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		//if plane is out of scene, remove plane
		if (x<=-20 ) {
			handler.removeObject(this);
		}
		collision();
	}
	//return hit detection boxes
	public Rectangle getBounds() {
		return null;
	}
	public Rectangle[] getAllBounds() {
		Rectangle[] bounds = new Rectangle[3];
		bounds[0] = new Rectangle((int)x,(int)y,48,16);
		bounds[1] = new Rectangle((int)x+20,(int)y-16,46,16);
		bounds[2] = new Rectangle((int)x+20,(int)y+16,46,16);
		return bounds;
	}
}
