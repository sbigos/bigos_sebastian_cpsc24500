package OOPTheoryQuizTool;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * This class reads the JSON file and returns an ArrayList of them
 */
public class QuestionReader {
    public ArrayList<Question> readFromJSON(String fname) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            FileReader reader = new FileReader(new File(fname));
            JSONParser parser = new JSONParser();
            JSONObject all = (JSONObject)parser.parse(reader);
            JSONArray arr = (JSONArray)all.get("questions");
            Iterator itr = arr.iterator();
            JSONObject questionObject;
            String question, choices, answer;
            while (itr.hasNext()) {
                questionObject = (JSONObject)itr.next();
                question = questionObject.get("question").toString();
                choices = questionObject.get("a").toString();
                answer = questionObject.get("answer").toString();
                questions.add(new Question(question,choices,answer));
            }
            reader.close();
            return questions;
        } catch (Exception ex) {
            return null;
        }
    }
}
