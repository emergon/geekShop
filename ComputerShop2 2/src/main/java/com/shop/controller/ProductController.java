package com.shop.controller;

 

import com.shop.entities.*;
import com.shop.service.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

 

@Controller
@RequestMapping("/")
public class ProductController {

 

    @Autowired
    ProductService service;

 

    @GetMapping/*("/list")*/
    public String showProducts(Model m) {

 

        List<Product> listNewest = service.getNewest();
        List<Product> listSales = service.findSales();

 

        int countCart = getCountCart();
        m.addAttribute("latestProducts", listNewest);
        m.addAttribute("salesProducts", listSales);
        m.addAttribute("countCart", countCart);
        return "products";
    }

 

    public static int getCountCart() {
        List<CartItem> cart = getCart();
        int count = 0;
        for (CartItem c : cart) {
            count = count + c.getQuantity();
        }
        return count;
    }

 

    public static List<CartItem> getCart() {
        List<CartItem> cartList = (List<CartItem>) getSession().getAttribute("cart");
        if (cartList == null) {
            cartList = new ArrayList<CartItem>();
            getSession().setAttribute("cart", cartList);
        }
        return cartList;
    }

 

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
}   











//package com.shop.controller;
//
//import com.shop.entities.*;
//import com.shop.service.*;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Controller
//@RequestMapping("/")
//public class ProductController {
//
//    @Autowired
//    ProductService service;
//    @Autowired
//    TowerService serviceTower;
//    @Autowired
//    MonitorService serviceMon;
//    @Autowired
//    MotherboardService serviceMother;
//    @Autowired
//    CpuService serviceCpu;
//    @Autowired
//    GpuService serviceGpu;
//    @Autowired
//    RamService serviceRam;
//    @Autowired
//    StorageService serviceStorage;
//    @Autowired
//    PsuService servicePsu;
//    @Autowired
//    MouseService serviceMouse;
//    @Autowired
//    KeyboardService serviceKeyboard;
//    
//    @GetMapping/*("/list")*/
//    public String showProducts(Model m)
//    {
//
//        List<Product> listNewest = service.getNewest();
//        List<Product> listSales = service.findSales();
//        
//        int countCart = getCountCart();
//        m.addAttribute("latestProducts", listNewest);
//        m.addAttribute("salesProducts", listSales);
//        m.addAttribute("countCart", countCart);
//        return "products";
//    }
//    
//    
//    public static int getCountCart() {
//        List<CartItem> cart = getCart();
//        int count = 0;
//        for (CartItem c : cart) {
//            count = count + c.getQuantity();
//        }
//        return count;
//    }
//    
//     public static List<CartItem> getCart() {
//        List<CartItem> cartList = (List<CartItem>) getSession().getAttribute("cart");
//        if (cartList == null) {
//            cartList = new ArrayList<CartItem>();
//            getSession().setAttribute("cart", cartList);
//        }
//        return cartList;
//    }
//
//    public List<Integer> ProductIdByCategory(BigDecimal initialPrice, BigDecimal finalPrice, Integer category){
//        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, category);
//        List<Integer> listIds = new ArrayList(); //Ids of products by category
//        for (int i = 0; i < productsByPrice.size(); i++) {
//            listIds.add(productsByPrice.get(i).getId());
//        }
//        return listIds;
//    }
//     
//     
//    @GetMapping("/displayAllProducts")
//    public String displayAllProducts(Model m) {
//        List<Product> allProducts = service.getAll();
//        m.addAttribute("listOfProducts", allProducts);
//        return "allProducts";
//    }
//    
//    
//
//    @GetMapping("/displayByCategory/{category}")
//    public String displayAllTowers(Model m, @PathVariable("category") Integer category
//            ){
//        int countCart = getCountCart();
//        m.addAttribute("countCart", countCart);
//        List<Product> productsByCategory = service.getByCategory(category);
//        switch (category) {
//            case 1:
//                List<Tower> allTowers = serviceTower.getAll();
//                return showTower(m, productsByCategory, allTowers);
//            case 2:
//                List<Motherboard> allMotherboards = serviceMother.getAll();
//                return showMotherboard(m, productsByCategory, allMotherboards);
//            case 3:
//                List<Cpu> allCpu = serviceCpu.getAll();
//                return showCpu(m, productsByCategory, allCpu);
//            case 4:
//                List<Gpu> allGpu = serviceGpu.getAll();
//                return showGpu(m, productsByCategory, allGpu);
//            case 5:
//                List<Ram> allRam = serviceRam.getAll();
//                return showRam(m, productsByCategory, allRam);
//            case 6:
//                List<Storage> allStorage = serviceStorage.getAll();
//                return showStorage(m, productsByCategory, allStorage);
//            case 7:
//                List<Psu> allPsu = servicePsu.getAll();
//                return showPsu(m, productsByCategory, allPsu);
//            case 8:
//                List<Monitor> allMonitors = serviceMon.getAll();
//                return showMonitor(m, productsByCategory, allMonitors);
//            case 9:
//                List<Mouse> allMouse = serviceMouse.getAll();
//                return showMouse(m, productsByCategory, allMouse);
//            default: //case 10
//                List<Keyboard> allKeyboard = serviceKeyboard.getAll();
//                return showKeyboard(m, productsByCategory, allKeyboard);
//        }
//
//    }
//
//    public String showTower(Model m, List<Product> productList, List<Tower> towerList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("towerList", towerList);
//        m.addAttribute("product", new Product());
//        List<TowerManufacturer> manufacturers = serviceTower.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<TowerType> types = serviceTower.findAllTypes();
//        m.addAttribute("types", types);
//        return "tower";
//    }
//
//    public String showMotherboard(Model m, List<Product> productList, List<Motherboard> motherboardList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("motherboardList", motherboardList);
//        List<MotherboardManufacturer> manufacturers = serviceMother.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<MotherboardSocket> types = serviceMother.findAllSockets();
//        m.addAttribute("types", types);
//        List<MotherboardSize> sizes = serviceMother.findAllSizes();
//        m.addAttribute("sizes", sizes);
//        List<MotherboardChipset> chipsets = serviceMother.findAllChipsets();
//        m.addAttribute("chipsets", chipsets);
//        List<MotherboardPort> ports = serviceMother.findAllPorts();
//        m.addAttribute("ports", ports);
//        return "motherboard";
//    }
//
//    public String showCpu(Model m, List<Product> productList, List<Cpu> cpuList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("cpuList", cpuList);
//        List<CpuManufacturer> manufacturers = serviceCpu.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<CpuChip> types = serviceCpu.findAllChips();
//        m.addAttribute("types", types);
//        List<Integer> cores = serviceCpu.findAllCores();
//        m.addAttribute("cores", cores);
//        return "cpu";
//    }
//
//    public String showGpu(Model m, List<Product> productList, List<Gpu> gpuList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("gpuList", gpuList);
//        List<GpuManufacturer> manufacturers = serviceGpu.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<GpuChipset> types = serviceGpu.findAllChipsets();
//        m.addAttribute("types", types);
//        List<Integer> memories = serviceGpu.findAllMemories();
//        m.addAttribute("memories", memories);
//        return "gpu";
//    }
//
//    public String showRam(Model m, List<Product> productList, List<Ram> ramList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("ramList", ramList);
//        List<RamManufacturer> manufacturers = serviceRam.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<RamType> types = serviceRam.findAllTypes();
//        m.addAttribute("types", types);
//        List<RamSize> sizes = serviceRam.findAllSizes();
//        m.addAttribute("sizes", sizes);
//        List<Integer> frequencies = serviceRam.findAllFrequencies();
//        m.addAttribute("frequencies", frequencies);
//        List<BigDecimal> voltages = serviceRam.findAllVoltage();
//        m.addAttribute("voltages", voltages);
//        return "ram";
//    }
//
//    public String showStorage(Model m, List<Product> productList, List<Storage> storageList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("storageList", storageList);
//        List<StorageManufacturer> manufacturers = serviceStorage.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<StorageType> types = serviceStorage.findAllTypes();
//        m.addAttribute("types", types);
//        List<StorageInches> inches = serviceStorage.findAllInches();
//        m.addAttribute("inches", inches);
//        return "storage";
//    }
//
//    public String showPsu(Model m, List<Product> productList, List<Psu> psuList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("psuList", psuList);
//        List<PsuManufacturer> manufacturers = servicePsu.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<PsuCableManagement> types = servicePsu.findAllCableManagement();
//        m.addAttribute("types", types);
//        List<Integer> watts = servicePsu.findAllWatts();
//        m.addAttribute("watts", watts);
//        List<PsuEfficiency> efficiencies = servicePsu.findAllEfficiencies();
//        m.addAttribute("efficiencies", efficiencies);
//        return "psu";
//    }
//
//    public String showMonitor(Model m, List<Product> productList, List<Monitor> monitorList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("monitorList", monitorList);
//        List<MonitorManufacturer> manufacturers = serviceMon.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<Integer> types = serviceMon.findAllInches();
//        m.addAttribute("types", types);
//        return "monitor";
//    }
//
//    public String showMouse(Model m, List<Product> productList, List<Mouse> mouseList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("mouseList", mouseList);
//        List<MouseManufacturer> manufacturers = serviceMouse.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<MouseType> types = serviceMouse.findAllTypes();
//        m.addAttribute("types", types);
//        return "mouse";
//    }
//
//    public String showKeyboard(Model m, List<Product> productList, List<Keyboard> keyboardList) {
//        m.addAttribute("productList", productList);
//        m.addAttribute("keyboardList", keyboardList);
//        List<KeyboardManufacturer> manufacturers = serviceKeyboard.findAllManufacturers();
//        m.addAttribute("manufacturers", manufacturers);
//        List<KeyboardType> types = serviceKeyboard.findAllTypes();
//        m.addAttribute("types", types);
//        return "keyboard";
//    }
//
//    @GetMapping("/price/{initial}/{final}/{category}")
//    public String getByPrice(Model m,
//            @PathVariable("initial") BigDecimal initialPrice,
//            @PathVariable("final") BigDecimal finalPrice,
//            @PathVariable("category") Integer category) {
//        int countCart = getCountCart();
//        m.addAttribute("countCart", countCart);
//        List<Product> productsByPrice = service.getByPrice(initialPrice, finalPrice, category);
//        List<Integer> listIds = new ArrayList();
//        for (int i = 0; i < productsByPrice.size(); i++) {
//            listIds.add(productsByPrice.get(i).getId());
//        }
//        switch (category) {
//            case 1:
//                List<Tower> towerList = towerSort(listIds);
//                return showTower(m, productsByPrice, towerList);
//            case 2:
//                List<Motherboard> motherboardList = motherboardSort(listIds);
//                return showMotherboard(m, productsByPrice, motherboardList);
//            case 3:
//                List<Cpu> cpuList = cpuSort(listIds);
//                return showCpu(m, productsByPrice, cpuList);
//            case 4:
//                List<Gpu> gpuList = gpuSort(listIds);
//                return showGpu(m, productsByPrice, gpuList);
//            case 5:
//                List<Ram> ramList = ramSort(listIds);
//                return showRam(m, productsByPrice, ramList);
//            case 6:
//                List<Storage> storageList = storageSort(listIds);
//                return showStorage(m, productsByPrice, storageList);
//            case 7:
//                List<Psu> psuList = psuSort(listIds);
//                return showPsu(m, productsByPrice, psuList);
//            case 8:
//                List<Monitor> monitorList = monitorSort(listIds);
//                return showMonitor(m, productsByPrice, monitorList);
//            case 9:
//                List<Mouse> mouseList = mouseSort(listIds);
//                return showMouse(m, productsByPrice, mouseList);
//            default:
//                List<Keyboard> keyboardList = keyboardSort(listIds);
//                return showKeyboard(m, productsByPrice, keyboardList);
//        }
//
//    }
//
//    @GetMapping("/manufacturer/{manufacturerId}/{category}")
//    public String getByManufacturer(Model m,
//            @PathVariable("manufacturerId") Integer manufacturerId,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        switch (category) {
//            case 1:
//                List<Tower> towerList = serviceTower.findByManufacturer(manufacturerId);
//                for (int i = 0; i < towerList.size(); i++) {
//                    listIds.add(towerList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showTower(m, productList, towerList);
//            case 2:
//                List<Motherboard> motherboardList = serviceMother.findByManufacturer(manufacturerId);
//                for (int i = 0; i < motherboardList.size(); i++) {
//                    listIds.add(motherboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMotherboard(m, productList, motherboardList);
//            case 3:
//                List<Cpu> cpuList = serviceCpu.findByManufacturer(manufacturerId);
//                for (int i = 0; i < cpuList.size(); i++) {
//                    listIds.add(cpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showCpu(m, productList, cpuList);
//            case 4:
//                List<Gpu> gpuList = serviceGpu.findByManufacturer(manufacturerId);
//                for (int i = 0; i < gpuList.size(); i++) {
//                    listIds.add(gpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showGpu(m, productList, gpuList);
//            case 5:
//                List<Ram> ramList = serviceRam.findByManufacturer(manufacturerId);
//                for (int i = 0; i < ramList.size(); i++) {
//                    listIds.add(ramList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showRam(m, productList, ramList);
//            case 6:
//                List<Storage> storageList = serviceStorage.findByManufacturer(manufacturerId);
//                for (int i = 0; i < storageList.size(); i++) {
//                    listIds.add(storageList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showStorage(m, productList, storageList);
//            case 7:
//                List<Psu> psuList = servicePsu.findByManufacturer(manufacturerId);
//                for (int i = 0; i < psuList.size(); i++) {
//                    listIds.add(psuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showPsu(m, productList, psuList);
//            case 8:
//                List<Monitor> monitorList = serviceMon.findByManufacturer(manufacturerId);
//                for (int i = 0; i < monitorList.size(); i++) {
//                    listIds.add(monitorList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMonitor(m, productList, monitorList);
//            case 9:
//                List<Mouse> mouseList = serviceMouse.findByManufacturer(manufacturerId);
//                for (int i = 0; i < mouseList.size(); i++) {
//                    listIds.add(mouseList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMouse(m, productList, mouseList);
//            default: //case: 10 
//                List<Keyboard> keyboardList = serviceKeyboard.findByManufacturer(manufacturerId);
//                for (int i = 0; i < keyboardList.size(); i++) {
//                    listIds.add(keyboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showKeyboard(m, productList, keyboardList);
//        }
//    }
//
//    @GetMapping("/type/{typeId}/{category}")
//    public String getByType(Model m,
//            @PathVariable("typeId") Integer typeId,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        switch (category) {
//            case 1:
//                List<Tower> towerList = serviceTower.findByType(typeId);
//                for (int i = 0; i < towerList.size(); i++) {
//                    listIds.add(towerList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showTower(m, productList, towerList);
//            case 2: //SOCKET
//                List<Motherboard> motherboardList = serviceMother.findBySocket(typeId);
//                for (int i = 0; i < motherboardList.size(); i++) {
//                    listIds.add(motherboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMotherboard(m, productList, motherboardList);
//            case 3: //CHIP
//                List<Cpu> cpuList = serviceCpu.findByChip(typeId);
//                for (int i = 0; i < cpuList.size(); i++) {
//                    listIds.add(cpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showCpu(m, productList, cpuList);
//            case 4: //CHIPSET
//                List<Gpu> gpuList = serviceGpu.findByChipset(typeId);
//                for (int i = 0; i < gpuList.size(); i++) {
//                    listIds.add(gpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showGpu(m, productList, gpuList);
//            case 5:
//                List<Ram> ramList = serviceRam.findByType(typeId);
//                for (int i = 0; i < ramList.size(); i++) {
//                    listIds.add(ramList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showRam(m, productList, ramList);
//            case 6:
//                List<Storage> storageList = serviceStorage.findByType(typeId);
//                for (int i = 0; i < storageList.size(); i++) {
//                    listIds.add(storageList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showStorage(m, productList, storageList);
//            case 7: //CABLE-MANAGEMENT
//                List<Psu> psuList = servicePsu.findByCableManagement(typeId);
//                for (int i = 0; i < psuList.size(); i++) {
//                    listIds.add(psuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showPsu(m, productList, psuList);
//            case 8: //INCHES
//                List<Monitor> monitorList = serviceMon.findByInches(typeId);
//                for (int i = 0; i < monitorList.size(); i++) {
//                    listIds.add(monitorList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMonitor(m, productList, monitorList);
//            case 9:
//                List<Mouse> mouseList = serviceMouse.findByType(typeId);
//                for (int i = 0; i < mouseList.size(); i++) {
//                    listIds.add(mouseList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMouse(m, productList, mouseList);
//            default: //case: 10 
//                List<Keyboard> keyboardList = serviceKeyboard.findByType(typeId);
//                for (int i = 0; i < keyboardList.size(); i++) {
//                    listIds.add(keyboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showKeyboard(m, productList, keyboardList);
//        }
//    }
//
//    @GetMapping("/attribute1/{Id}/{category}")
//    public String getByAttribute1(Model m,
//            @PathVariable("Id") Integer Id,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        switch (category) {
//            case 2: //SIZE
//                List<Motherboard> motherboardList = serviceMother.findBySize(Id);
//                for (int i = 0; i < motherboardList.size(); i++) {
//                    listIds.add(motherboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMotherboard(m, productList, motherboardList);
//            case 3: //CORES
//                List<Cpu> cpuList = serviceCpu.findByCores(Id);
//                for (int i = 0; i < cpuList.size(); i++) {
//                    listIds.add(cpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showCpu(m, productList, cpuList);
//            case 4: //MEMORY
//                List<Gpu> gpuList = serviceGpu.findByMemory(Id);
//                for (int i = 0; i < gpuList.size(); i++) {
//                    listIds.add(gpuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showGpu(m, productList, gpuList);
//            case 5: //SIZE
//                List<Ram> ramList = serviceRam.findBySize(Id);
//                for (int i = 0; i < ramList.size(); i++) {
//                    listIds.add(ramList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showRam(m, productList, ramList);
//            case 6: //INCHES
//                List<Storage> storageList = serviceStorage.findByInches(Id);
//                for (int i = 0; i < storageList.size(); i++) {
//                    listIds.add(storageList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showStorage(m, productList, storageList);
//            case 7: //POWER-->WATT
//                List<Psu> psuList = servicePsu.findByWatt(Id);
//                for (int i = 0; i < psuList.size(); i++) {
//                    listIds.add(psuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showPsu(m, productList, psuList);
//        }
//        return null;
//    }
//
//    @GetMapping("/attribute2/{Id}/{category}")
//    public String getByAttribute2(Model m,
//            @PathVariable("Id") Integer Id,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        switch (category) {
//            case 2: //CHIPSET
//                List<Motherboard> motherboardList = serviceMother.findByChipset(Id);
//                for (int i = 0; i < motherboardList.size(); i++) {
//                    listIds.add(motherboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMotherboard(m, productList, motherboardList);
//            case 5: //FREQUENCY
//                List<Ram> ramList = serviceRam.findByFrequency(Id);
//                for (int i = 0; i < ramList.size(); i++) {
//                    listIds.add(ramList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showRam(m, productList, ramList);
//            case 7: //EFFICIENCY
//                List<Psu> psuList = servicePsu.findByEfficiency(Id);
//                for (int i = 0; i < psuList.size(); i++) {
//                    listIds.add(psuList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showPsu(m, productList, psuList);
//        }
//        return null;
//    }
//
//    @GetMapping("/attribute3/{Id}/{category}")
//    public String getByAttribute3(Model m,
//            @PathVariable("Id") Integer Id,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        switch (category) {
//            case 2: //PORT
//                List<Motherboard> motherboardList = serviceMother.findByPort(Id);
//                for (int i = 0; i < motherboardList.size(); i++) {
//                    listIds.add(motherboardList.get(i).getId());
//                }
//                productList = productSort(listIds, category);
//                return showMotherboard(m, productList, motherboardList);
//        }
//        return null;
//    }
//
//    @GetMapping("/doubleValue/{value}/{category}")
//    public String getByDoubleValue(Model m,
//            @PathVariable("value") BigDecimal value,
//            @PathVariable("category") Integer category) {
//        List<Integer> listIds = new ArrayList();
//        List<Product> productList = new ArrayList();
//        System.out.println("**************" + value);
//        {
//            switch (category) {
//                case 5: //VOLTAGE
//                    List<Ram> ramList = serviceRam.findByVoltage(value);
//                    for (int i = 0; i < ramList.size(); i++) {
//                        listIds.add(ramList.get(i).getId());
//                    }
//                    productList = productSort(listIds, category);
//                    return showRam(m, productList, ramList);
//            }
//        }
//        return null;
//    }
//
//    public List<Product> productSort(List<Integer> listIds, Integer category) {
//        List<Product> productsbyCategory = service.getByCategory(category);  //DEN XREIAZETAI????????????
//        List<Product> concreteProducts = new ArrayList();
//        for (int i = 0; i < productsbyCategory.size(); i++) {
//            if (listIds.contains(productsbyCategory.get(i).getId())) {
//                concreteProducts.add(productsbyCategory.get(i));
//            }
//        }
//        return concreteProducts;
//    }
//
//    public List<Tower> towerSort(List<Integer> listIds) {
//        List<Tower> allTowers = serviceTower.getAll();
//        List<Tower> concreteTowers = new ArrayList();
//        for (int i = 0; i < allTowers.size(); i++) {
//            if (listIds.contains(allTowers.get(i).getId())) {
//                concreteTowers.add(allTowers.get(i));
//            }
//        }
//        return concreteTowers;
//    }
//
//    public List<Motherboard> motherboardSort(List<Integer> listIds) {
//        List<Motherboard> allMotherboards = serviceMother.getAll();
//        List<Motherboard> concreteMotherboards = new ArrayList();
//        for (int i = 0; i < allMotherboards.size(); i++) {
//            if (listIds.contains(allMotherboards.get(i).getId())) {
//                concreteMotherboards.add(allMotherboards.get(i));
//            }
//        }
//        return concreteMotherboards;
//    }
//
//    public List<Cpu> cpuSort(List<Integer> listIds) {
//        List<Cpu> allCpu = serviceCpu.getAll();
//        List<Cpu> concreteCpu = new ArrayList();
//        for (int i = 0; i < allCpu.size(); i++) {
//            if (listIds.contains(allCpu.get(i).getId())) {
//                concreteCpu.add(allCpu.get(i));
//            }
//        }
//        return concreteCpu;
//    }
//
//    public List<Gpu> gpuSort(List<Integer> listIds) {
//        List<Gpu> allGpu = serviceGpu.getAll();
//        List<Gpu> concreteGpu = new ArrayList();
//        for (int i = 0; i < allGpu.size(); i++) {
//            if (listIds.contains(allGpu.get(i).getId())) {
//                concreteGpu.add(allGpu.get(i));
//            }
//        }
//        return concreteGpu;
//    }
//
//    public List<Ram> ramSort(List<Integer> listIds) {
//        List<Ram> allRam = serviceRam.getAll();
//        List<Ram> concreteRam = new ArrayList();
//        for (int i = 0; i < allRam.size(); i++) {
//            if (listIds.contains(allRam.get(i).getId())) {
//                concreteRam.add(allRam.get(i));
//            }
//        }
//        return concreteRam;
//    }
//
//    public List<Storage> storageSort(List<Integer> listIds) {
//        List<Storage> allStorage = serviceStorage.getAll();
//        List<Storage> concreteStorage = new ArrayList();
//        for (int i = 0; i < allStorage.size(); i++) {
//            if (listIds.contains(allStorage.get(i).getId())) {
//                concreteStorage.add(allStorage.get(i));
//            }
//        }
//        return concreteStorage;
//    }
//
//    public List<Psu> psuSort(List<Integer> listIds) {
//        List<Psu> allPsu = servicePsu.getAll();
//        List<Psu> concretePsu = new ArrayList();
//        for (int i = 0; i < allPsu.size(); i++) {
//            if (listIds.contains(allPsu.get(i).getId())) {
//                concretePsu.add(allPsu.get(i));
//            }
//        }
//        return concretePsu;
//    }
//
//    public List<Monitor> monitorSort(List<Integer> listIds) {
//        List<Monitor> allMonitors = serviceMon.getAll();
//        List<Monitor> concreteMonitors = new ArrayList();
//        for (int i = 0; i < allMonitors.size(); i++) {
//            if (listIds.contains(allMonitors.get(i).getId())) {
//                concreteMonitors.add(allMonitors.get(i));
//            }
//        }
//        return concreteMonitors;
//    }
//
//    public List<Mouse> mouseSort(List<Integer> listIds) {
//        List<Mouse> allMouse = serviceMouse.getAll();
//        List<Mouse> concreteMouse = new ArrayList();
//        for (int i = 0; i < allMouse.size(); i++) {
//            if (listIds.contains(allMouse.get(i).getId())) {
//                concreteMouse.add(allMouse.get(i));
//            }
//        }
//        return concreteMouse;
//    }
//
//    public List<Keyboard> keyboardSort(List<Integer> listIds) {
//        List<Keyboard> allKeyboard = serviceKeyboard.getAll();
//        List<Keyboard> concreteKeyboard = new ArrayList();
//        for (int i = 0; i < allKeyboard.size(); i++) {
//            if (listIds.contains(allKeyboard.get(i).getId())) {
//                concreteKeyboard.add(allKeyboard.get(i));
//            }
//        }
//        return concreteKeyboard;
//    }
//
//   
//    public static HttpSession getSession() {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        return attr.getRequest().getSession(true); // true == allow create
//    }
//
//    
//
//
//
//    //    //@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
////    @PostMapping("/create")//BindingResult must be exactly after Valid Object
////    public String createOrUpdateCustomer(@Valid Customer c, BindingResult result){
////        if(result.hasErrors()){
////            return "formCustomer";
////        }
////        service.createOrUpdateCustomer(c);
////        return "redirect:/product/list";
////    }
////    
////    @GetMapping("/delete")
////    public String deleteCustomer(@RequestParam("customerId") int id){
////        service.deleteCustomer(id);
////        return "redirect:/customer/list";
////    }
////    
////    @GetMapping("/update")
////    public String showUpdateForm(
////            @RequestParam("customerId") Integer id, Model model
////    ){
////        Customer c = service.findCustomerById(id);
////        model.addAttribute("customer", c);
////        return "formCustomer";
////    }
////    
////    @GetMapping("/search")
////    public String showCustomersByName(
////            @RequestParam("searchName") String searchName, Model model){
////        List<Customer> list = service.findCustomersByName(searchName);
////        model.addAttribute("listOfCustomer", list);
////        return "listCustomer";
////    }
//}
