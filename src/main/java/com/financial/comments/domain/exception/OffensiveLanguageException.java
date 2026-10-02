package com.financial.comments.domain.exception;

public class OffensiveLanguageException extends RuntimeException {
    
    private final String offensiveWord;
    private final String commentContent;
    
    public OffensiveLanguageException(String message) {
        super(message);
        this.offensiveWord = null;
        this.commentContent = null;
    }
    
    public OffensiveLanguageException(String message, String offensiveWord, String commentContent) {
        super(message);
        this.offensiveWord = offensiveWord;
        this.commentContent = commentContent;
    }
    
    public OffensiveLanguageException(String message, Throwable cause) {
        super(message, cause);
        this.offensiveWord = null;
        this.commentContent = null;
    }
    
    public OffensiveLanguageException(String message, String offensiveWord, String commentContent, Throwable cause) {
        super(message, cause);
        this.offensiveWord = offensiveWord;
        this.commentContent = commentContent;
    }
    
    public String getOffensiveWord() {
        return offensiveWord;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public static OffensiveLanguageException withWord(String word, String content) {
        return new OffensiveLanguageException(
            "El comentario contiene lenguaje ofensivo: " + word,
            word,
            content
        );
    }
    
    public static OffensiveLanguageException withMultipleWords(String[] words, String content) {
        String wordList = String.join(", ", words);
        return new OffensiveLanguageException(
            "El comentario contiene lenguaje ofensivo: " + wordList,
            wordList,
            content
        );
    }
}