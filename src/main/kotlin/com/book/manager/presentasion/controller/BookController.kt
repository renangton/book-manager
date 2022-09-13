package com.book.manager.presentasion.controller

import com.book.manager.application.service.BookService
import com.book.manager.presentasion.form.BookInfo
import com.book.manager.presentasion.form.GetBookListResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class BookController(
  private val bookService: BookService
) {
  @GetMapping("/list")
  fun getList(): GetBookListResponse {
    val bookList = bookService.getList().map {
      BookInfo(it)
    }
    return GetBookListResponse(bookList)
  }
}
