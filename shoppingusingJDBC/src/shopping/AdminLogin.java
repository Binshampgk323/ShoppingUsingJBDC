package shopping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminLogin {

	ConnectionMag con=new ConnectionMag();
	
	public void admin() throws ClassNotFoundException, SQLException {
		
		Scanner s=new Scanner(System.in);
		System.out.println("Enter Username");
		String user=s.next();
		System.out.println("Enter Password");
		String pass=s.next();
		//get value from database
		
		Statement st=con.getConnection().createStatement();
		ResultSet rs=st.executeQuery("select * from adminlogin");//get all values from table store in result set
		while(rs.next()) 
		{
			String u=rs.getString(1); //store first value in u
			String p=rs.getString(2); //store second value in p
			
			//check the values are same as the value in admin table
			
			if(u.contentEquals(user)&&p.contentEquals(pass)) 
			{
				System.out.println("Successfully verified");
				int op=0;
				
			do
			{
				System.out.println("1) Add Product\n2) Display\n3) Remove\n4) Update\n5) Exit");
				System.out.println("Enter choice");
				op=s.nextInt();
				
				if(op==1)  //add product
				{
					System.out.println("Enter the product Id:");
					int id=s.nextInt();
					System.out.println("Enter the product Name:");
					String pname=s.next();
					System.out.println("Enter the minsellquantity:");
					int qu=s.nextInt();
					System.out.println("Enter the price:");
					int price=s.nextInt();
					
					//set value into database(admin table)
					
					PreparedStatement pr=con.getConnection().prepareStatement("insert into addproduct(productid,name,minsellquantity,price)values(?,?,?,?);");
					pr.setInt(1, id);
					pr.setString(2, pname);
					pr.setInt(3, qu);
					pr.setInt(4, price);
					pr.execute();
					System.out.println("Product added successfully");
					
				}
				else if(op==2) //display product
				{
					//get the value from database
					
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
				else if(op==3) //remove values from table
				{
					//display the id and name of product
					
					Statement st1=con.getConnection().createStatement();
					ResultSet rs1=st1.executeQuery("select productid,name from addproduct");
					while(rs1.next())
					{
						System.out.println("#######List of ID's##########");
						System.out.println("Product id ->"+rs1.getInt(1));
						System.out.println("Name ->"+rs1.getString(2));
					}
					//delete product
					
					System.out.println("Enter product id");
					int pid=s.nextInt();
					PreparedStatement pr=con.getConnection().prepareStatement("delete from addproduct where productid=?");
					pr.setInt(1, pid);
					pr.execute();
					System.out.println("Product removed");
				}
				else if(op==4) //update values in the table
				{
					//display id and name 
					
					Statement st1=con.getConnection().createStatement();
					ResultSet rs1=st1.executeQuery("select productid,name from addproduct");
					while(rs1.next())
					{
						System.out.println("#######List of ID's##########");
						System.out.println("Product id ->"+rs1.getInt(1));
						System.out.println("Name ->"+rs1.getString(2));
						System.out.println("*******************");
					}
					int f[]=new int[3];
					System.out.println("Enter id");
					int id=s.nextInt();
					System.out.println("How many field you want to update");
					int n=s.nextInt();
					System.out.println("Which field you want to update\n1)Name\n2)Price\n3)Quantity");
				for(int i=0;i<n;i++)
				{
					f[i]=s.nextInt();
				}
				for(int i=0;i<f.length;i++)
				{
					if(f[i]==1)
					{
						System.out.println("Enter name");
						String name=s.next();
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set name=? where productid=?");
						pr.setString(1, name);
						pr.setInt(2, id);
						pr.execute();
					}
					else if(f[i]==2)
					{
						System.out.println("Enter price");
						int price=s.nextInt();
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set price=? where productid=?");
						pr.setInt(1, price);
						pr.setInt(2, id);
						pr.execute();
					}
					else if(f[i]==3)
					{
					
						System.out.println("Enter the quantity:");
						int qu=s.nextInt();
						int id1=0,quant=0;
					
						//get product id and quantity from table
						
						Statement st2=con.getConnection().createStatement();
						ResultSet rs2=st2.executeQuery("select productid,minsellquantity from addproduct");
						while(rs2.next())
						{
							id1=rs2.getInt(1);
							if(id1==id)
							{
								quant=rs2.getInt(2);
							}	
						}
					
					//update new qunantity 
					
						quant=quant+qu;
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set minsellquantity=? where productid=?");
						pr.setInt(1, quant);
						pr.setInt(2, id);
						pr.execute();
					}
					else if(f[i]==1||f[i]==2)
					{
						System.out.println("Enter product name");
						String name=s.next();
						System.out.println("Enter price");
						int price=s.nextInt();
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set name=?,price=? where productid=?");
						pr.setString(1, name);
						pr.setInt(2, price);
						pr.setInt(3, id);
						pr.execute();
					}
					else if(f[i]==2||f[i]==3)
					{
						System.out.println("Enter price");
						int price=s.nextInt();
						System.out.println("Enter product quntity");
						int quan =s.nextInt();
						
						int id1=0,quant=0;
						
						//get product id and quantity from table
						
						Statement st2=con.getConnection().createStatement();
						ResultSet rs2=st2.executeQuery("select productid,minsellquantity from addproduct");
						while(rs2.next())
						{
							id1=rs2.getInt(1);
							if(id1==id)
							{
								quant=rs2.getInt(2);
							}	
						}
						quant=quant+quan;
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set price=?,minsellquantity=? where productid=?");
						pr.setInt(1, price);
						pr.setInt(2,quant);
						pr.setInt(3, id);
						pr.execute();
					}
					else if(f[i]==1||f[i]==3)
					{
						System.out.println("Enter product name");
						String name=s.next();
						System.out.println("Enter product quntity");
						int quan =s.nextInt();
						
						int id1=0,quant=0;
						
						//get product id and quantity from table
						
						Statement st2=con.getConnection().createStatement();
						ResultSet rs2=st2.executeQuery("select productid,minsellquantity from addproduct");
						while(rs2.next())
						{
							id1=rs2.getInt(1);
							if(id1==id)
							{
								quant=rs2.getInt(2);
							}	
						}
						quant=quant+quan;
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set name=?,minsellquantity=? where productid=?");
						pr.setString(1, name);
						pr.setInt(2,quant);
						pr.setInt(3, id);
						pr.execute();
					}
					else if(f[i]==1||f[i]==2||f[i]==3)
					{
						System.out.println("Enter product name");
						String name=s.next();
						System.out.println("Enter price");
						int price=s.nextInt();
						System.out.println("Enter product quntity");
						int quan =s.nextInt();
						
						int id1=0,quant=0;
						
						//get product id and quantity from table
						
						Statement st2=con.getConnection().createStatement();
						ResultSet rs2=st2.executeQuery("select productid,minsellquantity from addproduct");
						while(rs2.next())
						{
							id1=rs2.getInt(1);
							if(id1==id)
							{
								quant=rs2.getInt(2);
							}	
						}
						quant=quant+quan;
						PreparedStatement pr=con.getConnection().prepareStatement("update addproduct set name=? price=?,minsellquantity=? where productid=?");
						pr.setString(1, name);
						pr.setInt(2, price);
						pr.setInt(3,quant);
						pr.setInt(4, id);
						pr.execute();
					}
				}
				
					System.out.println("Details updated");
				}
				else if(op==5)
				{
					System.out.println("Admin logout...");
					Main m=new Main();
					m.choice();
				}	
			}while(op==1||op==2||op==3||op==4);	
			}
			else
			{
				System.out.println("Invalid username and password");
			}
		}
		
		
	}

}
