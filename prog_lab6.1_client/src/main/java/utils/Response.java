package utils;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -7879056808897113742L;
    // 0 - плохо, 1 - хорошо
    private int code;
    private String requestText;

    public Response(int code, String requestText) {
        this.code = code;
        this.requestText = requestText;
    }

    public void printResponse(){
        System.out.println("Статус ответа: " + String.valueOf(code));
        System.out.println("Ответ сервера: " + requestText);
    }
}
