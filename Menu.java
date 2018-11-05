

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == Game.STATE.Menu) {
			//play button
			if (mouseOver(mx,my,225,100,200,75)) {
				game.gameState = Game.STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2+32,Game.HEIGHT/2+32,ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			//exit button
			if (mouseOver(mx,my,225,300,200,75)) {
				System.exit(0);
			}
		
			//help button
			if (mouseOver(mx,my,225,200,200,75)) {
				game.gameState = Game.STATE.Help;
			}
		}
		//back button for help
		if (game.gameState == Game.STATE.Help) {
			if (mouseOver(mx,my,225,300,300,75)) {
				game.gameState = Game.STATE.Menu;
			}
		}
		if (game.gameState == Game.STATE.GameOver) {
			if (mouseOver(mx,my,210, 370, 200, 64)) {
				hud.score(1);
				hud.score(0);
				Spawn.resetScoreKeep();
				game.gameState = Game.STATE.Game;
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				handler.addObject(new Player(Game.WIDTH/2+32,Game.HEIGHT/2+32,ID.Player,handler));
			}
			else if (mouseOver(mx,my,210, 270, 200, 64)) {
				game.gameState = Game.STATE.Menu;
				hud.score(1);
				hud.score(0);
				Spawn.resetScoreKeep();
			}
			else if(mouseOver(mx,my,210,170,200, 64)) {
				System.exit(0);
			}
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
	public void render(Graphics g) {
		if (game.gameState == Game.STATE.Menu) {	
			Font fnt = new Font("arial",50,50);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawRect(225,100,200,75);
			g.drawString("Play",270,155);
			
			g.drawRect(225,200,200,75);
			g.drawString("Help",270,255);
			
			g.drawRect(225,300,200,75);
			g.drawString("Quit",270,355);
		}
		else if (game.gameState == Game.STATE.Help) {
			Font fnt = new Font("arial",50,50);
			Font fnt2 = new Font("arial", 25,25);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Help",270,80);
			g.drawString("Back",270,355);
			g.setFont(fnt2);
			g.drawString("Use WASD to move player. Avoid the enemies", 55, 200);
			g.drawString("Press spacebar to fire at boss", 150, 240);
			g.drawRect(240,300,175,75);
			
		}
		else if (game.gameState == Game.STATE.GameOver) {
			Font fnt = new Font("arial",50,50);
			Font fnt2 = new Font("arial", 25,25);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Game Over", 210, 70);
			g.setFont(fnt2);
			g.drawString("You lost with a score of: "+hud.getScore(), 180, 140);
			g.drawRect(210, 370, 200, 64);
			g.drawRect(210, 270, 200, 64);
			g.drawRect(210, 170	,200, 64);	
			g.drawString("Try again?", 260, 410);
			g.drawString("Return to Menu?", 220, 310);
			g.drawString("Quit", 280, 210);
		}
	}
}
