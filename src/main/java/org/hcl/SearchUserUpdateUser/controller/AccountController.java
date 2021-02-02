package org.hcl.SearchUserUpdateUser.controller;

import org.hcl.SearchUserUpdateUser.crudRepository.UserEntityCrudRepository;
import org.hcl.SearchUserUpdateUser.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	
	Long currentUserId;
	UserEntity updateUserDataClass;

	@Autowired
	UserEntityCrudRepository userEntityCrudRepository;

	@GetMapping(path = "/login")
	public String LoginGet(Model model) {
	
		UserEntity userData = new UserEntity();
		model.addAttribute("userData", userData);	
		return "login";
	}
	
	@PostMapping(path = "/login")
	public String LoginPost(@ModelAttribute("userFormData") UserEntity userData, BindingResult result) {
		
		System.out.println("ACCOUNTCONTROLLER - Login");
		System.out.println("Form Data: ");
		System.out.println("Username: " + userData.getName());
		System.out.println("Password: " + userData.getPassword());
		
		if (userData == null || userData.getName() == null) {
			throw new RuntimeException("Name Required");
		} 
		if (userData.getPassword() == null) {
			throw new RuntimeException("Password Required");
		}
		
		Boolean userLoginCorrect = false;
		
		Iterable<UserEntity> users = userEntityCrudRepository.findAll();
		for(UserEntity u : users) {
			System.out.println("CHECKING USERNAMES: " + u.getName() + " vs " + userData.getName());
			int nameRes = u.getName().compareTo(userData.getName());
			if (nameRes == 0) {
				System.out.println("CHECKING PASSWORDS: " + u.getPassword() + " vs " + userData.getPassword());
				int passwordRes = u.getPassword().compareTo(userData.getPassword());
				if (passwordRes == 0) {
					currentUserId = u.getId();
					userLoginCorrect = true;
					break;
				}
			}
		}
		
		if (userLoginCorrect) {
			System.out.println("++++USER LOGIN SUCCESSFUL++++");
			return "redirect:updateUser";
		} else {
			System.out.println("----USER LOGIN FALSE----");
			return "redirect:errorMessage";
		}
	}
	
	@GetMapping(path = "/updateUser")
	public String UpdateUserGet(Model model) {
		
		System.out.println("Looking at Current: " + currentUserId);
		
		UserEntity userUpdateData = new UserEntity();
		model.addAttribute("userUpdateData", userUpdateData);
		return "updateUser";
	}
	
	@PostMapping(path = "updateUser")
	public String updateUserPost(@ModelAttribute("userUpdateData") UserEntity userUpdateData, BindingResult result) {
		
		System.out.println("==== ACCOUNTCONTROLLER - CreateAccount");
		System.out.println("==== Form Data: ");
		System.out.println("==== Username: " + userUpdateData.getName());
		System.out.println("==== Password: " + userUpdateData.getPassword());
		
		if (userUpdateData == null || userUpdateData.getName() == null) {
			throw new RuntimeException("Name Required");
		} 
		if (userUpdateData.getPassword() == null) {
			throw new RuntimeException("Password Required");
		}
		
		Iterable<UserEntity> users = userEntityCrudRepository.findAll();
		for(UserEntity u : users) {
			if(u.getId().equals(currentUserId)) {
				System.out.println("FOUDN USER IN UPDATES");
				u.setName(userUpdateData.getName());
				u.setPassword(userUpdateData.getPassword());
				userEntityCrudRepository.save(u);
				break;
			}
		}
		
		return "redirect:allAccounts";
	}
	
	@GetMapping(path = "/allAccounts")
	public String ShowAccounts(@RequestParam(value = "userHtml", defaultValue = "null", required = true) String userHtml, Model model) {
		Iterable<UserEntity> users = userEntityCrudRepository.findAll();
		String allUserCodeHtml = "<ul>";
		
		for( UserEntity u : users) {
			allUserCodeHtml += "<li>";
			allUserCodeHtml += u.getId();
			allUserCodeHtml += " ==== ";
			allUserCodeHtml += u.getName();
			allUserCodeHtml += " === ";
			allUserCodeHtml += u.getPassword();
			allUserCodeHtml += "</li>";			
		}
		allUserCodeHtml += "</ul>";
		model.addAttribute("userHtml", allUserCodeHtml);

		return "allAccounts";
	}
	
	@GetMapping(path = "/errorMessage")
	public String ShowErrorMessage() {
		return "errorMessage";
	}
}
