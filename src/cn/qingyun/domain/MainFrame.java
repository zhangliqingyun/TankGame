package cn.qingyun.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 游戏主界面
 * @author 张立增
 *
 */
public class MainFrame extends JFrame implements ActionListener{
       
	private JMenuItem menuItem;
	private StartPanel startPanel;

	public MainFrame(){
		
		//初始化窗口
		initFrame();
		
		//添加面板
		addPanel();
	}
	

	//添加面板
	private void addPanel() {
		//菜单条
		JMenuBar menuBar = new JMenuBar();
		//菜单
		JMenu menu = new JMenu("游戏(G)");
		menu.setMnemonic('G');
		menuItem = new JMenuItem("开始新游戏(N)");
		JMenuItem exitGame = new JMenuItem("退出游戏(E)");
		exitGame.setMnemonic('E');
		//添加动作监听
		menuItem.addActionListener(this);
		menuItem.setActionCommand("newGame");
		exitGame.addActionListener(this);
		exitGame.setActionCommand("exitGame");
		
		menu.add(menuItem);
		menu.add(exitGame);
		menuBar.add(menu);
		startPanel = new StartPanel();
		Thread thread = new Thread(startPanel);
		thread.start();
		this.setJMenuBar(menuBar);
		
		this.add(startPanel);
	
		
	}

	//初始化窗口
	 private void initFrame() {
		this.setTitle("坦克大战");
		this.setSize(600, 450);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	      if(e.getActionCommand().equals("newGame")){
	    	  //游戏面板
	    	  MainPanel mainPanel = new MainPanel();
	    	  mainPanel.repaint();
	    	  Thread thread = new Thread(mainPanel);
	    	  thread.start();
	    	  //移除上一个面板
	    	  this.remove(startPanel);
	    	  this.add(mainPanel);
	    	  this.addKeyListener(mainPanel);
	    	  //刷新显示新面板
	    	  //声音文件
	    	  Voice voice = new Voice("F:\\游戏声音\\voice.wav");
	    	  voice.start();
	    	  this.setVisible(true);
	    	  
	      }else if(e.getActionCommand().equals("exitGame")){
	    	  System.exit(0);
	      }
	}


}
