package dao;
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; import java.util.List;
import Metier.SingletonConnection;
import Metier.User;
public class UserDaoImpl implements IUserDao {
	
	public boolean login(User u) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM USERS WHERE login=? AND password=?");
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getMp());
            ResultSet rs = ps.executeQuery();
            boolean userExists = false;
            if (rs.next() && rs.getInt(1) > 0) {
                userExists = true;
            }
            rs.close();
            ps.close();
            return userExists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User register(User u) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO USERS VALUES (null,?, ?, ?, ?)");
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getLogin());
            ps.setString(4, u.getMp());
            int rowsInserted = ps.executeUpdate();
            ps.close();
            return rowsInserted > 0 ? u : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> UserByNameOrLastname(String mc) {
        Connection conn = SingletonConnection.getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE last_name=? OR name=?");
            ps.setString(1, mc);
            ps.setString(2, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
	public User getUser(long id) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setNom(rs.getString("name"));
                user.setPrenom(rs.getString("last_name"));
				ps.close();
				return user;
			}else {
				ps.close();
				return null;
			}
		
		}  catch  (SQLException  e)  { 
			e.printStackTrace();
		}
		return null;
	}
	public User updateUser(User p) {
	    Connection conn = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE USERS SET name=?, last_name=?, password=? WHERE login=?");
	        ps.setString(1, p.getNom());
	        ps.setString(2, p.getPrenom());
	        ps.setString(3, p.getMp());
	        ps.setString(4, p.getLogin());
	        int rowsUpdated = ps.executeUpdate();
	        ps.close();
	        return rowsUpdated > 0 ? p : null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void deleteUser(int id) {
	    Connection conn = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("DELETE FROM USERS WHERE id_user = ?");
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        System.out.println("Deleted !!!");
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
