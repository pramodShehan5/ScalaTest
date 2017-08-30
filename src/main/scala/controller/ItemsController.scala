package controller

import config._
import scala.slick.driver.PostgresDriver.simple._

object ItemsController {
  def calculateTax(item: model.Items): Double = {
    (item.price * (item.tax / 100))
  }

  def calculatePrice(item: model.Items): Double = {
    (item.price + calculateTax(item)) * item.quantity
  }

  def insertItems(item: dao.Items): Unit = {
    Database.forURL(DatbaseConfig.connectionUrl, driver = DatbaseConfig.dbDriver) withSession {
      implicit session =>
        val items = TableQuery[dao.Items]

        val q = items.map(i => (i.name, i.tax, i.price, i.quantity))
          .insert(("Wine", 10.0, 100.0, 80))

        println("Item added successfully!")
    }
  }

  def getItems() = {

    Database.forURL(DatbaseConfig.connectionUrl, driver = DatbaseConfig.dbDriver) withSession {
      implicit session =>
        val items = TableQuery[dao.Items]

        val q = items.list foreach { row =>
          println(row + " item")
        }
    }
  }
}
