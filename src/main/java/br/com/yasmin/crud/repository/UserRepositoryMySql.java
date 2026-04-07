package br.com.yasmin.crud.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.yasmin.crud.models.User;
public class UserRepositoryMySql implements UserRepository
{
    private final String url;
    private final String user;
    private final String password;
    public  UserRepositoryMySql(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    @Override
    public void save(User user) {
        String query = "INSERT INTO users (id, name, email, age) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getAge());
            int rows = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                return user;
            }
            else  {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserById(String id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                users.add(user);
                return users;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                return user;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateName(String id, String name) {
        String query = "UPDATE users SET name = ? WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateAge(String id, int age) {
        String query = "UPDATE users SET age = ? WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, age);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateEmail(String id, String email) {
        String query = "UPDATE users SET email = ? WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private  Connection getConnection () throws SQLException {
           return DriverManager.getConnection(url, user, password);
   }
}

