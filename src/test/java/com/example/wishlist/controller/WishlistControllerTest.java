package com.example.wishlist.controller;

import com.example.wishlist.repository.JdbcWishlistRepository;
import com.example.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.security.test.context.support.WithMockUser;


@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;

    @MockBean
    private JdbcWishlistRepository wishlistJDBC;

    @Test
    @WithMockUser(username = "user1")
    void testGetWishlistMainPageByDefault() throws Exception {
        mockMvc.perform(get("/wishlist"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/wishlistOverview"));
    }

    @Test
    @WithMockUser(username = "user1")
    void testGetCreateWishlistPage() throws Exception {
        mockMvc.perform(get("/wishlist/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/createWishlist"));
    }

    @Test
    @WithMockUser(username = "user1")
    void testCreateWishlist() throws Exception {
        mockMvc.perform(post("/wishlist/create").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/wishlist/0"));
    }

    @Test
    @WithMockUser(username = "user1")
    void showPageForAddingWish() throws Exception {
        mockMvc.perform(get("/wishlist/1/addwish"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/addWish"));
    }


//    @Test
//    @WithMockUser(username = "user1")
//    void addWishToWishlist() throws Exception {
//        mockMvc.perform(post("/wishlist/1/addWish").with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/wishlist/1"));
//    }

    @Test
    @WithMockUser(username = "user1")
    void viewWishlistByName() throws Exception {
        mockMvc.perform(get("/wishlist/1/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/viewWishlist"));
    }

    @Test
    @WithMockUser(username = "user1")
    void deleteWishFromWishlistOnWishId() throws Exception {
        mockMvc.perform(get("/wishlist/1/wish/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/wishlist"));
    }

    @Test
    void deleteWishlistOnId() {
    }

//    @Test
//    @WithMockUser(username = "user1")
//    void createEditWishForm() throws Exception {
//        mockMvc.perform(get("/wishlist/1/wish/1/edit"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/wishlist/editWish"));
//    }

    @Test
    @WithMockUser(username = "user1")
    void editWish() throws Exception {
        mockMvc.perform(post("/wishlist/1/wish/1/edit").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/wishlist/1"));
    }
}