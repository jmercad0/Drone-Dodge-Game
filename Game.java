

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Game extends Canvas implements Runnable, ActionListener{
	private static Timer clock;
	public static String time;
	public static int timeCount;
	public static final int WIDTH = 640, HEIGHT = WIDTH/ 11*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static STATE gameState = STATE.Menu;
	public enum STATE{
		Menu,
		Game,
		Victory,
		GameOver;
	}
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Drone Plane Game", this);
		spawner = new Spawn(handler,hud);
		r = new Random();	
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
			if(hud.getScore()==3) {
				gameState = STATE.Victory;
				clock.stop();
			}
		}	
	 }
	 public void run()
	     {
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
	               System.out.println("FPS: "+ frames);
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
			if (HUD.lives <=0) {
				HUD.lives = 3;
				gameState = STATE.GameOver;
				handler.clearEnemies();
				timeCount = 0;
				time = "0:00";
				clock.stop();
			}
		}
		else if (gameState == STATE.Menu|| gameState == STATE.GameOver || gameState == STATE.Victory) {
			menu.tick();
			if (gameState == STATE.GameOver || gameState == STATE.Victory) {
				handler.clearEnemies();
			}
			
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameState == STATE.Game) {
			hud.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Victory) {
			menu.render(g);	
		}
		handler.render(g);
		g.dispose();
		bs.show();
	}
	public static float clamp(float var, float min, float max) {
		if (var >= max) { return var = max;}
		else if (var <= min) { return var = min;}
		else {return var;}
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
}
