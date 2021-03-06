package config

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.typesafe.config.ConfigFactory

import scala.util.Try
import slick.driver.PostgresDriver.api._

trait DbConf {
  val config = ConfigFactory.load("database.conf");

  lazy val dbName = Try(config.getString("psql.dbName")).getOrElse("postgres")
  lazy val psqlHost = Try(config.getString("psql.host")).getOrElse("172.17.0.1")
  lazy val psqlPort = Try(config.getString("psql.port")).getOrElse("5432")
  lazy val psqlUser = Try(config.getString("psql.user")).getOrElse("postgres")
  lazy val psqlPassword = Try(config.getString("psql.password")).getOrElse("12345")
  lazy val url = s"jdbc:postgresql://$psqlHost:$psqlPort/$dbName"
 // lazy val connectionUrl = "jdbc:postgresql://172.17.0.1:5432/postgres?user=postgres&password=12345"
  lazy val dbDriver="org.postgresql.Driver";

  val db = {
    val ds = new ComboPooledDataSource
    ds.setDriverClass("org.postgresql.Driver")
    ds.setUser(psqlUser)
    ds.setPassword(psqlPassword)
    ds.setJdbcUrl(url)
    ds.setMaxPoolSize(20)
    ds.setTestConnectionOnCheckin(true)
    ds.setPreferredTestQuery("SELECT 1")
    ds.setIdleConnectionTestPeriod(300)
    ds.setMaxIdleTimeExcessConnections(240)

    Database.forDataSource(ds)
  }
}
