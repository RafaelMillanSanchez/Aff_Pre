package com.minsait.affinity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.affinity.service.SalesforceBrandService;
import com.minsait.affinity.web.api.BrandsApi;
import com.minsait.affinity.web.model.WsBrands;

import io.swagger.annotations.ApiParam;

@RestController
public class BrandControllerImpl implements BrandsApi{
	
	@Autowired
	private SalesforceBrandService service;

	@Override
	@RequestMapping(value = "/brands",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsBrands>> getBrands(
			@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage) {
		List<WsBrands> brands = this.service.getBrands();
		if (brands != null) return new ResponseEntity<>(brands, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
