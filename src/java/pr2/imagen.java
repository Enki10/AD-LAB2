/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2;

/**
 *
 * @author marcu
 */
public class imagen {
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String creationDate;
    private String storageDate;
    private String filename;
            
    public imagen(){
        
    }
    public imagen(
    String title,
    String description,
    String keywords,
    String author,
    String creationDate,
    String storageDate,
    String filename
    ){
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.author = author;
        this.creationDate = creationDate;
        this.storageDate = storageDate;
        this.filename = filename;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
