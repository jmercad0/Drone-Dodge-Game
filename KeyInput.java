import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//manages movement and bullets for player
public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	//uses handler to access all gameObjects
	public KeyInput(Handler handler) {
		this.handler = handler;
		for (int i = 0; i<4;i++) {
			keyDown[i] = false;
		}
	}
	//takes input from keys to move or add gameObject in scene
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		
		for (int i = 0; i<handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			//only applies to player drone
			if (temp.getId()==ID.Player) {
				//use up,down,left,right arrow keys for movement
				if (key == KeyEvent.VK_UP) { temp.setVelY(-3); keyDown[0]=true; }
				if (key == KeyEvent.VK_DOWN) { temp.setVelY(3);  keyDown[1]=true; }
				if (key == KeyEvent.VK_RIGHT) { temp.setVelX(3);  keyDown[2]=true; }
				if (key == KeyEvent.VK_LEFT) { temp.setVelX(-3); keyDown[3]=true; }
				//use space to fire playerBullet
				if (key == KeyEvent.VK_SPACE) { handler.addObject(new PlayerBullet((int)temp.getX()+10,(int)temp.getY(),ID.PlayerBullet,handler)); }
			}
		}
		//exit scene with esc
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	//halts behavior of movement once key is released
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		
		for (int i = 0; i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			//only applies to player drone
			if (tempObject.getId()==ID.Player) {
				if (key == KeyEvent.VK_UP) {keyDown[0]=false; }
				if (key == KeyEvent.VK_DOWN) {keyDown[1]=false; }
				if (key == KeyEvent.VK_RIGHT) {keyDown[2]=false; }
				if (key == KeyEvent.VK_LEFT) {keyDown[3]=false; }
				
				//vertical movement halt
				if(!keyDown[0] && !keyDown[1]) { tempObject.setVelY(0); }
				//horiztonal movement halt
				if(!keyDown[2] && !keyDown[3]) { tempObject.setVelX(0); }
			}
		}
	}
}
