## 개요
* Spring Boot Interceptor Sample

## 빌드
* Spring Boot 1.5.14 (Spring 4.3.18)
* Maven
* lombok

## Comments
* @RestController or @ResponseBody 가 있는 경우 interceptor에서 response header 추가가 안 된다.<br/>
  대신 ResponseBodyAdvice를 구현한 @ControllerAdvice에서 추가해줄 수 있다.<br/>
  참고(https://stackoverflow.com/questions/48823794/spring-interceptor-doesnt-add-header-to-restcontroller-services)

