package com.email.common;
import java.io.IOException;

import javax.mail.MessagingException;

import com.email.inbox.PopFetcher;

/**
 * ������Ŀ��main��������Ŀ�����������
 * @author zhujuan
 *
 */
public class CommonMain {
    public static void main(String args[]) throws MessagingException, IOException{
    	PopFetcher.popfetcher();
    }
}
