package ch.boblitz.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private int id;

    @Version
    @Column(nullable = false)
    private long version;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        final BaseEntity that = (BaseEntity) o;
        return Objects.equals(this.id, that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.id);
    }

}
