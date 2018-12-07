package com.example.chris.play2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }
    }


    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_layout)

        val editText = findViewById<EditText>(R.id.editText)*/

        editText.setText("This is a text rendering error fix \n")

        editText.setText("Layout Designer rendering error\n" +
                "Section 5, Lecture 54\n" +
                "At the moment, November 2018, there's a bug in the Android Studio layout designer that causes problems when putting a large amount of text into a widget.\n" +
                "\n" +
                "Towards the end of the next video, we do that to show how to constrain a text widget that will contain a large amount of text.\n" +
                "\n" +
                "If you follow Tim's instructions, and paste the text into the EditText widget, all your widgets may disappear from the layout.\n" +
                "\n" +
                "If that happens, delete the text that you've just pasted into the widget's text attribute.\n" +
                "\n" +
                "Until Google fix the bug, the following will allow you to continue with the course.  It's not perfect, but at least you'll be able to carry on.\n" +
                "\n" +
                "If you get the problem, open up MainActivity.kt and add 2 lines to the onCreate method.\n" +
                "\n" +
                "I'll show the entire method here, so that you can copy and paste the method into your code\n" +
                "\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        setContentView(R.layout.note_layout)\n" +
                " \n" +
                "        val editText = findViewById<EditText>(R.id.editText)\n" +
                "        editText.setText(\"The OK button is now constrained vertically, to remain lined up with the baseline of the Cancel button. A baseline constraint like this is different to constraining the top or bottom of the button. In this case, it's the text that lines up - so if the buttons are different heights, the layout looks better, because the text is aligned.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"When it was constrained to the bottom edge of the layout, that allowed me to drag it up and down the screen - the only thing that changed was the distance from the bottom of the layout. Now, though, it's constrained to the Cancel button. If I try to drag it up or down, nothing happens.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"I can still drag it left or right, which just changes its margin from the Cancel button, but it can no longer be moved up or down.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Even cooler, watch what happens when I drag the Cancel button up or down! When the Cancel button moves up or down, the OK button follows it, because of the vertical constraint.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Whether I'd constrained the top, bottom or baseline, that would still be the case, the OK button can't be moved vertically because it's constrained to the Cancel button, and when the Cancel button moves vertically the OK button follows.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Notice that when the OK button's selected, the Inspector shows a downwards arrow to indicate that there is a constraint, but you can't change the margin value like you can with the constraint to the right. Baseline constraints don't have margins.\")\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "You won't see all that text in the Layout Designer, you'll need to run the app on your emulator to see it.\n" +
                "\n" +
                "It's frustrating, we know, but problems like this do happen when working in an environment that changes so frequently.")


    }
}
