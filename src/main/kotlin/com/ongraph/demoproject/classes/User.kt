package com.ongraph.demoproject.classes

import com.ongraph.demoproject.ModelClass.Database.MysqlDatabase
import com.ongraph.demoproject.ModelClass.LoginModel
import com.ongraph.demoproject.ModelClass.UserModel
import java.sql.ResultSet
import java.sql.Statement
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap


class User() {
    private val res = HashMap<String, Any>()
    private val mysql = MysqlDatabase()

    private lateinit var query: String
    private val conn = mysql.getConnection()
    private val stmt = conn.createStatement() as Statement
    fun register(user: UserModel): HashMap<String, Any> {
        try {
            val msg = checkFieldsForRegistration(user)
            if (msg == "") {
                val firstName = user.firstName.toString()
                val middleName = user.middleName.toString()
                val lastName = user.lastName.toString()
                val email = user.email.toString()
                val phoneNumber = user.phoneNumber.toString()
                val userName = user.userName.toString()
                val password = user.password.toString()
                val query = "insert into user (first_name,middle_name,last_name,email,phone_number,user_name,password) value('$firstName','$middleName','$lastName','$email','$phoneNumber','$userName','$password')"
                if (stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0) {
                    val data = HashMap<String, Any>()
                    val rs=stmt.generatedKeys
                    if (rs.next()) {
                        data["id"] = rs.getInt(1)
                    }
                    res["status"] = true
                    res["message"] = "The user has been added successfully."
                    res["data"] = data
                }
            } else {
                res["status"] = false
                res["message"] = msg
            }
        } catch (e: Exception) {
            res["status"] = false
            res["message"] = "ERROR:${e.message}"
        }
        return res
    }
    fun update(user: UserModel): HashMap<String, Any> {
        try {
            val firstName = user.firstName.toString()
            val middleName = user.middleName.toString()
            val lastName = user.lastName.toString()
            val email = user.email.toString()
            val phoneNumber = user.phoneNumber.toString()
            val userName = user.userName.toString()
            val password = user.password.toString()
            val timeStamp= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
            val query = "update user set first_name='$firstName',middle_name='$middleName',last_name='$lastName',email='$email',phone_number='$phoneNumber',user_name='$userName',password='$password',updated_at='$timeStamp' where id=${user.id}"
            if (stmt.executeUpdate(query) > 0) {
                res["status"] = true
                res["message"] = "User Details Updated Successfully."
            }
        } catch (e: Exception) {
            res["status"] = false
            res["message"] = "Error:${e.message}"
        }
        return res
    }
    fun delete(uid:Int): HashMap<String, Any> {
        try {
            val query = "delete from user where id=${uid}"
            if (stmt.executeUpdate(query) > 0) {
                res["status"] = true
                res["message"] = "User Deleted Successfully."
            }
        } catch (e: Exception) {
            res["status"] = false
            res["message"] = "Error:${e.message}"
        }
        return res
    }
    fun login(user:LoginModel): HashMap<String, Any> {
        try {
            val userName=user.userName
            val password=user.password
            val query = "select * from user where user_name='$userName' and password='$password'"
            val result=stmt.executeQuery(query)
            if (result.next()) {
                val data=HashMap<String,Any>()
                data["id"]=result.getString(1)
                data["firstName"]=result.getString(2)
                data["middleName"]=result.getString(3)
                data["lastName"]=result.getString(4)
                val firstName=result.getString(2)
                val middleName=result.getString(3)
                val lastName=result.getString(4)
                data["fullName"]="$firstName $middleName $lastName"
                data["email"]=result.getString(5)
                data["phoneNumber"]=result.getString(6)
                data["userName"]=result.getString(7)
                data["password"]=result.getString(8)
                res["status"] = true
                res["message"] = "Successfully Logged In"
                res["data"]=data
            }else{
                res["status"] = false
                res["message"] = "Invalid Username and Password"
            }
        } catch (e: Exception) {
            res["status"] = false
            res["message"] = "Error:${e.message}"
        }
        return res
    }

    private fun checkFieldsForRegistration(user: UserModel): String {
        var msg = ""
//        if (user.firstName == null) {
//            msg = "firstName is required"
//        } else if (user.lastName == null) {
//            msg = "lastName is required"
//        } else if (user.lastName == null) {
//            msg = "lastName is required"
//        } else if (user.email == null) {
//            msg = "email is required"
//        } else if (user.phoneNumber == null) {
//            msg = "phoneNumber is required"
//        } else if (user.userName == null) {
//            msg = "userName is required"
//        }else if(user.password==null) {
//            msg = "password is required"
//        }else if(user.confirmPassword==null){
//            msg = "confirmPassword is required"
//        }
        if (user.password != null && user.confirmPassword != null && !user.password.equals(user.confirmPassword)) {
            msg = "password and confirmPassword must be same"
        }
        if (user.email != null) {
            val mysql = MysqlDatabase()
            val conn = mysql.getConnection()
            val stmt = conn.createStatement() as Statement
            val email = user.email
            val query = "select email from user where email='$email'"
            val rs = stmt.executeQuery(query)
            if (rs.next()) {                            //if rs.next() returns false
                msg = "$email is already Registered"
            }
        }
        if (user.phoneNumber != null) {
            val mysql = MysqlDatabase()
            val conn = mysql.getConnection()
            val stmt = conn.createStatement() as Statement
            val phoneNumber = user.phoneNumber
            val query = "select phone_number from user where phone_number='$phoneNumber'"
            val rs = stmt.executeQuery(query)
            if (rs.next()) {                            //if rs.next() returns false
                msg = "$phoneNumber is already Registered"
            }
        }
        if (user.userName != null) {
            val mysql = MysqlDatabase()
            val conn = mysql.getConnection()
            val stmt = conn.createStatement() as Statement
            val userName = user.userName
            val query = "select user_name from user where user_name='$userName'"
            val rs = stmt.executeQuery(query)
            if (rs.next()) {                            //if rs.next() returns false
                msg = "$userName is already taken"
            }
        }
        return msg
    }
}