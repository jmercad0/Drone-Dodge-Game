
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;



public class GameOver extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	public GameOver(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (mouseOver(mx,my,180, 260, 250, 100)) {
			Game.gameState = Game.STATE.Menu;
		}
		
	}
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if (mx>x && mx<x+width) {
			if (my>y && my<y+height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public void tick() {
		
	}
	public static void render(Graphics g) {
		Font fnt = new Font("arial",50,50);
		Font fnt2 = new Font("arial",25,25);
		g.setFont(fnt);
		g.drawString("Game Over :(", 160, 50);
		g.drawRect(180, 260, 250, 100);
		g.setFont(fnt2);
		g.drawString("Return to Menu", 250, 320);
		
		
	}
}