import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	float x, y;
	ID id;
	float velX, velY;
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	//updates position & detects collision, game logic
	public abstract void tick();
	//draws gameObject in scene
	public abstract void render(Graphics g);
	//gets hit detection boxes
	public abstract Rectangle getBounds();
	public abstract Rectangle[] getAllBounds();
	//getters & setters for position, velocity & ID
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
	public ID getId() {
		return id;
	}
	public void setVelX(float x) {
		this.velX = x;
	}
	public void setVelY(float y) {
		this.velY = y;
	}
}
