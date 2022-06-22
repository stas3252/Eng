package project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    private String englishWord;
    private String russianWord;
    private int popularity;
}
