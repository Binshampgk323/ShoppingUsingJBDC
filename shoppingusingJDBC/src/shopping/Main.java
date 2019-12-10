package shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Main m=new Main();
		m.choice();

	}

	public void choice() throws ClassNotFoundException, SQLException {
		Scanner s=new Scanner(System.in);
		System.out.println("1)Admin Login \n2)Agent Login \n3)Exit");
		int ch=s.nextInt();
		switch(ch)
		{
		case 1:AdminLogin a=new AdminLogin();
				a.admin();
		break;
		case 2:Agent ag=new Agent();
			ag.agent();
		break;
		case 3:System.out.println("Process will be complete");
				System.exit(0);
		break;
		default:System.out.println("Invalid input");
		}
		
	}

}
