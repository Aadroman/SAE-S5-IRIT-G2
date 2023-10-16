package fr.sae.algebraictree;

import fr.irit.algebraictree.Predicate;

public class EPredicate {

    public EDotNotation attribute;

    public String operator;

    public String value;

    private Predicate correspondingPredicate;

    /**
     * @param attribute
     * @param operator
     * @param value
     */
    public EPredicate(EDotNotation attribute, String operator, String value) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    /**
     * @param predicate
     */
    public EPredicate(Predicate predicate) {
        this.correspondingPredicate = predicate;
    }

    /**
     * @return
     */
    public Predicate getCorrespondingPredicate(){
        return correspondingPredicate;
    }

    /**
     * @return
     */
    public String toString(){
        return attribute + " " + operator + " " + value;
    }
}
