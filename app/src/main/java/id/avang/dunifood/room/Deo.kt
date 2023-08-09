package id.avang.dunifood.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Deo {
    @Insert
    fun insertFood (food: Food)

    @Update
    fun updateFood (food:Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM FOOD")
    fun delereAllFood(food: Food)

    @Query ("SELECT *FROM FOOD")
    fun getAllFood() :List<Food>


    @Query("SELECT * FROM FOOD WHERE txtSubject LIKE '%' || :searching || '%' ")
    fun searchFood(searching :String):List<Food>
}