package com.mastery.java.task.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ErrorDto {
    private Date date;

    private HttpStatus httpStatus;

    private List<String> error;

    public ErrorDto() {
    }

    public ErrorDto(
            Date date,
            HttpStatus httpStatus,
            List<String> error
    ) {
        this.date = date;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto that = (ErrorDto) o;
        return Objects.equals(date, that.date) && httpStatus == that.httpStatus && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, httpStatus, error);
    }

    @Override
    public String toString() {
        return "EntityForExceptionHandler{" +
                "date=" + date +
                ", httpStatus=" + httpStatus +
                ", error=" + error +
                '}';
    }
}
