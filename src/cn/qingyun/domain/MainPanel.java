package cn.qingyun.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * 主面板
 * @author 张立增
 *
 */
public class MainPanel extends JPanel implements KeyListener,Runnable{

	//创建一个坦克实体
	RoleTank roleTank = null;
	
	//标记暂停和继续
	int flag = 1;
	//记录玩家死亡次数
	int roleTankOver = 0;
	//记录敌人坦克死亡次数
	int enemyTankOver = 0;
	
	//创建保存敌人坦克的集合
	Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	
	//定义敌人坦克的数量
	int enemyTankNum = 3;
	
	//定义三张图片
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	
	//定义保存爆炸类的集合
	Vector<Bobm> bobms = new Vector<Bobm>();
	
	public  MainPanel(){
		//初始化坦克 
		roleTank = new RoleTank(200,270);    
		
		//初始化敌人坦克
		for(int i = 0;i < enemyTankNum;i++){
			EnemyTank enemyTank = new EnemyTank((i+1)*50,0);
			//创建的坦克添加到坦克面板中
			enemyTank.setEnemyTanks(enemyTanks);
			
			enemyTank.setColor(0);
			enemyTank.setDirect(1);
			//启动敌人坦克线程
			Thread thread = new Thread(enemyTank);
			thread.start();
			//添加敌人坦克的子弹
			Shot shot = new Shot(enemyTank.getX()+10,enemyTank.getY()+30,enemyTank.direct);
			//子弹添加到子弹集合中
			enemyTank.shots.add(shot);
			//启动敌人子弹线程
			Thread threadShot = new Thread(shot);
			threadShot.start();
			
			enemyTanks.add(enemyTank);
		}
		
		//初始化爆炸图片
//		try {
//			image1 = ImageIO.read(new File("bomb_1.gif"));
//			image2 = ImageIO.read(new File("bomb_2.gif"));
//			image3 = ImageIO.read(new File("bomb_3.gif"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		ImageIcon icon = new ImageIcon(Panel.class.getResource("/bomb_1.gif"));
		image1 = icon.getImage();
		ImageIcon icon2 = new ImageIcon(Panel.class.getResource("/bomb_2.gif"));
		image2 = icon2.getImage();
		ImageIcon icon3 = new ImageIcon(Panel.class.getResource("/bomb_3.gif"));
		image3 = icon3.getImage();
	}
	
	
	//重写绘画方法
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//设置背景填充颜色
		g.fillRect(0, 0, 400, 300);
		
		//画出坦克的方法
		if(roleTank.isLive){
			drawTank(roleTank.getX(),roleTank.getY(),g,roleTank.getDirect(),1);
		}
		
		//画出游戏信息
		drawTank(60, 320, g, 0, 0);
		drawTank(430, 60, g, 0, 0);
		drawTank(130, 320, g, 0, 1);
		g.setColor(Color.black);
		Font font = new Font("宋体",Font.BOLD,30);
		g.drawString(Message.enemyTankNums+"", 90, 340);
		g.drawString(Message.roleTankNums+"", 160, 340);
		Font font2 = new Font("宋体",Font.BOLD,26);
		g.setFont(font2);
	    g.drawString("击中菜鸟数：", 430, 30);
	    g.drawString(Message.hitTankNums+"", 460, 85);
		
		//画出敌人坦克
		for(int i = 0;i < enemyTanks.size();i++){
			EnemyTank enemyTank = enemyTanks.get(i);
			if(enemyTank.isLive){
				//画出敌人坦克
				drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
				//画出敌人子弹
				for(int j = 0;j < enemyTank.shots.size();j++){
					Shot shot = enemyTank.shots.get(j);
					if(shot.isLive){
						g.draw3DRect(shot.getX(),shot.getY(), 2, 2, false);
					}else{
						//移除死亡的敌人子弹
						enemyTank.shots.remove(shot);
					}
					
				}
			}
		}
		
		//画出子弹集合
		for(int i = 0;i <this.roleTank.shots.size();i++){
			Shot myShot = this.roleTank.shots.get(i);
			if(myShot != null && myShot.isLive == true){
				g.setColor(Color.yellow);
				g.draw3DRect(myShot.getX(),myShot.getY(), 2, 2, false);
			}
			
			//移除出界不存活的子弹
			if(myShot.isLive == false){
				this.roleTank.shots.remove(myShot);
			}
		}
		

