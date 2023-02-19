package com.geek.controller;

import java.security.Principal;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.geek.model.Follow;
import com.geek.model.Like;
import com.geek.model.Posts;
import com.geek.model.Register;
import com.geek.repo.CommentRepository;
import com.geek.repo.FollowRepository;
import com.geek.repo.LikeRepository;
import com.geek.repo.PostsRepos;
import com.geek.repo.RegisterRepository;

@Controller
@RequestMapping("/post")
public class PostController {

	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostsRepos postsRepos;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	
	@GetMapping("/cms")
	public String Content(Model m,Principal principal) {
		
		Register byEmail = registerRepository.getByEmail(principal.getName());
		
		System.out.println(byEmail);
		
		m.addAttribute("user",byEmail);
		
		return "cms";
	}
	
	@GetMapping("/addpost")
	public String AddPost(Model m) {
		m.addAttribute("post",new Posts());

		
		
		return "add_post";
	}
	
	@GetMapping("/postslist")
	public String postList(Principal principal,Model m) {
		
		Register byEmail = registerRepository.getByEmail(principal.getName());
		
		List<Posts> findByRegister = postsRepos.findByRegister(byEmail);
		
		
		
		
		m.addAttribute("user", findByRegister);
		
		
		
		return "posts_list";
	}
	

	
	
	@PostMapping("/postsave")
	public String SavePost(@Valid @ModelAttribute("post") Posts posts,BindingResult result,Principal principal) {
		
		try {
			
			
			if(result.hasFieldErrors()) {
				return "redirect:/post/addpost";

			}
			
			Register byEmail = registerRepository.getByEmail(principal.getName());
			
			System.out.println(byEmail);

			posts.setRegister(byEmail);
			
			this.postsRepos.save(posts);
				
			System.out.println("Hello Its Saving Post");
			
		} catch (Exception e) {
			
			return "redirect:/post/addpost";
		
		}
		


		return "redirect:/post/cms";
	}
	
	
	@GetMapping("/{id}")
	public void DeleteContent(@PathVariable("id") String id) {
		
		
		
		this.postsRepos.deleteById(Integer.parseInt(id));
		
		return;
	}
	
		
	
	@PostMapping("/update/{id}")
	public String UpdateData(@PathVariable("id") Integer id,Model m) {
		
		  Posts posts = this.postsRepos.findById(id).get();
		
			m.addAttribute("post",posts);
		


		
		    return "update";
	}
	
