package com.example.mytestapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var okBtn: Button
    private lateinit var tvResult: TextView
    private lateinit var editTextName: EditText

    // Use List<String> to store the original messages
    private val originalMessages: List<String> by lazy {
        resources.getStringArray(R.array.messages).toList()
    }

    // Use MutableList<String> to create a copy for modifications
    private var messages: MutableList<String> = mutableListOf()

    private val colors: IntArray by lazy {
        resources.getIntArray(R.array.colors)
    }

    private var currentMessageIndex = 0
    private var currentColorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        okBtn = findViewById(R.id.button1)
        tvResult = findViewById(R.id.textView)
        editTextName = findViewById(R.id.editTextName)

        editTextName.setOnClickListener {
            editTextName.text.clear()
        }

        editTextName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextName.text.clear()
            }
        }

        // Initialize the messages list as a copy of the original messages
        messages.addAll(originalMessages)

        okBtn.setOnClickListener {
            val name = editTextName.text.toString().trim()

            if (name.isNotEmpty()) {
                // Update the copy of messages with user input
                messages[0] = String.format(originalMessages[0], name)

                // Display the current message and increment the counter
                tvResult.text = messages[currentMessageIndex]
                tvResult.setTextColor(colors[currentColorIndex])

                currentMessageIndex = (currentMessageIndex + 1) % messages.size
                currentColorIndex = (currentColorIndex + 1) % colors.size
            }
        }
    }
}


