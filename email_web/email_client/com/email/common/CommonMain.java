package com.email.common;
import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.UIManager;

import com.email.window.LoginFrame;
import com.email.window.MainFrame;

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
    public static void SystemExit(){
    	   String path = MainFrame.class.getResource("/")+"account.xml";
    	   File file = new File(path);  
    	    // 判断目录或文件是否存在  
    	    if (file.exists()) {  
    	        // 判断是否为文件  
    	        if (file.isFile()) {  // 为文件时调用删除文件方法  
    	            file.delete();
    	        }
    	    }  
    }
}
