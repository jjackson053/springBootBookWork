package quickPollApp.handler;


import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import quickPollApp.error.ErrorDetail;
import quickPollApp.error.ValidationError;
import quickPollApp.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Inject
    private MessageSource messageSource;


    @Override
    protected ResponseEntity<Object>handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDevelopMessage(ex.getClass().getName());
        return handleExceptionInternal(ex,errorDetail,headers,status,request);
    }


    @Override
    public  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers , HttpStatus status, WebRequest request){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(manve.getMessage());
        errorDetail.setDevelopMessage(manve.getClass().getName());
        return handleExceptionInternal(manve,errorDetail,headers,status,request);

    }

//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody  ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
//        ErrorDetail errorDetail = new ErrorDetail();
//        //Populate errorDetail instance
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if (requestPath == null){
//            requestPath = request.getRequestURI();
//        }
//        errorDetail.setTitle("Validation Failed");
//        errorDetail.setDetail("Input validation failed");
//        errorDetail.setDevelopMessage(manve.getClass().getName());
//
//        //Create VaildationError instances
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe : fieldErrors){
//            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
//            if ((validationErrorList == null)) {
//             validationErrorList = new ArrayList<ValidationError>();
//             errorDetail.getErrors().put(fe.getField(), validationErrorList);
//            }
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(messageSource.getMessage(fe,null));
//            validationErrorList.add(validationError);
//            }
//        return errorDetail;
//        }
    }

