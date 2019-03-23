package org.ceg.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    private String id ;
    private String name ;
    private double price ;
    @DBRef
    private Category category ;

}
