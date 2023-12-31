package id.avang.dunifood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val txtSubject :String ,
    val txtCity :String,
    val txtPrice :String ,
    val txtDictance :String ,
    val urlImage :String ,
    val numOfRating :Int ,
    val rating :Float
)
