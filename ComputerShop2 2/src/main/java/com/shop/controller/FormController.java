package com.shop.controller;

import com.shop.entities.*;
import com.shop.service.*;
import com.shop.service.TowerService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/form")
public class FormController {

//    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"Desktop/desktopImages";
    private static String UPLOADED_FOLDER = "Users/mike/Desktop/desktopImages";

    @Autowired
    ProductService service;

    @Autowired
    ServletContext context;
    
    @Autowired
    TowerService towerService;
    
    @Autowired
    CategoryService categoryService;
    
  

    //________________________________________FORM + upload____________________________________________
    @GetMapping("/showForm/{category}")
    public String showRegistrationForm(Model m,
            @PathVariable("category") Integer category) {
        m.addAttribute("product", new Product());
        m.addAttribute("category", category);
        m.addAttribute("tower", new Tower());
        
        return "addProductForm";
    }

    @PostMapping("/add/1")//Tower
    public String createTowerForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("type") Integer type,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(1);
        product.setCategory(category);
        int productId = addProductToForm(product,file);
        product.setId(productId);
       TowerManufacturer towerManufacturer = towerService.findByManufacturerId(manufacturer);
       TowerType towerType = towerService.findByTypeId(type);
        System.out.println("afroditi" + towerManufacturer);
       Tower tower = new Tower(productId, product, towerManufacturer, towerType);

        
       towerService.saveOrUpdate(tower);
 
        return "null";
    }
    
    @GetMapping("/update/1")//Tower
    public String updatetowerForm(Model m,
         @RequestParam("productId") Integer productId,
         @RequestParam("towerType") ArrayList<TowerType> types){ //ArrayList..??
        System.out.println("troll: "+ types);
        Product product = service.findById(productId);
        m.addAttribute("product", product);
        
//            @RequestParam("type") Integer type,
//            @RequestParam("file") MultipartFile file,
//            BindingResult result) {
//        Category category = categoryService.findById(1);
//        product.setCategory(category);
//        int productId = addProductToForm(product,file);
//        product.setId(productId);
//       TowerManufacturer towerManufacturer = towerService.findByManufacturerId(manufacturer);
//       TowerType towerType = towerService.findByTypeId(type);
//        System.out.println("afroditi" + towerManufacturer);
//       Tower tower = new Tower(productId, product, towerManufacturer, towerType);
//  
//       towerService.saveOrUpdate(tower);
 
        return "tower";
    }

//   
    public int addProductToForm(Product product, MultipartFile file) {

        String tomcatBase = System.getProperty("catalina.base");

        if (file != null || !file.isEmpty()) {
            try {
                int pictureName = service.createOrUpdate(product).getId();
                String uploadDir = tomcatBase + "/webapps/images";
                File transferFile = new File(uploadDir + "/" + pictureName + ".jpg");
                file.transferTo(transferFile);
                return pictureName;

            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
//        else
//        {  //validation
//            
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:success";
//        }

//        try {
//
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @ModelAttribute("katigories")
    public List<Category> fereDetails() {
        return service.getDetails();
    }

//   -----------------------------------session--------------------------------------
    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

}
