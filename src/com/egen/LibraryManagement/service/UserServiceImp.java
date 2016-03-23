package com.egen.LibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.egen.LibraryManagement.dao.UserDAO;
import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;
import com.egen.LibraryManagement.exception.UserNotFound;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	@Qualifier("userDAOImp")
	private UserDAO userDAO;

	@Override
	public User addUser(User u) throws UserBadRequest {
		boolean user=validateUser(u);
		if (user)
			return userDAO.addUser(u);
		else
			throw new UserBadRequest();
	}

	@Override
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}

	@Override
	public User updateUser(User u) throws UserNotFound {
		User existing = userDAO.getUserById(u.getId());
		if (existing == null) {
			 throw new UserNotFound();
		} else {
			return userDAO.updateUser(u);
		}
	}

	public boolean validateUser(User u) {
		/*
		 * Rules for validation:
		 */
		String regExfirstName = "[A-Za-z]+";
		String regExMiddleName = "[A-Za-z]?";
		String regExLastName = "[A-Za-z]+";
		String regAge = "0?[1-9]|[1-9][0-9]";
		String regExGender = "[MFmf]";
		String regExphone = "[0-9]{10}";

		if (u.getFirstName().matches(regExfirstName) && u.getLastName().matches(regExLastName)
				&& u.getAge().matches(regAge) && u.getGender().matches(regExGender) && u.getPhone().matches(regExphone)) {
			if(u.getMiddleName()==null|| u.getMiddleName().matches(regExMiddleName)){
				return true;
			}
		} 
			return false;
	}

}
