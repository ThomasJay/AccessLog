# AccessLog

This repository contains the source for the the Spring Boot Access Log sample.

![SpringBoot Logo](https://blogs.ashrithgn.com/content/images/2019/11/th.jpg)

## Introduction

This project consist of a Filter that can be installed into any SpringBoot project to allow logging for API access calls. This is similar to the Access Logs of Apache Tomcat.

## Description

This is a standard Spring Boot project based on 2.7.10 containing the spring-boot-starter-web and lombok dependencies in the Maven pom.xml file.

the lombok package is simply used to setup the @Sl4j annotation what is used in the filter for log output.

In general, a Filter is created extending the OncePerRequestFilter as an @Component and setting the @Order to Ordered.HIGHEST_PRECEDENCE indicating that this filter should always be ran first before any other filters and only once for any given request processing so if there are multiple filters in the chain this should be called the first and only once.

In the doFilterInternal method of the Filter, the request.getRequestURI() method is called and check to contain a lower case string of "/api" which is the URL path that the example API's can be found at. This is fairly common in Spring Boot Microservices and REST API code.

## Sample Log Output

![Sonny and Mariel high fiving.](https://content.codecademy.com/courses/learn-cpp/community-challenge/highfive.gif)

![Log Output](https://raw.githubusercontent.com/ThomasJay/AccessLog/main/images/log_sample.png)

## Filter Code

```java
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessLogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

     if (request.getRequestURI().toLowerCase().contains("/api")) {
         long time = System.currentTimeMillis();
         try {
             filterChain.doFilter(request, response);
         }
         finally {
             time = System.currentTimeMillis() - time;

             String remoteIpAddress = request.getHeader("X-FORWARDED-FOR");

             if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
                 remoteIpAddress = request.getRemoteAddr();
             }

             log.info("{} {} {} {} {} {}ms", remoteIpAddress, request.getMethod(), request.getRequestURI(), response.getContentType(),
                     response.getStatus(), time);

         }
     }
     else {
         filterChain.doFilter(request, response);
     }

    }
}

```

## Dependencies

This project is built with Java 1.8 and Spring Boot 2.7.10

Maven Dependencies as follows:

```
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

```

## How do I get set up?

- Clone the repo
- Java 8 is required for build and deployment runtime
- Build

```
mvn clean compile package -DskipTests
```

- Deploy file in target/AccessLog-0.0.1-SNAPSHOT.jar
- java -jer AccessLog-0.0.1-SNAPSHOT.jar

## Usage

```
curl http://localhost:8080/api/v1/test1
curl http://localhost:8080/api/v1/test2

```

---

### Learn More about Tom here

[YouTube](https://www.youtube.com/@fastandsimpledevelopment "Fast and Simple development")

[Udemy classes](https://github.com/ThomasJay "Udemy")

[Toms Meduim Blog](https://medium.com/@thomasjay200 "Medium - Tom Jay")

[Toms LinkedIn](https://www.linkedin.com/in/thomas-d-jay "LinkedIn - Thomas Jay")

[Toms Company Site](https://www.thomasjayconsulting.com "Thomas Jay Consulting")
