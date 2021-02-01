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
}
