package com.example.wishlist.controller;

import com.example.wishlist.dto.WishlistFormDto;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("")
    public String wishlistMainPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Wishlist> wishlistList = wishlistService.getAllWishlists(username);
        model.addAttribute("wishlists", wishlistList);
        return "wishlist/wishlistOverview";
    }

    @GetMapping("/create")
    public String createWishlist(Model model) {
        WishlistFormDto emptyWishlist = new WishlistFormDto("wishlistName", "pictureLink");
        model.addAttribute("wishlist", emptyWishlist);

        return "wishlist/createWishlist";
    }

    @PostMapping("/create")
    public String createWishlist(@ModelAttribute WishlistFormDto wishlistFormDto, Authentication authentication) {
        String username = authentication.getName();
        String wishlistName = wishlistFormDto.wishlistName();
        String pictureLink = wishlistFormDto.pictureLink();
        long wishlistId = wishlistService.createWishlist(wishlistName, pictureLink, username);

        return "redirect:/wishlist/" + wishlistId;
    }

//    @GetMapping("/{wishlistId}/addwish")
//    public String showPageForAddingWish(Model model, @PathVariable long wishlistId, Authentication authentication) {
//        Wish newWish = new Wish();
//        String username = authentication.getName();
//        String wishlistName = wishlistService.getWishlistNameFromWishlistId(username, wishlistId);
//
//        model.addAttribute("addWish", newWish);
//        model.addAttribute("wishlistName", wishlistName);
//        model.addAttribute("wishlistId", wishlistId);
//        return "wishlist/addWish";
//    }

//    @PostMapping("/{wishlistId}/addwish") //wishlistId bliver automatisk på wishlistId attributten i Wish-klassen, da det hedder det samme.
//    public String addWishToWishlist(@ModelAttribute Wish newWish) {
//        wishlistService.addWish(newWish);
//
//        return "redirect:/wishlist";
//    }


    @GetMapping("/{wishlistId}")
    public String viewWishlistById(@PathVariable long wishlistId, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);

        model.addAttribute("wishes", wishlist.getWishes());
        model.addAttribute("wishlistName", wishlist.getName());

        return "wishlist/viewWishlist";
    }
//    @GetMapping("/{wishlistId}/wish/{wishId}/delete")
//    public String deleteWishFromWishlistOnWishId(@PathVariable long wishId) {
//        wishlistService.deleteWish(wishId);
//
//        return "redirect:/wishlist";
//    }
    @GetMapping("/{wishlistId}/delete")
    public String deleteWishlistOnId(@PathVariable long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);

        return "redirect:/wishlist";
    }

    @GetMapping("/{wishlistId}/deleteWishes")
    public String deleteAllWishesOnWishlistId(@PathVariable long wishlistId) {
        wishlistService.deleteAllWishes(wishlistId);

        return "redirect:/wishlist";
    }

    @GetMapping("/{wishlistId}/edit")
    public String createEditWishlistForm(Model model, @PathVariable long wishlistId) {
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        model.addAttribute("wishlistToEdit", wishlist);

        return "wishlist/editWishlist";
    }

    @PostMapping("/{wishlistId}/edit")
    public String editWish(@ModelAttribute Wishlist editedWishlist, @PathVariable long wishlistId) {
        wishlistService.editWishlist(editedWishlist);

        return "redirect:/wishlist";
    }

//    @GetMapping("/{wishlistId}/wish/{wishId}/edit")
//    public String createEditWishForm(Model model, @PathVariable long wishId) {
//        Wish wish = wishlistService.getWishFromWishId(wishId);
//        System.out.println("WishID " + wish.getWishId());
//        model.addAttribute("wishToEdit", wish);
//
//        return "/wishlist/editWish";
//    }

//    @PostMapping("/{wishlistId}/wish/{wishId}/edit")
//    public String editWish(@ModelAttribute Wish editedWish, @PathVariable long wishlistId) {
//        wishlistService.editWish(editedWish);
//
//        return "redirect:/wishlist/" + wishlistId;
//    }
}