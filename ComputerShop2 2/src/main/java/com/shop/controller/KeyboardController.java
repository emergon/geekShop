package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shop.entities.*;
import com.shop.service.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/keyboard")
public class KeyboardController {
    
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    KeyboardService serviceKeyboard;

    List<Keyboard> allKeyboards;
    List<KeyboardManufacturer> manufacturers;
    List<KeyboardType> types;
    List<Product> productsByCategory;
    
    public String showKeyboard(Model m, List<Product> productList, List<Keyboard> keyboardList) {
        m.addAttribute("productList", productList);
        m.addAttribute("keyboardList", keyboardList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceKeyboard.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (types == null) {
            types = serviceKeyboard.findAllTypes();
        }
        m.addAttribute("types", types);
        return "keyboard";
    }

    @GetMapping
    public String displayAllKeyboard(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(10);
        }
        if (allKeyboards == null) {
            allKeyboards = serviceKeyboard.getAll();
        }
        return showKeyboard(m, productsByCategory, allKeyboards);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 10);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Keyboard> keyboardList = keyboardSort(listIds);
        return showKeyboard(m, productsByPrice, keyboardList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Keyboard> keyboardList = serviceKeyboard.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < keyboardList.size(); i++) {
            listIds.add(keyboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showKeyboard(m, productList, keyboardList);
    }
    
    @GetMapping("/type/{Id}")
    public String getByType(Model m,
            @PathVariable("Id") Integer Id) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Keyboard> keyboardList = serviceKeyboard.findByType(Id);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < keyboardList.size(); i++) {
            listIds.add(keyboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showKeyboard(m, productList, keyboardList);
    }
    
    public List<Keyboard> keyboardSort(List<Integer> listIds) {
        List<Keyboard> concreteKeyboard = new ArrayList();
        for (int i = 0; i < allKeyboards.size(); i++) {
            if (listIds.contains(allKeyboards.get(i).getId())) {
                concreteKeyboard.add(allKeyboards.get(i));
            }
        }
        return concreteKeyboard;
    }

    public List<Product> productSort(List<Integer> listIds) {
        List<Product> concreteProducts = new ArrayList();
        for (int i = 0; i < productsByCategory.size(); i++) {
            if (listIds.contains(productsByCategory.get(i).getId())) {
                concreteProducts.add(productsByCategory.get(i));
            }
        }
        return concreteProducts;
    }
    
    public List<Integer> ProductIdByPrice(List<Product> productsByPrice) {
        List<Integer> listIds = new ArrayList(); //Ids of products by category
        for (int i = 0; i < productsByPrice.size(); i++) {
            listIds.add(productsByPrice.get(i).getId());
        }
        return listIds;
    }
    
     //_______________________________FORM_______________________________________
    @PostMapping("/add")
    public String createMouseForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("type") Integer type,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(10);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        KeyboardManufacturer keyboardManufacturer = serviceKeyboard.findByManufacturerId(manufacturer);
        KeyboardType keyboardType = serviceKeyboard.findByTypeId(type);
        Keyboard keyboard = new Keyboard(productId, keyboardManufacturer, keyboardType, product);
        allKeyboards.add(keyboard);
        serviceKeyboard.saveOrUpdate(keyboard);
        return "redirect:/keyboard";
    }

    public int addProductToForm(Product product, MultipartFile file) {

        String tomcatBase = System.getProperty("catalina.base");

        if (file != null || !file.isEmpty()) {
            try {
                int pictureName = service.createOrUpdate(product).getId();
                productsByCategory.add(product);
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
}