		//画出爆炸效果
		for(int i = 0;i < bobms.size();i++){
			Bobm bobm = bobms.get(i);
			   if(bobm.isLive){
				   if(bobm.bobmLife > 6){
						g.drawImage(image1, bobm.getX(),bobm.getY(), 30, 30, this);
					}else if(bobm.bobmLife > 3){
						g.drawImage(image2, bobm.getX(),bobm.getY(), 30, 30, this);
					}else{
						g.drawImage(image3, bobm.getX(),bobm.getY(), 30, 30, this);
					}
			   }
				//生命值减1
				bobm.bobmDown();
				
				if(bobm.isLive == false){
					bobms.remove(bobm);
				}
		}
		
		
	}

	//判断子弹是否击中敌人坦克的方法
	private void hitTank(Shot shot,EnemyTank enemyTank){
		switch (enemyTank.direct) {
		case 0:
		case 1:
			  //判断是否击中(上下方向)
			  if(shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX()+20 && shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY()+30){
				  //子弹死亡
				  shot.isLive = false;
				  //敌人坦克死亡
				  enemyTank.isLive = false;
				  //敌人坦克数量减1
				  Message.downEnemyTankNums();
				  //玩家打死的坦克数加1
				  Message.addHitTankNumus();
				  //创建一个爆炸类
				  Bobm bobm = new Bobm(enemyTank.getX(),enemyTank.getY());
				  //添加到爆炸集合中
				  bobms.add(bobm);
				  
				  enemyTankOver++;
				  if(enemyTankOver < 18){
					  EnemyTank newEnemyTank = new EnemyTank(280,0);
						//创建的坦克添加到坦克面板中
					  newEnemyTank.setEnemyTanks(enemyTanks);
						
					  newEnemyTank.setColor(0);
					  newEnemyTank.setDirect(1);
						//启动敌人坦克线程
						Thread thread = new Thread(newEnemyTank);
						thread.start();
						//添加敌人坦克的子弹
						Shot newShot = new Shot(newEnemyTank.getX()+10,newEnemyTank.getY()+30,newEnemyTank.direct);
						//子弹添加到子弹集合中
						newEnemyTank.shots.add(newShot);
						//启动敌人子弹线程
						Thread threadShot = new Thread(newShot);
						threadShot.start();
						
						enemyTanks.add(newEnemyTank);
				  }else if(enemyTankOver == 20){
					  JOptionPane.showConfirmDialog(this, "你好吊啊！消灭了所有的敌人坦克！");
				  }
				 
			  }
			break;
		case 2:
		case 3:
			 //判断是否击中(左右方向)
			if(shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 30 && shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() +20){
				 //子弹死亡
				  shot.isLive = false;
				  //敌人坦克死亡
				  enemyTank.isLive = false;
				  //敌人坦克数量减1
				  Message.downEnemyTankNums();
				  //玩家打死的坦克数加1
				  Message.addHitTankNumus();
				  //创建一个爆炸类
				  Bobm bobm = new Bobm(enemyTank.getX(),enemyTank.getY());
				  //添加到爆炸集合中
				  bobms.add(bobm);
				  
				  enemyTankOver++;
				  if(enemyTankOver < 18){
					  EnemyTank newEnemyTank = new EnemyTank(280,0);
						//创建的坦克添加到坦克面板中
					  newEnemyTank.setEnemyTanks(enemyTanks);
						
					  newEnemyTank.setColor(0);
					  newEnemyTank.setDirect(1);
						//启动敌人坦克线程
						Thread thread = new Thread(newEnemyTank);
						thread.start();
						//添加敌人坦克的子弹
						Shot newShot = new Shot(newEnemyTank.getX()+10,newEnemyTank.getY()+30,newEnemyTank.direct);
						//子弹添加到子弹集合中
						newEnemyTank.shots.add(newShot);
						//启动敌人子弹线程
						Thread threadShot = new Thread(newShot);
						threadShot.start();
						
						enemyTanks.add(newEnemyTank);
				  }else if(enemyTankOver == 20){
					  JOptionPane.showConfirmDialog(this, "你好吊啊！消灭了所有的敌人坦克！");
				  }
				 
				
			}
			break;
		}
	}
	
	//判断敌人子弹是否击中玩家坦克的方法
	private void hitRoleTank(Shot shot, RoleTank roleTank2) {
		switch (roleTank2.direct) {
		case 0:
		case 1:
			  //判断是否击中(上下方向)
			  if(shot.getX() > roleTank2.getX() && shot.getX() < roleTank2.getX()+20 && shot.getY() > roleTank2.getY() && shot.getY() < roleTank2.getY()+30){
				  //子弹死亡
				  shot.isLive = false;
				  //敌人坦克死亡
				  roleTank2.isLive = false;
				  //玩家坦克数减1
				  Message.downRoleTankNums();
				 
				  //创建一个爆炸类
				  Bobm bobm = new Bobm(roleTank2.getX(),roleTank2.getY());
				  //添加到爆炸集合中
				  bobms.add(bobm);
				  
				//判断玩家是否死亡3次
				  roleTankOver++;
				 if(roleTankOver < 3){
					 roleTank = new RoleTank(100,100);
				 }else{
					 JOptionPane.showConfirmDialog(this, "垃圾！太菜了！！！");
				 }
				
			  }
			break;
		case 2:
		case 3:
			 //判断是否击中(左右方向)
			if(shot.getX() > roleTank2.getX() && shot.getX() < roleTank2.getX() + 30 && shot.getY() > roleTank2.getY() && shot.getY() < roleTank2.getY() +20){
				 //子弹死亡
				  shot.isLive = false;
				  //敌人坦克死亡
				  roleTank2.isLive = false;
				  
				  //玩家坦克数减1
				  Message.downRoleTankNums();
				
				  //创建一个爆炸类
				  Bobm bobm = new Bobm(roleTank2.getX(),roleTank2.getY());
				  //添加到爆炸集合中
				  bobms.add(bobm);
				//判断玩家是否死亡3次
				  roleTankOver++;
				 if(roleTankOver < 3){
					 roleTank = new RoleTank(100,100);
				 }else{
					 JOptionPane.showConfirmDialog(this, "垃圾！太菜了！！！");
				 }
				 
			}
			break;
		}
	}
	
	
	//画出坦克的方法
	private void drawTank(int x, int y, Graphics g, int direct, int type) {
		
		switch(type){   //设置颜色
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		switch(direct){   //设置方向
		case 0:            //向上
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//画出中间的圆
			g.fillOval(x+5,y+10, 10, 10);
			//画出线
			g.drawLine(x+10, y, x+10, y+10);
			break;
		case 1:           //向下
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//画出中间的圆
			g.fillOval(x+5,y+10, 10, 10);
			//画出线
			g.drawLine(x+10, y+20, x+10, y+30);
			break;
		case 2:         //向左
			//画出左边矩形
			g.fill3DRect(x-5, y+20, 30, 5, false);
			//画出右边矩形
			g.fill3DRect(x-5, y+5, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x, y+10, 20, 10, false);
			//画出中间的圆
			g.fillOval(x+5,y+10, 10, 10);
			//画出线
			g.drawLine(x+5, y+15, x-5, y+15);
			break;
		case 3:          //向右
			//画出左边矩形
			g.fill3DRect(x-5, y+20, 30, 5, false);
			//画出右边矩形
			g.fill3DRect(x-5, y+5, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x, y+10, 20, 10, false);
			//画出中间的圆
			g.fillOval(x+5,y+10, 10, 10);
			//画出线
			g.drawLine(x+15, y+15, x+25, y+15);
			break;
			
		}   
	}

	//键盘压下 W为向上，S为向下，A为向左，D为向右
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_W && this.roleTank.isStop == true){
			this.roleTank.setDirect(0);
			this.roleTank.moveUp();
		}else if(e.getKeyChar() == KeyEvent.VK_S && this.roleTank.isStop == true){
			this.roleTank.setDirect(1);
			this.roleTank.moveDown();
		}else if(e.getKeyChar() == KeyEvent.VK_A && this.roleTank.isStop == true){
			this.roleTank.setDirect(2);
			this.roleTank.moveLeft();
		}else if(e.getKeyChar() == KeyEvent.VK_D && this.roleTank.isStop == true){
			this.roleTank.setDirect(3);
			this.roleTank.moveRight();
		}  
		
		if(e.getKeyChar() == KeyEvent.VK_J && this.roleTank.isStop == true){
			//最多只能发射5颗子弹
			if(this.roleTank.shots.size() < 5 && this.roleTank.isLive == true ){
				//发射子弹
				this.roleTank.shotRole();
			}
			
		}
		
		//空格键为暂停
		if(e.getKeyChar() == KeyEvent.VK_SPACE){
			if(flag % 2 != 0){
				this.roleTank.speed = 0;
				this.roleTank.isStop = false;
				 for(int i = 0;i < enemyTanks.size();i++){
			        	EnemyTank enemyTank = enemyTanks.get(i);
			        	enemyTank.speed = 0;
			        	enemyTank.isStop = false;
			        	for(int j = 0;j < enemyTank.shots.size();j++){
			        		Shot shot = enemyTank.shots.get(j);
			        		shot.spend = 0;
			        	}
			        }
				flag ++;
			}else{
				this.roleTank.speed = 1;
				this.roleTank.isStop = true;
				 for(int i = 0;i < enemyTanks.size();i++){
			        	EnemyTank enemyTank = enemyTanks.get(i);
			        	enemyTank.speed = 1;
			        	enemyTank.isStop = true;
			        	for(int j = 0;j < enemyTank.shots.size();j++){
			        		Shot shot = enemyTank.shots.get(j);
			        		shot.spend = 3;
			        	}
			        }
				flag ++;
			}
			
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	//每隔100毫秒重绘一次图的线程
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//调用判断是否击中敌人坦克
			for( int i = 0;i < this.roleTank.shots.size();i++){
				Shot shot = this.roleTank.shots.get(i);
				if(shot.isLive){
					for( int j = 0;j < this.enemyTanks.size();j++){
						EnemyTank enemyTank = this.enemyTanks.get(j);
						if(enemyTank.isLive){
							hitTank(shot, enemyTank);
						}
					}
				}
			}
			
			//判断敌人坦克是否击中我的坦克
			for(int i = 0;i < enemyTanks.size();i++){
				EnemyTank enemyTank = enemyTanks.get(i);
				if(enemyTank.isLive){
					for(int j = 0;j < enemyTank.shots.size();j++){
						Shot shot = enemyTank.shots.get(j);
						if(shot.isLive){
							RoleTank roleTank = this.roleTank;
							if(roleTank.isLive){
								hitRoleTank(shot,roleTank);
							}
						}
					}
				}
			}
			
			
			this.repaint();
			
		}
		
		
	}


}
