package com.book.manager.presentasion.controller

import com.book.manager.application.service.AdminBookService
import com.book.manager.domain.model.Book
import com.book.manager.presentasion.form.RegisterBookRequest
import com.book.manager.presentasion.form.UpdateBookRequest
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class AdminBookController(
  private val adminBookService: AdminBookService
) {
  @PostMapping("/register")
  @CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
  fun register(@RequestBody request: RegisterBookRequest) {
    adminBookService.register(
      Book(
        request.id,
        request.title,
        request.author,
        request.releaseDate
      )
    )
  }

  @PutMapping("/update")
  @CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
  fun update(@RequestBody request: UpdateBookRequest) {
    adminBookService.update(
      request.id,
      request.title,
      request.author,
      request.releaseDate
    )
  }

  @DeleteMapping("/delete/{book_id}")
  @CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
  fun delete(@PathVariable("book_id") bookId: Long) {
    adminBookService.delete(bookId)
  }
}
