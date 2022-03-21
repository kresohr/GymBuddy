package com.ikresimir.gymbuddy

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

