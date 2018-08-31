package org.atomsk.chat1;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatletServer {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("READY.....................................");
        Socket socket = serverSocket.accept();

        Chatlet chatlet = new Chatlet(socket);

        //MAIN은 쓰레드 둘에 바통터치하고 종료
//        new Thread(()->{
//
//            try {
//                chatlet.read();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }S
//
//        }).start();
        new Thread(chatlet).start();;


//        new Thread(()->{
//
//            try {
//                chatlet.write();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        chatlet.write(); //main이 대신 실행


        System.out.println("END");


    }


}
