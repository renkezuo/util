package com.renke.nio;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * ��ͳsocket�����
 * ��������server.accept()+read()
 *
 */
public class OioServer {

	public static void main(String[] args) throws Exception {

		//����socket����,����10101�˿�
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(10101);
		System.out.println("������������");
		while(true){
			//��ȡһ���׽��֣�������
			final Socket socket = server.accept();
			System.out.println("����һ���¿ͻ��ˣ�");
			handler(socket);
//			Executors.newCachedThreadPool().execute(new Runnable() {
//				@Override
//				public void run() {
//					//ҵ����
//					handler(socket);
//				}
//			});
			
		}
	}
	
	/**
	 * ��ȡ����
	 * @param socket
	 * @throws Exception
	 */
	public static void handler(Socket socket){
		try {
			byte[] bytes = new byte[1024];
			InputStream inputStream = socket.getInputStream();
			
			while(true){
				//��ȡ���ݣ�������
				int read = inputStream.read(bytes);
				if(read != -1){
					System.out.println(new String(bytes, 0, read));
				}else{
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				System.out.println("socket�ر�");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}