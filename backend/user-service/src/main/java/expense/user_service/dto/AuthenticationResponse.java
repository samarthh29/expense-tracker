package expense.user_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String userId;
    private String message; // Message providing context about the response
    private String status; // Status of the authentication process (e.g., "success", "error")
    private LocalDateTime timestamp; // Timestamp of the response

    public AuthenticationResponse(String token, String userId ,String message) {
        this.token = token;
        this.userId = userId;
        this.message = message;
        this.status = token != null ? "success" : "error"; // Set status based on token presence
        this.timestamp = LocalDateTime.now(); // Set the current time
    }
}
