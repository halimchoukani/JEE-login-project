package dao;

import java.util.List;

import Metier.User;

public interface IUserDao {
	public User register(User u);
	public boolean login(User u);
	public  List<User>  UserByNameOrLastname(String  mc);
	public User getUser(long id);
	public  User  updateUser(User  p); 
	public  void  deleteUser(int  id);
}
