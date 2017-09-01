package db.model

import config.DbConf
import slick.lifted.TableQuery

object DbFactory extends DbConf{

  def init() = {
    val items = TableQuery[Items]
  }
}
