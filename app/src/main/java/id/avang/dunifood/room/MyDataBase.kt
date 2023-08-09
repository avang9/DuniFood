package id.avang.dunifood.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(version = 1 , entities =  [Food::class] , exportSchema = false)
abstract  class MyDataBase:RoomDatabase(){

    abstract val foodDao:Deo

    companion object{
        private  var dataBase:MyDataBase? = null
        fun getDatabase(context: Context):MyDataBase{
            if (dataBase == null){
                dataBase = Room.databaseBuilder(
                    context.applicationContext ,
                    MyDataBase::class.java ,
                    "MyDataBase.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dataBase!!
        }
    }
}