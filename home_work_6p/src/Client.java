import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String server = "localhost";
    private static final int port = 60080;

    public static void main( String[] args ) {
        Socket socket = null;
        Scanner scanner = new Scanner( System.in );
        try {
            socket = new Socket(server, port );
            System.out.println("Client joying" + socket.getRemoteSocketAddress());
            DataInputStream inputStream = new DataInputStream( socket.getInputStream() );
            DataOutputStream outputStream = new DataOutputStream( socket.getOutputStream() );

            Thread threadReader = new Thread (() ->{
                while (true){
                    try {
                        outputStream.writeUTF( scanner.nextLine() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadReader.setDaemon( true );
            threadReader.start();

            while(true){
                String str = inputStream.readUTF();
                if(str.equals( "quite" )){
                    System.out.println("Lost connection to server");
                    outputStream.writeUTF( "quite" );
                    break;
                }else {
                    System.out.println("Server " + str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}


