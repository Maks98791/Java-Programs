import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Socket socket = new Socket("localhost", 2500);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        
	        String line = in.readLine();
	        
	        String[] parts = line.split(";");
			int num1 = Integer.parseInt(parts[1]);
			int num2 = Integer.parseInt(parts[2]);
			
			int result = num1 - num2;
			
			out.println(result);
	     
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
