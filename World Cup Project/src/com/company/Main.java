package com.company;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here
        Scanner sc=new Scanner(System.in);

        //1]Register and load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2]It is used to Establish the connection to the DBMS,DriverManager.getConnection()
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cricket","root","Keshav798794@");

        //3]Create the statement object
        Statement st=con.createStatement();

        boolean flag=true;
        while(flag){
            System.out.println("Enter the Choice:");
            System.out.println("1.View the scoreboard:");
            System.out.println("2.Insert the new record:");
            System.out.println("3.Update the record:");
            System.out.println("4.Delete the record:");
            System.out.println("5.Exit");

            int choice=sc.nextInt();

            //executeQuery()-->It is used to View the database,present inside Statement object
            //executeUpdate()-->It is used to Insert,Update,Delete something
            switch (choice){
                case 1:
                    String sql="select * from scoretable;";
                    //ResultSet obj is used to Point First row of Database and the it point to the next row
                    //next()-->it is used to point row by row
                    //get()-->it is used to retrive the data of row
                    ResultSet rs=st.executeQuery(sql);
                    System.out.println("Id\t | Name\t | Runs\t | Balls");
                    while (rs.next()){
                        System.out.println(rs.getInt(1)+ "\t" +rs.getString(2) + "\t" +rs.getInt(3) + "\t" +rs.getInt(4));
                    }

                    System.out.println("********************************************************");
                    break;

                    //Insert
                case 2:
                    //Insert Values into Database table,Insert into scoretable values(1,"Kholi",100,38)
                    //For inserting data into Database we use executeUpdate()
                    System.out.println("Enter the Id:");
                    int id=sc.nextInt();

                    System.out.println("Enter the Name of Player:");
                    String name=sc.next();

                    System.out.println("Enter the Runs:");
                    int runs=sc.nextInt();

                    System.out.println("Enter the Balls:");
                    int balls=sc.nextInt();

                    String insertQuery="INSERT INTO scoretable VALUES("+id+", '"+name+"', "+runs+", "+balls+");";
                    int rows=st.executeUpdate(insertQuery);
                    System.out.println(rows+"rows inserted");

                    System.out.println("********************************************************");
                    break;


                    //Update
                case 3:
                    //Update scoretable runs=148,balls=48 Where id=1
                    //Update query is used to update records of Existing table
                    System.out.println("Enter the Id of Player:");
                    int Id=sc.nextInt();
                    System.out.println("Enter New Runs of Player:");
                    int Runs=sc.nextInt();
                    System.out.println("In how many Balls:");
                    int Balls=sc.nextInt();

                    String updateQuery="Update scoretable set runs="+Runs+",balls="+Balls+" Where id="+Id+";";
                    rows=st.executeUpdate(updateQuery);  //How many rows are Updated
                    System.out.println(rows+"rows updated");

                    System.out.println("********************************************************");
                    break;

                case 4:
                    //Delete from scoretable where id=102;
                    System.out.println("Enter the id of Player:");
                    Id=sc.nextInt();

                    String deleteQuery="Delete from scoretable Where id="+Id+";";
                    rows=st.executeUpdate(deleteQuery);
                    System.out.println(rows+"rows Deleted");

                    System.out.println("********************************************************");
                    break;

                default:
                    flag=false;
                    break;
            }

        }
    }
}
