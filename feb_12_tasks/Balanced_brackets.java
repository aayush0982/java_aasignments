//A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
//
//Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or }) of the exact same type. There are three types of matched pairs of brackets: [], {}, and ().
//
//A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, {[(])} is not balanced because the contents in between { and } are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a single, unbalanced closing square bracket, ].
//
//By this logic, we say a sequence of brackets is balanced if the following conditions are met:
//
//It contains no unmatched brackets.
//The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.
//Given  strings of brackets, determine whether each sequence of brackets is balanced. If a string is balanced, return YES. Otherwise, return NO.
//code problem above


package feb_12_tasks;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Balanced_brackets {

    public static String isBalanced(String inputString) {
        Stack<Character> stack = new Stack<>();

        for (int index = 0; index < inputString.length(); index++) {
            char currentChar = inputString.charAt(index);

            if (currentChar == '{' || currentChar == '(' || currentChar == '[') {
                stack.push(currentChar);
            } else {
                if (stack.isEmpty()) return "NO";

                char topBracket = stack.pop();

                if ((currentChar == '}' && topBracket != '{') ||
                    (currentChar == ')' && topBracket != '(') ||
                    (currentChar == ']' && topBracket != '[')) {
                    return "NO";
                }
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int testCases = Integer.parseInt(reader.readLine().trim());

        IntStream.range(0, testCases).forEach(i -> {
            try {
                String bracketString = reader.readLine();
                String result = isBalanced(bracketString);
                writer.write(result);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        reader.close();
        writer.close();
    }
}
