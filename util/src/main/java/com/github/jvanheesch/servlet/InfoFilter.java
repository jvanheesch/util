package com.github.jvanheesch.servlet;

import com.github.jvanheesch.io.IOStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

public class InfoFilter extends HttpFilter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("InfoFilter - start. ");
        LOGGER.info("RequestURI: {} ", request.getRequestURI());
        LOGGER.info("Method: {} ", request.getMethod());
        LOGGER.info("ContextPath: {} ", request.getContextPath());
        LOGGER.info("ServletPath: {} ", request.getServletPath());
        LOGGER.info("PathInfo: {} ", request.getPathInfo());
        LOGGER.info("ContentType: {} ", request.getContentType());
        LOGGER.info("CharacterEncoding: {} ", request.getCharacterEncoding());
        LOGGER.info("ContentLengthLong: {} ", request.getContentLengthLong());
        LOGGER.info("DispatcherType: {} ", request.getDispatcherType());
        LOGGER.info("Protocol: {} ", request.getProtocol());
        LOGGER.info("LocalAddr: {} ", request.getLocalAddr());
        LOGGER.info("LocalName: {} ", request.getLocalName());
        LOGGER.info("LocalPort: {} ", request.getLocalPort());
        LOGGER.info("RemoteAddr: {} ", request.getRemoteAddr());
        LOGGER.info("RemoteHost: {} ", request.getRemoteHost());
        LOGGER.info("RemotePort: {} ", request.getRemotePort());
        LOGGER.info("RemoteUser: {} ", request.getRemoteUser());
        LOGGER.info("Scheme: {} ", request.getScheme());
        LOGGER.info("ServerName: {} ", request.getServerName());
        LOGGER.info("ServerPort: {} ", request.getServerPort());
        LOGGER.info("Secure: {} ", request.isSecure());
        LOGGER.info("Cookies: {} ", Arrays.toString(request.getCookies()));
        LOGGER.info("Headers: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            // https://webaim.org/blog/user-agent-string-history/
            String headerName = headerNames.nextElement();
            LOGGER.info("  {}: {} ", headerName, request.getHeader(headerName));
        }
        LOGGER.info("Attributes: ");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            LOGGER.info("  {}: {} ", attributeName, request.getAttribute(attributeName));
        }
        LOGGER.info("Parameters    : ");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            LOGGER.info("  {}: {} ", paramName, request.getParameter(paramName));
        }
        ServletContext servletContext = request.getServletContext();
        LOGGER.info("ServletContext: {} ", servletContext);
        LOGGER.info("  EffectiveMajorVersion: {} ", servletContext.getEffectiveMajorVersion());

        LOGGER.info("InfoFilter - end. ");

        // TODO_JORIS: close will break when async, but kept simple for now.
        try (
                ServletInputStream servletInputStream = IOStreams.logInput(request.getInputStream(), LOGGER);
                ServletOutputStream servletOutputStream = IOStreams.logOutput(response.getOutputStream(), LOGGER);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletInputStream));
                PrintWriter printWriter = new PrintWriter(servletOutputStream)
        ) {

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
            HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(response) {
                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return servletOutputStream;
                }

                @Override
                public PrintWriter getWriter() throws IOException {
                    return printWriter;
                }

                @Override
                public void flushBuffer() throws IOException {
                    response.flushBuffer();
                    servletOutputStream.flush();
                }
            };

            chain.doFilter(
                    wrappedRequest,
                    wrappedResponse
            );
        }
    }
}
