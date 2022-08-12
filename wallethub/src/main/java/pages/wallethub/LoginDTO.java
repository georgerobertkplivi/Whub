package pages.wallethub;

import lombok.Data;

@Data
public class LoginDTO {
    public String email;
    public String password;
    public String reviewText;
    public String partEmail;
    public int expectedStars;
}
