package controller

import model.Items
import scala.slick.driver.PostgresDriver.simple._

class ItemsTable(tag: Tag) extends Table[( Int,String,Double,Double,Int)](tag, "items") {
  def id = column[Int]("id")
  def name = column[String]("name")
  def tax = column[Double]("tax")
  def price = column[Double]("price")
  def quantity = column[Int]("quantity")
  def * = ( id,name,tax,price,quantity)
}

object ItemsController {

  val connectionUrl = "jdbc:postgresql://172.17.0.1:5432/postgres?user=postgres&password=12345"

  def calculateTax(item : Items) : Double = {

    (item.price * (item.tax/100))
  }

  def calculatePrice(item : Items) : Double = {

    (item.price + calculateTax(item)) * item.quantity
  }

  def insertItems(item : Items):Unit ={

    Database.forURL(connectionUrl, driver = "org.postgresql.Driver") withSession {
      implicit session =>
        val items = TableQuery[ItemsTable]

        val q= items.map(i => (i.name, i.tax, i.price,i.quantity))
          .insert(("Wine",10.0,100.0,80))

        println("Item added successfully!")
    }
  }


  def getItems() = {

    Database.forURL(connectionUrl, driver = "org.postgresql.Driver") withSession {
      implicit session =>
        val items = TableQuery[ItemsTable]

             val q= items.list foreach { row =>
                println(row +  " item" )
              }

      //        items.filter(_.name === 1).list foreach { row =>
      //          println(" "  )
      //        }
    }
  }
}
