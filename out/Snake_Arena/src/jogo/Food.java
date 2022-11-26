package jogo;

import java.awt.Rectangle;

public class Food {
	private int x;
	private int y;
	
	public Food(Snake player, Snake player1) {
		this.random_spawn(player, player1);
	}
	
	public void random_spawn(Snake player, Snake player1) {
		boolean onSnake = true;
		while(onSnake) {
			onSnake = false;
			
			x = (int)(Math.random() * Game.width - 1);
			y = (int)(Math.random() * Game.height - 1);
			
			for(Rectangle r : player.getBody()){
				if(r.x == x && r.y == y) {
					onSnake = true;
				}
			}
			for(Rectangle r : player1.getBody()){
				if(r.x == x && r.y == y){
					onSnake = true;
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
