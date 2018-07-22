package com.G3.scala.jdbc

import scalikejdbc._

/**
  * DAO实现类
  */
object UserDAOImpl extends UserDAO {

  Class.forName("com.mysql.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost:3306/test", "root", "12580")

  override def insert(id: Int, name: String, age : Int)(implicit s :DBSession = AutoSession) : Unit = {
    sql"INSERT INTO USER  VALUE (${id},${name},${age})".update.apply()
  }

  override def deleteById(id : Int)(implicit s :DBSession = AutoSession) : Unit = {
    sql"DELETE FROM USER WHERE id = ${id}".update().apply()
  }

  override  def selectAll()(implicit s :DBSession = AutoSession) : Unit = {
    print(sql"SELECT * FROM user".map(rs => (rs.int("id"),rs.string("name"),rs.int("age"))).list().apply())
    }

}
