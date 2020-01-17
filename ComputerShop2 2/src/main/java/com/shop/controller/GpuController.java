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
@RequestMapping("/gpu")
public class GpuController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    GpuService serviceGpu;

    List<Gpu> allGpu;
    List<GpuManufacturer> manufacturers;
    List<Integer> memories;
    List<GpuChipset> chipsets;
    List<Product> productsByCategory;

    public String showGpu(Model m, List<Product> productList, List<Gpu> gpuList) {
        m.addAttribute("productList", productList);
        m.addAttribute("gpuList", gpuList);
        m.addAttribute("product", new Product());
        if (manufacturers == null) {
            manufacturers = serviceGpu.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (chipsets == null) {
            chipsets = serviceGpu.findAllChipsets();
        }
        m.addAttribute("chipsets", chipsets);
        if (memories == null) {
            memories = serviceGpu.findAllMemories();
        }
        m.addAttribute("memories", memories);
        return "gpu";
    }

    @GetMapping
    public String displayAllGpu(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(4);
        }
        if (allGpu == null) {
            allGpu = serviceGpu.getAll();
        }
        return showGpu(m, productsByCategory, allGpu);
    }

    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 4);
        List<Integer> listIds = ProductIdByPrice(productsByPrice);
        List<Gpu> gpuList = gpuSort(listIds);
        return showGpu(m, productsByPrice, gpuList);
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Gpu> gpuList = serviceGpu.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < gpuList.size(); i++) {
            listIds.add(gpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showGpu(m, productList, gpuList);
    }

    @GetMapping("/chipset/{chipsetId}")
    public String getByChip(Model m,
            @PathVariable("chipsetId") Integer chipsetId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Gpu> gpuList = serviceGpu.findByChipset(chipsetId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < gpuList.size(); i++) {
            listIds.add(gpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showGpu(m, productList, gpuList);
    }

    @GetMapping("/memory/{memoryValue}")
    public String getByMemory(Model m,
            @PathVariable("memoryValue") Integer memoryValue) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Gpu> gpuList = serviceGpu.findByMemory(memoryValue);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < gpuList.size(); i++) {
            listIds.add(gpuList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showGpu(m, productList, gpuList);
    }

    public List<Gpu> gpuSort(List<Integer> listIds) {
        List<Gpu> concreteGpu = new ArrayList();
        for (int i = 0; i < allGpu.size(); i++) {
            if (listIds.contains(allGpu.get(i).getId())) {
                concreteGpu.add(allGpu.get(i));
            }
        }
        return concreteGpu;
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
    public String createGpuForm(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("chipset") Integer chipset,
            @RequestParam("memory") Integer memory,
            @RequestParam("file") MultipartFile file,
            BindingResult result) {
        Category category = categoryService.findById(4);
        product.setCategory(category);
        int productId = addProductToForm(product, file);
        product.setId(productId);
        GpuManufacturer gpuManufacturer = serviceGpu.findByManufacturerId(manufacturer);
        GpuChipset gpuChipset = serviceGpu.findByChipsetId(chipset);
        Gpu gpu = new Gpu(productId, memory, gpuChipset, gpuManufacturer, product);
        allGpu.add(gpu);
        serviceGpu.saveOrUpdate(gpu);
        return "redirect:/gpu";
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
