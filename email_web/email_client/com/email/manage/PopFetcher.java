package com.email.manage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 原测试类(可舍弃)
 * @author zhujuan
 *
 */
public class PopFetcher{
	
    public static void main(String args[]) throws MessagingException, IOException{
    	Properties props = new Properties();
    	props.setProperty("mail.store.protocol", "pop3");
    	props.setProperty("mail.pop3.port", "110");
    	props.setProperty("mail.pop3.host", "pop.126.com");
    	Session session =Session.getDefaultInstance(props);
    	Store store = session.getStore("pop3");
    	try{
    	store.connect("emailtest15@126.com","ctqvewfikbwjsqvh");  //这里网易邮箱真实很流氓啊，必须用绑定手机，然后用客户端授权码登陆
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage()+"//"+e.getLocalizedMessage());
    		return;
    	}
    	Folder folder = store.getFolder("INBOX");
    	folder.open(Folder.READ_WRITE);
    	System.out.println("Here comes the emails~~~~");
    	int messageCount = folder.getMessageCount();  //全部邮件�?
    	System.out.println("邮件总数"+messageCount);
    	/**
    	System.out.println("邮件总数"+folder.getUnreadMessageCount());
    	System.out.println("邮件总数"+folder.getDeletedMessageCount());
    	System.out.println("邮件总数"+folder.getNewMessageCount());
    	*/
    	Message[] messages = folder.getMessages();
    	
    	if (messages == null || messages.length < 1)   
            throw new MessagingException("未找到要解析的邮�?!"); 
    	
    	for(int i=0;i<messages.length;i++){
    		System.out.println("----------我们来处理第"+i+"封邮�?---------");
    		Message message = messages[i];
    		System.out.println("发�?�时间："+message.getSentDate());
    		System.out.println("主题"+decodeText(message.getSubject()));
    		System.out.println("内容�?"+message.getContent());
    		
    		@SuppressWarnings("rawtypes")
			Enumeration headers = message.getAllHeaders();
    		System.out.println("--------------all headers-------------");
    		while (headers.hasMoreElements()){
    			Header header = (Header)headers.nextElement();
    			System.out.println(header.getName()+"-----"+header.getValue());
    		}
    		
    		Object content = message.getContent();
    		if(content instanceof MimeMultipart){
    			MimeMultipart multipart = (MimeMultipart) content;
    			parseMultipart(multipart);
    		}
    	}
    	folder.close(true);
    	store.close();
    	
    }
    
    @SuppressWarnings("static-access")
	public static void parseMultipart(Multipart multipart) throws MessagingException, IOException{
    	int count = multipart.getCount();
    	System.out.println("multipart_count="+count);
    	for(int idx=0;idx<count;idx++){
    		BodyPart bodyPart = multipart.getBodyPart(idx);
    		System.out.println("�?"+idx+"条的内容类型为："+bodyPart.getContentType());
    		if(bodyPart.isMimeType("text/plain")){
    			System.out.println("plain-------------"+bodyPart.getContent());
    		}
    		else if(bodyPart.isMimeType("text/html")){
    			System.out.println("html-------------"+bodyPart.getContent());
    		}
    		else if(bodyPart.isMimeType("multipart/*")){
    			Multipart mpart = (Multipart)bodyPart.getContent();
    			parseMultipart(mpart);
    		}
    		else if (bodyPart.isMimeType("application/octet-stream")){
    			String disposition = bodyPart.getDisposition();
    			System.out.println(disposition);
    			if(disposition!=null){
    			    if (disposition.equalsIgnoreCase(bodyPart.ATTACHMENT)){
    				    String fileName = decodeText(bodyPart.getFileName());
    				    InputStream is = bodyPart.getInputStream();
    				    OutputStream out= new FileOutputStream("E:\\Documents\\tmp\\"+fileName);
    				    copy(is, out);
    			    }
    			}
    		}
    	}
    }
    
    public static void copy(InputStream is, OutputStream os) throws IOException{
    	System.out.println("-------------------comein");
    	byte[] bytes = new byte[1024];
    	int len = 0;
    	while((len=is.read(bytes))!=-1){
    		os.write(bytes, 0, len);
    	}
    	if(os != null)
    		os.close();
    	if(is != null){
    		is.close();
    	}
    }
    /** 
     * 文本解码 
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本 
     * @return 解码后的文本 
     * @throws UnsupportedEncodingException 
     */  
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {  
        if (encodeText == null || "".equals(encodeText)) {  
            return "";  
        } else {  
            return MimeUtility.decodeText(encodeText);  
        }  
    }  
}
