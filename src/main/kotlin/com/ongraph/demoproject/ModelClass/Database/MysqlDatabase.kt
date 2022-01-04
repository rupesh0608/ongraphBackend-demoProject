package com.ongraph.demoproject.ModelClass.Database

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class MysqlDatabase {

    fun getConnection(): Connection {
        val connectionProps = Properties()
        connectionProps["user"] = "root"
        connectionProps["password"] = "qwer"
        lateinit var conn: Connection
        val dbname = "demoKotlinProject"
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/$dbname",
                    connectionProps)
        } catch (e: Exception) {
            println(e.message)
        }
        return conn
    }



}