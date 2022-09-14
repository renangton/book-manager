package com.book.manager.infrastructure.database.mapper.custom

import com.book.manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface BookWithRentalMapper {
  // 引数で渡しているselectStatementから事項するクエリを生成するための設定
  // type属性で指定しているSqlProviderAdapterクラスのselectメソッドを使用してクエリを生成
  @SelectProvider(type = SqlProviderAdapter::class, method = "select")
  // クエリの結果を受け取るオブジェクトのマッピングをする
  // id属性には任意の文字列を指定し、value属性にResultアノテーションを使い以下のしているする
  // column: テーブルのカラム名　property:戻り値のオブジェクトのプロパティ名
  // jdbcType: MyBatisで扱う際のJDBCタイプ id: 主キーの場合はtrue
  @Results(
    id = "BookWithRentalRecordResult", value = [
      Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      Result(column = "id", property = "title", jdbcType = JdbcType.VARCHAR),
      Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
      Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.DATE),
      Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
      Result(column = "rental_datetime", property = "rentalDatetime", jdbcType = JdbcType.TIMESTAMP),
      Result(column = "return_deadline", property = "returnDeadline", jdbcType = JdbcType.TIMESTAMP)
    ]
  )
  fun selectMany(selectStatement: SelectStatementProvider): List<BookWithRentalRecord>

  @SelectProvider(type = SqlProviderAdapter::class, method = "select")
  @ResultMap("BookWithRentalRecordResult")
  fun selectOne(selectStatement: SelectStatementProvider): BookWithRentalRecord
}
