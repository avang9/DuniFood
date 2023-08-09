package id.avang.dunifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.avang.dunifood.databinding.ActivityMainBinding
import id.avang.dunifood.databinding.DialogAddNewItemBinding
import id.avang.dunifood.databinding.DialogDeleteItemBinding
import id.avang.dunifood.databinding.DialogUpdeataItemBinding
import id.avang.dunifood.room.Food

class MainActivity : AppCompatActivity() , FoodAdapter.FoodEvents {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val foodList = arrayListOf(
//        Food( txtSubject = "Hamburger" , txtPrice = "15" , txtDictance = "3" , txtCity = "Isfahan, Iran" , urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg" ,  numOfRating = 20 , rating = 4.5f ) ,
//        Food( txtSubject="Grilled fish" , txtPrice ="20" ,txtDictance = "2.1" ,txtCity = "Tehran, Iran" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg" , numOfRating =   10 , rating =  4f ) ,
//        Food( txtSubject="Lasania" ,txtPrice = "40" ,txtDictance = "1.4" ,txtCity = "Isfahan, Iran" , urlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg" , numOfRating =   30 , rating =  2f ) ,
//        Food( txtSubject="pizza" ,txtPrice = "10" ,txtDictance = "2.5" ,txtCity = "Zahedan, Iran" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg" , numOfRating =  80 , rating = 1.5f ) ,
//        Food( txtSubject="Sushi" , txtPrice ="20" ,txtDictance = "3.2" ,txtCity = "Mashhad, Iran" , urlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg" , numOfRating =  200 , rating =  3f ) ,
//        Food( txtSubject="Roasted Fish" ,txtPrice = "40" ,txtDictance = "3.7" ,txtCity = "Jolfa, Iran" , urlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg" , numOfRating =  50 , rating =  3.5f ) ,
//        Food( txtSubject="Fried chicken" ,txtPrice = "70" ,txtDictance = "3.5" ,txtCity = "NewYork, USA" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg" , numOfRating =  70 , rating =  2.5f ) ,
//        Food( txtSubject="Vegetable salad" ,txtPrice = "12" ,txtDictance = "3.6" ,txtCity = "Berlin, Germany" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg" , numOfRating =  40 , rating =  4.5f ) ,
//        Food( txtSubject="Grilled chicken" ,txtPrice = "10" ,txtDictance = "3.7" ,txtCity = "Beijing, China" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg" , numOfRating =  15 , rating =  5f ) ,
//        Food( txtSubject="Baryooni" , txtPrice ="16" ,txtDictance = "10" , txtCity ="Ilam, Iran" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg" , numOfRating =  28 , rating =  4.5f ) ,
//        Food( txtSubject="Ghorme Sabzi" ,txtPrice = "11.5" ,txtDictance = "7.5" , txtCity ="Karaj, Iran" , urlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg" , numOfRating =   27 , rating =  5f ) ,
//        Food( txtSubject="Rice" ,txtPrice = "12.5" , txtDictance ="2.4" , txtCity ="Shiraz, Iran" ,urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg" , numOfRating =  35 , rating =  2.5f ) ,
//        )

//        myAdapter = FoodAdapter(foodList.clone() as ArrayList<Food>, this ,this)
//        binding.recyclerMain.adapter = myAdapter
//        binding.recyclerMain.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
//
//        binding.addFood.setOnClickListener {
//
//            val dialog = AlertDialog.Builder(this).create()
//
//            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogBinding.root)
//            dialog.setCancelable(true)
//
//            dialog.show()
//            dialogBinding.btnDone.setOnClickListener {
//                if (dialogBinding.edtName.length()>0 &&
//                    dialogBinding.edtSity.length()>0 &&
//                    dialogBinding.edtPrice.length()>0 &&
//                    dialogBinding.edtDistanci.length()>0 ){
//
//                    val txtName = dialogBinding.edtName.text.toString()
//                    val txtCity = dialogBinding.edtSity.text.toString()
//                    val txtPrice = dialogBinding.edtPrice.text.toString()
//                    val txtdistance = dialogBinding.edtDistanci.text.toString()
//                    val txtNumberRating:Int = (1..80 ).random()
//                    val ratingBArStar:Float = (1..5).random().toFloat()
//                    val randomUrl = (0..11).random()
//                    val urlPic = foodList[randomUrl].urlImage
//                    val newFood = Food(txtName , txtCity , txtPrice , txtdistance ,urlPic , txtNumberRating , ratingBArStar )
//                    myAdapter.addFood(newFood)
//                    binding.recyclerMain.scrollToPosition(0)
//                    dialog.dismiss()
//                }else{
//                    Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید!", Toast.LENGTH_SHORT).show()
//                }
//
//
//            }
//
//        }
//        binding.edtFindFood.addTextChangedListener {edtTextinput ->
//            if (edtTextinput!!.isNotEmpty()){
//                val cilonList = foodList.clone() as ArrayList<Food>
//                val filterList = cilonList.filter { Food ->
//                 Food.txtSubject.contains(edtTextinput )
//                }
//                myAdapter.setData(ArrayList(filterList))
//            }else{
//                myAdapter.setData(foodList.clone() as ArrayList<Food>)
//            }
//        }
    }

    override fun onFoodClicked(food: Food, position:Int) {

//        val dialog = AlertDialog.Builder(this).create()
//        val updateDialogBinding = DialogUpdeataItemBinding.inflate(layoutInflater)
//        dialog.setView(updateDialogBinding.root)
//        updateDialogBinding.edtName.setText(food.txtSubject)
//        updateDialogBinding.edtSity.setText(food.txtCity)
//        updateDialogBinding.edtPrice.setText(food.txtPrice)
//        updateDialogBinding.edtDistanci.setText(food.txtDictance)
//        dialog.setCancelable(true)
//        dialog.show()
//
//        updateDialogBinding.btnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        updateDialogBinding.btnDone.setOnClickListener {
//            dialog.dismiss()
//            if (updateDialogBinding.edtName.length()>0 &&
//                updateDialogBinding.edtSity.length()>0 &&
//                updateDialogBinding.edtPrice.length()>0 &&
//                updateDialogBinding.edtDistanci.length()>0 ){
//
//                val txtName = updateDialogBinding.edtName.text.toString()
//                val txtCity = updateDialogBinding.edtSity.text.toString()
//                val txtPrice = updateDialogBinding.edtPrice.text.toString()
//                val txtdistance = updateDialogBinding.edtDistanci.text.toString()
//
//                val newFood = Food(txtName , txtCity , txtPrice ,txtdistance , food.urlImage , food.numOfRating , food.rating)
//
//                myAdapter .updataFood(newFood, position)
//            }else{
//                Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید!", Toast.LENGTH_SHORT).show()
//            }
//
//
//
//        }

    }

    override fun onFoodLongClicked(food: Food, pos: Int) {

//        val dialog = AlertDialog.Builder(this).create()
//        val dialogDeletBinding = DialogDeleteItemBinding.inflate(layoutInflater)
//        dialog.setView(dialogDeletBinding.root)
//        dialog.cancel()
//        dialog.show()
//
//        dialogDeletBinding.butno.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogDeletBinding.butYes.setOnClickListener {
//            dialog.dismiss()
//            myAdapter.removeFood(food , pos )
//        }

    }


}