package cn.qingyun.domain;

/**
 * 爆炸类
 * @author 张立增
 *
 */
public class Bobm {
	
    //爆炸坐标
	int x,y;
	//判断爆炸是否结束
	boolean isLive = true;
	//定义爆炸的时间
	int bobmLife = 9;
	
	public Bobm(int x,int y){
		this.x = x;
		this.y = y;
	}

	//爆炸时间减1
	public void bobmDown(){
		bobmLife -= 1;
		if(bobmLife == 0){
			isLive = false;
		}
	}
	
	//-----------------------------------------------------
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
