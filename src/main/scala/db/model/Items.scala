package db.model

import scala.slick.driver.PostgresDriver.simple._


case class Item(name:String  ,  price : Double , tax : Double , quantity : Int)

class Items(tag: Tag) extends Table[( Int,String,Double,Double,Int)](tag, "items") {
  def id = column[Int]("id")
  def name = column[String]("name")
  def tax = column[Double]("tax")
  def price = column[Double]("price")
  def quantity = column[Int]("quantity")
  def * = ( id,name,tax,price,quantity)
}
