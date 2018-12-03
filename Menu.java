import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	public void mousePressed(MouseEvent e) {
		//mouse coordinates
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == Game.STATE.Menu) {
			//play button
			if (mouseOver(mx,my,225,100,200,75)) {
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH/10+32,Game.HEIGHT/2+32,ID.Player,handler));
			}
			//exit button
			if (mouseOver(mx,my,225,200,200,75)) {
				System.exit(0);
			}
		}
		if (game.gameState == Game.STATE.GameOver ||game.gameState == Game.STATE.Victory) {
			//play button
			if (mouseOver(mx,my,210, 170, 200, 64)) {
				hud.score(0);
				Spawn.resetScoreKeep();
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH/10+32,Game.HEIGHT/2+32,ID.Player,handler));
			}
			//menu button
			else if (mouseOver(mx,my,210, 270, 200, 64)) {
				hud.score(0);
				Spawn.resetScoreKeep();
				game.gameState = Game.STATE.Menu;
			}
			//exit button
			else if(mouseOver(mx,my,210,370,200, 64)) {
				System.exit(0);
			}
		}
	}
	//used for menu system logic, determines update of gameState depending on box pressed
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
	public void render(Graphics g) {
		//start menu screen
		if (game.gameState == Game.STATE.Menu) {	
			Font fnt = new Font("arial",50,50);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawRect(225,100,200,75);
			g.drawString("Play",270,155);
			g.drawRect(225,200,200,75);
			g.drawString("Quit",270,255);
		}
		//gameOver screen
		else if (game.gameState == Game.STATE.GameOver) {
			Font fnt = new Font("arial",50,50);
			Font fnt2 = new Font("arial", 25,25);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("Game Over", 183, 70);
			g.setFont(fnt2);
			g.drawString("You lost with a score of: "+hud.getScore(), 160, 140);
			g.drawRect(210, 370, 200, 64);
			g.drawRect(210, 270, 200, 64);
			g.drawRect(210, 170	,200, 64);	
			g.drawString("Try Again", 255, 210);
			g.drawString("Return to Menu", 225, 310);
			g.drawString("Quit", 285, 410);
		}
		//victory screen
		else if(game.gameState == Game.STATE.Victory) {
			Font fnt = new Font("arial",50,50);
			Font fnt2 = new Font("arial", 25,25);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("Victory!", 230, 70);
			g.setFont(fnt2);
			g.drawString("You've beaten the enemies!", 160, 140);
			g.drawRect(210, 370, 200, 64);
			g.drawRect(210, 270, 200, 64);
			g.drawRect(210, 170	,200, 64);	
			g.drawString("Play Again", 255, 210);
			g.drawString("Return to Menu", 225, 310);
			g.drawString("Quit", 285, 410);
		}
	}
	public void mouseReleased(MouseEvent e) {}
}
