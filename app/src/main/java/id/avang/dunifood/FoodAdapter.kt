package id.avang.dunifood

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.avang.dunifood.room.Food

class FoodAdapter(
    private val data: ArrayList<Food>,
    val context: Context,
    private val foodEvents: FoodEvents
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val txtSubject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val txtCity = itemView.findViewById<TextView>(R.id.item_txt_city)
        val txtprice = itemView.findViewById<TextView>(R.id.item_txt_price)
        val txtDictance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        val reting = itemView.findViewById<RatingBar>(R.id.item_rating_main)
        val ratingBar = itemView.findViewById<TextView>(R.id.txt_Rating_ray)

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            txtDictance.text = data[position].txtDictance + " miles from you"
            txtSubject.text = data[position].txtSubject
            txtprice.text = "$ " + data[position].txtPrice + " vip"
            txtCity.text = data[position].txtCity
            reting.rating = data[position].rating
            ratingBar.text = "(" + data[position].numOfRating.toString() + " Ratings )"

            Glide.with(context)
                .load(data[position].urlImage)
                .into(imgMain)


            itemView.setOnClickListener {
                foodEvents.onFoodClicked(data[adapterPosition] , adapterPosition)
            }

            itemView.setOnLongClickListener {
                foodEvents.onFoodLongClicked(data[adapterPosition], adapterPosition)

                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindData(position)

    }

    override fun getItemCount(): Int {
        return data.size

    }

    fun addFood(newFood: Food) {
        data.add(0, newFood)
        notifyItemInserted(0)

    }

    fun removeFood(oldFood: Food, oldPosition: Int) {

        data.remove(oldFood)
        notifyItemRemoved(oldPosition)
    }

    fun updataFood(newFood: Food, position: Int) {
        data.set(position , newFood)
        notifyItemChanged(position)
    }
    fun setData(newList :ArrayList<Food>){
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface FoodEvents {
        fun onFoodClicked(food: Food, adapterPosition: Int)
        fun onFoodLongClicked(food: Food, pos: Int)
    }

}


