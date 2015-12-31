package com.email.common;
import java.io.IOException;

import javax.mail.MessagingException;

import com.email.inbox.PopFetcher;

/**
 * 整个项目的main函数，项目的入口在这里
 * @author zhujuan
 *
 */
public class CommonMain {
    public static void main(String args[]) throws MessagingException, IOException{
    	PopFetcher.popfetcher();
    }
}
