package com.hackathon.utilgeneric;

import org.testng.annotations.DataProvider;

public class Data 
{

	@DataProvider
	public Object[][] loginVal()
	{
		return new Object[][]
				{	
					{"test@invalid.com","test1234%"},//Account which is not exist
					{"t@m.com","Test@1"},//password less than 8 chars
					{"Test","testtest"},//password with no alpha numeric
					{"Test","Test123t"},//password with no special char
					{"Test","Test@#qt"},//password with no special char
					{"mpattar@gmail.com","Test@1234"}//Valid
				};
	}
	
	@DataProvider
	public Object[][] searchValidation()
	{
		return new Object[][]
				{	
					{"asdfgh"},//Invalid
					{"wom"},//three char
					{"Women",},//More than 3char
				};
	}
	
	@DataProvider
	public Object[][] searchValidation2()
	{
		return new Object[][]
		{
			{new TestData("asdfgh")},	
			{new TestData("wom")}, 
			{new TestData("Women")},  
	
		};
	}
	
	@DataProvider
	public Object[][] personalDetailsVal()
	{
		return new Object[][]
		{
			{new TestData("","","","")},	//Blank
			{new TestData("t","","test@gmailcom","3333333333333333")}, // invalid password AND invalid Phone number AND blank LName
			{new TestData("","t","testgmail.com","3333333333")},  // invalid Email Id AND blank FName
			{new TestData("test","test","test@","333")},	// invalid password AND invalid Phone number
			{new TestData("test1","test2","adi123@gmail.com","test123456")},	// Alphanumeric
			{new TestData("Adi","Reddy","areddy@gmail.com","3333333333")},	// Existing Email ID
			{new TestData("Mohan","Pattar","mohan@gmail.com","9999999999")},	//Valid (Reset)
		};
	}
	
	@DataProvider
	public Object[][] personalDetailsPasswordVal()
	{
		return new Object[][]
		{
			{new TestData("","","")},	//Blank
			{new TestData("asdf","asdf","asdf")}, // invalid chars
			{new TestData("123456","123456","123456")},  // invalid
			{new TestData("Test1234","Test1234","Test1234")},  // invalid without non alpha character
			{new TestData("Test@1","Test@12","Test@12")},  // Less characters
			{new TestData("Asdf@1234","Asdf@1234","Asdf@1234")},  // Invalid CURRENT password
			{new TestData("Test1234@","Asdf@1234","Asdf1234@")},  // Different new and conf passwords
		};
	}
	
	@DataProvider
	public Object[][] accessRequestNumVal()
	{
		return new Object[][]
			{
				{new TestData("0","")},		//Blank
				{new TestData("1","123")},		//less than 6 chars
				{new TestData("2","ASDFGH")},		//Alphabets
				{new TestData("3","!@#$%^")},		//special chars
				{new TestData("4","123AS@")},		//Alphanumerics and Special chars
				{new TestData("5","123456789012345")},		//more than 6 chars
				{new TestData("6","123456")},		//invalid account number
				{new TestData("7","0000101680")},	//Valid
			};
	}
	
	@DataProvider
	public Object[][] accessRequestFormVal()
	{
		return new Object[][]
			{
				{new TestData("0","", "", "", "")},	//Blank
				{new TestData("1","Test12@","Test12@", "Test12@", "Test12@" )}, //Alpha numeric nd symbols
				{new TestData("2","Fname","", "test@gmailcom","Cname")},	//invalid email and empty LAst name
				{new TestData("3","","Lname", "testgmail.com","Cname")},	//invalid email and Empty first name
				{new TestData("4","Fname","Lname", "test@gmail@com","Cname")},	//invalid email and empty company name
				{new TestData("5","Fname","Lname", "t  .est@gmail.com","Cname")},	//invalid email
				{new TestData("6","Fname","Lname", "test@gm_ail.com","Cname")},	//invalid email with special chars
				{new TestData("7","Fname","Lname", "test@g m ail.com","Cname")},	//invalid email with space
				{new TestData("8","Fname","Lname", "test@gmail.com","")},	//Valid details without company name
				
			};
	}
	
	@DataProvider
	public Object[][] creditCardFieldValidation()
	{
		return new Object[][]
			{
				{new TestData("", "", "", "","","")},	//Blank
				{new TestData("", "", "", "","","")},	//Blank Credit card number
				
			};
	}
}

