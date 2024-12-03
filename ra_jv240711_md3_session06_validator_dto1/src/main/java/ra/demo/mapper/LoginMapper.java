package ra.demo.mapper;

import org.springframework.stereotype.Component;
import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.model.User;

@Component
public class LoginMapper implements GenericMapper<LoginDtoRequest, User, LoginDtoResponse> {
    @Override
    public User mapperRequestToEntity(LoginDtoRequest request) {
//        User user = new User();
//        user.setUserName(request.getUserName());
//        user.setPassword(request.getPassword());
//        return user;
        return User.builder().userName(request.getUserName())
                .password(request.getPassword()).build();
    }

    @Override
    public LoginDtoResponse mapperEntityToResponse(User entity) {
        if (entity!=null) {
            return new LoginDtoResponse(entity.getUserId(), entity.getUserName(), entity.getFullName());
        }
        return null;
    }
}
