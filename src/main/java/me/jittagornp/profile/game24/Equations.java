/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile.game24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jittagornp
 */
public class Equations {

    private final Integer answer;
    private String number;
    private ScriptEngine engine;
    private List<String> operators;

    private Equations(int answer) {
        this.answer = answer;
    }

    public static Equations withAnswer(int answer) {
        return new Equations(answer);
    }

    public Equations andNumbers(String number) {
        this.number = number;
        return this;
    }

    public Equations andOperators(String... operators) {
        this.operators = Arrays.asList(operators);
        return this;
    }

    private List<String> getOperators() {
        if (operators == null) {
            operators = Arrays.asList(
                    "+",
                    "-",
                    "*",
                    "/"
            );
        }

        return operators;
    }

    private ScriptEngine getEngine() {
        if (engine == null) {
            ScriptEngineManager manager = new ScriptEngineManager();
            engine = manager.getEngineByName("js");

        }

        return engine;
    }

    private List<String> toList(String str) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            results.add(str.charAt(i) + "");
        }

        return results;
    }

    private String toEquation(String number, String operator, String bracket) {
        String[] numbers = StringUtils.split(number, ":");
        String[] opers = StringUtils.split(operator, ":");

        return bracket
                .replace("A", numbers[0])
                .replace("B", numbers[1])
                .replace("C", numbers[2])
                .replace("D", numbers[3])
                .replace("x", opers[0])
                .replace("y", opers[1])
                .replace("z", opers[2]);
    }

    private Integer execute(String equation) {
        try {
            Object result = getEngine().eval(equation);
            Float valFloat = Float.valueOf(result.toString());
            Integer valInt = valFloat.intValue();
            if(valFloat - valInt == 0){
                return valInt;
            }
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Set<String> get() {
        List<String> uniqueNumbers = Probability
                .ofElements(toList(number))
                .distinct()
                //.unique()
                .find();

        List<String> opers = Probability
                .ofElements(getOperators())
                .size(number.length() - 1)
                .find();

        List<String> brackets = Arrays.asList(
                "((AxB)yC)zD",
                "(AxB)y(CzD)",
                "(Ax(ByC))zD",
                "Ax((ByC)zD)",
                "Ax(By(CzD))"
        );

        Set<String> results = new HashSet<>();
        for (String numb : uniqueNumbers) {
            for (String operator : opers) {
                //System.out.println("operator => " + operator);
                for (String bracket : brackets) {
                    String equation = toEquation(numb, operator, bracket);
                    Integer result = execute(equation);
                    if (Objects.equals(result, answer)) {
                        results.add(equation);
                    }
                }
            }
        }

        return results;
    }
}
