package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName("ankitsax");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPasswrod(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test
	public void testPostUser(){
		logger.info("**************** Creating User *************");
		Response response = UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		logger.info("**************** User Created *************");
	}

	@Test 
	public void testGetUser(){
		
		logger.info("**************** Get User *************");
		Response response = UserEndpoints2.readUser(this.userPayload.getUserName());				
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		logger.info("**************** User Retrieved *************");
	}
	
	@Test
	public void testUpdateUser(){
		
		logger.info("**************** Updating User *************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndpoints2.UpdateUser(this.userPayload.getUserName(), userPayload);
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		logger.info("**************** User Updated *************");
	}
	
	@Test
	public void testDeleteUser(){
		
		logger.info("**************** Delete User *************");
		Response response = UserEndpoints2.deleteUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(200, response.getStatusCode());
		logger.info("**************** User Deleted *************");
	}
}
