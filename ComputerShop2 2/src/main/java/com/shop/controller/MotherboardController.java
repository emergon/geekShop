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
@RequestMapping("/motherboard")
public class MotherboardController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MotherboardService serviceMother;

    List<Motherboard> allMotherboards;
    List<MotherboardManufacturer> manufacturers; 
    List<MotherboardSocket> sockets; 
    List<MotherboardSize> sizes;
    List<MotherboardChipset> chipsets;
    List<MotherboardPort> ports;
    List<Product> productsByCategory;

    public String showMotherboard(Model m, List<Product> productList, List<Motherboard> motherboardList) {
        m.addAttribute("productList", productList);
        m.addAttribute("motherboardList", motherboardList);
        if (manufacturers == null) {
            manufacturers = serviceMother.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (sockets == null) {
            sockets = serviceMother.findAllSockets();;
        }
        m.addAttribute("sockets", sockets);
        if (sizes == null) {
            sizes = serviceMother.findAllSizes();
        }
        m.addAttribute("sizes", sizes);
        if (chipsets == null) {
            chipsets = serviceMother.findAllChipsets();
        }
        m.addAttribute("chipsets", chipsets);
        if (ports == null) {
            ports = serviceMother.findAllPorts();
        }
        m.addAttribute("ports", ports);
        m.addAttribute("product", new Product());
        return "motherboard";
    }

    @GetMapping
    public String displayAllMotherboards(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(2);
        }
        if (allMotherboards == null) {
            allMotherboards = serviceMother.getAll();
        }
        return showMotherboard(m, productsByCategory, allMotherboards);
    }
    
    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 2);
        List<Integer> listIds = ProductIdByPrice(productsByPrice); //ids of the above products 
        List<Motherboard> motherboardList = motherboardSort(listIds); //motherboards with the above Ids
        return showMotherboard(m, productsByPrice, motherboardList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Motherboard> motherboardList = serviceMother.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < motherboardList.size(); i++) {
            listIds.add(motherboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMotherboard(m, productList, motherboardList);
    }
    
    @GetMapping("/socket/{typeId}")
    public String getBySocket(Model m,
            @PathVariable("typeId") Integer typeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Motherboard> motherboardList = serviceMother.findBySocket(typeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < motherboardList.size(); i++) {
            listIds.add(motherboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMotherboard(m, productList, motherboardList);
    }
    
    @GetMapping("/size/{sizeId}")
    public String getBySize(Model m,
            @PathVariable("sizeId") Integer sizeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Motherboard> motherboardList = serviceMother.findBySize(sizeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < motherboardList.size(); i++) {
            listIds.add(motherboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMotherboard(m, productList, motherboardList);
    }
    
    @GetMapping("/chipset/{chipsetId}")
    public String getByChipset(Model m,
            @PathVariable("chipsetId") Integer chipsetId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Motherboard> motherboardList = serviceMother.findByChipset(chipsetId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < motherboardList.size(); i++) {
            listIds.add(motherboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMotherboard(m, productList, motherboardList);
    }
    
    @GetMapping("/port/{portId}")
    public String getByPort(Model m,
            @PathVariable("portId") Integer portId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Motherboard> motherboardList = serviceMother.findByPort(portId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < motherboardList.size(); i++) {
            listIds.add(motherboardList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showMotherboard(m, productList, motherboardList);
    }
    
    public List<Motherboard> motherboardSort(List<Integer> listIds) {
        List<Motherboard> concreteMotherboards = new ArrayList();
        for (int i = 0; i < allMotherboards.size(); i++) {
            if (listIds.contains(allMotherboards.get(i).getId())) {
                concreteMotherboards.add(allMotherboards.get(i));
            }
        }
        return concreteMotherboards;
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
    public String createMotherboardForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("socket") Integer socket,
            @RequestParam("size") Integer size,
            @RequestParam("chipset") Integer chipset,
            @RequestParam("port") Integer port,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(2);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        MotherboardManufacturer motherboardManufacturer = serviceMother.findManufacturerById(manufacturer);
        MotherboardSocket motherboardSocket = serviceMother.findSocketById(socket);
        MotherboardSize motherboardSize = serviceMother.findSizeById(size);
        MotherboardChipset motherboardChipset = serviceMother.findChipsetById(chipset);
        MotherboardPort motherboardPort = serviceMother.findPortById(port);
        Motherboard motherboard = new Motherboard(productId, motherboardChipset, motherboardManufacturer, motherboardPort, motherboardSize, motherboardSocket, product);
        serviceMother.saveOrUpdate(motherboard);
        allMotherboards.add(motherboard);
        return "redirect:/motherboard";
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
