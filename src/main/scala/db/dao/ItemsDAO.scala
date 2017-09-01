package db.dao

import config.DbConf
import db.model.{Item, Items}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.PostgresDriver.api._

import scala.concurrent.duration.Duration

object AccountsDAO extends TableQuery(new Items(_)) with DbConf {

  //val db = Database.forURL("jdbc:postgresql://172.17.0.1:5422/postgres?user=postgres&password=12345", driver="org.postgresql.Driver")
  //  db.createSession().database
  def findById(id: Long): Future[Option[Item]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def create(account: Item): Future[Item] = {
    db.run(this returning this.map(_.id) into ((acc, id) => acc.copy(id = id)) += account)
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }


}

object MainTest {
  def main(args: Array[String]): Unit = {
    //val db = Database.forURL("jdbc:postgresql://172.17.0.1:54362/postgres?user=postgres&password=12345", driver="org.postgresql.Driver")

    //println(db)
//    val f:Future[Item] = AccountsDAO.create(Item(6, "xfghxf",2,3,4))
    val f:Future[Option[Item]]  = AccountsDAO.findById(1)
    val item=Await.result(f, Duration.Inf)
    println(item)
  }
}

