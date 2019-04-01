package cn.qingyun.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 开始面板
 * @author 张立增
 *
 */
public class StartPanel extends JPanel implements Runnable{

	int times = 0;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		if(times % 2 == 0){
			g.setColor(Color.yellow);
			Font myFont = new Font("华文新魏",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("State : 1", 150, 150);
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			times++;
			this.repaint();
		}
	}
}
