package com.email.common;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.UIManager;

import com.email.window.LoginFrame;

/**
 * 整个项目的main函数，项目的入口在这里
 * @author zhujuan
 *
 */
public class CommonMain {
    public static void main(String args[]) throws MessagingException, IOException{
    	// 设置界面为本地模式
    			try {
    				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			javax.swing.SwingUtilities.invokeLater(new Runnable() {
    				public void run() {
    					new LoginFrame().setVisible(true);
    					// new MainFrame().setVisible(true);
    				}
    			});

    }
}
