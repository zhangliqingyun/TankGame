package cn.qingyun.domain;

/**
 * 游戏信息
 * @author 张立增
 *
 */
public class Message {

	public  static int enemyTankNums = 20;  //敌人坦克数
	public  static int roleTankNums = 3;    //玩家坦克数
	public  static int hitTankNums = 0;     //打掉的坦克数
	
	//打死一两敌人坦克数量减一
	public static void downEnemyTankNums(){
		enemyTankNums--;
	}
	
	//打死一两玩家坦克数量减一
	public static void downRoleTankNums(){
		roleTankNums--;
	}
	
	//打死一两敌人坦克，打击坦克数加1
	public static void addHitTankNumus(){
		hitTankNums++;
	}
	
	public static int getEnemyTankNums() {
		return enemyTankNums;
	}
	public static void setEnemyTankNums(int enemyTankNums) {
		Message.enemyTankNums = enemyTankNums;
	}
	
	public static int getRoleTankNums() {
		return roleTankNums;
	}

	public static void setRoleTankNums(int roleTankNums) {
		Message.roleTankNums = roleTankNums;
	}

	public static int getHitTankNums() {
		return hitTankNums;
	}
	public static void setHitTankNums(int hitTankNums) {
		Message.hitTankNums = hitTankNums;
	}
	
	
}
