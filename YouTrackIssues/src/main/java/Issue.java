import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.StringJoiner;

public class Issue {

    private final String summary;
    private final String description;

    Issue(final String summary, final String description) {
        this.summary = summary;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(summary)
                .append(description)
                .build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Issue) {
            final Issue other = (Issue) obj;
            return new EqualsBuilder()
                    .append(summary, other.summary)
                    .append(description, other.description)
                    .build();
        }
        return false;
    }
}
