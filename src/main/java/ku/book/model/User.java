package ku.book.model;

import ku.book.config.AttributeEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition="VARBINARY(256)")
    @ColumnTransformer(
            read = "cast(AES_DECRYPT(username, 'key') as char(255))",
            write = "AES_ENCRYPT(?, 'key')"
    )
    private String username;
    private String password;

    @Column(columnDefinition="VARBINARY(256)")
    @ColumnTransformer(
            read = "cast(AES_DECRYPT(name, 'key') as char(255))",
            write = "AES_ENCRYPT(?, 'key')"
    )
    private String name;

    private Instant createdAt;
}
