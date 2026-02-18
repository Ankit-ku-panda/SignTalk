package com.example.signtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView emojiPreview, resultBox;

    ArrayList<String> selectedEmojis = new ArrayList<>();

    EmojiInterpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // Link UI
        emojiPreview = findViewById(R.id.emojiPreview);
        resultBox = findViewById(R.id.resultBox);


        interpreter = new EmojiInterpreter();


        // Emoji buttons
        int[] emojiButtons = {

                R.id.e1,
                R.id.e2,
                R.id.e3,
                R.id.e4,
                R.id.e5,
                R.id.e6,
                R.id.e7,
                R.id.e8

        };


        for (int id : emojiButtons) {

            Button b = findViewById(id);

            b.setOnClickListener(v -> {

                String emoji = ((Button) v).getText().toString();

                selectedEmojis.add(emoji);

                emojiPreview.setText(
                        emojiPreview.getText().toString() + " " + emoji
                );

            });

        }


        // Translate Button
        Button translateBtn = findViewById(R.id.translateBtn);

        translateBtn.setOnClickListener(v -> {

            String sentence = interpreter.translate(selectedEmojis);

            resultBox.setText(sentence);

        });



        // Clear Button
        Button clearBtn = findViewById(R.id.clearBtn);

        clearBtn.setOnClickListener(v -> {

            selectedEmojis.clear();

            emojiPreview.setText("");

            resultBox.setText("Translation will appear here");

        });


        // Camera Button
        Button cameraBtn = findViewById(R.id.cameraBtn);

        cameraBtn.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this,
                            CameraActivity.class);

            startActivity(intent);

        });

    }

}
