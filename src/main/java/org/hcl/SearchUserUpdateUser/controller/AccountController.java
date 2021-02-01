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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

	@Autowired
	UserEntityCrudRepository userEntityCrudRepository;

	@GetMapping(path = "/createAccount")
	public String CreateAccountGet(Model model) {
		
		UserEntity newUser = new UserEntity();
		model.addAttribute("newUser", newUser);
		return "createAccount";
	}
	
	@PostMapping(path = "/createAccount")
	public void CreateAccountPost(@ModelAttribute("userFormData") UserEntity userEntity, BindingResult result) {

		System.out.println("==== ACCOUNTCONTROLLER - CreateAccount");
		System.out.println("==== Form Data: ");
		System.out.println("==== Username: " + userEntity.getName());
		System.out.println("==== Password: " + userEntity.getPassword());
						
		if (userEntity == null || userEntity.getName() == null) {
			throw new RuntimeException("Name Required");
		} 
		if (userEntity.getPassword() == null) {
			throw new RuntimeException("Password Required");
		}
		userEntityCrudRepository.save(userEntity);
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
}
