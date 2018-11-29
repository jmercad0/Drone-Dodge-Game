
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		for (int i = 0; i<4;i++) {
			keyDown[i] = false;
		}
	}
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		
		for (int i = 0; i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()==ID.Player) {
				if (key == KeyEvent.VK_UP) { tempObject.setVelY(-3); keyDown[0]=true; }
				if (key == KeyEvent.VK_DOWN) { tempObject.setVelY(3);  keyDown[1]=true; }
				if (key == KeyEvent.VK_RIGHT) { tempObject.setVelX(3);  keyDown[2]=true; }
				if (key == KeyEvent.VK_LEFT) { tempObject.setVelX(-3); keyDown[3]=true; }
				if (key == KeyEvent.VK_SPACE) { handler.addObject(new PlayerBullet((int)tempObject.getX()+10,(int)tempObject.getY(),ID.PlayerBullet,handler)); }

			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		
		for (int i = 0; i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()==ID.Player) {
				if (key == KeyEvent.VK_UP) { keyDown[0]=false; }
				if (key == KeyEvent.VK_DOWN) {keyDown[1]=false; }
				if (key == KeyEvent.VK_RIGHT) {keyDown[2]=false; }
				if (key == KeyEvent.VK_LEFT) {keyDown[3]=false; }
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horiztonal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}
