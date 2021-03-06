/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.*; 
import java.net.*;
public class SimpleServer01 {
        public static void main (String args[]) {
          //固定されていたポートを第一引数から持ってくる
          int PORT = Integer.parseInt(args[0]); 
            try {
                // ソケットを生成
                ServerSocket serverSocket = new ServerSocket(PORT);
                // 入出力ストリームを用意し，クライアントからの要求を待つ
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream writer = new PrintStream(socket.getOutputStream());

                // データを読み込み，そのまま返す
                while (true) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                    writer.println(line);
                }
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
