import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener{


	private static final long serialVersionUID = 1L;
	
	public Gameover gameover = new Gameover();


	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	private boolean running;
	
	private SnakeBody b;
	private ArrayList<SnakeBody> snake;
	
	private int xCoord = 10, yCoord = 10, size = 5;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private int ticks = 0;
	private boolean right = true, left = false, up = false, down = false;
	
	private Random r;
	private int score = 0;
	private int fontSize = 20; 
	
	
	
	
	public Gamepanel() {
		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		snake = new ArrayList<SnakeBody>();
		apples = new ArrayList<Apple>();
		r = new Random();
		
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tick() {
		if(snake.size() == 0) {
			b = new SnakeBody(xCoord, yCoord, 10);
			snake.add(b);
		}
		ticks++;
		if(ticks > 250000) {
			if(right) xCoord++;
			if(left) xCoord--;
			if(up) yCoord--;
			if(down) yCoord++;
			ticks = 0;
			b = new SnakeBody(xCoord, yCoord, 10);
			snake.add(b);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
		if(apples.size() ==0) {
			int xCoord = r.nextInt(49);
			int yCoord = r.nextInt(49);
			
			apple = new Apple(xCoord, yCoord, 10);
			apples.add(apple);
			
		}
		
		for(int i = 0; i < apples.size(); i++) {
			if(xCoord == apples.get(i).getxCoord() && yCoord == apples.get(i).getyCoord()) {
				size++;
				apples.remove(i);
				i++;
				score++;
			}
		}
		
		for(int i = 0; i < snake.size(); i++) {
			if(xCoord == snake.get(i).getxCoord() && yCoord == snake.get(i).getyCoord()) {
				if(i != snake.size()-1) {
					System.out.println("Game Over!");
					getGraphics().drawString("test", 250, 250);
					getGraphics().fillOval(250, 250, 90, 90);
					stop();
				}
			}
		}
		
		
		if(xCoord < 0 || xCoord > 49 || yCoord < 0 || yCoord > 49) {
			System.out.println("Game Over!");
			getGraphics().drawString("test", 250, 250);
			getGraphics().fillOval(250, 250, 90, 90);
			stop();
		}
		
		
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
		g.setColor(Color.BLUE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.drawString("Score: " + score, 30, 30);
	}
	

	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			up = false;
			down = true;
		}
		if(key == KeyEvent.VK_UP && !down) {
			right = false;
			up = true;
			down = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
