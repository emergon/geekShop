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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/psu")
public class PsuController {
    
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PsuService servicePsu;

    List<Psu> allPsu;
    List<PsuManufacturer> manufacturers;
    List<PsuCableManagement> cableManagements;
    List<PsuEfficiency> efficiencies;
    List<Integer> watts;
    List<Product> productsByCategory;
 
    public String showPsu(Model m, List<Product> productList, List<Psu> psuList) {
        m.addAttribute("productList", productList);
        m.addAttribute("psuList", psuList);
        if (manufacturers == null) {
            manufacturers = servicePsu.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (cableManagements == null) {
            cableManagements = servicePsu.findAllCableManagement();
        }
        m.addAttribute("cableManagements", cableManagements);
        if (efficiencies == null) {
            efficiencies = servicePsu.findAllEfficiencies();
        }
        m.addAttribute("efficiencies", efficiencies);
        if (watts == null) {
            watts = servicePsu.findAllWatts();
        }
        m.addAttribute("watts", watts);
        m.addAttribute("product", new Product());
        return "psu";
    }
    
    @GetMapping
    public String displayAllPsu(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(7);
        }
        if (allPsu == null) {
            allPsu = servicePsu.getAll();
        }
        return showPsu(m, productsByCategory, allPsu);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 7);
        List<Integer> listIds = ProductIdByPrice(productsByPrice); //ids of the above products 
        List<Psu> psuList = psuSort(listIds); 
        return showPsu(m, productsByPrice, psuList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Psu> psuList = servicePsu.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < psuList.size(); i++) {
            listIds.add(psuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showPsu(m, productList, psuList);
    }
    
    @GetMapping("/watt/{wattValue}")
    public String getByType(Model m,
            @PathVariable("wattValue") Integer wattValue) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Psu> psuList = servicePsu.findByWatt(wattValue);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < psuList.size(); i++) {
            listIds.add(psuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showPsu(m, productList, psuList);
    }
    
    @GetMapping("/cableManagement/{Id}")
    public String getByCableManagement(Model m,
            @PathVariable("Id") Integer Id) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Psu> psuList = servicePsu.findByCableManagement(Id);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < psuList.size(); i++) {
            listIds.add(psuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showPsu(m, productList, psuList);
    }
    
    @GetMapping("/efficiency/{Id}")
    public String getByInches(Model m,
            @PathVariable("Id") Integer Id) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Psu> psuList = servicePsu.findByEfficiency(Id);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < psuList.size(); i++) {
            listIds.add(psuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showPsu(m, productList, psuList);
    }
    
    public List<Psu> psuSort(List<Integer> listIds) {
        List<Psu> concretePsu = new ArrayList();
        for (int i = 0; i < allPsu.size(); i++) {
            if (listIds.contains(allPsu.get(i).getId())) {
                concretePsu.add(allPsu.get(i));
            }
        }
        return concretePsu;
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
    public String createPsuForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("watt") Integer watt,
            @RequestParam("cableManagement") Integer cableManagement,
            @RequestParam("efficiency") Integer efficiency,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(7);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        PsuManufacturer psuManufacturer = servicePsu.findManufacturerById(manufacturer);
        PsuCableManagement psuCableManagement = servicePsu.findCableManagementById(cableManagement);
        PsuEfficiency psuEfficiency = servicePsu.findEfficiencyById(efficiency);
        Psu psu = new Psu(productId, watt, product, psuCableManagement, psuEfficiency, psuManufacturer);
        servicePsu.saveOrUpdate(psu);
        allPsu.add(psu);
        return "redirect:/psu";
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
