

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	private Handler handler;
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		velX = 5;
		velY = 0;
	}
	public void render(Graphics g) {
	
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 48, 16);
		g.setColor(Color.BLUE);
		g.drawLine((int)x+20, (int)y, (int)x+38, (int)y-16);
		g.drawLine((int)x+48, (int)y, (int)x+66, (int)y-16);
		g.drawLine((int)x+66, (int)y-16, (int)x+38, (int)y-16);
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		if (y<=0 || y>=Game.HEIGHT-32) {
			velY *= -1;
		}
		if (x<=0 || x>=Game.WIDTH-10) {
			velX*=-1;
		}
//		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16,(float) 0.04,handler));
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
}
