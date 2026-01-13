package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.adapter.ProductAdapter
import com.example.ecommerceapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnUpload).setOnClickListener {
            startActivity(Intent(this, UploadProductActivity::class.java))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productList = mutableListOf<Product>()
        val adapter = ProductAdapter(productList) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("id", product.id)
            intent.putExtra("title", product.title)
            intent.putExtra("description", product.description)
            intent.putExtra("price", product.price)
            intent.putExtra("imageUrl", product.imageUrl)
            intent.putExtra("userEmail", product.userEmail)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnFavorites).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        recyclerView.adapter = adapter

        FirebaseFirestore.getInstance()
            .collection("products")
            .get()
            .addOnSuccessListener {
                for (doc in it.documents) {
                    val product = doc.toObject(Product::class.java)
                    if (product != null) {
                        product.id = doc.id
                        productList.add(product)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        findViewById<Button>(R.id.btnUpload).setOnClickListener {
            startActivity(Intent(this, UploadProductActivity::class.java))
        }

    }
}
