/*
* SimpleClient.java: 送ったメッセージをそのまま受信するクライアント
*/
import java.io.*; import java.net.*;
public class TimeClient {

        public static void main (String args[]) {
          
            int PORT = Integer.parseInt(args[1]);
            String HOST = args[0];

            try {
                // ソケットを作成
                Socket socket = new Socket(HOST, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
	    
                // サーバにメッセージを送る
                writer.writeBytes("Hello\n");
	    
                //サーバからメッセージを受け取る
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Server: " + line);
                }
                writer.close();
                reader.close();
                socket.close();
            } catch (UnknownHostException e) {
                System.err.println("Host not found");
                System.exit(-1);
            } catch (SocketException e) {
                System.err.println("Socket error");
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO error");
                System.exit(-1);
            }
        }
}
