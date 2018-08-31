package org.atomsk.chat1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


//모든 시작이 소켓이기 떄문에 생성자를 소켓으로
public class Chatlet implements Runnable{

    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dos;
    private Scanner scanner;

    public Chatlet(Socket socket) throws Exception{
        this.socket = socket;
        this.din = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.scanner = new Scanner(System.in);
    }
    public void write() throws Exception{
        while (true){
            String msg = scanner.nextLine();
            dos.writeUTF(msg);

        }

    }

    public void read()throws Exception{
        while (true){
            String msg = din.readUTF();
            System.out.println(msg);

        }


    }

    public void closeAll(){ //따로따로 트라이를 잡는 이유. 같이 했을때 위에 부분이 클로즈가 안되고 오류뜨면 아래도 안됨
        try{ scanner.close();}catch (Exception e){}
        try{ din.close();}catch (Exception e){}
        try{ dos.close();}catch (Exception e){}
        try{ socket.close();}catch (Exception e){}
    }


    @Override
    public void run() {//얘가 쓰레드가 관통하는 코드
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
            closeAll();//여기서 잘못되면 다 정지
        }




    }
}
