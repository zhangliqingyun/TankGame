package cn.qingyun.domain;

/**
 * 子弹类
 * @author 张立增
 *
 */
public class Shot implements Runnable{
    
	//子弹的坐标
	int x,y;
	//子弹的方向
	int direct;
	//子弹的速度
	int spend = 5;
	//判断线程是否存活
	boolean isLive = true;
	
	public Shot(int x, int y,int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	

	//根据方向，每隔50毫秒更爱位置
	@Override
	public void run() {
		while(true){
				try {
					Thread.sleep(50);
					switch(this.direct){
					case 0:
						y -= spend;
						break;
					case 1:
						y += spend;
						break;
					case 2:
						x -= spend;
						break;
					case 3:
						x += spend;
						break;	
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//判断子弹是否出界
				if(this.x < -2 ||this.x > 400 ||this.y < -2 ||this.y > 300){
					this.isLive = false;
					break;
				}
		}
		
	}
	
	
	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
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
