package com.ikresimir.gymbuddy

import com.ikresimir.gymbuddy.Daily_tracking.autoIncrement
import com.ikresimir.gymbuddy.Daily_tracking.references
import com.ikresimir.gymbuddy.Goal.references
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate


object User : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name",32)
    val sex: Column<String> = char("sex",1)
    val current_weight: Column<Double> = double("current_weight")
    val age: Column<Int> = integer("age")
    val height: Column<Double> = double("height")
    val bmi: Column<Double> = double("bmi")
    val username: Column<String> = varchar("username", 32)
    val password: Column<String> = varchar("password", 120)

    override val primaryKey = PrimaryKey(id, name="user_pk")
}

object Goal : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val desired_weight: Column<Double> = double("desired_weight")
    val goal_start: Column<LocalDate> = date("goal_start")
    val goal_end: Column<LocalDate> = date("goal_end")
    val activity_level: Column<Int> = integer("activity_level")
    val user_id: Column<Int> = integer("user_id").references(User.id)

    override val primaryKey = PrimaryKey(id, name="goal_pk")
}

object Daily_tracking : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val date: Column<LocalDate> = date("date")
    val weight: Column<Double> = double("weight")
    val calories: Column<Int> = integer("calories")
    val user_id: Column<Int> = integer("user_id").references(User.id)
}

object Training : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val date: Column<LocalDate> = date("date")
    val user_id: Column<Int> = integer("user_id").references(User.id)
}

