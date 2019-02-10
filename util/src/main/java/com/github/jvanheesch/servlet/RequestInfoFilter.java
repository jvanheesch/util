package com.github.jvanheesch.servlet;

import com.github.jvanheesch.Executable;
import com.github.jvanheesch.io.InterceptorServletInputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;

public class RequestInfoFilter extends AbstractHttpFilter {
    private final OutputStream out;

    public RequestInfoFilter() {
        this(System.out);
    }

    public RequestInfoFilter(OutputStream out) {
        this.out = out;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        println("RequestInfoFilter - start. ");
        println(String.format("RequestURI: %s ", request.getRequestURI()));
        println(String.format("Method: %s ", request.getMethod()));
        println(String.format("DispatcherType: %s ", request.getDispatcherType()));
        println(String.format("Protocol: %s ", request.getProtocol()));
        println(String.format("LocalAddr: %s ", request.getLocalAddr()));
        println(String.format("LocalName: %s ", request.getLocalName()));
        println(String.format("LocalPort: %s ", request.getLocalPort()));
        println(String.format("RemoteAddr: %s ", request.getRemoteAddr()));
        println(String.format("RemoteHost: %s ", request.getRemoteHost()));
        println(String.format("RemotePort: %s ", request.getRemotePort()));
        println(String.format("RemoteUser: %s ", request.getRemoteUser()));
        println(String.format("Scheme: %s ", request.getScheme()));
        println(String.format("Secure: %s ", request.isSecure()));
        println(String.format("Cookies: %s ", Arrays.toString(request.getCookies())));
        println("Headers: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            // https://webaim.org/blog/user-agent-string-history/
            String headerName = headerNames.nextElement();
            println(String.format("  %s: %s ", headerName, request.getHeader(headerName)));
        }
        println("Attributes: ");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            println(String.format("  %s: %s ", attributeName, request.getAttribute(attributeName)));
        }
        println("Parameters    : ");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            println(String.format("  %s: %s ", paramName, request.getParameter(paramName)));
        }

        println("RequestInfoFilter - end. ");

        ServletInputStream servletInputStream = new InterceptorServletInputStream(request.getInputStream(), out, Executable.noop());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletInputStream));

        HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return servletInputStream;
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return bufferedReader;
            }
        };

        chain.doFilter(
                wrappedRequest,
                response
        );

        if (wrappedRequest.isAsyncStarted()) {
            wrappedRequest.getAsyncContext().addListener(new OnCompleteAsyncListener(unusedEvent -> {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } else {
            bufferedReader.close();
        }
    }

    private void println(String s) throws IOException {
        this.out.write(s.getBytes());
        this.out.write("\n".getBytes());
    }
}
