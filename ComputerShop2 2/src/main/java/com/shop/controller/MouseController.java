package com.shop.controller;

import com.shop.entities.*;
import com.shop.service.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mouse")
public class MouseController {
    
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MouseService serviceMouse;

    List<Mouse> allMouses;
    List<MouseManufacturer> manufacturers;
    List<MouseType> types;
    List<Product> productsByCategory;
    
    public String showMouse(Model m, List<Product> productList, List<Mouse> mouseList) {
        m.addAttribute("productList", productList);
        m.addAttribute("mouseList", mouseList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceMouse.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (types == null) {
            types = serviceMouse.findAllTypes();
        }
        m.addAttribute("types", types);
        return "mouse";
    }

    @GetMapping
    public String displayAllMouse(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(9);
        }
        if (allMouses == null) {
            allMouses = serviceMouse.getAll();
        }
        return showMouse(m, productsByCategory, allMouses);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 9);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Mouse> mouseList = mouseSort(listIds);
        return showMouse(m, productsByPrice, mouseList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Mouse> mouseList = serviceMouse.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < mouseList.size(); i++) {
            listIds.add(mouseList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMouse(m, productList, mouseList);
    }
    
    @GetMapping("/type/{Id}")
    public String getByType(Model m,
            @PathVariable("Id") Integer Id) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Mouse> mouseList = serviceMouse.findByType(Id);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < mouseList.size(); i++) {
            listIds.add(mouseList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMouse(m, productList, mouseList);
    }
    
    public List<Mouse> mouseSort(List<Integer> listIds) {
        List<Mouse> concreteMouse = new ArrayList();
        for (int i = 0; i < allMouses.size(); i++) {
            if (listIds.contains(allMouses.get(i).getId())) {
                concreteMouse.add(allMouses.get(i));
            }
        }
        return concreteMouse;
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
        Category category = categoryService.findById(9);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        MouseManufacturer mouseManufacturer = serviceMouse.findByManufacturerId(manufacturer);
        MouseType mouseType = serviceMouse.findByTypeId(type);
        Mouse mouse = new Mouse(productId, mouseManufacturer, mouseType, product);
        allMouses.add(mouse);
        serviceMouse.saveOrUpdate(mouse);
        return "redirect:/mouse";
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
