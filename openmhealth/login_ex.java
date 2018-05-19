import java.io.*;
import java.util.*;
import java.lang.*;

public class login {
	public static void main(String[] args) { 

		String pass;
		String user;
		Scanner suser = new Scanner(System.in);
		Scanner spass = new Scanner(System.in);

		System.out.println("Enter user: ");
		user = suser.nextLine();
		System.out.println("Enter PW: ");
		pass = spass.nextLine();
		System.out.println("User : "+user+" PW: "+pass);



		if(pass.equals("blue") && user.equals("bob") ){
			System.out.println("Credentials Accepted.");
		}
		else{
			System.out.println("Please try again.");
		}

		System.out.println("Hello, World");
	}
}
