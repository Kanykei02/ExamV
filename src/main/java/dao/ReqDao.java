package dao;

import model.Requests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReqDao {
    public static boolean createReq(Requests requests){
        String sql = "insert into requests (request_date_time, full_name, birth_year, gender) values(now(), ?, ?, ?)";
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(2, requests.getFullName());
            stmt.setLong(3, requests.getBirthYear());
            stmt.setString(4, requests.getGender());
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Requests> getAllRequests(){
        List<Requests> reqList = new ArrayList<>();
        String sql = "select * from requests";
        try(Connection conn = DataBase.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Requests requests = new Requests(
                        rs.getLong("id"),
                        rs.getDate("request_date_time"),
                        rs.getString("full_name"),
                        rs.getLong("birth_year"),
                        rs.getString("gender")
                );
                reqList.add(requests);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return reqList;
    }

    public static Requests getUserById(int id){
        String SQL = "select * from users where id = ?";
        Requests user = null;
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    user = new Requests(
                            rs.getLong("id"),
                            rs.getString("full_name"),
                            rs.getLong("birth_year"),
                            rs.getString("gender")
                    );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
    }

}
