package br.com.yasmin.crud.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.yasmin.crud.exceptions.DatabaseException;
import br.com.yasmin.crud.exceptions.UserNotFoundException;
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
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error saving user", e);
        }
    }

    @Override
    public User getUserById(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query))
        {stmt.setString(1, id);
            try(ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw new UserNotFoundException("User not found with this id");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error getting user by id", e);
        }
    }
    @Override
    public void deleteUserById(String id) {
        String query = "DELETE FROM users WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                //No futuro, desativar autoCommit e fazer rollback
                throw new IllegalStateException("Illegal amount of rows changed" + rows);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting user by id", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery())
        {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapRow(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DatabaseException("Error getting users", e);
        }
    }
    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query))
        {    stmt.setString(1, email);
            try(ResultSet rs = stmt.executeQuery() ) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw  new UserNotFoundException("User not found with this email");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error getting user by email", e);
        }


    }
    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        return user;
    }

    @Override
    public void updateName(String id, String name) {
        String query = "UPDATE users SET name = ? WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new IllegalStateException("Illegal amount of rows affected " + rows);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error Updating name", e);
        }

    }

    @Override
    public void updateAge(String id, int age) {
        String query = "UPDATE users SET age = ? WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, age);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new IllegalStateException("Illegal amount of rows affected " + rows);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error updating age", e);
        }
    }
    @Override
    public void updateEmail(String id, String email) {
        String query = "UPDATE users SET email = ? WHERE id = ?";
        try(Connection conn  = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, id);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new IllegalStateException("Illegal amount of rows changed" + rows);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error updating email", e);
        }

    }
    private  Connection getConnection () throws SQLException {
           return DriverManager.getConnection(url, user, password);
   }
}

