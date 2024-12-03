package ra.demo.mapper;

import org.springframework.stereotype.Component;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.model.User;

import java.time.LocalDate;

@Component
public class RegisterMapper<P> implements GenericMapper<RegisterDtoRequest, User, P> {
    @Override
    public User mapperRequestToEntity(RegisterDtoRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setSex(request.isSex());
        user.setAddress(request.getAddress());
        user.setBod(request.getBod());
        return user;
    }

    @Override
    public P mapperEntityToResponse(User entity) {
        return null;
    }
}
