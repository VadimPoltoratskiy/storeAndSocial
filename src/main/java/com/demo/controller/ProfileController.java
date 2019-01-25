package com.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.Article;
import com.demo.entity.CartItem;
import com.demo.entity.Category;
import com.demo.entity.Comment;
import com.demo.entity.FileInfo;
import com.demo.entity.Message;
import com.demo.entity.Product;
import com.demo.entity.Profile;
import com.demo.entity.SafeProfile;
import com.demo.entity.User;
import com.demo.entity.Worker;
import com.demo.service.ArticleService;
import com.demo.service.CustomUserDetailsService;
import com.demo.service.FileService;
import com.demo.service.MessageService;
import com.demo.service.ProductService;
import com.demo.service.ProfileService;
import com.demo.service.CategoryService;
import com.demo.service.CommentService;

import java.nio.file.StandardOpenOption;
import org.springframework.http.MediaType;
import java.net.URLConnection;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private PolicyFactory htmlPolicy;
	
	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommentService commentService;
	
	
	private User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		return userService.get(name);
	}
	
	@RequestMapping(value = "/upload-profile-photo", method = RequestMethod.POST) 
	public String photoUploads(@RequestParam("file") MultipartFile file) throws Exception {		
		
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);		
		
		try {
			FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photo", "profile", 300, 300);
			
			profile.setPhotoDetails(photoInfo);
			profileService.save(profile);
			
			
			//System.out.println(photoInfo);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return "redirect:/worker/list";
	}
	
	@RequestMapping(value = "/profilephoto/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<InputStreamResource> servePhoto(@PathVariable("id") Long id) throws IOException {
		
		//User user = getUser();
		User user = userService.get(id);
		Profile profile = profileService.getUserProfile(user);

		Path photoPath = Paths.get(photoUploadDirectory, "default", "avatar.png");
		
		if (profile != null && profile.getPhoto(photoUploadDirectory) != null) {
			photoPath = profile.getPhoto(photoUploadDirectory);
		}

	return ResponseEntity.ok().contentLength(Files.size(photoPath))
				.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
				.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
	}
	
	
	//showAllProfiles
	//1. DB -> all id
	//2. show(id) -> profile  ->  List save
	//3. profiles -> jsp
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String show(ModelMap model, @PathVariable("id") Long id) {
		
		/*System.out.println("============ id: " + id + "===============");*/
		
		User user = userService.get(id);
		Profile profile = profileService.getUserProfile(user);
		List<Article> articles = articleService.getArticlesByProfileId(profile.getId());
		
		for (Article article : articles) {
			article.setComments(commentService.getCommentsByArticleId(article.getId()));
		}
		
		show(model, user);
		model.put("articles", articles);
		
		return "show-profile";
	}

	private void show(ModelMap model, User user) {
		Profile profile = profileService.getUserProfile(user);
		
		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}
		
		Profile safeProfile = new Profile();
		safeProfile.copyFrom(profile);
		model.put("profile", safeProfile);
		model.put("userId", user.getUserid());
		model.put("profileId", profile.getId());
	}
	
	@RequestMapping(value="/edit-profile-about", method = RequestMethod.GET)
	public String editProfileAbout(ModelMap model) {
		
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);  //освоить дебаг разобраться с профилями и юзерами, чтобы отдельно работали их контроллеры и сервисы
		
		Profile safeProfile = new Profile();
		safeProfile.copyFrom(profile);
		
		model.put("profile", safeProfile);
		
		return "edit-profile-about";
		
	}
	
	@RequestMapping(value="/edit-profile-about", method = RequestMethod.POST)
	public String editProfileAbout(ModelMap model, @Valid Profile webProfile,
			BindingResult result) {
		
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);
		profile.mergeFrom(webProfile, htmlPolicy);
		
		if (!result.hasErrors()) {
			profileService.save(profile);
			return "redirect:/profile/show";
		}
		
		return "edit-profile-about";
	}
	
	@RequestMapping(value="/show-messages", method = RequestMethod.GET)
	public String showMessages(ModelMap model) {
		
		List<Message> messages = messageService.getMessages();
		model.put("messages", messages);
		
		return "show-messages";
	}
	
	
	@RequestMapping(value = "/messages/{profileId}", method = RequestMethod.GET)
	public String listMessagesProfile(@PathVariable(value = "profileId") Long profileId,  Model model) {

		List<Message> messages = messageService.getMessagesByProfileId(profileId);
				
		model.addAttribute("messages", messages);
			
			return "show-messages-profile";
	}
	
	private SafeProfile getSafeProfile(User user) {
		
		Profile profile = profileService.getUserProfile(user);
		
		if (profile == null) {
			return null;
		}
		
		Profile safeProfile = new Profile();
		safeProfile.copyFrom(profile);		
		
		SafeProfile webProfile = new SafeProfile();
		webProfile.setSafeProfile(safeProfile);
		webProfile.setProfileId(profile.getId());
		webProfile.setUserId(user.getUserid());
		
		return webProfile;
	}
	
	@RequestMapping(value="/showAll", method = RequestMethod.GET)
	public String showAll(ModelMap model) {		
		
		List<User> users = userService.getUsers();
		List<SafeProfile> safeProfiles = new ArrayList<>();
		
		for (User user : users) {
			
			SafeProfile profile = getSafeProfile(user);
			
			if (profile != null) {				
				safeProfiles.add(profile);
			}
		}
		
		model.put("profiles_list", safeProfiles);
		
		return "show-all-profiles";
	}
	
	@RequestMapping(value = "/showCurrent", method = RequestMethod.GET)
	public String showCurrent(ModelMap model) {

		User user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		if(profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}
		
		SafeProfile safeProfile = getSafeProfile(user);
		int unreadMessagesCount = messageService.getUnreadCount(safeProfile.getProfileId());
		
		List<Article> articles = articleService.getArticlesByProfileId(safeProfile.getProfileId());
		
		model.put("unreadMessagesCount", unreadMessagesCount);
		model.put("profile", safeProfile);		
		model.put("articles", articles);
		
		/*model.put("unreadMessagesCount", unreadMessagesCount);
		model.put("articles", articles); complex into the same dto */
		
		return "show-current-profile";
	}
	
	@RequestMapping(value="/sendMessage", method = RequestMethod.GET)
	public String sendMessage(@RequestParam("userId") Long id, ModelMap model) {	
		
		User user = getUser();
		Profile fromProfile = profileService.getUserProfile(user);
		
		User user2 = userService.get(id);
		Profile toProfile = profileService.getUserProfile(user2);
		
		Message message = new Message();
		message.setFromProfile(fromProfile);
		message.setProfile(toProfile);
		model.put("message", message);		
		
		return "send-message";
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("message") Message message) {

		messageService.add(message);
		return "redirect:/profile/showAll";
	}
	
	@RequestMapping(value = "/sentMessages/{profileId}", method = RequestMethod.GET)
	public String listSentMessagesProfile(@PathVariable(value = "profileId") Long profileId, Model model) {

		List<Message> sentMessages = messageService.getSentMessagesByProfileId(profileId);
		model.addAttribute("sentMessages", sentMessages);

		return "show-sent-messages-profile";
	}	
	
	@RequestMapping(value = "/message/delete", method = RequestMethod.GET)
	public String messageDelete(@RequestParam("messageId") Long id) {

		messageService.delete(id);
		return "redirect:/profile/showCurrent";
	}
	
	@RequestMapping(value = "/message/show", method = RequestMethod.GET)
	public String messageShow(@RequestParam("messageId") Long id, Model model) {

		messageService.open(id);
		Message message = messageService.getMessageById(id);
		model.addAttribute("message", message);
		
		
		return "show-message";
	}
	
	@RequestMapping(value = "/article/show", method = RequestMethod.GET)
	public String articleShow(@RequestParam("articleId") Long id, Model model) {

		
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		
		
		return "show-articles-profile";
	}
	
	@RequestMapping(value="/showProducts", method = RequestMethod.GET)
	public String showProducts(ModelMap model) {
		
		List<Product> products = productService.getProducts();
		model.put("products", products);
		
		return "showProducts";
	}
	
	@RequestMapping(value = "/product/show", method = RequestMethod.GET)
	public String productShow(@RequestParam("productId") Long id, Model model) {

		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		return "show-product";
	}
	
	@RequestMapping(value="/showCategories", method = RequestMethod.GET)
	public String showCategories(ModelMap model) {
		
		List<Category> categories = categoryService.getCategories();
		model.put("categories", categories);
		
		return "showCategories";
	}
	 
	@RequestMapping(value = "/showCategories/{category_id}", method = RequestMethod.GET)
	public String showProductsCategory(@PathVariable(value = "category_id") int id, Model model) {

		List<Product> listProductsCategory = productService.getProductsByCategoryId(id);
		model.addAttribute("listProductsCategory", listProductsCategory);

		return "show-products-category";
	}	
	
	@RequestMapping(value="/leaveComment", method = RequestMethod.GET)
	public String leaveComment(@RequestParam("articleId") Long id, ModelMap model) {	
		
		User user = getUser();
		Profile fromProfile = profileService.getUserProfile(user);
		
				
		Comment comment = new Comment();
		comment.setProfile(fromProfile);
		comment.setArticle(articleService.getArticleById(id));
		model.put("comment", comment);
		
		return "leave-comment";
	}
	
	@RequestMapping(value = "/leaveComment", method = RequestMethod.POST)
	public String leaveComment(@ModelAttribute("comment") Comment comment) {

		commentService.add(comment);
		return "redirect:/profile/showAll";
	}
}




