package example.day08._2인과제.model.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoDTO {
    private int no;
    private String content;
    private boolean state;

    public TodoDTO(String content){
        this.content = content;
    }

    public TodoDTO(int no , String content){
        this.no = no;
        this.content = content;
    }
}
