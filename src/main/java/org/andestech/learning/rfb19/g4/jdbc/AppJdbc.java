package org.andestech.learning.rfb19.g4.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class AppJdbc {

    public static void main(String[] args) throws ClassNotFoundException
    {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://213.199.*******/rfb19";
        Properties props = new Properties();
        props.setProperty("user","*******");
        props.setProperty("password","*****");
        props.setProperty("ssl","false");

        Connection conn = null;
        Statement statement = null;
        PreparedStatement pstate = null;

        try {
            conn = DriverManager.getConnection(url, props);

            System.out.println(conn);
            conn.setAutoCommit(true);

            statement =
                    conn.createStatement();

            pstate = conn.prepareStatement("insert into table01(id,data) values(?, ?)");




           // statement.execute("create table table01(id int, data varchar(20));");

//            statement.executeUpdate("insert into table01(id,data) values(1, 'Hello!')");
//            statement.executeUpdate("insert into table01(id,data) values(2, 'Привет!')");

            statement.executeUpdate("update table01 set data='Howdoyoudo!' where id=1");


            for(int j = 3; j<28; j++)
            {
                String datas =  "Data" + new Random().nextInt(200000);
                pstate.setInt(1,j);
                pstate.setString(2,datas);

                pstate.execute();

            }




            ResultSet rset = statement.executeQuery("select * from table01");

            while(rset.next()){

                System.out.println( rset.getInt(1) + " : " +
                        rset.getString(2));
            }

//
//            if (pstate != null)    pstate.close();
//            if (statement != null) statement.close();
//            if (conn != null)      conn.close();
        }
        catch (SQLException ex){ex.printStackTrace();}
        finally {
            try {
                if (pstate != null) pstate.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();

            } catch (SQLException ex) {ex.printStackTrace();}

        }





    }
}
