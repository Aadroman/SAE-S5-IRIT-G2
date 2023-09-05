package fr.irit.algebraictree;

public class Predicate {
    public DotNotation attribute;
    public String operator;
    public String value;
    public Predicate(DotNotation attribute, String operator, String value) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }
    public String toString(){
        return attribute + " " + operator + " " + value;
    }
}
