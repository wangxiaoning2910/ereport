package com.ytincl.ereport.util.exception;

import java.io.IOException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ytincl.ereport.constant.ErrorConstants;





/**
 * �쳣��ǿ����JSON����ʽ���ظ��ͷ���
 * �쳣��ǿ���ͣ�NullPointerException,RunTimeException,ClassCastException,
���������������� NoSuchMethodException,IOException,IndexOutOfBoundsException
���������������� �Լ�springmvc�Զ����쳣�ȣ����£�
SpringMVC�Զ����쳣��Ӧ��status code  
           Exception                       HTTP Status Code  
ConversionNotSupportedException         500 (Internal Server Error)
HttpMessageNotWritableException         500 (Internal Server Error)
HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
NoSuchRequestHandlingMethodException    404 (Not Found) 
TypeMismatchException                   400 (Bad Request)
HttpMessageNotReadableException         400 (Bad Request)
MissingServletRequestParameterException 400 (Bad Request)
 *
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	//����ʱ�쳣   
    @ExceptionHandler(RuntimeException.class)  
    @ResponseBody  
    public String runtimeExceptionHandler(RuntimeException runtimeExceptio) {  
        return ReturnFormat.retParam(ErrorConstants.ERR_000001);
    }  
    //��ָ���쳣
    @ExceptionHandler(NullPointerException.class)  
    @ResponseBody  
    public String nullPointerExceptionHandler(NullPointerException ex) {  
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000002);
    }   
    //����ת���쳣
    @ExceptionHandler(ClassCastException.class)  
    @ResponseBody  
    public String classCastExceptionHandler(ClassCastException ex) {  
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000003);  
    }  

    //IO�쳣
    @ExceptionHandler(IOException.class)  
    @ResponseBody  
    public String iOExceptionHandler(IOException ex) {  
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000004); 
    }  
    //δ֪�����쳣
    @ExceptionHandler(NoSuchMethodException.class)  
    @ResponseBody  
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {  
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000005);
    }  

    //����Խ���쳣
    @ExceptionHandler(IndexOutOfBoundsException.class)  
    @ResponseBody  
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {  
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000006);
    }
    //400����
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public String requestNotReadable(HttpMessageNotReadableException ex){
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000007);
    }
    //400����
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public String requestTypeMismatch(TypeMismatchException ex){
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000007);
    }
    //400����
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public String requestMissingServletRequest(MissingServletRequestParameterException ex){
        ex.printStackTrace();
        return ReturnFormat.retParam(ErrorConstants.ERR_000007);
    }
    //405����
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public String request405(){
        return ReturnFormat.retParam(ErrorConstants.ERR_000009);
    }
    //406����
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public String request406(){
        return ReturnFormat.retParam(ErrorConstants.ERR_000010);
    }
    //500����
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    public String server500(RuntimeException runtimeException){
        return ReturnFormat.retParam(ErrorConstants.ERR_000011);
    }

}
