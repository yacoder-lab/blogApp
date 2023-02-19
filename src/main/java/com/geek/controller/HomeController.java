package com.geek.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geek.model.Comment;
import com.geek.model.Like;
import com.geek.model.Posts;
import com.geek.model.Register;
import com.geek.repo.CommentRepository;
import com.geek.repo.LikeRepository;
import com.geek.repo.PostsRepos;
import com.geek.repo.RegisterRepository;

@Controller
public class HomeController {

	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RegisterRepository registerRepository;
	

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostsRepos postsRepos;
	
	@RequestMapping("/")
	public String index(Model m) {
		
		List<Posts> findAll = this.postsRepos.findAll();
		
		List<Posts> collect = findAll.stream().filter(e->e.isPublish()).collect(Collectors.toList());
				
		m.addAttribute("data",collect);
		
		
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		
		
		return "login";
	}
	
	
	
	@GetMapping("/register")
	public String register(Model m) {
		
		m.addAttribute("loginData",new Register());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String RegisterHandle(@Valid @ModelAttribute("loginData") Register register,BindingResult result,HttpSession session,Model m) {
		
		
		try {
			if(result.hasFieldErrors()) {

				
				System.out.println(result);
				
				return "register";
			}
			register.setUserRole("ROLE_NORMAL");
			register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
			registerRepository.save(register);
			
			System.out.println(register);
			
			
			return "redirect:/login";
			
			
		} catch (Exception e) {
			
			m.addAttribute("loginData",new Register());
			
			session.setAttribute("username", "username alreday exists");

			session.setAttribute("email", "email alreday exists");

			return "register";
		
		}
		
		
	
		
	}
	
	@GetMapping("/post_lists/{id}")
	public String postLists(@PathVariable("id") String id,Model m) {
		try{
		Posts posts = this.postsRepos.findById(Integer.parseInt(id)).get();
		
		m.addAttribute("post",posts);
		
		List<Like> findByPost2 = this.likeRepository.findByPost(posts).stream().filter(e->e.isLike()).collect(Collectors.toList());
		
		m.addAttribute("likeshow",findByPost2.size());
		
		List<Comment> findAll2 = this.commentRepository.findByPost(posts);
		
		m.addAttribute("commentss",findAll2);

		System.out.println(id);
		m.addAttribute("userdata",posts);
		
		}
		
		
		catch(Exception e) {
			return "redirect:/post/postlist"+Integer.parseInt(id);
		}
		return "user_post";
	}
	

	@GetMapping("/forgotpassword")
	public String forgotPassword() {
		
		return "forgotpassword";
	}

	@PostMapping("/forgotpasswordaction")
	public String forgotPasswordAction(@RequestParam("username") String email ,@RequestParam("password") String password,Model m) {
		
		Register byEmail = this.registerRepository.getByEmail(email);
		
		
		boolean matches = this.bCryptPasswordEncoder.matches(password,byEmail.getPassword());	

		
		if(matches) {
			
			m.addAttribute("data", byEmail);
			
			return "forgotpassword2";
		}
		
		return "redirect:/login";
		
	}
	
	@PostMapping("/forgotpasswordactions")
	public String ForgotPassowrdActions(@RequestParam("password") String password,@RequestParam("confirmpassword") String passwords,@RequestParam("username") String email,@RequestParam("agreed")boolean agreed) {
		
		Register byEmail = this.registerRepository.getByEmail(email);
		
		
		if(password.equals(passwords)) {
			
		
			byEmail.setAgreed(agreed);
			byEmail.setPassword(this.bCryptPasswordEncoder.encode(passwords));
			
			registerRepository.save(byEmail);
			
		}else {
			
			return "redirect:/forgotpassword";
		}
		
		
		
		return "redirect:/";
		
		
		
	}
	
}
