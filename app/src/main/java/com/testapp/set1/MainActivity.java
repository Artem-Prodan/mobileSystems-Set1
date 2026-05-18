package com.testapp.set1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // input field
    EditText guessInput;
    // button
    Button guessButton;
    // result message
    TextView resultText;
    // attempt counter
    TextView attemptText;

    // random number to guess
    int randomNumber;

    // current attempt count
    int attempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // connect XML elements to Java variables
        guessInput = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.guessButton);
        resultText = findViewById(R.id.resultText);
        attemptText = findViewById(R.id.attemptText);


        // start first game
        startNewGame();
        guessButton.setOnClickListener(v -> checkGuess());
    }

    /*
     * starts new game
     * generates random number
     * resets attempts
     */

    private void startNewGame() {

        Random random = new Random();
        // random number
        randomNumber = random.nextInt(10) + 1;
        // reset attempt count
        attempts = 0;
        // update UI
        attemptText.setText("Attempt: 1");
        // clear input field
        guessInput.setText("");
    }


    // user guesses handling
    private void checkGuess() {

        String input = guessInput.getText().toString().trim();

        // empty validation
        if(input.isEmpty()) {
            resultText.setText("Please enter an integer number.");
            return;
        }

        int guess;

        /*
         * convert text to integer
         * try/catch prevents crashes
         */
        try {
            guess = Integer.parseInt(input);
        }
        catch(NumberFormatException e) {
            resultText.setText("Please enter an integer number.");
            return;
        }


        // range validation
        if(guess < 1 || guess > 10) {
            resultText.setText("Number must be in range between 1 and 10.");
            return;
        }

        // increase attempts only for valid input
        attempts++;
        attemptText.setText("Attempt: " + (attempts + 1));

        // guess too small or too large
        if(guess < randomNumber) {
            resultText.setText("Value too small");
        }
        else if(guess > randomNumber) {
            resultText.setText("Value too large");
        }
        // correct guess
        else {
            // correct only on second attempt
            if(attempts == 2) {
                resultText.setText(
                    "Correct — achieved on the 2nd attempt"
                );
            }
            else {
                resultText.setText(
                    "Correct, but not on the 2nd attempt. Try again."
                );
                // Restart game
                startNewGame();
            }
        }
    }
}