package com.email.window;


/**
 * ��˵���������������ϵĸ�����
 * 
 */
public class FrameFactory {
	private static FrameFactory factory = new FrameFactory();

	private FrameFactory() {
	}

	public static FrameFactory getFrameFactory() {
		return factory;
	}

	// �ռ������

	public ReceiveFrame getReceiveFrame() {
		return new ReceiveFrame();
	}

	// �ѷ����ʼ�����

	public SendedFrame getSendedFrame() {
		return new SendedFrame();
	}

	// �����ʼ�����

	public SendFrame getSendFrame() {
		return new SendFrame();
	}

	// ����վ����
	public RecycleFrame getRecycleFrame() {
		return new RecycleFrame();
	}

	// ��ϵ���б�

	public AddLinkManFrame getAddLinkManFrame() {
		return new AddLinkManFrame();
	}
	
	// �˻��б�
	public AddAccountFrame getAddAccountFrame() {
		return new AddAccountFrame();
	}
}
