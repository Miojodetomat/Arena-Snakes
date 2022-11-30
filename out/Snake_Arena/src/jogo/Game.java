package jogo;//package cliente;

import Comunicados.ComunicadoDeCrescimento;
import Comunicados.ComunicadoDeMorte;
import Comunicados.ComunicadoDeMovimento;
import Comunicados.Parceiro;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game 
implements KeyListener{
	private Snake playerLulu;
	private Snake player1;
	private Food food;
	private Graphics graphics;
	private Parceiro servidor;

	private JFrame window;
	
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	
	public Game(Parceiro servidor) throws Exception {
		window = new JFrame();

		if(servidor == null)
			throw new Exception("Porta inválida");
		this.servidor = servidor;

		playerLulu = new Snake(2,4, "DIREITA");
		player1 = new Snake(2, 2, "ESQUERDA");
		
		food = new Food(playerLulu, player1);
		
		graphics = new Graphics(this);
		
		window.add(graphics);
		
		window.setTitle("Snake");
		window.setSize(width * dimension + 2, height * dimension + dimension + 4);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		graphics.state = "RUNNING";
	}
	
	public void update() {
		if(graphics.state == "RUNNING") {
			if(check_food_collision()) {
				playerLulu.grow();
				food.random_spawn(playerLulu, player1);
				try {
					servidor.receba(new ComunicadoDeCrescimento(food.getX(), food.getY()));
				}
				catch (Exception e)
				{}
			}
			else if(check_wall_collision() || check_self_collision()) {
				graphics.state = "END";
				try{
					servidor.receba(new ComunicadoDeMorte());
				}
				catch (Exception e)
				{}
			}
			else {
				playerLulu.move();
				try {
					servidor.receba(new ComunicadoDeMovimento("FRENTE"));
				}
				catch (Exception e)
				{}
				player1.move();
			}
		}
	}
	
	private boolean check_wall_collision() {
		if(playerLulu.getX() < 0 || playerLulu.getX() >= width * dimension
				|| playerLulu.getY() < 0|| playerLulu.getY() >= height * dimension) {
			return true;
		}
		return false;
	}
	
	private boolean check_food_collision() {
		if(playerLulu.getX() == food.getX() * dimension && playerLulu.getY() == food.getY() * dimension) {
			return true;
		}
		return false;
	}
	
	private boolean check_self_collision() {
		for(int i = 1; i < playerLulu.getBody().size(); i++) {
			if((playerLulu.getX() == playerLulu.getBody().get(i).x &&
					playerLulu.getY() == playerLulu.getBody().get(i).y)) {
				return true;
			}
		}
		for(int i = 1; i < player1.getBody().size(); i++)
		{
			if((player1.getX() == player1.getBody().get(i).x &&
					player1.getY() == player1.getBody().get(i).y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(graphics.state == "RUNNING") {
			if(keyCode == KeyEvent.VK_UP && playerLulu.getMove() != "DOWN") {
				playerLulu.up();
				try {
					servidor.receba(new ComunicadoDeMovimento("CIMA"));
				}
				catch (Exception err)
				{}
			}
		
			if(keyCode == KeyEvent.VK_DOWN && playerLulu.getMove() != "UP") {
				playerLulu.down();
				try {
					servidor.receba(new ComunicadoDeMovimento("BAIXO"));
				}
				catch (Exception err)
				{}
			}
		
			if(keyCode == KeyEvent.VK_LEFT && playerLulu.getMove() != "RIGHT") {
				playerLulu.left();
				try {
					servidor.receba(new ComunicadoDeMovimento("ESQUERDA"));
				}
				catch (Exception err)
				{}
			}
		
			if(keyCode == KeyEvent.VK_RIGHT && playerLulu.getMove() != "LEFT") {
				playerLulu.right();
				try {
					servidor.receba(new ComunicadoDeMovimento("DIREITA"));
				}
				catch (Exception err)
				{}
			}
		}
		//else {
			//mudar maneira de iniciar o jogo
			//this.start();
		//}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	public Snake getPlayerLulu() {
		return playerLulu;
	}

	public Snake getPlayer1() { return player1; }

	public void setPlayerLulu(Snake playerLulu) {
		this.playerLulu = playerLulu;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public Graphics getGraphics() {
		return graphics;
	}
}
