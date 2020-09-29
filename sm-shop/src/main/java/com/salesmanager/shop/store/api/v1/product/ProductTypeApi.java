package com.salesmanager.shop.store.api.v1.product;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductTypeList;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to create, read, update and delete a Product API to create Manufacturer
 *
 * @author Carl Samson
 */
@RestController
@RequestMapping("/api/v1")
@Api(tags = {"Product type resource (Product Type Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product type resource", description = "Manage product types")
})
public class ProductTypeApi {


  @Inject private ProductTypeFacade productTypeFacade;


  private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeApi.class);
  
  
  @GetMapping(value = "/private/products/types", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get product types list",
      notes = "", produces = "application/json", response = List.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "vi")})
  public ReadableProductTypeList list(
	  @RequestParam(name="count", defaultValue="10") int count,
	  @RequestParam(name="page", defaultValue="0") int page,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
	  
	  return productTypeFacade.getByMerchant(merchantStore, language, count, page);
    

    
  }
  
  @GetMapping(value = "/private/products/type/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get product type",
      notes = "", produces = "application/json", response = ReadableProductType.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "vi")})
  public ReadableProductType get(
	  @PathVariable String code,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    
    return productTypeFacade.get(merchantStore, code, language);
    
  }
  
  @PostMapping(value = "/private/products/type", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Create product type",
      notes = "", produces = "application/json", response = Entity.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "vi")})
  public Entity post(
	  @RequestBody PersistableProductType type,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    
	  Long id = productTypeFacade.save(type, merchantStore, language);
	  Entity entity = new Entity();
	  entity.setId(id);
	  return entity;
    
  }
  
  @PutMapping(value = "/private/products/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "PUT", value = "Update product type",
      notes = "", produces = "application/json", response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "vi")})
  public void put(
	  @RequestBody PersistableProductType type,
	  @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
	  
	  productTypeFacade.update(type, id, merchantStore, language);
    
  }
  
  @DeleteMapping(value = "/private/products/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "DELETE", value = "Delete product type",
      notes = "", produces = "application/json", response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "vi")})
  public void delete(
	  @PathVariable Long id,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
	  
	  productTypeFacade.delete(id, merchantStore, language);

  }



}
