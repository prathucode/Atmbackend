package practice.AtmBackend.dao;

import practice.AtmBackend.dto.Atmdto;

import java.sql.*;
import java.util.ArrayList;

public class Atmdao {
     static Connection con=null;
     static ArrayList<Atmdto>list=new ArrayList<>();
    static
    {
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","sql@123");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public int signin(Atmdto a1) {
        PreparedStatement ps=null;
        int count=0;
        String query="insert into atm_info values(?,?,?,?,?,?)";
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1,a1.getId());
            ps.setString(2,a1.getUsername());
            ps.setString(3,a1.getPassword());
            ps.setString(5,a1.getAccno());
            ps.setDouble(4,a1.getAmount());
            ps.setString(6,a1.getBranch());
            list.add(a1);
          count=ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return count;

    }

    public int withdrawamt(Atmdto a1) {
        int count=0;
       PreparedStatement ps=null;
        ResultSet rs=null;
        String query="select password,amount from atm_info";
        try {
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                if(rs.getString(1).equals(a1.getPassword()))
                {
                    if(a1.getAmount()<rs.getDouble(2))
                    {
                        String query1="update atm_info set amount=amount-"+a1.getAmount()+"where accno='"+a1.getAccno()+"'";
                        Statement stmt=null;
                        stmt= con.createStatement();
                       count= stmt.executeUpdate(query1);
                    }
                    else {
                        System.out.println("insufficient Balance in your Account");
                    }

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return count;
    }

    public ArrayList<Atmdto> checkbalance(Atmdto a1) {
        ArrayList<Atmdto>list=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String query="select password from atm_info";
        try {
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                if(rs.getString(1).equals(a1.getPassword()))
                {
                    String query1="select amount from atm_info where accno='"+a1.getAccno()+"'";
                    ps=con.prepareStatement(query1);
                    rs=ps.executeQuery();
                    while(rs.next())
                    {
                       double amt=rs.getDouble(1);
                       Atmdto a2=new Atmdto();
                       a2.setAmount(amt);
                       list.add(a2);

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return list;
    }
}
