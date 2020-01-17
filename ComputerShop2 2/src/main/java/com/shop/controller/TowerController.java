package com.shop.controller;

import com.shop.entities.*;
import com.shop.service.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tower")
public class TowerController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TowerService serviceTower;

    List<Tower> allTowers; // = serviceTower.getAll();
    List<TowerManufacturer> manufacturers; //= serviceTower.findAllManufacturers();
    List<TowerType> types; //= serviceTower.findAllTypes();
    List<Product> productsByCategory;
    Integer indexOfProduct = -1;

    public String showTower(Model m, List<Product> productList, List<Tower> towerList) {
        m.addAttribute("productList", productList);
        m.addAttribute("towerList", towerList);
        Product productCheck = (Product) m.getAttribute("product");
        if (productCheck == null) {
            m.addAttribute("product", new Product());
        }
        if (manufacturers == null) {
            manufacturers = serviceTower.findAllManufacturers();
        }
        m.addAttribute("manufacturers", manufacturers);
        if (types == null) {
            types = serviceTower.findAllTypes();
        }
        m.addAttribute("types", types);
        return "tower";
    }

    @GetMapping
    public String displayAllTowers(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        if (productsByCategory == null) {
            productsByCategory = service.getByCategory(1);
        }
        if (allTowers == null) {
            allTowers = serviceTower.getAll();
        }
        return showTower(m, productsByCategory, allTowers);
    }

    //_______________________________FILTERS____________________________________
    @GetMapping("/price/{initial}/{final}")
    public String getByPrice(Model m,
            @PathVariable("initial") BigDecimal initialPrice,
            @PathVariable("final") BigDecimal finalPrice) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, 1);
        /*products with category 1
        and price between initial and final price*/
        List<Integer> listIds = ProductIdByPrice(productsByPrice); //ids of the above products 
        List<Tower> towerList = towerSort(listIds); //towers with the above Ids
        return showTower(m, productsByPrice, towerList);
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    public String getByManufacturer(Model m,
            @PathVariable("manufacturerId") Integer manufacturerId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Tower> towerList = serviceTower.findByManufacturer(manufacturerId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < towerList.size(); i++) {
            listIds.add(towerList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showTower(m, productList, towerList);
    }

    @GetMapping("/type/{typeId}")
    public String getByType(Model m,
            @PathVariable("typeId") Integer typeId) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<Tower> towerList = serviceTower.findByType(typeId);
        List<Integer> listIds = new ArrayList();
        for (int i = 0; i < towerList.size(); i++) {
            listIds.add(towerList.get(i).getId());
        }
        List<Product> productList = productSort(listIds);
        return showTower(m, productList, towerList);
    }

    public List<Tower> towerSort(List<Integer> listIds) {
        List<Tower> concreteTowers = new ArrayList();
        for (int i = 0; i < allTowers.size(); i++) {
            if (listIds.contains(allTowers.get(i).getId())) {
                concreteTowers.add(allTowers.get(i));
            }
        }
        return concreteTowers;
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
    
    //___________________________________SEE MORE INFO__________________________________________
    @GetMapping("/information/{Id}")
    public String viewMore(Model m,
            @PathVariable("Id") Integer productId){
        Product product = service.findById(productId);
        Tower tower = serviceTower.findById(productId);
        return showOneTowerWithInfos(m, product, tower);
    }
    
    public String showOneTowerWithInfos(Model m, Product product, Tower tower){
        m.addAttribute("product", product);
        m.addAttribute("towerType", tower.getTowerType());
        m.addAttribute("towerManufacturer", tower.getManufacturer());
        List<Product> listOfProducts = new ArrayList();
        Random random = new Random();
        int index = -1;
        for(int i=0; i<4; i++){
            Product p = new Product();
            do{
               index = random.nextInt((productsByCategory.size()-1) + 1);
               p = productsByCategory.get(index);
            }while(listOfProducts.contains(p));
            listOfProducts.add(p);
        }
        m.addAttribute("listOfProducts", listOfProducts);
        return "towerProduct";
    }

    //_______________________________CREATE OR UPDATE___________________________________________
    @PostMapping("/add")
    public String addProductTower(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("manufacturer") Integer manufacturer,
            @RequestParam("type") Integer type,
            @RequestParam("pricee") String price,
            @RequestParam("ammountt") String ammount,
            @RequestParam("discountt") String discount,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("pricee", price);
        redirectAttrs.addFlashAttribute("ammountt", ammount);
        redirectAttrs.addFlashAttribute("discountt", discount);
        redirectAttrs.addFlashAttribute("product", product); // ksanadinoume sto model to simplirwmeno pleon product etsi wste an petaksei kapoio error na ksanagirisei stin forma exontas ta stoixeia tou product simplirwmena
        boolean isUpdate = false;
        if (product.getId() != null) {
            isUpdate = true;
        }
        if (isUpdate) { // katalavainei an exei patithei to update
            if (service.hasSameNameExceptThisOne(product)) {
                String msg = "There is another Product with this name";
                redirectAttrs.addFlashAttribute("msgName", msg);
                return "redirect:/tower";
            }
            if (service.hasSameCodeExceptThisOne(product)) {
                String msg = "There is another product code with this value";
                redirectAttrs.addFlashAttribute("msgCode", msg);
                return "redirect:/tower";
            }

        } else {
            if (service.hasSameName(product)) {
                String msg = "product name already exists";
                redirectAttrs.addFlashAttribute("msgName", msg);
                return "redirect:/tower";
            }
            if (service.hasSameCode(product)) {
                String msg = "product code already exists";
                redirectAttrs.addFlashAttribute("msgCode", msg);
                return "redirect:/tower";
            }
        }
        try {
            BigDecimal priceBigDecimal = new BigDecimal(price);
            product.setPrice(priceBigDecimal);
        } catch (Exception e) {
            String msg = "Only numbers allowed";
            redirectAttrs.addFlashAttribute("msgNumbersPrice", msg);
            return "redirect:/tower";
        }
        try {
            Integer ammountInt = Integer.parseInt(ammount);
            product.setQuantity(ammountInt);
        } catch (Exception e) {
            String msg = "Only numbers allowed";
            redirectAttrs.addFlashAttribute("msgNumbersAmmount", msg);
            return "redirect:/tower";
        }
        try {
            BigDecimal discounteBigDecimal = new BigDecimal(discount);
            product.setSales(discounteBigDecimal);
        } catch (Exception e) {
            String msg = "Only numbers allowed";
            redirectAttrs.addFlashAttribute("msgNumbersDiscount", msg);
            return "redirect:/tower";
        }
        if (product.getQuantity() != (int) product.getQuantity()) {
            String msg = "Only numbers allowed";
            redirectAttrs.addFlashAttribute("msgNumbers", msg);
            return "redirect:/tower";
        }
        try {
            if (!isUpdate) {
                if (file.getContentType().equals("application/octet-stream")) {
                    String msg = "You need to select a photo";
                    redirectAttrs.addFlashAttribute("msgImg", msg);
                    return "redirect:/tower";
                } else if ((!file.getContentType().startsWith("image"))) {
                    String msg = "Wrong File Type";
                    redirectAttrs.addFlashAttribute("msgImg", msg);
                    return "redirect:/tower";
                } else if (file.getSize() > 1000000) {
                    String msg = "Size must be less than 1Mb";
                    redirectAttrs.addFlashAttribute("msgImg", msg);
                    return "redirect:/tower";
                }

            } else if (isUpdate && !file.getContentType().equals("application/octet-stream")) {
                if ((!file.getContentType().startsWith("image"))) {
                    String msg = "Wrong File Type";
                    redirectAttrs.addFlashAttribute("msgImg", msg);
                    return "redirect:/tower";
                } else if (file.getSize() > 1000000) {
                    String msg = "Size must be less than 1Mb";
                    redirectAttrs.addFlashAttribute("msgImg", msg);
                    return "redirect:/tower";
                }
            }
            Category category = categoryService.findById(1); // find category from id
            product.setCategory(category); // add category to product
            try {

                int productId = service.createOrUpdate(product).getId();//add product to db and grab the id  //  ___________________CREATE__________________
                if (isUpdate) {
                    productsByCategory.set(indexOfProduct, product);
                } else {
                    productsByCategory.add(product);  //****************prostheto to product sti lista****************************
                }

                if (!file.getContentType().equals("application/octet-stream")) {
                    addPhoto(productId, file); // add photo to Tomcat's folder 
                }
                product.setId(productId);// insert product's id from db into this product
                TowerManufacturer towerManufacturer = serviceTower.findByManufacturerId(manufacturer); // find manufacturer from id
                TowerType towerType = serviceTower.findByTypeId(type); // find towertype from id
                Tower tower = new Tower(productId, product, towerManufacturer, towerType); // create product's additional info package(unique for each categoty) 
                boolean towerInsert = serviceTower.saveOrUpdate(tower); //save this package to db
                if (towerInsert) {                             //*************************************************************************************************** 
                    allTowers.add(tower);
                    price = null;
                    ammount = null;
                    discount = null;
                    
                    redirectAttrs.addFlashAttribute("pricee", price);
                    redirectAttrs.addFlashAttribute("ammountt", ammount);
                    redirectAttrs.addFlashAttribute("discountt", discount);
                    redirectAttrs.addFlashAttribute("product", new Product());

                    return "redirect:/tower";
                } else {
                    String msg = "Something went wrong on inserting tower's info. Please try again";
                    redirectAttrs.addFlashAttribute("msgGeneral", msg);
                    return "redirect:/tower";
                }
            } catch (Exception e) {
                String msg = "Something went wrong. Check if all the fields are filled!";
                redirectAttrs.addFlashAttribute("msgGeneral", msg);
                return "redirect:/tower";
            }

        } catch (Exception e) {
            String msg = "Something went wrong. Try again!";
            redirectAttrs.addFlashAttribute("msgGeneral", msg);
            return "redirect:/tower";
        }
    }

    @GetMapping("/update")
    public String updatetowerForm(Model m,
            @RequestParam("productId") Integer productId,
            RedirectAttributes redirectAttrs) {
        Product product = service.findById(productId);
        String price = product.getPrice().toString();
        String ammount = product.getQuantity().toString();
        String discount = product.getSales().toString();
        redirectAttrs.addFlashAttribute("product", product);
        redirectAttrs.addFlashAttribute("pricee", price);
        redirectAttrs.addFlashAttribute("ammountt", ammount);
        redirectAttrs.addFlashAttribute("discountt", discount);
        indexOfProduct = productsByCategory.indexOf(product);
        return "redirect:/tower";
    }

    public String addPhoto(int imgName, MultipartFile file) {
        String tomcatBase = System.getProperty("catalina.base");
        try {
            String uploadDir = tomcatBase + "/webapps/images";
            File transferFile = new File(uploadDir + "/" + imgName + ".jpg");
            file.transferTo(transferFile);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

//    productsByCategory.add(product); //CREATE PRODUCT --> ADD PRODUCT
//    public int addProductToForm(Product product, MultipartFile file) {
//
//        String tomcatBase = System.getProperty("catalina.base");
//
//        if (file != null || !file.isEmpty()) {
//            try {
//                int pictureName = service.createOrUpdate(product).getId();
//                productsByCategory.add(product);
//                String uploadDir = tomcatBase + "/webapps/images";
//                File transferFile = new File(uploadDir + "/" + pictureName + ".jpg");
//                file.transferTo(transferFile);
//                return pictureName;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return 0;
//            }
//        }
////        else
////        {  //validation
////            
////            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
////            return "redirect:success";
////        }
//
////        try {
////
////            // Get the file and save it somewhere
////            byte[] bytes = file.getBytes();
////            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
////            Files.write(path, bytes);
////
////            redirectAttributes.addFlashAttribute("message",
////                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        return 0;
//    }
    //_________________________________DELETE___________________________________
    @GetMapping("/delete")
    public String deleteTower(Model m,
            @ModelAttribute("product") Product product,
            @RequestParam("productId") Integer productId) {
        Tower tower = serviceTower.findById(productId);
        System.out.println("test dao________" + tower);
        allTowers.remove(tower);
        serviceTower.delete(tower);
        service.delete(product);
        return "redirect:/tower";
    }
}
