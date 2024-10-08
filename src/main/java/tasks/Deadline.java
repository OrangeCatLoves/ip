
package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline represents a specific subset of Task that comes with an additional deadline
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Deadline represents a specific subset of Task that comes with an additional deadline
     * @param description the description of the task
     * @param by the deadline of the task
     * @param priority the priority of the task
     */
    public Deadline(String description, LocalDate by, int priority) {
        super(description, priority);
        this.by = by;
        Task.incrementTaskCount();
    }
    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by + " | " + super.getPriorityNum();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormatter) + ")";
    }

    /**
     * Returns the deadline date of the task
     * @return the deadline date of the task
     */
    public LocalDate getDeadlineDate() {
        return this.by;
    }
}
