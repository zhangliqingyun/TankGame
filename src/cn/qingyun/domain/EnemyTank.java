package cn.qingyun.domain;

import java.util.Vector;

/**
 * 敌人坦克类
 * @author 张立增
 *
 */
public class EnemyTank extends Tank implements Runnable{

	//判断敌人坦克是否存活
	boolean isLive = true;
	
	//敌人坦克的速度
	//int spend = 1;
	//标记是否暂停
	boolean isStop = true;
	
	//存放敌人子弹的集合
	Vector<Shot> shots = new Vector<Shot>();
	
	//存放面板中的坦克集合
	Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	
	
	public EnemyTank(int x, int y) {
		super(x, y);
	}

	//判断坦克是否有重叠的方法
	public boolean isTouchOtherTank(){
		
		boolean flag = false;
		
		switch (this.direct) {
		
		case 0:    //当前坦克向上
			for(int i = 0;i < enemyTanks.size();i++){
				EnemyTank enemyTank = enemyTanks.get(i);
				if(enemyTank != this){  //坦克不是当前坦克
					
					if(enemyTank.direct == 0 || enemyTank.direct == 1){   //坦克向上或者向下
						
						//this坦克的左上点
						if(this.x >= enemyTank.x && this.x <= enemyTank.x+20 && this.y >= enemyTank.y && this.y <= enemyTank.y+30){
							return true;
						}
						//this坦克的右上点
						if(this.x+20 >= enemyTank.x && this.x+20 <= enemyTank.x+20 && this.y >= enemyTank.y && this.y <= enemyTank.y+30){
							return true;
						}		
					}
					
					if(enemyTank.direct == 2 || enemyTank.direct == 3){   //坦克向左或者向右
						//this坦克的左上点
						if(this.x >= enemyTank.x && this.x <=enemyTank.x+30 && this.y >= enemyTank.y && this.y <= enemyTank.y+20){
							return true;
						}
						//this坦克的左下点
						if(this.x >= enemyTank.x && this.x <= enemyTank.x+30 && this.y+30 >= enemyTank.y && this.y+30 <= enemyTank.y+20){
							return true;
						}
					}
				}
			}
			break;
			
		case 1:   //当前坦克向下
			for(int i = 0;i < enemyTanks.size();i++){
				EnemyTank enemyTank = enemyTanks.get(i);
				if(this !=enemyTank){
					
					if(enemyTank.direct == 0 || enemyTank.direct == 1){ //坦克向下或者想上
						
						//this坦克的左下点
						if(this.x >= enemyTank.x && this.x <= enemyTank.x+20 && this.y+30 >= enemyTank.y && this.y+30 <= enemyTank.y+30){
							return true;
						}
						//this坦克的右下点
						if(this.x+20 >= enemyTank.x && this.x+20 <= enemyTank.x+20 && this.y+30 >= enemyTank.y && this.y+30 <= enemyTank.y+30){
							return true;
						}
					}
					
					if(enemyTank.direct == 2 || enemyTank.direct == 3){  //坦克向左或者想右
						
						//this坦克的左上点
						if(this.x >= enemyTank.x && this.x <= enemyTank.x+30 && this.y >= enemyTank.y && this.y <= enemyTank.y+20){
							return true;
						}
						//this坦克的左下点
						if(this.x >= enemyTank.x && this.x <= enemyTank.x+30 && this.y+30 >= enemyTank.y && this.y+30 <= enemyTank.y+20){
							return true;
						}
					}
				}
			}
			break;
			
		case 2:   //当前坦克向左
	         for(int i = 0;i < enemyTanks.size();i++){
	        	 EnemyTank enemyTank = enemyTanks.get(i);
	        	 if(this != enemyTank){
	        		 
	        		 if(enemyTank.direct == 0 || enemyTank.direct == 1){  //坦克向下或者下上移动
	        			 
	        			 //this坦克左上角点
	        			 if(this.x >= enemyTank.x && this.x <= enemyTank.x+20 && this.y >= enemyTank.y && this.y <= enemyTank.y+30){
	        				 return true;
	        			 }
	        			 //this坦克左下角点
	        			 if(this.x >= enemyTank.x && this.x <= enemyTank.x+20 && this.y+20 >= enemyTank.y && this.y+20 <= enemyTank.y+30){
	        				 return true;
	        			 }
	        		 }
	        		 
	        		 if(enemyTank.direct == 2 || enemyTank.direct == 3){  //坦克向左或者向右移动
	        			 
	        			 //this坦克左上角点
	        			 if(this.x >= enemyTank.x && this.x <= enemyTank.x+30 && this.y >= enemyTank.y && this.y <= enemyTank.y+20){
	        				 return true;
	        			 }
	        			 //this坦克左下角点
	        			 if(this.x >= enemyTank.x && this.x <= enemyTank.x +30 && this.y+20 >= enemyTank.y && this.y+20 <= enemyTank.y+20){
	        				 return true;
	        			 }
	        		 }
	        	 }
	         }
			break;
			
		case 3:   //当前坦克向右
			for(int i = 0;i < enemyTanks.size();i++){
				EnemyTank enemyTank = enemyTanks.get(i);
				if(this != enemyTank){
					
					if(enemyTank.direct == 0 || enemyTank.direct == 1 ){ //坦克向上或者向下移动
						
						//this坦克的右上点
						if(this.x+30 >= enemyTank.x && this.x+30 <= enemyTank.x+20 && this.y >= enemyTank.y && this.y <= enemyTank.y+30){
							return true;
						}
						//this坦克的右下点
						if(this.x+30 >= enemyTank.x && this.x+30 <= enemyTank.x+20 && this.y+20 >= enemyTank.y && this.y+20 <= enemyTank.y+30){
							return true;
						}
					}
					
					if(enemyTank.direct == 2 || enemyTank.direct == 3){   //坦克向左或者向右移动
						
						//this坦克的右上点
						if(this.x+30 >= enemyTank.x && this.x+30 <= enemyTank.x+30 && this.y >= enemyTank.y && this.y <= enemyTank.y+20){
							return true;
						}
						//this坦克的右下点
						if(this.x+30 >= enemyTank.x && this.x+30 <= enemyTank.x+30 && this.y+20 >= enemyTank.y && this.y+20 <= enemyTank.y+20){
							return true;
						}
					}
				}
			}
			break;

	
		}
		
		return flag;
	}
	//实现run方法
	@Override
	public void run() {
		
		while(true){
			
			switch (this.direct) {
			case 0:
				  for(int i = 0;i < 30;i++){
					  if(this.y > 0 && !isTouchOtherTank()){
						  this.y -= getSpeed(); 
					  }
					  try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				  }
				 
				break;

			case 1:
				 for(int i = 0;i < 30;i++){
					 if(this.y < 275 && !isTouchOtherTank()){
						 this.y += getSpeed();
					 }
					  try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				  }
				  
				break;
				
			case 2:
				 for(int i = 0;i < 30;i++){
					 if(this.x > 0 && !isTouchOtherTank()){
						 this.x -= getSpeed();
					 }
					  try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				  }
				  
				break;
				
			case 3:
				 for(int i = 0;i < 30;i++){
					 if(this.x < 380 && !isTouchOtherTank()){
						 this.x += getSpeed();
					 }
					  try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				  }
				  
				break;
			}
			
			//判断是否要给坦克加入子弹
			
				if(this.isLive && isStop){
					Shot shot = null;
					if(this.shots.size() < 5){
						//根据方向判断子弹的位置
						switch(this.getDirect()){
						case 0:
							//创建一颗子弹
							shot = new Shot(this.getX()+8,this.getY()-10,this.getDirect());
							//添加到集合中
							this.shots.add(shot);
							break;
						case 1:
							shot = new Shot(this.getX()+10,this.getY()+32,this.getDirect());
							this.shots.add(shot);
							break;
						case 2:
							shot = new Shot(this.getX()-10,this.getY()+12,this.getDirect());
							this.shots.add(shot);
							break;
						case 3:
							shot = new Shot(this.getX()+30,this.getY()+12,this.getDirect());
							this.shots.add(shot);
							break;
						}
					}
					Thread t = new Thread(shot);
					t.start();
				}
			
			
			
			//随机产生一个方向
			if(isStop){
				this.direct = (int)(Math.random()*4);
			}
			//判断敌人坦克是否死亡
			if(this.isLive == false){
				break;
			}
			
			
		}
	}

	
	
	//-----------------------------------------------
	
	
	public int getDirect() {
		return direct;
	}


	public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
		this.enemyTanks = enemyTanks;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}


}
