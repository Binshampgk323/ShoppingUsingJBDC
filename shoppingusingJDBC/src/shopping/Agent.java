package shopping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Agent {

	ConnectionMag con=new ConnectionMag();
	Scanner s=new Scanner(System.in);
	public void agent() throws ClassNotFoundException, SQLException {
		System.out.println("Enter your username");
		String user=s.next();
		System.out.println("Enter password");
		String pass=s.next();
		Statement st=con.getConnection().createStatement();
		ResultSet rs=st.executeQuery("select * from agentlogin");
		while(rs.next())
		{
			String u=rs.getString(1);
			String p=rs.getString(2);
			if(u.contentEquals(user)&&p.contentEquals(pass))
			{
				System.out.println("Successfully verified");
				int op=0;
				do
				{
					System.out.println("1)Buy sell\n2)View Product\n3)Logout");
					System.out.println("Enter your choice");
					op=s.nextInt();
					if(op==1)
					{
						System.out.println("Enter the Id");
						int id=s.nextInt();
						System.out.println("Enter the Quantity");
						int quantity=s.nextInt();
						
						int id1=0,price=0,quantity1=0;
						Statement st1=con.getConnection().createStatement();
						ResultSet rs1=st.executeQuery("select productid,price,minsellquantity from addproduct");
						while(rs1.next())
						{
							 id1=rs1.getInt(1);
							 if(id1==id)
							 {
								 price=rs1.getInt(2);
								 quantity1=rs1.getInt(3);
								 if(quantity1==0)
								 {
									 System.out.println("Sorry .. it's empty");
								 }
								 else if(quantity1<quantity)
								 {
									 System.out.println("There is only "+quantity1+" is presnet");
									 
								 }
								 else if(quantity1>quantity)
								 {
									 System.out.println("Cost is:"+quantity*price);
									 quantity1=quantity1-quantity;
									 if(quantity1>=0)
									 {
										 quantity1=quantity1;
									 }
									 else
									 {
										 quantity1=0;
									 }
								 }
							 }
						}
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set minsellquantity=? where productid=?");
						pr.setInt(1, quantity1);
						pr.setInt(2, id);
						pr.execute();
						System.out.println("Enter 1 to confirm..");
						int c=s.nextInt();
						System.out.println("Your booking is confirmed\nThank you.....");
					}
					else if(op==2)
					{
						Statement st1=con.getConnection().createStatement();
						ResultSet rs1=st1.executeQuery("select * from addproduct ");
						while(rs1.next())
						{
							System.out.println("#########********########");
							System.out.println("Product ID   ->  "+rs1.getInt(1));
							System.out.println("Product Name  ->  "+rs1.getString(2));
							System.out.println("Quantity     -> "+rs1.getInt(3));
							System.out.println("Price      -> "+rs1.getInt(4));
							System.out.println("#########********########");
						}
					}
					else if(op==3)
					{
						System.out.println("Agent logout.. ");
						Main n=new Main();
						n.choice();
					}
					else
					{
						System.out.println("invalid input");
					}
				}while(op==1||op==2);
			}
			else
			{
				System.out.println("Invalid username and password");
			}
		}
		
	}

}
