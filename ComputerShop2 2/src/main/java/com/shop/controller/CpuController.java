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
@RequestMapping("/cpu")
public class CpuController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CpuService serviceCpu;

    List<Cpu> allCpu;
    List<CpuManufacturer> manufacturers;
    List<CpuChip> chips;
    List<Integer> cores;
    List<Product> productsByCategory;

    public String showCpu(Model m, List<Product> productList, List<Cpu> cpuList) {
        m.addAttribute("productList", productList);
        System.out.println("giannis*9**************\n" + cpuList);
        m.addAttribute("cpuList", cpuList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceCpu.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (chips == null) {
            chips = serviceCpu.findAllChips();
        }
        m.addAttribute("chips", chips);
        if (cores == null) {
            cores = serviceCpu.findAllCores();
        }
        m.addAttribute("cores", cores);
        return "cpu";
    }

    @GetMapping
    public String displayAllCpu(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(3);
        }
        if (allCpu == null) {
            allCpu = serviceCpu.getAll();
        }
        return showCpu(m, productsByCategory, allCpu);
    }

    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 3);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Cpu> cpuList = cpuSort(listIds);
        return showCpu(m, productsByPrice, cpuList);
    }
    
    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Cpu> cpuList = serviceCpu.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < cpuList.size(); i++) {
            listIds.add(cpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showCpu(m, productList, cpuList);
    }
    
    @GetMapping("/chip/{chipId}")
    public String getByChip(Model m,
            @PathVariable("chipId") Integer chipId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Cpu> cpuList = serviceCpu.findByChip(chipId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < cpuList.size(); i++) {
            listIds.add(cpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showCpu(m, productList, cpuList);
    }
    
    @GetMapping("/cores/{coresId}")
    public String getByCores(Model m,
            @PathVariable("coresId") Integer coresId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Cpu> cpuList = serviceCpu.findByCores(coresId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < cpuList.size(); i++) {
            listIds.add(cpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showCpu(m, productList, cpuList);
    }

    public List<Cpu> cpuSort(List<Integer> listIds) {
        List<Cpu> concreteCpu = new ArrayList();
        for (int i = 0; i < allCpu.size(); i++) {
            if (listIds.contains(allCpu.get(i).getId())) {
                concreteCpu.add(allCpu.get(i));
            }
        }
        return concreteCpu;
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
    public String createCpuForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("chip") Integer chip,
            @RequestParam("cores") Integer cores,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(3);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        CpuManufacturer cpuManufacturer = serviceCpu.findByManufacturerId(manufacturer);
        CpuChip cpuChip = serviceCpu.findByChipId(chip);
        Cpu cpu = new Cpu(productId, cores, cpuChip, cpuManufacturer, product);
        allCpu.add(cpu);
        serviceCpu.saveOrUpdate(cpu);
        return "redirect:/cpu";
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
