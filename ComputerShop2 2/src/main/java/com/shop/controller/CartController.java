
package com.shop.controller;

import com.shop.entities.CartItem;
import com.shop.entities.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    ProductService service;
    
    @Autowired
    CategoryService categoryService;
    
    
    
    
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int id
            ,@RequestParam("jspName") String jspName
            ,Model m) {
        Product p = service.findById(id);
        addItem(p);
       if(!jspName.equals("products") ){
           
           return "redirect:/"+jspName; 
       }
         return "redirect:/";
    }

    @GetMapping("/showCart")
    public String showCart(Model m) {
        int countCart = ProductController.getCountCart();
        m.addAttribute("countCart", countCart);
        List<CartItem> cart = getCart();
        m.addAttribute("cart", cart);
        double finalPrice = 0;
        for (int i = 0; i < cart.size(); i++) {
            finalPrice = finalPrice + (cart.get(i).getProduct().getPrice().doubleValue()-cart.get(i).getProduct().getSales().doubleValue()) * cart.get(i).getQuantity();
        }
        m.addAttribute("finalPrice", finalPrice);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("authentication "+ currentPrincipalName);
        
        return "cart";
    }

    public boolean addItem(Product p) {
        List<CartItem> cart = getCart();
        if (cart == null) {
            cart = new ArrayList();
            cart.add(new CartItem(p));
            getSession().setAttribute("cart", cart);

        } else {
            for (CartItem c : cart) {
                if (c.getProduct().getId() == p.getId()) {
                    c.addQuantity();
                    getSession().setAttribute("cart", cart);
                    return true;
                }
            }
            cart.add(new CartItem(p));
            getSession().setAttribute("cart", cart);
        }
        return true;
    }

    public int getCountCart() {
        List<CartItem> cart = getCart();
        int count = 0;
        for (CartItem c : cart) {
            count = count + c.getQuantity();
        }
        return count;
    }

    @GetMapping("/cartItem/delete")
    public String deleteCartItem(@RequestParam("productId") int id) {
        List<CartItem> cart = getCart();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                if (cart.get(i).getQuantity() > 1) {
                    cart.get(i).setQuantity(cart.get(i).getQuantity() - 1);
                } else {
                    cart.remove(i);
                }
            }
        }
        getSession().setAttribute("cart", cart);
        return "redirect:/cart/showCart";
    }
    
    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public List<CartItem> getCart() {
        List<CartItem> cartList = (List<CartItem>) getSession().getAttribute("cart");
        if (cartList == null) {
            cartList = new ArrayList<CartItem>();
            getSession().setAttribute("cart", cartList);
        }
        return cartList;
    }
}
