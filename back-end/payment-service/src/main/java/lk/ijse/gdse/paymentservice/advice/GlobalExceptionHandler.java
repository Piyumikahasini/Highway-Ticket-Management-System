package lk.ijse.gdse.paymentservice.advice;

import lk.ijse.gdse.paymentservice.service.exception.DuplicateRecordException;
import lk.ijse.gdse.paymentservice.service.exception.NotFoundException;
import lk.ijse.gdse.paymentservice.service.exception.ServiceException;
import lk.ijse.gdse.paymentservice.service.exception.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String,Object>> handleServiceException(ServiceException exp){
        Map<String,Object> commonErrorAttribute;
        if (exp instanceof DuplicateRecordException){
            commonErrorAttribute = getCommonErrorAttribute(HttpStatus.CONFLICT);
        }
        else if (exp instanceof NotFoundException){
            commonErrorAttribute = getCommonErrorAttribute(HttpStatus.NOT_FOUND);
        }
        else if (exp instanceof ServiceUnavailableException){
            commonErrorAttribute = getCommonErrorAttribute(HttpStatus.SERVICE_UNAVAILABLE);
        }
        else {
            commonErrorAttribute=getCommonErrorAttribute(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        commonErrorAttribute.put("message",exp.getMessage());
        return new ResponseEntity<>(commonErrorAttribute,HttpStatus.valueOf((Integer) commonErrorAttribute.get("code")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleDataValidationException(MethodArgumentNotValidException exp){
        Map<String, Object> errorAttribute = getCommonErrorAttribute(HttpStatus.BAD_REQUEST);
        List<Map<String,Object>> errorList = new ArrayList<>();

        for (FieldError fieldError : exp.getFieldErrors()) {
            LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>();
            errorMap.put("field", fieldError.getField());
            errorMap.put("message", fieldError.getDefaultMessage());
            errorMap.put("rejected", fieldError.getRejectedValue());

            errorList.add(errorMap);
        }
        errorAttribute.put("message", "Data validation failed");
        errorAttribute.put("errors", errorList);

        return errorAttribute;
    }

    private Map<String, Object> getCommonErrorAttribute(HttpStatus status) {
        LinkedHashMap<String, Object> errAttribute = new LinkedHashMap<>();
        errAttribute.put("code",status.value());
        errAttribute.put("status",status);
        return errAttribute;
    }
}
