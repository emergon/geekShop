package com.shop.controller;

import com.shop.entities.*;
import com.shop.service.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
    
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MonitorService serviceMonitor;

    List<Monitor> allMonitors;
    List<MonitorManufacturer> manufacturers;
    List<Integer> inches;
    List<Product> productsByCategory;

    
    public String showMonitor(Model m, List<Product> productList, List<Monitor> monitorList) {
        m.addAttribute("productList", productList);
        m.addAttribute("monitorList", monitorList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceMonitor.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (inches == null) {
            inches = serviceMonitor.findAllInches();
        }
        m.addAttribute("inches", inches);
        return "monitor";
    }

    @GetMapping
    public String displayAllMonitor(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(8);
        }
        if (allMonitors == null) {
            allMonitors = serviceMonitor.getAll();
        }
        return showMonitor(m, productsByCategory, allMonitors);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 8);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Monitor> monitorList = monitorSort(listIds);
        return showMonitor(m, productsByPrice, monitorList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Monitor> monitorList = serviceMonitor.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < monitorList.size(); i++) {
            listIds.add(monitorList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMonitor(m, productList, monitorList);
    }
    
    @GetMapping("/inches/{Id}")
    public String getBySize(Model m,
            @PathVariable("Id") Integer Id) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Monitor> monitorList = serviceMonitor.findByInches(Id);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < monitorList.size(); i++) {
            listIds.add(monitorList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMonitor(m, productList, monitorList);
    }
    
    public List<Monitor> monitorSort(List<Integer> listIds) {
        List<Monitor> concreteMonitor = new ArrayList();
        for (int i = 0; i < allMonitors.size(); i++) {
            if (listIds.contains(allMonitors.get(i).getId())) {
                concreteMonitor.add(allMonitors.get(i));
            }
        }
        return concreteMonitor;
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
    public String createMonitorForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("inches") Integer inches,
            @RequestParam("frequency") Integer frequency,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(8);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        MonitorManufacturer monitorManufacturer = serviceMonitor.findByManufacturerId(manufacturer);
        Monitor monitor = new Monitor(productId, product, monitorManufacturer, inches, frequency);
        allMonitors.add(monitor);
        serviceMonitor.saveOrUpdate(monitor);
        return "redirect:/monitor";
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
