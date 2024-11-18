package com.crio.starter.data;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "meme")
public class Meme {


    @Id
    public String id;

    public String name;
    
    @JsonProperty("url")
    public String imageUrl;

    public String caption;

}
