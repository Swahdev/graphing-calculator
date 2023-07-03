public class Evaluator {

    private ExpressionTokenizer tokenizer;
    String input;
    double x;

    public Evaluator(String anExpression, double theX) {
        input = anExpression;
        x = theX;
        tokenizer = new ExpressionTokenizer(anExpression);
    }


    public double Evaluate() throws NumberFormatException {
        int numOpenParenthesis = 0;
        int numCloseParenthesis = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') numOpenParenthesis++;
            if (input.charAt(i) == ')') numCloseParenthesis++;
        }
        if (numOpenParenthesis != numCloseParenthesis) {
            throw new NumberFormatException();
        }
        return getExpressionValue();
    }

    /**
     * Evaluation de l'expression
     */
    public double getExpressionValue() throws NumberFormatException {
        double value = getTermValue();
        boolean done = false;
        while (!done) {
            String next = tokenizer.peekToken();
            if ("+".equals(next) || "-".equals(next)) {
                tokenizer.nextToken(); //   "+"  ou "-"
                double value2 = getTermValue();
                if ("+".equals(next)) {
                    value = value + value2;
                } else {
                    value = value - value2;
                }
            } else {
                done = true;
            }
        }
        return value;
    }

    /*---------------------------------------------------------------------*/


    //Evaluation du prochain terme de l'expression.

    public double getTermValue() throws NumberFormatException {
        double value = getFactorValue();
        boolean done = false;
        while (!done) {
            String next = tokenizer.peekToken();
            if ("*".equals(next) || "/".equals(next) || "^".equals(next)) {
                tokenizer.nextToken();
                double value2 = getFactorValue();
                if ("*".equals(next)) {
                    value = value * value2;
                } else if ("/".equals(next)) {
                    value = value / value2;
                } else {
                    value=Math.pow(value,value2);
                }
            } else {
                done = true;
            }
        }
        return value;
    }

    /*---------------------------------------------------------------------*/

    /**
     * Evaluation du prochain facteur trouvé dans l'expression.
     */
    public double getFactorValue() throws NumberFormatException {
        double value;
        String next = tokenizer.peekToken();

        if ("(".equals(next)) {
            tokenizer.nextToken(); //  "("
            value = getExpressionValue();


            if (!")".equals(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        }
        //Sin
        else if ("sin".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "sin"
            tokenizer.nextToken(); //   "("
            value = Math.sin(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //   ")"
            }
        }
        //Cos
        else if ("cos".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "cos"
            tokenizer.nextToken(); //   "("
            value = Math.cos(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //   ")"
            }
        }
        //Tan
        else if ("tan".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "tan"
            tokenizer.nextToken(); //  "("
            value = Math.tan(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        }
        //Cosecant
        else if ("csc".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "csc"
            tokenizer.nextToken(); //   "("
            value = 1.0 / Math.sin(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //   ")"
            }
        }
        //Secant
        else if ("sec".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "sec"
            tokenizer.nextToken(); //  "("
            value = 1.0 / Math.cos(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        }
        //Cotangent
        else if ("cot".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "cot"
            tokenizer.nextToken(); //   "("
            value = 1.0 / Math.tan(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        }
        //ln
        else if ("ln".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); //   "("
            value = Math.log(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //   ")"
            }
        }
        //exp
        else if ("exp".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); //   "("
            value = Math.exp(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        //sqrt
        }  else if ("sqrt".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); //   "("
            value = Math.sqrt(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //   ")"
            }
        //cosh
        } else if ("cosh".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); //  "("
            value = Math.cosh(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); //  ")"
            }
        //asin
        }  else if ("asin".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); //"log"
            tokenizer.nextToken(); // "("
            value = Math.asin(getExpressionValue());

            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); // ")"
            }
        }
        //acos
        else if ("acos".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); // "("
            value = Math.acos(getExpressionValue());

            //On renvoie une exception si la parenthèse fermante ) n'apparait pas.
            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); // ")"
            }
        //atan
        }  else if ("atan".equalsIgnoreCase(next)) {
            tokenizer.nextToken(); // "log"
            tokenizer.nextToken(); // "("
            value = Math.atan(getExpressionValue());

            //On renvoie une exception si la parenthèse fermante ) n'apparait pas.
            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); // ")"
            }
        }
        //If racine
        else if ("sinh".equalsIgnoreCase(next)) {
            tokenizer.nextToken();
            tokenizer.nextToken(); // "("
            value = Math.sinh(getExpressionValue());

            //On renvoie une exception si la parenthèse fermante ) n'apparait pas.
            if (!")".equalsIgnoreCase(tokenizer.peekToken())) {
                throw new NumberFormatException();
            } else {
                tokenizer.nextToken(); // ")"
            }
        }

        //x
        else if ("x".equals(next) || "X".equals(next)) {
            tokenizer.nextToken(); // "x"
            value = x;
        }
        //tout autre chiffre
        else {
            value = Double.parseDouble(tokenizer.nextToken());
        }
        return value;
    }
}

