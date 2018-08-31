package org.atomsk.chat1;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatletClient {

    public static void main(String[] args) throws Exception {


        Socket socket = new Socket("10.10.10.86",7777);

        System.out.println("client ready");

        Chatlet chatlet = new Chatlet(socket);

        //MAIN은 쓰레드 둘에 바통터치하고 종료
//        new Thread(() -> {
//
//            try {
//                chatlet.read();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//
//
//        new Thread(() -> {
//
//            try {
//                chatlet.write();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        new Thread(chatlet).start();;
        chatlet.write(); //main이 대신 실행

        System.out.println("END");
    }
}
