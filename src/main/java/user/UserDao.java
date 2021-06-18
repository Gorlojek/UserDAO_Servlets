package user;

import org.mindrot.jbcrypt.BCrypt;
import user.User;
import utils.DbUtil;


import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE ID = ?";
    private static final String UPDATE_USER_QUERY = "update users set username = ?,email = ? where id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "update users set password = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";

    public User create_user(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, hashPassword(user.getPassword()));
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                int userID = rs.getInt(1);
                user.setId(userID);
                System.out.println("Inserted ID: " + userID);

            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
    //pobieranie id uzytkownika z klawiatury


    public User read(int userId) {
        User readedUser = new User();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(READ_USER_QUERY)) {
            prepStmt.setInt(1, userId);
            prepStmt.executeQuery();
            ResultSet rs = prepStmt.getResultSet();
            if (rs.next()) {
                readedUser.setId(rs.getInt("id"));
                readedUser.setUserName(rs.getString("username"));
                readedUser.setPassword(rs.getString("password"));
                readedUser.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readedUser;
    }

    public void updateUserInfo(User user, int userID) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement(UPDATE_USER_QUERY)) {

            prep.setInt(3, userID);
            prep.setString(1, user.getUserName());
            prep.setString(2, user.getEmail());
            prep.executeUpdate();
            System.out.println("Dane użytkownika zostały zaktualizowane");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void updatePassword() {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement(UPDATE_PASSWORD_QUERY)) {

            int userID = getUserId();

            if (passCheck(userID)) {

                prep.setString(1, hashPassword(getNewPassword()));
                prep.setInt(2, userID);
                prep.executeUpdate();
                System.out.println("Hasło zostało zaktualizowane");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean passCheck(int userID) {
        boolean check = false;
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement("SELECT password FROM users where id = ?")) {
            Scanner scan = new Scanner(System.in);
            System.out.println("podaj hasło użytkownika");
            String candidate = scan.next();
            prep.setInt(1, userID);
            ResultSet rs = prep.executeQuery();
            rs.next();
            if (BCrypt.checkpw(candidate, rs.getString(1))) {
                System.out.println("Ok");
                check = true;
            } else {
                System.out.println("Podane hasło jest nieprawidłowe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement(DELETE_USER_QUERY)) {

            prep.setInt(1, userId);
            prep.executeUpdate();

            System.out.println("user.User o id: " + userId + ", został usunięty.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User[] findAll() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User templateUser = new User();
                templateUser.setUserName(rs.getString("username"));
                templateUser.setEmail(rs.getString("email"));
                templateUser.setId(rs.getInt("id"));
                templateUser.setPassword(rs.getString("password"));
                users = addToArray(templateUser, users);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);

        tmpUsers[users.length] = u;
        return tmpUsers;
    }
    public int getUserId() {
        System.out.println("Podaj id użytkownika: ");
        Scanner scan = new Scanner(System.in);
        int userID;
        while (true) {
            if (scan.hasNextInt()) {
                userID = scan.nextInt();
                if (userID > 0) {
                    return userID;
                }
            } else {
                scan.next();
            }
            System.out.println("Podaj prawidłową dodatnią liczbę całkowitą");
        }
    }
    public String getNewPassword(){
        System.out.println("Podaj nowe hasło: ");
        Scanner scan = new Scanner(System.in);
        return scan.next();

    }
}
