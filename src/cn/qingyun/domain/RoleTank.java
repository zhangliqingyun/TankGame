package cn.qingyun.domain;

import java.util.Vector;


/**
 * 具体坦克实体
 * @author 张立增
 *
 */
public class RoleTank extends Tank{

	//实现子弹类
	Shot shot = null;
	//存放子弹的集合
	Vector<Shot> shots = new Vector<Shot>();
	//判断坦克是否存活
	boolean isLive = true;
	//判断是否暂停
	boolean isStop = true;
	
	//实现父类
	public RoleTank(int x, int y) {
		super(x, y);
		
	}
    
	//发射子弹方法
	public void shotRole(){
		//根据方向判断子弹的位置
		switch(this.getDirect()){
		case 0:
			//创建一颗子弹
			shot = new Shot(this.getX()+8,this.getY()-10,this.getDirect());
			//添加到集合中
			shots.add(shot);
			break;
		case 1:
			shot = new Shot(this.getX()+10,this.getY()+32,this.getDirect());
			shots.add(shot);
			break;
		case 2:
			shot = new Shot(this.getX()-10,this.getY()+12,this.getDirect());
			shots.add(shot);
			break;
		case 3:
			shot = new Shot(this.getX()+30,this.getY()+12,this.getDirect());
			shots.add(shot);
			break;
		}
		//启动子弹线程
		Thread t = new Thread(shot);
		t.start();
		
	}
}
