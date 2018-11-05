

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.Timer;

public class Game extends Canvas implements Runnable, ActionListener{
	private static Timer clock;
	public static String time;
	private int timeCount;
	private static final long serialVersionUID = -1898107373679889251L;
	public static final int WIDTH = 640, HEIGHT = WIDTH/ 11*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public enum STATE{
		Menu,
		Help,
		Game,
		GameOver;
	}
	public static STATE gameState = STATE.Menu;
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Drone Plane Game", this);
		
		spawner = new Spawn(handler,hud);
		
		r = new Random();
		if (gameState == STATE.Game) {
			
//			handler.addObject(new Player(WIDTH/2+32,HEIGHT/2+32,ID.Player,handler));
//			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
		}
		else {
			for (int i = 0; i<10;i++) {
//				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.MenuParticle,this.handler));
			}
		}
		
		
		
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	
	}
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void setClock() {
		clock = new Timer(1000, this);
        clock.setInitialDelay(1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timeCount++;
		if (timeCount<10) {
			time = "0:0"+timeCount;
		}
		else if(timeCount>=10&&timeCount<60) {
			time = "0:"+timeCount;
		}
		else if(timeCount>=60&&timeCount<70) {
			time = "1:0"+(timeCount%10);
		}
		else if(timeCount>=70&&timeCount<=90) {
			time = "1:"+(timeCount-60);
		}
		else {
			hud.score(hud.getScore()+1);
			timeCount=0;
			time="0:00";
		}
		
	}
	 public void run()
	     {
		 //timer, stop watch
		 //put timer at beginning of while loop 
		 //tick & tock delta
		 	 this.requestFocus();
	         long lastTime = System.nanoTime();
	         double amountOfTicks = 60.0;
	         double ns = 1000000000 / amountOfTicks;
	         double delta = 0;
	         long timer = System.currentTimeMillis();
	         int frames = 0;
	        
	         setClock();
	         time = "0:00";
	         timeCount = 0;
	         while(running)
	         {
	        	 
	             long now = System.nanoTime();
	             delta += (now - lastTime) / ns;
	             lastTime = now;
	             while(delta >=1)
	             {
	                 tick();
	                 delta--;
	             }
	             if(running)
	                 render();
	                 frames++;                
	             if(System.currentTimeMillis() - timer > 1000)
	             {
	               timer += 1000;
	               System.out.println(timer);
	               //System.out.println("FPS: "+ frames);
	               frames = 0;
	             }
	         }
	                 stop();
	     }
	
	private void tick() {
		handler.tick();
		if (gameState == STATE.Game) {
			clock.start();
			hud.tick();
			spawner.tick();
			if (HUD.HEALTH <=0) {
				HUD.HEALTH = 100;
				gameState = STATE.GameOver;
				handler.clearEnemies();
				timeCount = 0;
				time = "0:00";
				clock.stop();
			}
		}
		else if (gameState == STATE.Menu|| gameState == STATE.GameOver) {
			menu.tick();
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if (gameState == STATE.Game) {
			hud.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver) {
			menu.render(g);	
		}
		else if (gameState == STATE.GameOver) {
			GameOver.render(g);
		}
		
		
		
		g.dispose();
		bs.show();
	}
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
}
