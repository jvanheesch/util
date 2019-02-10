package com.github.jvanheesch.servlet;

import com.github.jvanheesch.Executable;
import com.github.jvanheesch.io.InterceptorServletOutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

// TODO_JORIS: property log response (headers, ...)
// currently not as important as request logging
public class ResponseInfoFilter extends AbstractHttpFilter {
    private final OutputStream out;

    public ResponseInfoFilter() {
        this(System.out);
    }

    public ResponseInfoFilter(OutputStream out) {
        this.out = out;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        println("ResponseInfoFilter - start. ");

        ServletOutputStream servletOutputStream = new InterceptorServletOutputStream(response.getOutputStream(), out, Executable.noop());
        PrintWriter printWriter = new PrintWriter(servletOutputStream);

        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(response) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return servletOutputStream;
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return printWriter;
            }
        };

        chain.doFilter(
                request,
                wrappedResponse
        );

        if (request.isAsyncStarted()) {
            request.getAsyncContext().addListener(new OnCompleteAsyncListener(unusedEvent -> printWriter.close()));
        } else {
            printWriter.close();
        }
    }

    private void println(String s) throws IOException {
        this.out.write(s.getBytes());
        this.out.write("\n".getBytes());
    }
}
