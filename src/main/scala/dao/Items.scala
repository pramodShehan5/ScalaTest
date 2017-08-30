package dao

import scala.slick.driver.PostgresDriver.simple._

class Items(tag: Tag) extends Table[( Int,String,Double,Double,Int)](tag, "items") {
  def id = column[Int]("id")
  def name = column[String]("name")
  def tax = column[Double]("tax")
  def price = column[Double]("price")
  def quantity = column[Int]("quantity")
  def * = ( id,name,tax,price,quantity)
}
