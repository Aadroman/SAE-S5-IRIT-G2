package fr.sae.algebraictree;

import fr.irit.algebraictree.Predicate;

public class EPredicate {
    public EDotNotation attribute;
    public String operator;
    public String value;
    private Predicate correspondingPredicate;
    public EPredicate(EDotNotation attribute, String operator, String value) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    public EPredicate(Predicate p) {
        this.correspondingPredicate = p;

    }

    public String toString(){
        return attribute + " " + operator + " " + value;
    }
}
