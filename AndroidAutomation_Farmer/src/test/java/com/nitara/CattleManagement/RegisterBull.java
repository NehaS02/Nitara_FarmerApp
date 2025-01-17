package com.nitara.CattleManagement;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nitara.GenericBase.GenericBase;
import com.nitara.Helper.GenerateRandomData;
import com.nitara.PageObjects.BullRegister_Page;
import com.nitara.PageObjects.CattleRegistrationSuccess_Page;
import com.nitara.PageObjects.FarmerHomePage;
import com.nitara.PageObjects.LoginObjects;
import com.nitara.utils.DataProviderUtils;

public class RegisterBull extends GenericBase{
	
	@Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
	public void RegisterCattle_Bull(Map<String,String> data) throws InterruptedException {
		
		GenerateRandomData numb = new GenerateRandomData();
		//ExtentTest test = extent.createTest("Register Bull Cattle", "Verify user is able to register Bull cattle");

		String username = prop.getProperty("Username");
		String password = prop.getProperty("Password"); 

		
		String tagNumber = numb.generateRandomNumber(7);
		String cooptagNumber = numb.generateRandomNumber(12);

		/*//Login with Farmer credentials
		LoginObjects login = new LoginObjects();
		login.userLogin(username, password);
		*/

		//Farmer Home page - Select Register Cattle
		FarmerHomePage obj = new FarmerHomePage();
		obj.press_RegisterCattleButton();
		

		
		// Bull Register form
		BullRegister_Page reg = new BullRegister_Page();
		reg.findElement("BULL");
		reg.assert_CattleType();
		reg.enter_TagNumber(tagNumber);
		reg.enter_CoopTagNumber(cooptagNumber);
		reg.select_YOB(data.get("yearOfBirth"));
		reg.select_month(data.get("monthOfBirth"));
		reg.select_cattleType(data.get("cattleType"));
		reg.select_cattleBreed(data.get("breed"));
/*
		if(data.get("isCrossBreed").equalsIgnoreCase("true")) {
			reg.select_crossbreedToggle(data.get("isCrossBreed"), data.get("crossedWith"));}*/
		
		reg.enter_weight(data.get("weight"));

		reg.press_SaveButton();


		CattleRegistrationSuccess_Page msg = new CattleRegistrationSuccess_Page();
		msg.assertCattleTag(tagNumber);
		msg.assertSuccessMsg("Registration has been saved successfully for");

		
		
	}

}
