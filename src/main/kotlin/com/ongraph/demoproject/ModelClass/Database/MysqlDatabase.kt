package com.ongraph.demoproject.ModelClass.Database

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class MysqlDatabase {

    fun getConnection(): Connection {
        val connectionProps = Properties()
        connectionProps["user"] = "admin"
        connectionProps["password"] = "qwertyuiop"
        lateinit var conn: Connection
        val dbname = "demokotlinproject"
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                    "jdbc:mysql://ongraph.coq4newt9li2.ap-south-1.rds.amazonaws.com:3306/$dbname",
                    connectionProps)
        } catch (e: Exception) {
            println(e.message)
        }
        return conn
    }



}