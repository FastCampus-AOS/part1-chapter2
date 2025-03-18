package fastcampus.aos.part1.part1_chapter2

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import fastcampus.aos.part1.part1_chapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var inputNumber: Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        val inputEditText = binding.inputEditText
        val outputTextView = binding.outputTextView
        val inputUnitTextView = binding.inputUnitTextView
        val outputUnitTextView = binding.outputUnitTextView
        val swapImageButton = binding.swapImageButton

        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()) 0 else text.toString().toInt()

            if (cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}