package com.email.common;
import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.UIManager;

import com.email.window.LoginFrame;
import com.email.window.MainFrame;

/**
 * ������Ŀ��main��������Ŀ�����������
 * @author zhujuan
 *
 */
public class CommonMain {
    public static void main(String args[]) throws MessagingException, IOException{
    	// ���ý���Ϊ����ģʽ
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
    	    // �ж�Ŀ¼���ļ��Ƿ����  
    	    if (file.exists()) {  
    	        // �ж��Ƿ�Ϊ�ļ�  
    	        if (file.isFile()) {  // Ϊ�ļ�ʱ����ɾ���ļ�����  
    	            file.delete();
    	        }
    	    }  
    }
}
