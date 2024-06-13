import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Exam {
    private List<Question> questions;
    private int[] userAnswers;
    private int timeLimit; // in seconds
    private boolean timeUp;

    public Exam(List<Question> questions, int timeLimit) {
        this.questions = questions;
        this.userAnswers = new int[questions.size()];
        this.timeLimit = timeLimit;
        this.timeUp = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("\nTime is up! Submitting the exam.");
                submit();
            }
        }, timeLimit * 1000);

        for (int i = 0; i < questions.size(); i++) {
            if (timeUp) {
                break;
            }

            Question question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.getQuestion());
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ": " + options[j]);
            }

            System.out.print("Your answer: ");
            while (!scanner.hasNextInt()) {
                scanner.next(); // clear the invalid input
                System.out.print("Please enter a valid option number: ");
            }
            userAnswers[i] = scanner.nextInt() - 1;
        }

        timer.cancel();
        if (!timeUp) {
            submit();
        }
        scanner.close();
    }

    private void submit() {
        int correctCount = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).isCorrectAnswer(userAnswers[i])) {
                correctCount++;
            }
        }
        System.out.println("You got " + correctCount + " out of " + questions.size() + " correct.");
    }
}
