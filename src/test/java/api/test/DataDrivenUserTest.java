package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenUserTest {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testpostuser(String userID, String userName, String pwd, String fName, String lName, String email, String ph) {
		
		User userPayLoad = new User();
		userPayLoad.setId(Integer.parseInt(userID));
		userPayLoad.setUserName(userName);
		userPayLoad.setPasswrod(pwd);
		userPayLoad.setFirstName(fName);
		userPayLoad.setLastName(lName);
		userPayLoad.setEmail(email);
		userPayLoad.setPhone(ph);
		
		Response response = UserEndpoints.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUeerByName(String userName) {
		
		Response response = UserEndpoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
