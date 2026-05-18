The application was crashing during input validations until I used try/catch blocks. 
I was able to handle attempts counter using if/else blocks to prevent counter increasing during incorrect input.
Also, at first i had some troubles with reset logic after guessing the number on wrong attempt.
I solved it by creating a separate startNewGame method which resets both random number and counter.
