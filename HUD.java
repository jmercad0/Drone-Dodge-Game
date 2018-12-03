import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public static int lives = 3;
	private int score = 0;
	public void render(Graphics g) {
		//draw lives, score & timer
		g.setColor(Color.white);
		Font fnt = new Font("arial",30,30);
		g.setFont(fnt);
		g.drawRect(8, 5, 280, 100);
		g.setColor(Color.red);
		g.drawString("Remaining Lives: "+lives,15, 30);
		g.setColor(Color.yellow);
		g.drawString("Score: "+score, 15, 65);
		g.setColor(Color.green);
		g.drawString("Time: "+Game.time, 15, 100);
		//draw clouds
		g.setColor(Color.white);
		drawClouds(g,300,175);
		drawClouds(g,100,140);
		drawClouds(g,475,50);
		//draw sun
		g.setColor(Color.yellow);
		g.fillOval(350, 40, 50, 50);
		g.drawLine(350, 40, 330, 20);
		g.drawLine(400, 90, 420, 110);
		g.drawLine(400, 40, 420, 20);
		g.drawLine(350, 90, 330, 110);
		g.drawLine(340, 60, 310, 60);
		g.drawLine(410, 60, 440, 60);
		g.drawLine(375, 30, 375, 10);
		g.drawLine(375, 100, 375, 120);
	}
	public void drawClouds(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillOval(x, y, 50, 50);
		g.fillOval(x+30, y, 50, 50);
		g.fillOval(x+60, y, 50, 50);
		g.fillOval(x+90, y, 50, 50);
		g.fillOval(x, y+30, 50, 50);
		g.fillOval(x+30, y+30, 50, 50);
		g.fillOval(x+60, y+30, 50, 50);
		g.fillOval(x+90, y+30, 50, 50);
	}
	public void score(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
}
