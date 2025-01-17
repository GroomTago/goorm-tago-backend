package io.goormtago.controller.view;

import io.goormtago.entity.Bookmark;
import io.goormtago.jpa.repository.BookmarkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/view/bookmarks")
public class BookmarkViewController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @GetMapping
    public String getAllBookmarks(Model model) {
        model.addAttribute("bookmarks", bookmarkRepository.findAll());
        return "bookmark-list";
    }

    @PostMapping("/add")
    public String addBookmark(@ModelAttribute Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
        return "redirect:/api/view/bookmarks";
    }

    @PostMapping("/edit/{id}")
    public String updateBookmark(@PathVariable Long id, @ModelAttribute Bookmark bookmark) {
        bookmark.setId(id);
        bookmarkRepository.save(bookmark);
        return "redirect:/api/view/bookmarks";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteBookmark(@PathVariable Long id) {
        bookmarkRepository.deleteById(id);
        return "redirect:/api/view/bookmarks";
    }
}
