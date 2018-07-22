package com.G3.scala.jdbc

import scalikejdbc.{AutoSession, ConnectionPool, DBSession}

/*
DAO接口
 */
trait UserDAO {

  //给表添加元素
  def insert(id: Int, name: String, age : Int)(implicit s :DBSession = AutoSession)

  //根据Id删除一个表
  def deleteById(id : Int)(implicit s :DBSession = AutoSession)

  //根据Id查询一个表
  def selectAll()(implicit s :DBSession = AutoSession)


}
