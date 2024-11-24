package com.elecone.guidedexercise;

import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;

public class Credits extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Credits (Gilas Tech)");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setting title and subtitle text dynamically
        TextView titleTextView = findViewById(R.id.tv_title);
        titleTextView.setText("Credits");

        TextView subtitleTextView = findViewById(R.id.tv_subtitle);
        subtitleTextView.setText("Developers");

        // Dynamically add names to the list
        LinearLayout nameListContainer = findViewById(R.id.name_list_container);
        String[] names = {
                "• BALUYUT, SEBASTIAN A",
                "• CUIZON, MARY ANTOINETTE P",
                "• SIAZON, MARK ANGELO D",
                "• VILLAROSA, JAM EMMANUEL A",
                "• VILLENA, LANS CLARENCE"
        };

        // Add names to the LinearLayout container
        for (String name : names) {
            TextView nameTextView = new TextView(this);
            nameTextView.setText(name);
            nameTextView.setTextSize(20);
            nameTextView.setPadding(4, 4, 4, 4);
            nameTextView.setTextColor(ContextCompat.getColor(this, R.color.gray));
            nameListContainer.addView(nameTextView);
        }

        // Footer TextView
        TextView footerTextView = findViewById(R.id.tv_footer);
        footerTextView.setText("Thank you for using our app!");
    }
}
