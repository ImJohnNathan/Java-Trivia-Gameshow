//Library to read console?
import java.io.*;
import java.util.Random;

class game
{
    static String playerName;
    static String gametype;
    static String option;
    static int points = 0;

    public static void startSelection()
    {
        Console line = System.console();
        System.out.println("Your options are: play / stats / help / end");
        option = line.readLine();
        switch(option.toLowerCase())
        {
            case "play":
                System.out.println("We will play 3 rounds and see how you feel after!");
                startRound(3);
                break;
            case "stats":
                stats();
                break;
            case "help":
                help();
            case "end":
                System.out.println("\nClosing Java Trivia game. \n\nHope to see you soon!");
                System.exit(0);
            default:
                System.out.println("\nSorry, that was not an option. Check spelling and try again!\n");
                startSelection();
                break;
        }
    }

    public static void startRound(int roundLength)
    {
        Console c = System.console();
        question q = new question();

        System.out.println("What subject would you like to do? Type back to return to previous menu.");
        String subject = c.readLine("Math / Science / History / All / Back: ");
        System.out.println("\n");
        switch(subject.toLowerCase())
        {
            case "math":
                q.assignRandomQuestion("math");
                break;
            case "science":
                q.assignRandomQuestion("science");
                break;
            case "history":
                q.assignRandomQuestion("history");
                break;
            case "all":
                q.assignRandomQuestion("all");
                break;
            case "back":
                startSelection();
            default:
                System.out.println("Invalid subject! >:(\n Please try again.");
                startRound(roundLength);
        }
        for(int i = 0; i < roundLength; i++)
        {
            System.out.println(q.question);
            String userAnswer = c.readLine().toLowerCase();
            if(userAnswer.equals(q.answer.toLowerCase()))
            {
                System.out.println("Correct! You have been awarded 100 points!\n");
                points += 100;
            } else {
                System.out.println("Incorrect! The correct answer was: " + q.answer + "\n");
            }
            if(i == roundLength - 1)
            {
                System.out.println("That's a wrap! Would you like to play again? Y/N");
                String response = c.readLine();
                switch(response)
                {
                    case "Y":
                        System.out.println("Okay! Starting new round...\n");
                        startRound(3);
                    case "N":
                        System.out.println("Okay! Heading back to main menu...\n");
                        startSelection();
                    default:
                        System.out.println("No response! Stoic over here! Guess what, you get more rounds! :D\n\n");
                        startRound(3);
                }
                return;
            }
            q.assignRandomQuestion(subject);
        }

    }

    public static void help()
    {
        System.out.println("\nThis is a text-based trivia game! Simply select a category, and answer in the command prompt!\n --~~==+&0&+==~~--");
        System.out.println("Type play to enter 3 rounds of trivia.");
        System.out.println("Type stats to get your total amount of points.");
        System.out.println("Type help to get back to this help message.");
        System.out.println("Type end to terminate the program! :)\n");
        startSelection();
    }

    public static void stats()
    {
        Console line = System.console();
        System.out.println("\nWow, " + playerName + "! You've amassed a grand total of " + points + " points!\n");
        if(points == 0)
        {
            System.out.println("Damn, 0 points? May I suggest getting thy bands up?\n");
        }
        startSelection();
    }

    public static void main(String[] args)
    {
        Console line = System.console();
        playerName = line.readLine("Welcome to the Java Trivia Gameshow! Please enter your name here! ");
        System.out.println("Hello " + playerName + ", what would you like to do?");
        startSelection();

    }
}

class question
{
    String question = "hi im qqqqq";
    String answer = "hi im ans";

    String[] questionsAlreadyAnswered = {};

    String[] mathQuestions = {
        "What is the derivative of x^2?", 
        "What is the derivative of sin(x)?",
        "What is the name for the identity sin^2(x)+cos^2(x)=1?"
    };
    String[] mathAnswers = {
        "2x", 
        "cos(x)",
        "pythagorean identity",
     };

    String[] scienceQuestions = {
        "What is the most electronegative element?",
        "A system heats up it's surroundings. What process is it going through? (Endothermic/Exothermic)",
        "Group 1 of the period table is also known as the..."
    };

    String[] scienceAnswers = {
        "Fluorine",
        "Exothermic",
        "Alkali metals",
    };

    String[] historyQuestions = {
        "What German war plan mistakenly got Britain involved during WWI?",
        "What were the initials of the US president who implemented the New Deal?",
        "What was the largest land empire in history?",
    };

    String[] historyAnswers = {
        "Schlieffen plan",
        "FDR",
        "British Empire",
    };

    String[][] categoriesOfQuestions = {mathQuestions, scienceQuestions, historyQuestions};
    String[][] categoriesOfAnswers = {mathAnswers, scienceAnswers, historyAnswers};

    public boolean checkIfAlreadyAnswered(String answer)
    {
        boolean passedTest = false;
        for(int i = 0; i < questionsAlreadyAnswered.length; i++)
        {
            if(answer.equals(questionsAlreadyAnswered[i]))
            {
                passedTest = true;
            }
            passedTest = false;
        }
        return passedTest;
    }

    public void assignRandomQuestion(String subject)
    {
        int randomNumber = (int)Math.floor(Math.random() * (2) + 1);
        switch(subject.toLowerCase())
        {
            case "math":
                question = mathQuestions[randomNumber];
                answer = mathAnswers[randomNumber];
                break;
            case "science":
                question = scienceQuestions[randomNumber];
                answer = scienceAnswers[randomNumber];
                break;
            case "history":
                question = historyQuestions[randomNumber];
                answer = historyAnswers[randomNumber];
                break;
            case "all":
                int randCategories = (int)Math.floor(Math.random() * (2) + 1);
                question = (categoriesOfQuestions[randCategories])[randomNumber];
                answer = (categoriesOfAnswers[randCategories])[randomNumber];
                break;
            default:
                System.out.println("assignRandomQuestion function was not given valid parameter.");
                break;
        }
    }
}