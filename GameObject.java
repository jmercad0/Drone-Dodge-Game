import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle[] getAllBounds();
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(float x) {
		this.velX = x;
	}
	public void setVelY(float y) {
		this.velY = y;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}

}