	@PostMapping("/update-process")
	public String UpdateDatas(@Valid @ModelAttribute("user") Posts post,BindingResult result,Principal principal) {
		
		
		Register byEmail = registerRepository.getByEmail(principal.getName());
		
		
		try {
			if(result.hasFieldErrors()) {
				
				return "redirect:/post/update/"+post.getId();
				
			}
			
			post.setRegister(byEmail);
			this.postsRepos.save(post);
			
			return "redirect:/post/postslist";

			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		
		return "redirect:/post/postslist";
		
	}
	
	
	@GetMapping("/edit")
	public String editPosts(Principal principal,Model m){
		
		Register byEmail = registerRepository.getByEmail(principal.getName());
		byEmail.setAgreed(true);

		m.addAttribute("updateedit",byEmail);
		
		return "edit";
	}
	
	@PostMapping("/UpdateProfile")
	public String UpdateProfile(@Valid @ModelAttribute("updateedit") Register register,BindingResult result,Principal principal) {
		
		Register byEmail = registerRepository.getByEmail(principal.getName());
		
		try {
			if(result.hasFieldErrors()) {
				
				
				System.out.println(result);
				
				return "redirect:/post/cms";
				
				
			}
		
			System.out.println("Hello World Succesfully !!!!");
			register.setUserRole("ROLE_NORMAL");
			register.setUsername(byEmail.getUsername());
			register.setPassword(byEmail.getPassword());
			register.setGender(byEmail.getGender());
			
			registerRepository.save(register);
			
			

			
		} catch (Exception e) {
			System.out.println("exception");
			System.out.println(e);
		}		
		
		return "redirect:/post/cms";
		
		
	}
	
	@GetMapping("/posthome")
	public String postHome(Model m) {
List<Posts> findAll = this.postsRepos.findAll();
		
		List<Posts> collect = findAll.stream().filter(e->e.isPublish()).collect(Collectors.toList());
				
		m.addAttribute("data",collect);
		return "index2";
	}
	
	@GetMapping("/postlist/{id}")
	public String postLists(@PathVariable("id") String id,Model m,Principal principal) {
		
		Register byEmail = this.registerRepository.getByEmail(principal.getName());
		Posts posts = this.postsRepos.findById(Integer.parseInt(id)).get();
		
		List<Comment> findByPost = this.commentRepository.findByPost(posts);
		
		m.addAttribute("commentneed",findByPost);
		
		
		List<Comment> findAll2 = this.commentRepository.findByPostAndRegister(posts,byEmail);
		
		List<Like> findByPost2 = this.likeRepository.findByPost(posts).stream().filter(e->e.isLike()).collect(Collectors.toList());
		
		m.addAttribute("likeshow",findByPost2.size());
		
		m.addAttribute("commentss",findAll2);
		
//		******
		
		List<Like> findByRegisterAndPost = this.likeRepository.findByRegisterAndPost(byEmail, posts);
		
		
		
		int l=0;
		if(findByRegisterAndPost.size()==0) {
			m.addAttribute("findLikeByRegisterid",0);
			m.addAttribute("likes",false);

		}		
		else {
			l=findByRegisterAndPost.get(0).getId();
			m.addAttribute("findLikeByRegisterid",l);
			m.addAttribute("likes",findByRegisterAndPost.get(0).isLike());


		}

//		*****
		
		
				
	
		
		m.addAttribute("userdata",posts);
		m.addAttribute("post",posts);
		
		m.addAttribute("userlog",byEmail.getId());


		List<Follow> findByRegister = this.followRepository.findByRegister(byEmail);
		
		List<Follow> collect = this.followRepository.findByRegister(byEmail).stream().filter(i->i.isFollow() || i.getFollowname()==i.getRegister().getEmail()).collect(Collectors.toList());

		
		
	System.out.println("Collections "+collect);	
		
		if(findByRegister.size()==0) {
			m.addAttribute("followsCheck", false);
			m.addAttribute("followsID", 0);


		}
		else {
		
		m.addAttribute("followdata", collect.size());
		
		m.addAttribute("followsCheck", findByRegister.get(0).isFollow());

		m.addAttribute("followsID", findByRegister.get(0).getId());

		m.addAttribute("followsReg", findByRegister.get(0).getRegister().getId());

		}
		
		return "user_post2";
	}
	
	@PostMapping("/postcomment")
	public String postcomment(@RequestParam("comment") String comment ,@RequestParam("post") Integer id,Principal principal) {
		
		try {
		Posts posts = this.postsRepos.findById(id).get();
		Register register2 = this.registerRepository.getByEmail(principal.getName());
		
		Comment comment2 = new Comment();
		comment2.setComment(comment);
		comment2.setPost(posts);
		comment2.setRegister(register2);

this.commentRepository.save(comment2);}
		
	catch(Exception e) {
			return "redirect:/post/cms";
		}
		
		return "redirect:/post/postlist/"+id;
		
		
	}
	
	
	@PostMapping("/postlike")
	public String postlike(@RequestParam(name="like",required = false) boolean like ,@RequestParam("postidlike") Integer id,@RequestParam("findLikeByRegisterid") Integer findLikeByRegisterid,Principal principal) {
		try {
	
			
			
		 Posts posts = this.postsRepos.findById(id).get();
		 Register byEmail = this.registerRepository.getByEmail(principal.getName());
			
			Like like2 = new Like();
			
			like2.setId(findLikeByRegisterid);
			like2.setLike(like);
			
			like2.setPost(posts);
			like2.setRegister(byEmail);
			
			
			this.likeRepository.save(like2);
		
			return "redirect:/post/postlist/"+id;
		
			
			
			
		}catch(Exception e) {
			return "redirect:/post/cms";
	
		}
		
	}
	
	
	@PostMapping("/postfollow")
	public String postFollow(@RequestParam(required=false,name = "likes") boolean follow ,@RequestParam("postidfollow") Integer id,@RequestParam("email") String email,@RequestParam("followId") int followid,Principal principal) {
	

		Register byEmail = this.registerRepository.getByEmail(principal.getName());
		Follow follow2 = new Follow();
		
		follow2.setId(followid);
		follow2.setFollow(follow);
		follow2.setRegister(byEmail);
		follow2.setFollowname(email);
		this.followRepository.save(follow2);
	
		
		return "redirect:/post/postlist/"+id;

	
	
	}
	
	
	
}
