import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	public static int scoreKeep = 0;
	private Random r = new Random();
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public static void resetScoreKeep() {
		scoreKeep=0;
	}
	public void tick() {
		scoreKeep++;
		
		if (hud.getScore()==10&&handler.boss) {
			resetScoreKeep();
			
		}
		if (scoreKeep>=50) {
			if (hud.getScore()==2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			else if(hud.getScore()==3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			
			
			
		}
	}
}
