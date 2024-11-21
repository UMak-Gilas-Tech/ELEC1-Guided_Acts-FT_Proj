package com.elecone.guidedexercise;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;


// Handle the back button in the action bar
public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button press
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the current activity and go back to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
