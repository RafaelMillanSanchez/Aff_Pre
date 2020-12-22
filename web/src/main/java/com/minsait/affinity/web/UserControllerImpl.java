package com.minsait.affinity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.affinity.service.SalesforceUserService;
import com.minsait.affinity.web.api.UserApi;
import com.minsait.affinity.web.model.WsUser;

import io.swagger.annotations.ApiParam;

@RestController
public class UserControllerImpl implements UserApi {
	
	@Autowired
	private SalesforceUserService service;

	@Override
    @RequestMapping(value = "/user",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsUser>> getUsers(@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage){
		List<WsUser> users = this.service.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@Override
	   @RequestMapping(value = "/user/{username}",
       produces = { "application/json" }, 
       method = RequestMethod.GET)
	public ResponseEntity<WsUser> getUserByUsername(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		WsUser user = this.service.getUserByUsername(username);
		if (null == user) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	

}
