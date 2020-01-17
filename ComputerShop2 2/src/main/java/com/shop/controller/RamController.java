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
@RequestMapping("/ram")
public class RamController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RamService serviceRam;

    List<Ram> allRam;
    List<RamManufacturer> manufacturers;
    List<BigDecimal> voltages;
    List<Integer> frequencies;
    List<RamSize> sizes;
    List<RamType> types;
    List<Product> productsByCategory;

    public String showRam(Model m, List<Product> productList, List<Ram> ramList) {
        m.addAttribute("productList", productList);
        m.addAttribute("ramList", ramList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceRam.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (voltages == null) {
            voltages = serviceRam.findAllVoltage();
        }
        m.addAttribute("voltages", voltages);
        if (frequencies == null) {
            frequencies = serviceRam.findAllFrequencies();
        }
        m.addAttribute("frequencies", frequencies);
        m.addAttribute("voltages", voltages);
        if (sizes == null) {
            sizes = serviceRam.findAllSizes();
        }
        m.addAttribute("sizes", sizes);
        m.addAttribute("voltages", voltages);
        if (types == null) {
            types = serviceRam.findAllTypes();
        }
        m.addAttribute("types", types);
        return "ram";
    }
    
    @GetMapping
    public String displayAllRam(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(5);
        }
        if (allRam == null) {
            allRam = serviceRam.getAll();
        }
        return showRam(m, productsByCategory, allRam);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 5);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Ram> ramList = ramSort(listIds);
        return showRam(m, productsByPrice, ramList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Ram> ramList = serviceRam.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < ramList.size(); i++) {
            listIds.add(ramList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showRam(m, productList, ramList);
    }
    
    @GetMapping("/type/{typeId}")
    public String getByType(Model m,
            @PathVariable("typeId") Integer typeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Ram> ramList = serviceRam.findByType(typeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < ramList.size(); i++) {
            listIds.add(ramList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showRam(m, productList, ramList);
    }
    
    @GetMapping("/size/{sizeId}")
    public String getBySize(Model m,
            @PathVariable("sizeId") Integer sizeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Ram> ramList = serviceRam.findBySize(sizeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < ramList.size(); i++) {
            listIds.add(ramList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showRam(m, productList, ramList);
    }
    
    @GetMapping("/frequency/{frequencyValue}")
    public String getByCores(Model m,
            @PathVariable("frequencyValue") Integer frequencyValue) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Ram> ramList = serviceRam.findByFrequency(frequencyValue);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < ramList.size(); i++) {
            listIds.add(ramList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showRam(m, productList, ramList);
    }
    
    @GetMapping("/voltage/{voltageValue:.+}")
    public String getByVoltage(Model m,
            @PathVariable("voltageValue") BigDecimal voltageValue) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Ram> ramList = serviceRam.findByVoltage(voltageValue);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < ramList.size(); i++) {
            listIds.add(ramList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showRam(m, productList, ramList);
    }
    
    public List<Ram> ramSort(List<Integer> listIds) {
        List<Ram> concreteRam = new ArrayList();
        for (int i = 0; i < allRam.size(); i++) {
            if (listIds.contains(allRam.get(i).getId())) {
                concreteRam.add(allRam.get(i));
            }
        }
        return concreteRam;
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
    public String createRamForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("voltage") BigDecimal voltage,
            @RequestParam("frequency") Integer frequency,
            @RequestParam("size") Integer size,
            @RequestParam("type") Integer type,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(5);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        RamManufacturer ramManufacturer = serviceRam.findByManufacturerId(manufacturer);
        RamSize ramSize = serviceRam.findBySizeId(size);
        RamType ramType = serviceRam.findByTypeId(type);
        Ram ram = new Ram(productId, voltage, product, frequency, ramManufacturer, ramSize, ramType);
        allRam.add(ram);
        serviceRam.saveOrUpdate(ram);
        
        return "redirect:/ram";
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
