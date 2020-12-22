package com.minsait.affinity.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minsait.affinity.jpa.model.Profile;
import com.minsait.affinity.jpa.model.User;
import com.minsait.affinity.repo.ProfileRepository;
import com.minsait.affinity.repo.UserRepository;
import com.minsait.affinity.web.model.WsUser;

@Component
public class UserDataMapper {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public WsUser salesforce2web(User user) {
		if (null == user) return null;
		WsUser wsu = new WsUser();
		wsu.setId(user.getSfid());
		wsu.setEmail(user.getEmail());
		wsu.setName(user.getName());
		wsu.setPhone(user.getPhone());
		wsu.setAlias(user.getAlias());
		wsu.setLastname(user.getLastname());
		wsu.setEmailencodingkey(user.getEmailencodingkey());
		wsu.setLanguagelocalekey(user.getLanguagelocalekey());
		wsu.setLocalesidkey(user.getLocalesidkey());
		wsu.setProfileid(user.getProfileid());
		wsu.setStaffCountry(user.getStaffCountryC());
		Profile p = this.profileRepository.findBySfid(user.getProfileid());
		if (p == null) wsu.setRol("No identificado");
		else wsu.setRol(p.getName());
		wsu.setTimezonesidkey(user.getTimezonesidkey());
		wsu.setUsername(user.getUsername());
		String managerid = user.getManagerid();
		User tam = new User();
		if (managerid != null) {
			tam = this.userRepository.findBySfid(managerid);
		}	
		wsu.setTAM(tam.getName());
		wsu.setSapId(user.getTxtSapIdC());
		return wsu;
	}
	
	public User web2salesforce(WsUser user) {
		if (null == user) return null;
		User u = new User();
		//u.setId(123456);
		u.setName(user.getName());
		u.setPhone(user.getPhone());
		u.setEmail(user.getEmail());
		u.setAlias(user.getAlias());
		u.setLanguagelocalekey(user.getLanguagelocalekey());
		u.setEmailencodingkey(user.getEmailencodingkey());
		u.setUsername(user.getUsername());
		u.setLocalesidkey(user.getLocalesidkey());
		u.setLastname(user.getLastname());
		u.setProfileid(user.getProfileid());
		u.setTimezonesidkey(user.getTimezonesidkey());
		return u;
	}
	
	public User update2salesforce(WsUser user, Integer id) {
		if (null == user) return null;
		User u = new User();
		u.setId(id);
		u.setName(user.getName());
		u.setPhone(user.getPhone());
		u.setEmail(user.getEmail());
		u.setAlias(user.getAlias());
		return u;
	}
	
	
	public List<WsUser> salesforce2web(Iterable<User> user){
		List<WsUser> lst = null;
		if (null != user) {
			lst = new ArrayList<>();
			for (User u : user) {
				lst.add(this.salesforce2web(u));
			}
		}
		return lst;
	}


}
