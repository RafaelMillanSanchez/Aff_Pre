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

import com.minsait.affinity.service.SalesforcePicklistService;
import com.minsait.affinity.web.api.PicklistApi;
import com.minsait.affinity.web.model.WsPicklist;

import io.swagger.annotations.ApiParam;


@RestController
public class PicklistControllerImpl implements PicklistApi{

	@Autowired
	private SalesforcePicklistService service;
	
	@Override
	@RequestMapping(value = "/picklist",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsPicklist>> getAllPK(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("pck") String pck,
			@ApiParam(value = "", required = true) @PathVariable("relatedpck") String relatedpck,
			@ApiParam(value = "", required = true) @PathVariable("relatedValue") String relatedValue,
			@ApiParam(value = "", required = true) @PathVariable("relatedpck2") String relatedpck2,
			@ApiParam(value = "", required = true) @PathVariable("relatedValue2") String relatedValue2) {
		List<WsPicklist> picklist = this.service.getPicklist(pck, relatedpck, relatedValue, relatedpck2, relatedValue2);
		if (picklist != null) return new ResponseEntity<>(picklist, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	
	

}
