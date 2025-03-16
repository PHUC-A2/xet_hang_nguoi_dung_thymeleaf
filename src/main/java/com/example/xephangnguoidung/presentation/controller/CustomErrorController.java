package com.example.xephangnguoidung.presentation.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("statusCode", statusCode);

            HttpStatus httpStatus = HttpStatus.resolve(statusCode);
            model.addAttribute("errorMessage",
                    httpStatus != null ? httpStatus.getReasonPhrase() : "Lỗi không xác định");
        }

        if (exception instanceof Exception) {
            StringWriter sw = new StringWriter();
            ((Exception) exception).printStackTrace(new PrintWriter(sw));
            model.addAttribute("stackTrace", sw.toString());
        } else {
            model.addAttribute("stackTrace", "Không có thông tin lỗi chi tiết.");
        }

        return "error"; // Trả về file error.html
    }
}
