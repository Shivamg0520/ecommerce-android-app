package com.example.ecommerceapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.ecommerceapp.db.AppDatabase
import com.example.ecommerceapp.db.FavoriteEntity
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val price = intent.getLongExtra("price", 0)
        val imageUrl = intent.getStringExtra("imageUrl")

        val ivProductImage = findViewById<ImageView>(R.id.ivProductImage)
        val tvProductTitle = findViewById<TextView>(R.id.tvProductTitle)
        val tvProductDescription = findViewById<TextView>(R.id.tvProductDescription)
        val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)

        tvProductTitle.text = title
        tvProductDescription.text = description
        tvProductPrice.text = "₹ $price"

        Glide.with(this)
            .load(imageUrl)
            .into(ivProductImage)

        val favBtn = findViewById<Button>(R.id.btnFavorite)

        favBtn.setOnClickListener {
            val db = AppDatabase.getInstance(this)
            val fav = FavoriteEntity(
                id = id ?: "",
                title = title ?: "",
                price = price,
                imageUrl = imageUrl ?: ""
            )

            lifecycleScope.launch {
                db.favoriteDao().insert(fav)
                runOnUiThread {
                    Toast.makeText(this@ProductDetailsActivity,
                        "Added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
