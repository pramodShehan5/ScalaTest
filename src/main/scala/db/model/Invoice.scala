package db.model

import scala.slick.driver.PostgresDriver.simple._

case class Invoice( id:Int , listItems:List[Item])

