package com.ongraph.demoproject.ModelClass.Database

import org.springframework.boot.CommandLineRunner
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class PostgreSqlDatabase {
    fun getConnection(): Connection {
        val connectionProps = Properties()
        connectionProps["user"] = "uxvoqnllvdtdwb"
        connectionProps["password"] = "296f3f3135d9dbf980655446173c2db7b2b66ade18e8acaa5270e24d1fdec90f"
        lateinit var conn: Connection
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgres://uxvoqnllvdtdwb:296f3f3135d9dbf980655446173c2db7b2b66ade18e8acaa5270e24d1fdec90f@ec2-34-233-214-228.compute-1.amazonaws.com:5432/d1iqusjfccdvag",
                    connectionProps)
        } catch (e: Exception) {
            println(e.message)
        }
        return conn
    }
}