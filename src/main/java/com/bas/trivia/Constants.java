package com.bas.trivia;

/**
 * Maybe spring has a standard for it.
 * 
 * A file of constants would only hold variables, therefor a enum would be
 * more applicable then the use of a class. No private constructors and much cleaner
 * @author Bas
 */
public enum Constants {;
    /**
     * The URL for fetching questions 
     */
    public static final String OPENTDB_URI = "https://opentdb.com/api.php?amount={amount}&type=multiple";
    
    /**
     * Maybe too much, but it can always be variable
     */
    public static final int QUESTION_AMOUNT = 5;
}
