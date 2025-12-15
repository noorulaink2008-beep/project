import java.util.Scanner;

public class QuizGameApplication {

    static String[] questions = new String[20];
    static String[][] options = new String[20][4];
    static char[] correctAnswers = new char[20];
    static int questionCount = 0;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter numbers only!");
                input.nextLine();
                continue;
            }

            input.nextLine();

            if (choice == 1) {
                int role = login();

                if (role == 0)
                    adminMenu();
                else if (role == 1)
                    studentMenu();

            } else if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- LOGIN PORTAL ------------------
    public static void loginPortal() {
        System.out.println("\n--- Select Role ---");
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.print("Enter choice: ");

        int roleChoice;
        try {
            roleChoice = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Error: Please enter numbers only!");
            input.nextLine();
            return;
        }

        if (roleChoice == 1) {
            adminLogin();
        } else if (roleChoice == 2) {
            studentLogin();
        } else {
            System.out.println("Error: Invalid choice! Enter 1 for Admin or 2 for Student.");
        }
    }

    // ---------------- ADMIN LOGIN ------------------
    public static void adminLogin() {
        System.out.print("Enter Admin username: ");
        String username = input.nextLine();
        System.out.print("Enter Admin password: ");
        String password = input.nextLine();

        if (username.equals("admin") && password.equals("123")) {
            System.out.println("Login successful! Welcome Admin.");
            adminMenu();
        } else {
            System.out.println("Invalid Admin credentials!");
        }
    }

    // ---------------- STUDENT LOGIN ------------------
    public static void studentLogin() {
        System.out.print("Enter Student username: ");
        String username = input.nextLine();
        System.out.print("Enter Student password: ");
        String password = input.nextLine();

        if (username.equals("student") && password.equals("abc")) {
            System.out.println("Login successful! Welcome Student.");
            studentMenu();
        } else {
            System.out.println("Invalid Student credentials!");
        }
    }

    // ---------------- ADMIN MENU ------------------
    public static void adminMenu() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Question");
            System.out.println("2. View Questions");
            System.out.println("3. Update Question");
            System.out.println("4. Search Question");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Numbers only!");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    viewQuestions();
                    break;
                case 3:
                    updateQuestion();
                    break;
                case 4:
                    searchQuestion();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- STUDENT MENU ------------------
    public static void studentMenu() {
        while (true) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Start Quiz");
            System.out.println("2. View Last Result");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Numbers only!");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    startQuiz();
                    break;
                case 2:
                    viewLastResult();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- ADD QUESTION ------------------
    public static void addQuestion() {
        if (questionCount >= 20) {
            System.out.println("Cannot add more questions.");
            return;
        }
        System.out.print("Enter question: ");
        String q = input.nextLine();
        questions[questionCount] = q;

        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (char)('A' + i) + ": ");
            options[questionCount][i] = input.nextLine();
        }

        char correct;
        while (true) {
            System.out.print("Enter correct answer (A/B/C/D): ");
            String ans = input.nextLine().toUpperCase();
            if (ans.length() == 1 && "ABCD".contains(ans)) {
                correct = ans.charAt(0);
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
        correctAnswers[questionCount] = correct;
        questionCount++;
        System.out.println("Question added successfully!");
    }

    // ---------------- VIEW QUESTIONS ------------------
    public static void viewQuestions() {
        if (questionCount == 0) {
            System.out.println("No questions available.");
            return;
        }
        for (int i = 0; i < questionCount; i++) {
            System.out.println("\nQuestion " + (i+1) + ": " + questions[i]);
            for (int j = 0; j < 4; j++) {
                System.out.println(options[i][j]);
            }
            System.out.println("Correct Answer: " + correctAnswers[i]);
        }
    }

    // ---------------- UPDATE QUESTION ------------------
    public static void updateQuestion() {
        if (questionCount == 0) {
            System.out.println("No questions to update.");
            return;
        }
        System.out.print("Enter question number to update: ");
        int qno;
        try {
            qno = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
            return;
        }
        if (qno < 1 || qno > questionCount) {
            System.out.println("Invalid question number!");
            return;
        }
        int index = qno - 1;
        System.out.print("Enter new question text: ");
        questions[index] = input.nextLine();

        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (char)('A' + i) + ": ");
            options[index][i] = input.nextLine();
        }

        char correct;
        while (true) {
            System.out.print("Enter correct answer (A/B/C/D): ");
            String ans = input.nextLine().toUpperCase();
            if (ans.length() == 1 && "ABCD".contains(ans)) {
                correct = ans.charAt(0);
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
        correctAnswers[index] = correct;
        System.out.println("Question updated successfully!");
    }

    // ---------------- SEARCH QUESTION ------------------
    public static void searchQuestion() {
        if (questionCount == 0) {
            System.out.println("No questions to search.");
            return;
        }
        System.out.print("Enter keyword to search: ");
        String keyword = input.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < questionCount; i++) {
            if (questions[i].toLowerCase().contains(keyword)) {
                System.out.println("\nQuestion " + (i+1) + ": " + questions[i]);
                for (int j = 0; j < 4; j++) {
                    System.out.println(options[i][j]);
                }
                System.out.println("Correct Answer: " + correctAnswers[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No questions found with this keyword.");
        }
    }

    // ---------------- START QUIZ ------------------
    public static void startQuiz() {
        if (questionCount == 0) {
            System.out.println("No questions available.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            System.out.println("\nQuestion " + (i+1) + ": " + questions[i]);
            for (int j = 0; j < 4; j++) {
                System.out.println(options[i][j]);
            }

            char ans;
            while (true) {
                System.out.print("Enter your answer (A/B/C/D): ");
                String in = input.nextLine().toUpperCase();
                if (in.length() == 1 && "ABCD".contains(in)) {
                    ans = in.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            if (ans == correctAnswers[i]) {
                System.out.println("Correct Answer!");
                score++;
            } else {
                System.out.println("Wrong Answer! Correct answer: " + correctAnswers[i]);
            }
        }
        System.out.println("\nQuiz Completed! Your Score: " + score + " out of " + questionCount);
        lastScore = score;
    }

    // ---------------- VIEW LAST RESULT ------------------
    public static void viewLastResult() {
        if (lastScore == -1) {
            System.out.println("No quiz taken yet.");
        } else {
            System.out.println("Your last score: " + lastScore + " out of " + questionCount);
        }
    }

    // ---------------- ADD 10 SAMPLE QUESTIONS ------------------
    public static void addSampleQuestions() {
        String[] sampleQuestions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is 5 + 7?",
            "Which ocean is the largest?",
            "What is the chemical symbol for water?",
            "Who painted the Mona Lisa?",
            "What is the square root of 64?",
            "Which country is known for sushi?",
            "Who discovered gravity?"
        };

        String[][] sampleOptions = {
            {"A. Paris", "B. London", "C. Rome", "D. Berlin"},
            {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"},
            {"A. Shakespeare", "B. Dickens", "C. Tolkien", "D. Rowling"},
            {"A. 10", "B. 11", "C. 12", "D. 13"},
            {"A. Atlantic", "B. Pacific", "C. Indian", "D. Arctic"},
            {"A. H2O", "B. CO2", "C. O2", "D. NaCl"},
            {"A. Van Gogh", "B. Picasso", "C. Da Vinci", "D. Michelangelo"},
            {"A. 6", "B. 7", "C. 8", "D. 9"},
            {"A. China", "B. Japan", "C. Korea", "D. Thailand"},
            {"A. Newton", "B. Einstein", "C. Galileo", "D. Tesla"}
        };

        char[] sampleAnswers = {'A','B','A','C','B','A','C','C','B','A'};

        for (int i = 0; i < sampleQuestions.length; i++) {
            questions[questionCount] = sampleQuestions[i];
            for (int j = 0; j < 4; j++) {
                options[questionCount][j] = sampleOptions[i][j];
            }
            correctAnswers[questionCount] = sampleAnswers[i];
            questionCount++;
        }
    }
}
