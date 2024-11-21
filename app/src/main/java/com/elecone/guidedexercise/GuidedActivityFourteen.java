package com.elecone.guidedexercise;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GuidedActivityFourteen extends BaseActivity implements View.OnLongClickListener {

    // Declare UI components
    private ImageView batman, superman, ironman, wolverine, dropHere;
    private TextView status, heroName;
    private ConstraintLayout constraintLayout;
    private ClipData clipData;
    private String imageName;
    private Animation animateImage, animateText;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_activity_fourteen); // Ensure this layout exists

        // Setting up the ActionBar with a custom title and back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Elec1DragAndDrop");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        // Initialize all views and animations
        init();

        // Set drag listener for the drop area
        dropHere.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                // Handle drag events
                final View draggedView = (View) dragEvent.getLocalState();
                int action = dragEvent.getAction();
                Log.d("DragEvent", "Action: " + action); // Log the drag event for debugging

                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (dragEvent.getClipDescription() != null) {
                            imageName = dragEvent.getClipDescription().getLabel().toString();
                            status.setText("ACTION_DRAG_STARTED");
                            heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                            dropHere.setImageResource(R.drawable.hero);
                            dropHere.startAnimation(animateImage);
                        }
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        status.setText("ACTION_DRAG_ENTERED");
                        int id = getResources().getIdentifier(imageName, "drawable", getPackageName());
                        drawable = getResources().getDrawable(id, getTheme());
                        dropHere.setImageDrawable(drawable);
                        dropHere.startAnimation(animateImage);
                        heroName.setText(imageName);
                        heroName.startAnimation(animateText);
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        status.setText("ACTION_DRAG_EXITED");
                        dropHere.setImageResource(R.drawable.hero);
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        heroName.clearAnimation();
                        break;

                    case DragEvent.ACTION_DROP:
                        status.setText("ACTION_DROP");
                        heroName.setText(imageName);
                        dropHere.setImageDrawable(drawable);
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        status.setText("ACTION_DRAG_ENDED");
                        heroName.setText("DRAG AND DROP YOUR HERO HERE!");
                        draggedView.setVisibility(View.VISIBLE); // Ensure dragged view is visible again
                        dropHere.clearAnimation();
                        heroName.clearAnimation();
                        break;
                }
                return true;
            }
        });
    }

    private void init() {
        // Load animations
        animateText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_animation);
        animateImage = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);

        // Bind views
        batman = findViewById(R.id.ivBatman);
        superman = findViewById(R.id.ivSuperman);
        ironman = findViewById(R.id.ivIronman);
        wolverine = findViewById(R.id.ivWolverine);
        dropHere = findViewById(R.id.ivHero);
        status = findViewById(R.id.tvStatus);
        heroName = findViewById(R.id.tvNameOfHero);
        constraintLayout = findViewById(R.id.clDragNDrop);

        // Set tags for drag sources
        batman.setTag("batman");
        superman.setTag("superman");
        ironman.setTag("ironman");
        wolverine.setTag("wolverine");

        // Set long click listeners for drag sources
        batman.setOnLongClickListener(this);
        superman.setOnLongClickListener(this);
        ironman.setOnLongClickListener(this);
        wolverine.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        // Hide the view being dragged
        view.setVisibility(View.INVISIBLE);

        // Create a drag shadow and start the drag operation
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
        clipData = ClipData.newPlainText(view.getTag().toString(), null); // Use tag for clip data
        view.startDragAndDrop(clipData, dragShadowBuilder, view, 0);
        return true;
    }
}
