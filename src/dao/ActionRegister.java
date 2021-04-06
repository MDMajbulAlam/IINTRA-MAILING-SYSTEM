package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ActionRegister {

    public boolean insertregister(BeanRegister data) 
    {
        boolean ans = false;
        String strInsert = "insert into register(uname,pass,gender,contact,emailid,address,sques,answer)values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);

            ps.setString(1, data.getUserName());
            ps.setString(2, data.getPassword());
            ps.setString(3, data.getGender());
            ps.setString(4, data.getContact());
            ps.setString(5, data.getEmailid());
            ps.setString(6, data.getAddress());
            ps.setString(7, data.getSQuestion());
            ps.setString(8, data.getAnswer());

            if (ps.executeUpdate() > 0) {
                ans = true;
            }
            return ans;
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
    }
    public boolean activeRegister(String uname) 
    {
        boolean ans = false;
        String strInsert = "update register set active=0 where uname=?";
        try 
        {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);
            ps.setString(1, uname);
            if (ps.executeUpdate() > 0) 
            {
                ans = true;
            }
            return ans;
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
    }

    public boolean activate(String uname) 
    {
        boolean ans = false;
        String strInsert = "update register set active=1 where uname=?";
        try 
        {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);
            ps.setString(1, uname);
            if (ps.executeUpdate() > 0) 
            {
                ans = true;
            }
            return ans;
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
    }

    
    public boolean deleteMail(int mid) 
    {
        boolean ans = false;
        String strUpdate = "update messageInfo set IsRecieverDel='yes' where MessageID=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strUpdate);
            ps.setInt(1, mid);
            if (ps.executeUpdate() > 0) 
            {
                ans = true;
            }
            return ans;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }
    public boolean deleteMailOutbox(int mid) 
    {
        boolean ans = false;
        String strUpdate = "update messageInfo set IsSenderDel='yes' where MessageID=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strUpdate);
            ps.setInt(1, mid);
            if (ps.executeUpdate() > 0) 
            {
                ans = true;
            }
            return ans;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }
    public String getLogin(String uname, String pass) 
    {
        String ans = null;
        String strSelect = "select * from register where uname=? and pass=? and active=1";
        try 
        {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ps.setString(2, pass);
             int i;
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                ans = rs.getString("role");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return ans;
    }
    
    public boolean checkLogin(String uname) 
    {
        boolean ans = false;
        String strSelect = "select * from register where uname=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                ans = true;
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
        return ans;
    }
    public boolean checkQestion(String uname,String sques, String sans) 
    {
        boolean ans = false;
        String strSelect = "select * from register where uname=? and sques=? and answer=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ps.setString(2, sques);
            ps.setString(3, sans);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                ans = true;
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
        return ans;
    }
    public boolean updatePassword(String uname, String pass) 
    {
        boolean ans = false;
        String strInsert = "update register set pass=? where uname=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);

            ps.setString(1, pass);
            ps.setString(2, uname);
            if (ps.executeUpdate() > 0) 
            {
                ans = true;
            }
            return ans;
        } catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
    }
   public boolean getRequest(String uname,String contact, String emailid,String reasons) 
    {
        boolean ans = false;
        String strSelect = "select * from request where uname=? and contact=? and emailid=? and emailid=?";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ps.setString(2, contact);
            ps.setString(3, emailid);
            ps.setString(4, reasons);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                ans = true;
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            return false;
        }
        return ans;
    }
    public ArrayList<BeanRegister> getAllRecords() {
        String strSelect = "select * from register";
        ArrayList<BeanRegister> list = null;
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                list = new ArrayList();
                BeanRegister data = null;
                do {
                    data = new BeanRegister();
                    data.setUserName(rs.getString(1));
                    data.setPassword(rs.getString(2));
                    data.setGender(rs.getString(3));
                    data.setContact(rs.getString(4));
                    data.setEmailid(rs.getString(5));
                    data.setAddress(rs.getString(6));
                    data.setSQuestion(rs.getString(7));
                    data.setAnswer(rs.getString(8));
                    list.add(data);
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return list;
    }
public ArrayList<BeanMessages> getAllRequest() 
{
        String strSelect = "select * from request r1  join register r2 on r1.username=r2.uname where r2.active=0";
        ArrayList<BeanMessages> list = null;
        try 
        {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                list = new ArrayList();
                BeanMessages data = null;
                do {
                    data = new BeanMessages();
                    data.setMid(rs.getInt(1));
                    data.setUsername(rs.getString(2));
                    data.setContact(rs.getString(3));
                    data.setEmailid(rs.getString(4));
                    data.setReasons(rs.getString(5));
                    
                    list.add(data);
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return list;
    }
    
    public boolean insertcompose(BeanMessages data) {
        boolean ans = false;
        String strInsert = "insert into messageInfo(SenderUserName,ReceiverUserName,MessageTitle,MessageText,MessageDate)values(?,?,?,?,now())";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);
            ps.setString(1, data.getSender());
            ps.setString(2, data.getReceiver());
            ps.setString(3, data.getTitle());
            ps.setString(4, data.getMessage());
            int i;
            i=ps.executeUpdate();
            if(i>0)
            {
                ans = true;
            }
            return ans;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean insertrequest(BeanRegister data) {
        boolean ans = false;
        String strInsert = "insert into request(id,username,contact,emailid,reasons)values(?,?,?,?,?)";
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strInsert);

            ps.setString(1, data.getId());
            ps.setString(2, data.getUsername());
            ps.setString(3, data.getContact());
            ps.setString(4, data.getEmailid());
            ps.setString(5, data.getReasons());

            if (ps.executeUpdate() > 0) {
                ans = true;
            }
            return ans;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public ArrayList<BeanMessages> getAllRecord(String uname) 
    {
        String strSelect = "SELECT *,date_format(MessageDate,'%D of %M, %Y') FROM messageinfo where ReceiverUserName=? and IsRecieverDel='no'";
        ArrayList<BeanMessages> list = null;
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                list = new ArrayList();
                BeanMessages data = null;
                do {
                    data = new BeanMessages();
                    data.setMessageID(rs.getInt(1));
                    data.setSenderUserName(rs.getString(2));
                    data.setReceiverUserName(rs.getString(3));
                    data.setMessageTitle(rs.getString(4));
                    data.setMessageText(rs.getString(5));
                    data.setMessageDate(rs.getString(10));
                    list.add(data);
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return list;
    }
    
    public ArrayList<BeanMessages> getAllRecordOutbox(String uname) 
    {
        String strSelect = "SELECT *,date_format(MessageDate,'%D of %M, %Y') FROM messageinfo where SenderUserName=? and IsSenderDel='no'";
        ArrayList<BeanMessages> list = null;
        try {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                list = new ArrayList();
                BeanMessages data = null;
                do {
                    data = new BeanMessages();
                    data.setMessageID(rs.getInt(1));
                    data.setSenderUserName(rs.getString(2));
                    data.setReceiverUserName(rs.getString(3));
                    data.setMessageTitle(rs.getString(4));
                    data.setMessageText(rs.getString(5));
                    data.setMessageDate(rs.getString(10));
                    list.add(data);
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return list;
    }

    public BeanMessages getRecord(int mid) {
        String strSelect = "SELECT *,date_format(MessageDate,'%D of %M, %Y') FROM messageinfo where MessageId=?";
        BeanMessages data = null;
           
        try 
        {
            PreparedStatement ps = DataConnection.createConnection().prepareStatement(strSelect);
            ps.setInt(1, mid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
            {
                    data=new BeanMessages();
                    data.setMessageID(rs.getInt(1));
                    data.setSenderUserName(rs.getString(2));
                    data.setReceiverUserName(rs.getString(3));
                    data.setMessageTitle(rs.getString(4));
                    data.setMessageText(rs.getString(5));
                    data.setMessageDate(rs.getString(10));
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            return null;
        }
        return data;
    }

}
