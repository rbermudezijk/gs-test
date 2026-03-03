import java.util.ArrayList;

class WellBalancedParenthesis {
    public boolean isValid(String s) {
        ArrayList<Character> stack = new ArrayList<Character>();

        for (char symbol : s.toCharArray()) {
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                stack.add(symbol);
            } else {
                int size = stack.size();
                if (size == 0)
                    return false;
                char last = stack.get(size - 1);
                if ((symbol == ')' && last != '(')
                        || (symbol == ']' && last != '[')
                        || (symbol == '}' && last != '{')) {
                    return false;
                }
                stack.remove(size - 1);
            }
        }
        return stack.size() == 0;
    }
}