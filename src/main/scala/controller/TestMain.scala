package controller

import controller.InvoiceController.{getTotal, getTotalOfInvoice}
import controller.ItemsController._
import controller._
import model.{Invoice, Items}

object TestMain {

  def main(args: Array[String]): Unit = {
    var invoice = new Invoice(1,List(new Items("item1",10,10,2),new Items("item2",10,10,5)))
    println(getTotalOfInvoice(invoice))
    var invoice1 = new Invoice(1,List(new Items("item2",10,10,4),new Items("item3",10,10,3)))
    println(getTotalOfInvoice(invoice1))
    val listInvoice = List(invoice,invoice1)
    println("Total of all invoices : " + getTotal(listInvoice))
   // insertItems(new Items("item2",10,10,5))

    getItems()
  }
}
