package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 算法实现类
 * 基于以下BCNF:
 * <pre>
 * E=T{(+|-)T}
 * T=F{(*|/)F}
 * F=(E)|(-E)|number
 * </pre>
 *
 * @author 李俊宏
 */
public class AlgorithmImpl implements Algorithm {
    //代表+-*/()数字
    enum TokenType {
        ADD, SUB, MUL, DIV, LEFT, RIGHT, NUMBER, END
    }

    //储存正则表达式
    private String expression;
    //解析的位置
    private int pos;
    //数字匹配
    private Pattern numberPatt = Pattern.compile("(\\d+\\.?\\d+)|(\\d+)");
    Matcher matcher;
    //单词
    private TokenType token;
    //单词对应的实际字符串
    private String tokenString;

    /**
     * 获取token
     *
     * @throws Exception
     */
    private void getToken() throws Exception {
        //读取到结尾
        if (pos >= expression.length()) {
            token = TokenType.END;
            return;
        }
        //读入token
        Character c = expression.charAt(pos);
        if (c <= '9' && c >= '0') {
            matcher.find(pos);
            tokenString = matcher.group();
            token = TokenType.NUMBER;
            pos = matcher.end();
        } else {
            switch (c) {
                case '+':
                    token = TokenType.ADD;
                    break;
                case '-':
                    token = TokenType.SUB;
                    break;
                case '*':
                    token = TokenType.MUL;
                    break;
                case '/':
                    token = TokenType.DIV;
                    break;
                case '(':
                    token = TokenType.LEFT;
                    break;
                case ')':
                    token = TokenType.RIGHT;
                    break;
                default:
                    throw new Exception("\"" + expression + "\"位置" + pos + ":未知符号");
            }
            tokenString = c.toString();
            ++pos;
        }
    }


    /**
     * 匹配token
     *
     * @param token 需要匹配的token
     * @throws Exception
     */
    private void match(TokenType token) throws Exception {
        if (this.token == token) {
            getToken();
        } else {
            throw new Exception("\"" + expression + "\"位置" + pos + ":应为" + token.toString());
        }
    }

    private String E() throws Exception {
        String res = T(), temp;
        while (token == TokenType.ADD || token == TokenType.SUB) {
            temp = tokenString;
            if (token == TokenType.ADD) match(TokenType.ADD);
            else match(TokenType.SUB);
            res += " " + T();
            res += " " + temp;
        }
        return res;
    }

    private String T() throws Exception {
        String res = F(), temp;
        while (token == TokenType.MUL || token == TokenType.DIV) {
            temp = tokenString;
            if (token == TokenType.MUL) match(TokenType.MUL);
            else match(TokenType.DIV);
            res += " " + F();
            res += " " + temp;
        }
        return res;
    }

    private String F() throws Exception {
        String res = null;
        if (token == TokenType.LEFT) {
            match(TokenType.LEFT);
            if (token == TokenType.SUB) {
                match(TokenType.SUB);
                res = E() + " !";
            } else {
                res = E();
            }
            match(TokenType.RIGHT);
        } else if (token == TokenType.NUMBER) {
            res = tokenString;
            match(TokenType.NUMBER);
        } else {
            throw new Exception("\"" + expression + "\"位置" + pos + ":应该为表达式或数字");
        }
        return res;
    }

    /**
     * @param prefixExp 可能含有空白字符的中缀表达式字符串
     * @return 转换后的后缀表达式
     * @throws Exception 转换时的异常
     */
    @Override
    public String translateToSuffixExp(String prefixExp) throws Exception {
        expression = prefixExp.replaceAll("\\s", "");
        pos = 0;
        matcher = numberPatt.matcher(expression);

        getToken();
        String res = E();
        match(TokenType.END);
        return res;
    }


}

