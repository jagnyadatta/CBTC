import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(int answer) {
        return correctAnswer == answer;
    }
}
