/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.*; 
import java.net.*;
import java.util.Date;
public class HttpServer {
        public static void main (String args[]) {
            int PORT = Integer.parseInt(args[0]); 
            Date date = new Date();
            try {
                // ソケットを生成
                ServerSocket serverSocket = new ServerSocket(PORT);
                // 入出力ストリームを用意し，クライアントからの要求を待つ
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream writer = new PrintStream(socket.getOutputStream());

                String line;
                // データを読み込み，そのまま返す
                do{
                  line = reader.readLine();
                  System.out.println(line);
               }while (line != null && !line.isEmpty());


                writer.println("Hello!");
                writer.close();
                reader.close();
                socket.close();
                serverSocket.close();
            } catch (SocketException e) {
                System.err.println("Socket error");
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO error");
                System.exit(-1);
            }
        }
}
