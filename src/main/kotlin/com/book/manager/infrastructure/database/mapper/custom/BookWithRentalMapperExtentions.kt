package com.book.manager.infrastructure.database.mapper.custom

import com.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book
import com.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.author
import com.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.id
import com.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.releaseDate
import com.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.title
import com.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental
import com.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.rentalDatetime
import com.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.returnDeadline
import com.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.userId
import com.book.manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.mybatis.dynamic.sql.SqlBuilder.equalTo
import org.mybatis.dynamic.sql.SqlBuilder.select
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.from

private val columnList = listOf(
  id,
  title,
  author,
  releaseDate,
  userId,
  rentalDatetime,
  returnDeadline
)

// SELECT b.id, b.title, b.author, b.release_date, r.user_id, r.rental_datetime, r.return_deadline
// FROM book b
// LEFT JOIN rental r
// ON b.id = r.book_id
fun BookWithRentalMapper.select(): List<BookWithRentalRecord> {
  val selectStatement = select(columnList).from(Book, "b") {
    leftJoin(Rental, "r") {
      on(id, equalTo(Rental.bookId))
    }
  }
  return selectMany(selectStatement)
}
