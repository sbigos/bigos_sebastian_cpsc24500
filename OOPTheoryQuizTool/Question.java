package OOPTheoryQuizTool;
/**
 * This is the model class that holds the data we care about.
 * Includes:
 * -the question
 * -the choices
 * -the answer
 * -toString function to print the data as a string
 */
public class Question {
    private String question;
    private String choices;
    private String answer;
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getChoices() {
        return choices;
    }
    public void setChoices(String choices) {
        this.choices = choices;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Question() {
        question = "none";
        choices = "none";
        answer = "none";
    }
    public Question(String question, String choices, String answer) {
        setQuestion(question);
        setChoices(choices);
        setAnswer(answer);
    }
    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", question, choices, answer);
    }
}
