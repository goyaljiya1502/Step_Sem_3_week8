package Week8;

import java.util.*;

abstract class Question {
    protected String correctAnswer;
    protected String studentAnswer;
    protected double points;

    Question(String correctAnswer, String studentAnswer, double points) {
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    abstract double getScore();
}

class MCQ extends Question {
    MCQ(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double getScore() {
        return studentAnswer.equals(correctAnswer) ? points : 0;
    }
}

class TF extends Question {
    TF(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double getScore() {
        return studentAnswer.equals(correctAnswer) ? points : 0;
    }
}

class Essay extends Question {
    Essay(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double getScore() {
        String answer = studentAnswer.toLowerCase();
        String[] keywords = correctAnswer.split(",");
        int count = 0;

        for (String keyword : keywords) {
            if (answer.contains(keyword.trim().toLowerCase()))
                count++;
        }

        if (count >= 2)
            return points * 0.75;
        else if (count == 1)
            return points * 0.50;
        else
            return 0;
    }
}

public class ExaminationGrader {
    static String[] parse(String line) {
        List<String> result = new ArrayList<>();
        int start = -1;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                if (start == -1)
                    start = i + 1;
                else {
                    result.add(line.substring(start, i));
                    start = -1;
                }
            }
        }

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String type = line.substring(0, line.indexOf(" "));
            String[] data = parse(line);

            String correctAnswer = data[1];
            String studentAnswer = data[2];
            double points = Double.parseDouble(data[3]);

            Question question;

            if (type.equals("MCQ"))
                question = new MCQ(correctAnswer, studentAnswer, points);
            else if (type.equals("TF"))
                question = new TF(correctAnswer, studentAnswer, points);
            else
                question = new Essay(correctAnswer, studentAnswer, points);

            double score = question.getScore();
            total += score;

            System.out.printf("%s: %.2f%n", type, score);
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
