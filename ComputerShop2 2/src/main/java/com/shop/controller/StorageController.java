package com.shop.controller;

import com.shop.service.*;
import com.shop.entities.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    StorageService serviceStorage;

    List<Storage> allStorages;
    List<StorageManufacturer> manufacturers;
    List<StorageInches> inches;
    List<StorageType> types;
    List<Product> productsByCategory;

    public String showStorage(Model m, List<Product> productList, List<Storage> storageList) {
        m.addAttribute("productList", productList);
        m.addAttribute("storageList", storageList);
        if (manufacturers == null) {
            manufacturers = serviceStorage.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (inches == null) {
            inches = serviceStorage.findAllInches();
        }
        m.addAttribute("inches", inches);
        if (types == null) {
            types = serviceStorage.findAllTypes();
        }
        m.addAttribute("types", types);
        m.addAttribute("product", new Product());
        return "storage";
    }
    
    @GetMapping
    public String displayAllStorages(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(6);
        }
        if (allStorages == null) {
            allStorages = serviceStorage.getAll();
        }
        return showStorage(m, productsByCategory, allStorages);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 6);
        List<Integer> listIds = ProductIdByPrice(productsByPrice); //ids of the above products 
        List<Storage> storageList = storageSort(listIds); 
        return showStorage(m, productsByPrice, storageList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Storage> storageList = serviceStorage.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < storageList.size(); i++) {
            listIds.add(storageList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showStorage(m, productList, storageList);
    }
    
    @GetMapping("/type/{typeId}")
    public String getByType(Model m,
            @PathVariable("typeId") Integer typeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Storage> storageList = serviceStorage.findByType(typeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < storageList.size(); i++) {
            listIds.add(storageList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showStorage(m, productList, storageList);
    }
    
    @GetMapping("/inches/{inchesId}")
    public String getByInches(Model m,
            @PathVariable("inchesId") Integer inchesId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Storage> storageList = serviceStorage.findByInches(inchesId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < storageList.size(); i++) {
            listIds.add(storageList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showStorage(m, productList, storageList);
    }
    
    public List<Storage> storageSort(List<Integer> listIds) {
        List<Storage> concreteStorages = new ArrayList();
        for (int i = 0; i < allStorages.size(); i++) {
            if (listIds.contains(allStorages.get(i).getId())) {
                concreteStorages.add(allStorages.get(i));
            }
        }
        return concreteStorages;
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
    public String createStorageForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("inches") Integer inches,
            @RequestParam("type") Integer type,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(6);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        StorageManufacturer storageManufacturer = serviceStorage.findManufacturerById(manufacturer);
        StorageInches storageInches = serviceStorage.findInchesById(inches);
        StorageType storageType = serviceStorage.findTypeById(type);
        Storage storage = new Storage(productId, product, storageInches, storageManufacturer, storageType);
        serviceStorage.saveOrUpdate(storage);
        allStorages.add(storage);
        return "redirect:/storage";
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
