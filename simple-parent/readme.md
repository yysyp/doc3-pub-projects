##Markdown: <https://www.runoob.com/markdown/md-tutorial.html>
#Project structure:

**Springdoc Swagger**
<http://localhost:8080/springdoc/docs.html>

**Service:**
    Service receives a DTO object, it should try to make sense out of it
by querying for the corresponding model object from database and then do 
the necessary operation and create a response DTO to be sent back to the
calling service.
i.e.:
`@Override
public UserDto updateProfile(UserDto userDto) {
    Optional<User> user = Optional.ofNullable(userDao.findByEmail(userDto.getEmail()));
    if (user.isPresent()) {
        UserModel userModel = user.get();
        userModel.setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName());
        return UserConverter.toUserDto(userDao.save(userModel));
    }
    throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
}`

**Http Status**
<https://datatracker.ietf.org/doc/html/rfc7231>
Client Error 4xx (Consumer can fix it)
<pre>
400 Bad Request
401 Unauthorized
402 Payment Required
403 Forbidden
404 Not Found
405 Method Not Allowed
406 Not Acceptable
408 Request Timeout
409 Conflict
410 Gone
411 Length Required
413 Payload Too Large
414 URI Too Long
415 Unsupported Media Type
417 Expectation Failed
426 Upgrade Required

Server Error 5xx (Consumer can't fix it)
500 Internal Server Error
501 Not Implemented
502 Bad Gateway
503 Service Unavailable
504 Gateway Timeout
505 HTTP Version Not Supported
</pre>

Common http status code practice debates:
401 "Unauthorized" OR 403 "Forbidden"
401 is for users who are not authenticated.
403 is for those who are authenticated but not allowed to perform the attempted operation.

400 "Bad Request" OR 404 "Not Found"
If a query say, /products/{product_id} is performed, API should check the product_id
to see whether is malformed, if it's malformed then return 400 Bad Request, otherwise 
if the product_id resource doesn't exist then return 404 Not Found.


