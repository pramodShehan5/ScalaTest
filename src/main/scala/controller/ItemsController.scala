package controller

import config.DbConf
import db.model.{Item, Items}

import scala.slick.driver.PostgresDriver.simple._
//import dao.Items  ItemDAO

object ItemsController extends DbConf {
  def calculateTax(item: Item): Double = {
    (item.price * (item.tax / 100))
  }

  def calculatePrice(item: Item): Double = {
    (item.price + calculateTax(item)) * item.quantity
  }

  def insertItems(item: Item): Unit = {
    Database.forURL(url, driver = dbDriver) withSession {
      implicit session =>
        val items = TableQuery[Items]

        val q = items.map(i => (i.name, i.tax, i.price, i.quantity))
          .insert(("Wine", 10.0, 100.0, 80))

        println("Item added successfully!")
    }
  }

  def getItems() = {

    Database.forURL(url, driver =dbDriver) withSession {
      implicit session =>
        val items = TableQuery[Items]

        val q = items.list foreach { row =>
          println(row + " item")
        }
    }
  }
}
