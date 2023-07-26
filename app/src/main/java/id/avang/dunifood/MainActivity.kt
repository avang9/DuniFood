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

class MainActivity : AppCompatActivity() , FoodAdapter.FoodEvents {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val foodList = arrayListOf(
        Food( "Hamburger" , "15" , "3" , "Isfahan, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg" ,  20 , 4.5f ) ,
        Food( "Grilled fish" , "20" , "2.1" , "Tehran, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg" ,  10 , 4f ) ,
        Food( "Lasania" , "40" , "1.4" , "Isfahan, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg" ,  30 , 2f ) ,
        Food( "pizza" , "10" , "2.5" , "Zahedan, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg" ,  80 , 1.5f ) ,
        Food( "Sushi" , "20" , "3.2" , "Mashhad, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg" ,  200 , 3f ) ,
        Food( "Roasted Fish" , "40" , "3.7" , "Jolfa, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg" ,  50 , 3.5f ) ,
        Food( "Fried chicken" , "70" , "3.5" , "NewYork, USA" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg" ,  70 , 2.5f ) ,
        Food( "Vegetable salad" , "12" , "3.6" , "Berlin, Germany" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg" ,  40 , 4.5f ) ,
        Food( "Grilled chicken" , "10" , "3.7" , "Beijing, China" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg" ,  15 , 5f ) ,
        Food( "Baryooni" , "16" , "10" , "Ilam, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg" ,  28 , 4.5f ) ,
        Food( "Ghorme Sabzi" , "11.5" , "7.5" , "Karaj, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg" ,  27 , 5f ) ,
        Food( "Rice" , "12.5" , "2.4" , "Shiraz, Iran" , "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg" ,  35 , 2.5f ) ,
        )

        myAdapter = FoodAdapter(foodList.clone() as ArrayList<Food>, this ,this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)

        binding.addFood.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()

            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)

            dialog.show()
            dialogBinding.btnDone.setOnClickListener {
                if (dialogBinding.edtName.length()>0 &&
                    dialogBinding.edtSity.length()>0 &&
                    dialogBinding.edtPrice.length()>0 &&
                    dialogBinding.edtDistanci.length()>0 ){

                    val txtName = dialogBinding.edtName.text.toString()
                    val txtCity = dialogBinding.edtSity.text.toString()
                    val txtPrice = dialogBinding.edtPrice.text.toString()
                    val txtdistance = dialogBinding.edtDistanci.text.toString()
                    val txtNumberRating:Int = (1..80 ).random()
                    val ratingBArStar:Float = (1..5).random().toFloat()
                    val randomUrl = (0..11).random()
                    val urlPic = foodList[randomUrl].urlImage
                    val newFood = Food(txtName , txtCity , txtPrice , txtdistance ,urlPic , txtNumberRating , ratingBArStar )
                    myAdapter.addFood(newFood)
                    binding.recyclerMain.scrollToPosition(0)
                    dialog.dismiss()
                }else{
                    Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید!", Toast.LENGTH_SHORT).show()
                }


            }

        }
        binding.edtFindFood.addTextChangedListener {edtTextinput ->
            if (edtTextinput!!.isNotEmpty()){
                val cilonList = foodList.clone() as ArrayList<Food>
                val filterList = cilonList.filter { Food ->
                 Food.txtSubject.contains(edtTextinput )
                }
                myAdapter.setData(ArrayList(filterList))
            }else{
                myAdapter.setData(foodList.clone() as ArrayList<Food>)
            }
        }
    }

    override fun onFoodClicked(food: Food , position:Int) {

        val dialog = AlertDialog.Builder(this).create()
        val updateDialogBinding = DialogUpdeataItemBinding.inflate(layoutInflater)
        dialog.setView(updateDialogBinding.root)
        updateDialogBinding.edtName.setText(food.txtSubject)
        updateDialogBinding.edtSity.setText(food.txtCity)
        updateDialogBinding.edtPrice.setText(food.txtPrice)
        updateDialogBinding.edtDistanci.setText(food.txtDictance)
        dialog.setCancelable(true)
        dialog.show()

        updateDialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        updateDialogBinding.btnDone.setOnClickListener {
            dialog.dismiss()
            if (updateDialogBinding.edtName.length()>0 &&
                updateDialogBinding.edtSity.length()>0 &&
                updateDialogBinding.edtPrice.length()>0 &&
                updateDialogBinding.edtDistanci.length()>0 ){

                val txtName = updateDialogBinding.edtName.text.toString()
                val txtCity = updateDialogBinding.edtSity.text.toString()
                val txtPrice = updateDialogBinding.edtPrice.text.toString()
                val txtdistance = updateDialogBinding.edtDistanci.text.toString()

                val newFood = Food(txtName , txtCity , txtPrice ,txtdistance , food.urlImage , food.numOfRating , food.rating)

                myAdapter .updataFood(newFood, position)
            }else{
                Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید!", Toast.LENGTH_SHORT).show()
            }



        }

    }

    override fun onFoodLongClicked(food: Food, pos: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogDeletBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeletBinding.root)
        dialog.cancel()
        dialog.show()

        dialogDeletBinding.butno.setOnClickListener {
            dialog.dismiss()
        }
        dialogDeletBinding.butYes.setOnClickListener {
            dialog.dismiss()
            myAdapter.removeFood(food , pos )
        }

    }


}