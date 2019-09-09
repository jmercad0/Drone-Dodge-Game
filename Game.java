import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import javax.swing.Timer;

public class Game extends Canvas implements Runnable, ActionListener{
	private static Timer clock;
	public static String time;
	public static int timeCount;
	public static final int WIDTH = 640, HEIGHT = WIDTH/ 11*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static STATE gameState = STATE.Menu;
	public enum STATE{
		Menu,
		Game,
		Victory,
		Help,
		GameOver;
	}
	//instantiates components of game
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Drone Plane Game", this);
		spawner = new Spawn(handler,hud);
	}
	//starts game thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	//ends game thread
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//tracks time in game to update level & check if victory
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timeCount++;
		if (timeCount<10)  time = "0:0"+timeCount;	
		else if(timeCount>=10&&timeCount<60)  time = "0:"+timeCount; 
		else {
			hud.score(hud.getScore()+500);
			timeCount=0;
			time="0:00";
			if(hud.getScore()>=3000)
				gameState = STATE.Victory;
				clock.stop();
		}	
	}
	public void setClock() {
		clock = new Timer(1000, this);
        clock.setInitialDelay(1);
	}
	
	//constantly used to update game logic 
	private void tick() {
		handler.tick();
		//starts game logic for components of game
		if (gameState == STATE.Game) {
			clock.start();
			spawner.tick();
			//if player loses all lives, game over
			if (HUD.lives <=0) {
				HUD.lives = 3;
				gameState = STATE.GameOver;
				handler.clearEnemies();
				timeCount = 0;
				time = "0:00";
				clock.stop();
			}
		}
		//logic for menu system
		if (gameState == STATE.GameOver || gameState == STATE.Victory)
			handler.clearEnemies();
	}
	//draws components of game in scene
	private void render() {
		//organizes graphics resources for varying machines
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//background render
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//renders game components
		if (gameState == STATE.Game)
			hud.render(g);
		//renders menu components
		else if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Victory || gameState == STATE.Help) 
			menu.render(g);	
		handler.render(g);
		//releases graphics resources at end of each use
		g.dispose();
		bs.show();
	}
	//used to prevent player from moving off screen
	public static float clamp(float var, float min, float max) {
		if (var >= max) { return var = max;}
		else if (var <= min) { return var = min;}
		else {return var;}
	}
	public void run() {
		this.requestFocus();
	    long last = System.nanoTime();
	    //amount of updates per second
	    double numOfTicks = 60.0;
	    double ns = 1000000000 / numOfTicks;
	    //difference between time now & last observed
	    double delta = 0;
	    long timer = System.currentTimeMillis();
	    //set timer for player HUD
	    setClock();
	    time = "0:00";
	    timeCount = 0;
	    //game engine loop
	    while(running) {
	    	long now = System.nanoTime();
	        delta += (now - last) / ns;
	        last = now;
	        //game logic
	        while(delta >=1) {
	            tick();
	            delta--;
	        }
	        //update graphics	
	        if(running)
	        	render();
	        if(System.currentTimeMillis() - timer > 1000)
	           	timer += 1000;
	           	System.out.println(timer);
	    }
	    stop();
	}
	//starts new game
	public static void main(String args[]) {
		new Game();
	}	
}
