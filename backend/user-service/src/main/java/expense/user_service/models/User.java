package expense.user_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_table") // Specifies the MongoDB collection
public class User {
    @Id
    private String userId; // Use String for MongoDB's ObjectId or custom ID
    private String userName;
    private String email;
    private String password;
}
