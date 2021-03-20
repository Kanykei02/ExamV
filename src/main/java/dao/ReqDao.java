package dao;

import model.Requests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReqDao {
    public static String createReq(Requests requests){
        String sql = "insert into requests (request_date_time, full_name, birth_year, gender) values(now(), ?, ?, ?)";
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(2, requests.getFullName());
            stmt.setLong(3, requests.getBirthYear());
            stmt.setString(4, requests.getGender());
            return "ok";
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "not ok";
    }

    public List<Requests> getAllRequests() {
        String sql = "SELECT * FROM request";
        List<Requests> requestList = new ArrayList<>();
        try (Connection conn = new DataBase().connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Requests request = new Requests();
                request.setId(rs.getLong("id"));
                request.setRequestTime(rs.getDate("request_date_time"));
                request.setFullName(rs.getString("full_name"));
                request.setBirthYear(rs.getLong("birth_year"));
                request.setGender(rs.getString("gender"));
                requestList.add(request);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requestList;
    }

    public static Requests getUserById(int id){
        String SQL = "select * from requests where id = ?";
        Requests requests = null;
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    requests = new Requests(
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
        return requests;
    }

    public List<Requests> getReqByName(String name) {
        String sql = "SELECT * FROM requests WHERE name = ?";
        List<Requests> requestList = new ArrayList<>();

        try (Connection conn = new DataBase().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Requests request = new Requests();
                    request.setId(rs.getLong("id"));
                    request.setRequestTime(rs.getDate("request_date_time"));
                    request.setFullName(rs.getString("full_name"));
                    request.setBirthYear(rs.getLong("birth_year"));
                    request.setGender(rs.getString("gender"));
                    requestList.add(request);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requestList;
    }

    public List<Requests> getReqByBirthYear(Integer year) {
        String sql = "SELECT * FROM request WHERE birth_year = ?";

        List<Requests> requestList = new ArrayList<>();

        try (Connection conn = new DataBase().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Requests request = new Requests();
                    request.setId(rs.getLong("id"));
                    request.setRequestTime(rs.getDate("request_date_time"));
                    request.setFullName(rs.getString("name"));
                    request.setBirthYear(rs.getLong("birth_year"));
                    request.setGender(rs.getString("gender"));
                    requestList.add(request);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requestList;
    }

    public String updateReq(Requests requests){
        String SQL = "update requests set full_name = ?, birth_year = ?, gender = ?, where id = ?";
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, requests.getFullName());
            stmt.setLong(2, requests.getBirthYear());
            stmt.setString(3, requests.getGender());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "not ok";
    }

    public String deleteReq(Long id){
        String SQL = "delete from requests where id = ?";
        try(Connection conn = DataBase.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
            return "Ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Not ok";
    }

    public void createRequest(Requests request) {
    }
}
