package com.hughes.TourManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hughes.TourManagement.model.Client;
import com.hughes.TourManagement.model.User;
import com.hughes.TourManagement.repository.ClientRepository;

@Service
public class ClientService {
	
	@Value("${jwt.expiration}")
	private int jwtExpirationMs;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Autowired
	private ClientRepository client_repo;
	
private Security sec=new Security();
	
	public String login(Client client){
		try
		{
//			System.out.println(user_repo.findByEmail(user.getEmail()).toString());
			if (client_repo.findByEmail(client.getEmail()) == null) {
				client_repo.save(client);
				String token = sec.generateJwtToken(client_repo.findByEmail(client.getEmail()).getId(), jwtExpirationMs, jwtSecret);
				return token;
			} else {
				Client data = client_repo.findByEmail(client.getEmail());
				String token = sec.generateJwtToken(data.getId(), jwtExpirationMs, jwtSecret);
				return token;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Error Occured!!!";
		}
		
	}
	
	public Client getClient(String token) {
		int id=Integer.parseInt(sec.fetchUser(token, jwtSecret));
//		System.out.println("ID "+id);
		Optional<Client> data = client_repo.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}

}
