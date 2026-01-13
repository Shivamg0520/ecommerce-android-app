package com.example.ecommerceapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UploadProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDesc = findViewById<EditText>(R.id.etDescription)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etImageUrl = findViewById<EditText>(R.id.etImageUrl)
        val btnUpload = findViewById<Button>(R.id.btnUploadProduct)

        btnUpload.setOnClickListener {

            val title = etTitle.text.toString().trim()
            val desc = etDesc.text.toString().trim()
            val price = etPrice.text.toString().toLongOrNull()
            val imageUrl = etImageUrl.text.toString().trim()

            if (title.isEmpty() || desc.isEmpty() || price == null || imageUrl.isEmpty()) {
                Toast.makeText(this, "Fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val product = Product(
                title = title,
                description = desc,
                price = price,
                imageUrl = imageUrl,
                userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
            )

            FirebaseFirestore.getInstance()
                .collection("products")
                .add(product)
                .addOnSuccessListener {
                    Toast.makeText(this, "Product uploaded", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        } // ✅ FIX: Removed the extra closing brace that was here
    }
}
