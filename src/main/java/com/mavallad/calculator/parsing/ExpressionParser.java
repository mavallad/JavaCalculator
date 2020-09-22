package com.mavallad.calculator.parsing;

import com.mavallad.calculator.Expression;
import com.mavallad.calculator.ExpressionTree;
import com.mavallad.calculator.Literal;
import com.mavallad.calculator.Operation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

public class ExpressionParser {
    public Expression parse(String text) {
        Stack<String> tokensStack = createTokensStack(text);
        if (tokensStack.isEmpty()) {
            throw new InvalidExpressionException(text);
        }
        try {
            return buildExpressionFromStack(tokensStack);
        } catch (InvalidTokenException _ite) {
            throw new InvalidExpressionException(text);
        }
    }

    
    private Expression buildExpressionFromStack(Stack<String> tokensStack) throws InvalidTokenException {
        Expression rightOperand = createRightOperand(tokensStack);
        if (tokensStack.isEmpty()) {
            return rightOperand;
        } else {
            Operation operation = extractOperation(tokensStack);
            Expression leftOperand = buildExpressionFromStack(tokensStack);
            return new ExpressionTree(operation, leftOperand, rightOperand);
        }
    }

    /**
     * As we are left associative, the right operand is the first smallest expression
     * @param tokensStack
     * @return The smallest expression we can obtain from the top of the stack 
     */
    private Expression createRightOperand(Stack<String> tokensStack) throws InvalidTokenException {
        String topToken = tokensStack.pop();
        if (StringUtils.isNumeric(topToken)) {
            return new Literal(Integer.parseInt(topToken));
        } else if (isCloseParenthesis(topToken)) {
            Stack<String> betweenParenthesis = extractParenthesisSubStack(tokensStack);
            return buildExpressionFromStack(betweenParenthesis);
        } else {
            throw new InvalidTokenException();
        }
    }

    private boolean isCloseParenthesis(String text) {
        return text.equals(")");
    }

    private Stack<String> extractParenthesisSubStack(Stack<String> tokensStack) throws InvalidTokenException {
        List<String> elementsExtracted = new ArrayList<>();
        int parenthesisToMatch = 1;
        while (parenthesisToMatch > 0 && !tokensStack.isEmpty()) {
            String token = tokensStack.pop();
            if (token.equals("(")) {
                parenthesisToMatch--;
            } else if (token.equals(")")) {
                parenthesisToMatch++;
            }
            if (parenthesisToMatch != 0) {
                elementsExtracted.add(token);
            }
        }
        if (parenthesisToMatch != 0) {
            throw new InvalidTokenException();
        }
        Stack<String> stackBetweenParenthesis = new Stack();
        for (int i = elementsExtracted.size() - 1; i >= 0; i--) {
            stackBetweenParenthesis.add(elementsExtracted.get(i));
        }
        return stackBetweenParenthesis;
    }

    private Operation extractOperation(Stack<String> tokensStack) throws InvalidTokenException {
        String token = tokensStack.pop();
        return Operation.fromText(token)
                .orElseThrow(InvalidTokenException::new);
    }

    private Stack<String> createTokensStack(String text) {
        String[] tokens = text.split(" ");
        Stack<String> stackTokens = new Stack();
        Arrays.stream(tokens).filter(tk -> !tk.isEmpty()).forEach(tk -> stackTokens.push(tk));
        return stackTokens;
    }
}
