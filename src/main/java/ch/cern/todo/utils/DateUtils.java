package ch.cern.todo.utils;

import ch.cern.todo.exceptions.InvalidDeadlineException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDateTime validateDeadline(String deadline) {

        if (deadline == null) {
            throw new InvalidDeadlineException("Deadline cannot be null.");
        }
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

            if (parsedDeadline.isBefore(LocalDateTime.now())) {
                throw new InvalidDeadlineException("Deadline cannot be in the past.");
            }
            return parsedDeadline;
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("Invalid deadline format. Please provide the deadline in the format 'yyyy-MM-dd'T'HH:mm:ss'.");
        }

    }
}