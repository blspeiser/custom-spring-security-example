package net.cambium.examples.rest.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * CustomAuthorizationFilter.
 *
 *  Introduce our own custom security mechanism to determine access. 
 *  We use a simple checksum verification as an oversimplified example. 
 *
 * @author Baruch Speiser, Cambium.
 *
 */
public class CustomAuthorizationFilter implements Filter {
  private static final String AUTHORIZATION_TYPE_PREFIX = "X-Custom-Checksum ";
  private static final Logger log = LoggerFactory.getLogger(CustomAuthorizationFilter.class);
  
  /** Perform basic HTTP request filter handling. */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
  throws IOException, ServletException 
  {
    if(request instanceof HttpServletRequest) {
      //We only care about HTTP requests, there isn't anything else here anyway
      HttpServletRequest req = (HttpServletRequest)request;
      if(!isAuthenticated(req)) {
        //Not allowed in, reject the request:
        HttpServletResponse res = (HttpServletResponse)response;
        res.sendError(HttpStatus.UNAUTHORIZED.value());
        return;
      }
      //otherwise, fall through and continue handling the request
    } 
    chain.doFilter(request, response);
  }

  /** Check to see if the request includes our custom authorization header. */
  private boolean isAuthenticated(HttpServletRequest request) {
    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      //our custom authorization mechanism is here:
       if(null != authorization && authorization.startsWith(AUTHORIZATION_TYPE_PREFIX)) {
        String numerical = authorization.substring(AUTHORIZATION_TYPE_PREFIX.length());
        long number = Long.parseLong(numerical);
        return isValidChecksum(number); 
      }
      //otherwise:
      return false;
    } catch(Exception e) {
      log.warn("Failure while parsing authorization header, could have been sent in an incorrect format", e);
      return false;
    }
  }

  /** Validate checksum, i.e. verify the supplied "credentials" to see if they are valid */
  private boolean isValidChecksum(long number) {
    //Extract the last digit:
    long last = number % 10;
    //Add up all the other digits:
    long sum = 0;
    for(long num = (number /= 10); num > 0; num /= 10) {
      sum += num % 10;
    }
    //Now check if the last digit of the sum matches the last digit of the original number
    return (sum % 10 == last); 
  }
  
}
