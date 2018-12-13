package academy.learnprogramming.sqlitetest

import android.content.ContentValues
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val database = baseContext.openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null)
        var sql = "CREATE TABLE contacts(_id INTEGER PRIMARY KEY NOT NULL, name TEXT, phone INTEGER, email TEXT)"
        Log.d(TAG, "onCreate: sql is $sql")
        database.execSQL(sql)
    //    database.rawQuery(sql,null,null)

        sql = "INSERT INTO contacts(name, phone, email VALUES('tim', 6546789, 'tim@email.com')"
        Log.d(TAG, "onCreate: sql is $sql")
       // database.execSQL(sql)

        val values = ContentValues().apply {
            put("name", "Fred")
            put("phone", 12345)
            put("email", "fred@nurk.com")
        }

        val generatedId = database.insert("contacts", null, values)
        Log.d(TAG, "onCreate: record added with id $generatedId")


        // val values = ContentValues()
        // values.put("name", "Fred")
        // values.put("phone", 12345)
        // values.put("email", "fred@nurk.com")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
