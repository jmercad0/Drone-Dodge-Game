import java.awt.Graphics;
import java.util.LinkedList;

//manages resources, logic & rendering of all game objects ins scene
public class Handler {
	LinkedList<GameObject> object = new LinkedList<>();
	public void tick() {
		for (int i = 0; i<object.size();i++) {
			object.get(i).tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i<object.size();i++) {
			object.get(i).render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void clearEnemies() {
		object.clear();
	}
}
