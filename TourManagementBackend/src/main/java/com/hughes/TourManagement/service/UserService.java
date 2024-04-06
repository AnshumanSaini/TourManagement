package com.hughes.TourManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hughes.TourManagement.model.User;
import com.hughes.TourManagement.repository.UserRepository;

@Service
public class UserService {
	
	@Value("${jwt.expiration}")
	private int jwtExpirationMs;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Autowired
	private UserRepository user_repo;
	
	private Security sec=new Security();
	
	public String login(User user){
		try
		{
//			System.out.println(user_repo.findByEmail(user.getEmail()).toString());
			if (user_repo.findByEmail(user.getEmail()) == null) {
				user_repo.save(user);
				String token = sec.generateJwtToken(user_repo.findByEmail(user.getEmail()).getId(), jwtExpirationMs, jwtSecret);
				return token;
			} else {
				User data = user_repo.findByEmail(user.getEmail());
				String token = sec.generateJwtToken(data.getId(), jwtExpirationMs, jwtSecret);
				return token;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Error Occured!!!";
		}
		
	}
	
	public User getUser(String token) {
	    try {
	        int id = Integer.parseInt(sec.fetchUser(token, jwtSecret));
	        Optional<User> data = user_repo.findById(id);
	        return data.orElse(null);
	    } catch (NumberFormatException e) {
	        // Handle invalid token format
	        System.err.println("Invalid token format: " + token);
	        e.printStackTrace();
	        return null;
	    } catch (Exception e) {
	        // Handle other exceptions (e.g., database errors)
	        System.err.println("Error fetching user: " + e.getMessage());
	        return null;
	    }
	}


}
