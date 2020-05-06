package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    BufferedReader reader;
    PrintWriter writer;
    GameClient(){
        startConexion();
    }
    public void startConexion(){
        try  {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 6868);
            System.out.println(socket.getInetAddress());
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
            startGame();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startGame() throws IOException {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Start or join a game\n");
            String command = scanner.nextLine();
            writer.println(command);
            String response = reader.readLine();
            System.out.println(response);
            if(response.contains("Game started")) break;

        }
    }
    public void sendMakeMove(int pos){
        writer.println("make move "+pos/15+" "+pos%15);
        try {
            String response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int receiveMove(){
        writer.println("receive move");
        try {
            String response = reader.readLine();
            int position = Integer.parseInt(response);
            return position;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int getColor(){
        writer.println("get color");
        try {
            String response = reader.readLine();
            System.out.println(response);
            return Integer.parseInt(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
