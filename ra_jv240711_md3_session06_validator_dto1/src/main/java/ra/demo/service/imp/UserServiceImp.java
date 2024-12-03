package ra.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.mapper.LoginMapper;
import ra.demo.mapper.RegisterMapper;
import ra.demo.model.User;
import ra.demo.repository.UserRepository;
import ra.demo.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public LoginDtoResponse findUser(LoginDtoRequest loginDtoRequest) {
        //1. request --> entity
        User user = loginMapper.mapperRequestToEntity(loginDtoRequest);
        //2. Goi repository --> entity
        User userLogin = userRepository.findUser(user);
        //3. entity --> response
        return loginMapper.mapperEntityToResponse(userLogin);
    }

    @Override
    public boolean create(RegisterDtoRequest registerDtoRequest) {
        //1. request --> entity
        User user = registerMapper.mapperRequestToEntity(registerDtoRequest);
        //2. goi repository --> boolean
        return userRepository.create(user);
    }
}
