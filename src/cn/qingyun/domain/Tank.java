package cn.qingyun.domain;

import javax.swing.JPanel;

/**
 * 坦克类用户设定坦克的位置 
 * @author 张立增
 *
 */
public class Tank  {
	
	//坦克出现位置
	int x; 
	int y;
	
	//定义坦克的速度
	int speed = 3;
	
	//定义坦克移动的方向 0:上  1：下  2：左  3：右
	int direct = 0;
	
	//定义坦克的颜色
	int Color = 0;
	
	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	//上移方法
	public void moveUp(){
		y -= speed;
	}
	
	//上移方法
	public void moveDown(){
		y += speed;
	}
	
	//上移方法
	public void moveLeft(){
		x -= speed;
	}
	
	//上移方法
	public void moveRight(){
		x += speed;
	}

	
	//----------------------------------------------
	
	public int getX() {
		return x;
	}
	public int getColor() {
		return Color;
	}

	public void setColor(int color) {
		Color = color;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	} 
	
	
}
