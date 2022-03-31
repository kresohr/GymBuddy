package com.ikresimir.gymbuddy.repository

import Databasez
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.*
import com.ikresimir.gymbuddy.model.GoalProfile
import com.ikresimir.gymbuddy.model.TrackingProfile
import com.ikresimir.gymbuddy.model.UserProfile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate

class Repository {
    var dbTest = Databasez
    var loginCombination = false
    var successfullyRegistered = false
    var profileSet = false
    var currentUser = ""
    var goalID = ""

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveUser(
        context: Context,
        userFirstName: String,
        userAge: Int,
        userHeight: Double,
        currentWeight: Double,
        userSex: String
    ) {
        getLoggedInUser(context)
        transaction {
            User.update({ User.username eq currentUser }) {
                it[name] = userFirstName
                it[sex] = userSex
                it[current_weight] = currentWeight
                it[age] = userAge
                it[height] = userHeight
            }
        }
        Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show()
        rememberProfile(context)
    }

    fun checkLogin(context: Context, username: String, password: String): Boolean {

        transaction {
            addLogger(StdOutSqlLogger)
            for (user in User.select {
                (User.username eq username)
            }) {
                val hashedPassword = user[User.password]
                val checkPassword = BCrypt.checkpw(password, hashedPassword)
                if (checkPassword) {
                    loginCombination = true
                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                    rememberUser(context, username)
                }
                if (!loginCombination) {
                    Toast.makeText(context, "Wrong login credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return loginCombination
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerUser(context: Context, newUsername: String, newPassword: String) {
        var usernameAlreadyExists = false
        transaction {
            addLogger(StdOutSqlLogger)
            for (user in User.select {
                (User.username eq newUsername)
            }) {
                usernameAlreadyExists = true
            }
            if (!usernameAlreadyExists) {
                val hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt())
                User.insert {
                    it[username] = newUsername
                    it[password] = hashedPassword
                }
                successfullyRegistered = true
                val registrationMessage =
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
            } else {
                val registrationMessage =
                    Toast.makeText(context, "Username already exists!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveStartGoal(context: Context, desiredWeight: Double, startDate: String, intensity: Int){
        var userId = 0
        val fullStartDate: List<String> = startDate.split("-")
        val startDateYear = fullStartDate[0].toInt()
        val startDateMonth = fullStartDate[1].toInt()
        val startDateDay = fullStartDate[2].toInt()

        getLoggedInUser(context)
        transaction {
            for (user in User.select{
                (User.username eq currentUser)
            }){
                userId = user[User.id]
            }
        }
        transaction {
            Goal.insert {
                it[desired_weight] = desiredWeight
                it[goal_start] = LocalDate.of(startDateYear,startDateMonth,startDateDay)
                it[activity_level] = intensity
                it[user_id] = userId
            }
        }
        transaction {
            for (user in Goal.select{(Goal.user_id eq userId and (Goal.goal_end.isNull()))
            }){
                goalID = user[Goal.id].toString()
            }
        }
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("GoalID", MODE_PRIVATE)
        var editor = sharedPreference.edit()
            editor.putString("GoalID",goalID)
            editor.commit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveEndGoal(context: Context, endDate: String){
        var userId: Int = 0
        val fullEndDate: List<String> = endDate.split("-")
        val endDateYear = fullEndDate[0].toInt()
        val endDateMonth = fullEndDate[1].toInt()
        val endDateDay = fullEndDate[2].toInt()

        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("GoalID", MODE_PRIVATE)
            goalID = sharedPreference.getString("GoalID", null).toString()

        if (goalID.isNotEmpty()){
            transaction {
                Goal.update({Goal.id eq goalID.toInt()}) {
                    it[goal_end] = LocalDate.of(endDateYear,endDateMonth,endDateDay)
                }
            }
        }
        else{
            getLoggedInUser(context)
            transaction {
                for (user in User.select{
                    (User.username eq currentUser)
                }){
                    userId = user[User.id]
                }
            }
            transaction {
                for (user in Goal.select{(Goal.user_id eq userId and (Goal.goal_end.isNull()))
                }){
                    goalID = user[Goal.id].toString()
                }
            }
            transaction {
                Goal.update({Goal.id eq goalID.toInt()}){
                    it[goal_end] = LocalDate.of(endDateYear,endDateMonth,endDateDay)
                }
            }
        }

    }

    fun rememberProfile(context: Context){
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("Profile", MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putBoolean("Profile",true)
        editor.commit()
    }

    fun checkIfProfileSet(context: Context){
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("Profile", MODE_PRIVATE)
        profileSet = sharedPreference.getBoolean("Profile", false)
    }

    fun rememberUser(context: Context, username: String) {
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("Current User", MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("username", username)
        editor.commit()
    }

    fun getLoggedInUser(context: Context) {
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("Current User", MODE_PRIVATE)
        var getUsername = sharedPreference.getString("username", null).toString()

        transaction {
            for (user in User.select { User.username eq getUsername })
            {
                currentUser = user[User.username]
            }
        }

    }

    fun logout(context: Context) {
        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("Current User", MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.clear().apply()
        //editor.remove("username")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserData(context: Context): MutableList<UserProfile> {
        getLoggedInUser(context)
        var firstName: String = ""
        var userAge: Int = 0
        var height: Double = 0.0
        var currentWeight: Double = 0.0
        var sex: String = ""
        var bmi: Double = 0.0
        var userProfile = mutableListOf<UserProfile>()

        transaction {
            for (user in User.select {
                (User.username eq currentUser)
            }){
                if (user[User.height]!=null){
                    firstName = user[User.name]
                    userAge = user[User.age]
                    height = user[User.height]
                    currentWeight = user[User.current_weight]
                    sex = user[User.sex]
                    bmi = user[User.bmi]
                    userProfile.add(UserProfile(firstName,userAge,height,currentWeight,sex,bmi))
                }
                println("NEMA PODATAKA!!!")
            }
        }
        return userProfile
    }

    fun getGoalInformation(context: Context): MutableList<GoalProfile>{
        getLoggedInUser(context)
        var desiredWeight = 0.0
        var activityLevel = 0
        var userId = 0
        var goalInformation = mutableListOf<GoalProfile>()
        var goalHasEnded = false

        val sharedPreference: SharedPreferences =
            context.getSharedPreferences("GoalID", MODE_PRIVATE)
        goalID = sharedPreference.getString("GoalID", null).toString()

        if(goalID.isNotEmpty()){
            transaction {
                for (goal in Goal.select { (Goal.id eq goalID.toInt()) }){
                    desiredWeight = goal[Goal.desired_weight]
                    activityLevel = goal[Goal.activity_level]
                    if (goal[Goal.goal_end]!=null){
                        goalHasEnded=true
                    }
                }
            }
            goalInformation.add(GoalProfile(desiredWeight,activityLevel,goalHasEnded))
        }
        else{
            transaction {
                for (user in User.select{
                    (User.username eq currentUser)
                }){
                    userId = user[User.id]
                }
            }
            transaction {
                for (goal in Goal.select{(Goal.user_id eq userId and (Goal.goal_end.isNull()))
                }){
                    desiredWeight = goal[Goal.desired_weight]
                    activityLevel = goal[Goal.activity_level]
                    if (goal[Goal.goal_end]!=null){
                        goalHasEnded=true
                    }
                }
            }
            goalInformation.add(GoalProfile(desiredWeight,activityLevel,goalHasEnded))
        }
        return goalInformation
    }

    fun checkIfGoalExists(context: Context): Boolean{
        getLoggedInUser(context)
        var userId = 0
        var found = false

        transaction {
            for (user in User.select { (User.username eq currentUser) }) {
                userId = user[User.id]
            }
        }
        transaction {
           for (user in Goal.select { (Goal.user_id eq userId and (Goal.goal_end.isNull())) }){
               found = true
            }
        }
        return found
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTrackingData(context: Context, currentDate: String, currentWeight: Double, todayCalories: Int){
        var userId = 0
        var entryExists = false
        val fullDate: List<String> = currentDate.split("-")
        val dateYear = fullDate[0].toInt()
        val dateMonth = fullDate[1].toInt()
        val dateDay = fullDate[2].toInt()

        getLoggedInUser(context)
        transaction {
            for (user in User.select{
                (User.username eq currentUser)
            }){
                userId = user[User.id]
            }
        }
        transaction {
            for (entry in Daily_tracking.select{
            (Daily_tracking.date eq LocalDate.of(dateYear,dateMonth,dateDay)) and (Daily_tracking.user_id eq userId)
        }){
                entryExists = true
                updateTrackingData(context,userId,currentDate,currentWeight,todayCalories)
            }
        }
        if (!entryExists){
            transaction {
                Daily_tracking.insert {
                    it[date] =  LocalDate.of(dateYear,dateMonth,dateDay)
                    it[weight] = currentWeight
                    it[calories] = todayCalories
                    it[user_id] = userId
                }
            }
            Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTrackingData(context: Context, userId:Int, currentDate: String, currentWeight: Double, todayCalories: Int){
        val fullDate: List<String> = currentDate.split("-")
        val dateYear = fullDate[0].toInt()
        val dateMonth = fullDate[1].toInt()
        val dateDay = fullDate[2].toInt()
        transaction {
            Daily_tracking.update({
                Daily_tracking.user_id eq userId and (Daily_tracking.date eq LocalDate.of(
                    dateYear,
                    dateMonth,
                    dateDay
                ))
            }) {
                it[weight] = currentWeight
                it[calories] = todayCalories
            }
        }
        Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()
    }

    fun getTrackingList(context: Context) : MutableList<TrackingProfile>{
        val listOfTrackedItems = mutableListOf<TrackingProfile>()
        var userId = 0
        getLoggedInUser(context)
        transaction {
            for (user in User.select{
                (User.username eq currentUser)
            }){
                userId = user[User.id]
            }
        }

        transaction {
            for (entry in Daily_tracking.select{
                Daily_tracking.user_id eq userId
            }){
                listOfTrackedItems.add(TrackingProfile(entry[Daily_tracking.weight],
                    entry[Daily_tracking.calories],entry[Daily_tracking.date].toString(),entry[Daily_tracking.user_id]))
            }
        }

        return listOfTrackedItems
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteFromTrackingList(itemDate: String, userId: Int, context: Context){
        val fullDate: List<String> = itemDate.split("-")
        val dateYear = fullDate[0].toInt()
        val dateMonth = fullDate[1].toInt()
        val dateDay = fullDate[2].toInt()

        transaction {
            Daily_tracking.deleteWhere { (Daily_tracking.date eq LocalDate.of(dateYear,dateMonth,dateDay) and (Daily_tracking.user_id eq userId)) }
        Toast.makeText(context,"Successfully deleted",Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun test(){
        /*
        Testing raw Import & Select SQL statements as Jetbrains Exposed doesn't support JSON.
         */

        //Test Values
        val date = LocalDate.of(2022,1,1)
        val userId = 31
        val json = Json.encodeToString(UserProfile("test",20,190.0,180.0,"m",22.0))
        transaction {
            exec("INSERT INTO training(user_id,date,exercise_list) VALUES($userId,'$date','$json')")
            val result = arrayListOf<String>()
            TransactionManager.current().exec("SELECT exercise_list FROM training WHERE user_id=31") { rs ->
                while (rs.next()) {
                    result += rs.getString("exercise_list")
                    //val test = Json.decodeFromString<UserProfile>(rs.getString("exercise_list"))
                }
                result
            }
            //val jsontest = Json.decodeFromString<UserProfile>(result[1])
            for (user in result){
                println(user)
            }
        }


    }

}


