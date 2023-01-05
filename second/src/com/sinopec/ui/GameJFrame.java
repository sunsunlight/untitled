package com.sinopec.ui;

import javax.swing.*;

public class GameJFrame extends JFrame {

    public GameJFrame(){
        initJFrame();

        initJmenu();

        //设置界面显示出来
        setVisible(true);

    }

    private void initJmenu() {
        //初始化菜单
        //创建整改个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象（功能  关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下面的条目
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem acountItem = new JMenuItem("公众号");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(acountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        setSize(603,680);
        //设置界面的标题
        setTitle("拼图游戏单机版1.0");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(3);
    }
}
