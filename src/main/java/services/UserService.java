package services;

import models.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static List<User> getAllUsers()  {
        List<User> users = new ArrayList<User>();
        User user = null;
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(Constants.SELECT_USERS_QUERY);
            while(rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setProfile(ProfileEnum.valueOf(rs.getString(6)));
                users.add(user);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
            }
        }

        return users;
    }

    public static User getUserById(int id){
        List<User> users = getAllUsers();
        User user = null;
        for (User u : users){
            if (u.getId() == id){
                user = u;
            }
        }
        return user;
    }

    public static Resultat createUser(HttpServletRequest request){
        Resultat res = new Resultat();
        String fullName = request.getParameter("fullname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String profile = request.getParameter("profile");
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStm = con.prepareStatement(Constants.INSERT_USER_QUERY);
            preparedStm.setString(1, fullName);
            preparedStm.setString(2, login);
            preparedStm.setString(3, password);
            preparedStm.setString(4, email);
            preparedStm.setString(5, profile);
            preparedStm.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Ajout effectué avec succès en base");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }

        return res;
    }

    public static Resultat deleteUser(HttpServletRequest request){
        Resultat res = new Resultat();
        Connection con = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStmt = con.prepareStatement(Constants.DELETE_USER_QUERY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("User supprimé avec succes.");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(res.getMessage() + e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }

    public static Resultat updateUser(HttpServletRequest request){
        Resultat res = new Resultat();
        int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("fullname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String profile = request.getParameter("profile");
        Connection con = null;
        try {
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStmt = con.prepareStatement(Constants.UPDATE_USER_QUERY);
            preparedStmt.setString(1, fullname);
            preparedStmt.setString(2, login);
            preparedStmt.setString(3, password);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, profile);
            preparedStmt.setInt(6, id);
            preparedStmt.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("User mis à jour avec succès.");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }
}
