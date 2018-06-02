package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

   TextView alsKnownAsTv;
   TextView ingridTv;
   TextView plcOfOriginTv;
   TextView descripTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        alsKnownAsTv = (TextView) findViewById(R.id.also_known_tv) ;
        ingridTv= (TextView)findViewById(R.id.ingredients_tv);
        plcOfOriginTv= (TextView)findViewById(R.id.origin_tv);
        descripTv=(TextView)findViewById(R.id.description_tv);
        List<String> alsKnAsList = new ArrayList<>();
        List<String> ingtsList = new ArrayList<>();
        alsKnAsList=sandwich.getAlsoKnownAs();
        for(String alsKn : alsKnAsList){
            alsKnownAsTv.setText(alsKn);
        }
        descripTv.setText(sandwich.getDescription());
        plcOfOriginTv.setText(sandwich.getPlaceOfOrigin());

        ingtsList= sandwich.getIngredients();
        for(String ingts: ingtsList ){
            ingridTv.setText(ingts);
        }
    }
}
