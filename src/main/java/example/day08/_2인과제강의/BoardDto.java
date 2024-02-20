package example.day08._2인과제강의;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class BoardDto {
    private int bno;
    private String bcontent;
    private String bwriter;
    private String bpassword;
}
