package com.example.chris.play3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_layout)

        val editText = findViewById<EditText>(R.id.editText)

        editText.setText("The OK button is now constrained vertically, to remain lined up with the baseline of the Cancel button. A baseline constraint like this is different to constraining the top or bottom of the button. In this case, it's the text that lines up - so if the buttons are different heights, the layout looks better, because the text is aligned.\n" +
              	                "When it was constrained to the bottom edge of the layout, that allowed me to drag it up and down the screen - the only thing that changed was the distance from the bottom of the layout. Now, though, it's constrained to the Cancel button. If I try to drag it up or down, nothing happens.\n" +
                            "\n" +
                            "I can still drag it left or right, which just changes its margin from the Cancel button, but it can no longer be moved up or down.\n" +
                           "\n" +
                             "Even cooler, watch what happens when I drag the Cancel button up or down! When the Cancel button moves up or down, the OK button follows it, because of the vertical constraint.\n" +
                          "\n" +
             	                "Whether I'd constrained the top, bottom or baseline, that would still be the case, the OK button can't be moved vertically because it's constrained to the Cancel button, and when the Cancel button moves vertically the OK button follows.\n" +
                             "\n" +
         	                "Notice that when the OK button's selected, the Inspector shows a downwards arrow to indicate that there is a constraint, but you can't change the margin value like you can with the constraint to the right. Baseline constraints don't have margins.")
          }
}

